package fr.pierrickviret.javaquest.db;
import com.google.gson.Gson;
import fr.pierrickviret.javaquest.Menu;
import fr.pierrickviret.javaquest.board.Board;
import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.character.Warrior;
import fr.pierrickviret.javaquest.character.Wizard;
import fr.pierrickviret.javaquest.equipement.OffensiveEquipement;

import java.sql.*;
import java.util.ArrayList;

public class SQLRepository
{
    Menu menu =  Menu.getInstance();
    Connection conn = null;
    Statement stmt = null;
    ResultSet res = null;

    private static SQLRepository instance;

    /**
     * initialisation de la class
     */
    private SQLRepository() {}

    /**
     * Permet de récupérer le singleton
     * @return une instance de ConnectMySQL
     */
    public static SQLRepository getInstance() {
        return instance==null?instance = new SQLRepository():instance;
    }
    /**
     * Permet d'afficher les personnages en BD
     */
    public void showHeroes()
    {
        try
        {

            conn = getConnection();
            stmt = conn.createStatement();

            res = stmt.executeQuery("SELECT * FROM JavaquestCharacter");

            menu.showInformation("voici les Heroes du jeu");
            while(res.next())
                menu.showInformation(res.getString("name"));

            closeConnection();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * permet de cree un nouveau personnage dans la BDD
     * @param character {@code MainCharacter} nom du personnage
     * @return {@code Integer} retourne l'ID du personnage créer
     */
    public void save(MainCharacter character) {
        try {
            conn = getConnection();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM JavaquestCharacter WHERE id = ?");
            pstmt.setInt(1, character.getID());
            pstmt.executeUpdate();

            // Requête avec des paramètres (?)
            String query = "INSERT INTO JavaquestCharacter(id, name, type, health, maxHealth, attack, experience, boostAttack, position, offensiveEquipment1, offensiveEquipment2) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // Attribution des paramètres
            pstmt.setInt(1, character.getID());
            pstmt.setString(2, character.getName());
            pstmt.setString(3, character.getType().toString());
            pstmt.setInt(4, character.getHealth());
            pstmt.setInt(5, character.getMaxHealth());
            pstmt.setInt(6, character.getAttackValue());
            pstmt.setInt(7, character.getExperience());
            pstmt.setBoolean(8, character.getBoostAttackValue());
            pstmt.setInt(9, character.getPosition());

            Gson gson = GsonConfig.getInstance();
            String offensiveEquipement = gson.toJson(character.getOffensiveEquipement(1));
            pstmt.setString(10, offensiveEquipement);
            offensiveEquipement = gson.toJson(character.getOffensiveEquipement(2));
            pstmt.setString(11, offensiveEquipement);


            pstmt.executeUpdate();
            closeConnection();

        } catch(SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        } catch(Exception e) {
            System.out.println("Erreur générale : " + e.getMessage());
        }
    }

    public MainCharacter getCharacter(int id) {
        try
        {
            conn = getConnection();
            PreparedStatement pstmt;

            pstmt = conn.prepareStatement("SELECT * FROM JavaquestCharacter WHERE ID=?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int Id =  rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                int health = rs.getInt("health");
                int maxHealth = rs.getInt("maxHealth");
                int attack = rs.getInt("attack");
                int  experience = rs.getInt("experience");
                Boolean boostAttack = rs.getBoolean("boostAttack");
                int position = rs.getInt("position");
                String offensiveEquipementString1 = rs.getString("offensiveEquipment1");
                String offensiveEquipementString2 = rs.getString("offensiveEquipment2");

                Gson gson = GsonConfig.getInstance();
                OffensiveEquipement offensiveEquipement1 = gson.fromJson(offensiveEquipementString1, OffensiveEquipement.class);
                OffensiveEquipement offensiveEquipement2 = gson.fromJson(offensiveEquipementString2, OffensiveEquipement.class);
                ArrayList<OffensiveEquipement> offensiveEquipements = new ArrayList<>();
                offensiveEquipements.add(offensiveEquipement1);
                offensiveEquipements.add(offensiveEquipement2);

                switch (type) {
                    case "Wizard":
                        Wizard wizard = new Wizard(name,id);
                        wizard.initMainCharacter(id, name, health, maxHealth, attack, experience, boostAttack, position, offensiveEquipements);
                        return wizard;

                    case "Warrior":
                        Warrior warrior = new Warrior(name,id);
                        warrior.initMainCharacter(id, name, health, maxHealth, attack, experience, boostAttack, position, offensiveEquipements);
                        return warrior;
                }
            }

            closeConnection();
            return null;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    private Connection getConnection(){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Java", "root", "motdepasse");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    private void closeConnection(){
        try
        {
            if(conn != null){conn.close();}
            if(stmt != null){stmt.close();}
            if(res != null){ res.close();}
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    // Save Board
    public Integer saveBoard(Board board) {
        try {
            Integer id = 0;
            conn = getConnection();

            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM JavaquestBoard");
            pstmt.executeUpdate();

            Gson gson = GsonConfig.getInstance();
            String boardJson = gson.toJson(board);

            pstmt = conn.prepareStatement(
                    "INSERT INTO JavaquestBoard (Data) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, boardJson);

            pstmt.executeUpdate();

            //get ID of board
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            closeConnection();
            return id;
        } catch (Exception e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
        return null;
    }

    public boolean hasBoard() {
        return getBoard() != null;
    }

    public Board getBoard() {
        try
        {
            conn = getConnection();
            PreparedStatement pstmt;

            pstmt = conn.prepareStatement("SELECT * FROM JavaquestBoard");
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Récupérer les données du ResultSet
                String boardData = rs.getString("Data"); // Remplacez par le nom de votre colonne

                closeConnection();
                Gson gson = GsonConfig.getInstance();
                return gson.fromJson(boardData, Board.class);
            }

            closeConnection();
            return null;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}