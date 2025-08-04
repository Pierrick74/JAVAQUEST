package fr.pierrickviret.javaquest.db;
import com.google.gson.Gson;
import fr.pierrickviret.javaquest.Menu;
import fr.pierrickviret.javaquest.board.Board;
import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.commun.CharacterType;
import fr.pierrickviret.javaquest.commun.DefensiveEquipmentType;
import fr.pierrickviret.javaquest.commun.OffensiveEquipmentType;

import java.sql.*;

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
     * permet de récupérer le singleton
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
     * @param name {@code String} nom du personnage
     * @param type {@code CharacterType} le type du personnage
     * @param LifePoints {@code Integer} les points de vie
     * @param Strength {@code Integer} la force
     * @param OffensiveEquipement {@code OffensiveEquipment} les armes offensives, indiquer empty si null
     * @param DefensiveEquipment {@code DefensiveEquipment} les armes defensive, indiquer empty si null
     * @return {@code Integer} retourne l'ID du personnage créer
     */
    public Integer createHeroes(String name, CharacterType type, Integer LifePoints, Integer Strength, OffensiveEquipmentType OffensiveEquipement, DefensiveEquipmentType DefensiveEquipment) {
        try {
            int id = 0;
            conn = getConnection();

            // Requête avec des paramètres (?)
            String query = "INSERT INTO JavaquestCharacter(Name, Type, LifePoints, Strength, OffensiveEquipment, DefensiveEquipment) VALUES(?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // Attribution des paramètres
            pstmt.setString(1, name);
            pstmt.setString(2, type.toString());
            pstmt.setInt(3, LifePoints);
            pstmt.setInt(4, Strength);
            pstmt.setString(5, OffensiveEquipement.toString());
            pstmt.setString(6, DefensiveEquipment.toString());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            //affiche l'ensemble des créations
            //getHeroes();
            closeConnection();
            return id;
        } catch(SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        } catch(Exception e) {
            System.out.println("Erreur générale : " + e.getMessage());
        }
        return null;
    }

    public void editHero(MainCharacter character) {
        try {
            conn = getConnection();
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(
                    "UPDATE JavaquestCharacter SET Name=?, Type=?, LifePoints=?, Strength=?, OffensiveEquipment=?, DefensiveEquipment=? WHERE ID=?");

           // String characterOffensiveEquipment = character.getOffensiveEquipements() != null ? character.getOffensiveEquipements().getName() : OffensiveEquipmentType.empty.toString();
            String characterDefensiveEquipment = character.getDefensiveEquipement() != null ? character.getDefensiveEquipement().getName() : DefensiveEquipmentType.empty.toString();

            pstmt.setString(1, character.getName());
            pstmt.setString(2, character.getType().toString());
            pstmt.setInt(3, character.getHealth());
            pstmt.setInt(4, character.getAttackValue());
            pstmt.setString(5, character.getOffensiveEquipements().toString());
            pstmt.setString(6, characterDefensiveEquipment);
            pstmt.setInt(7, character.getID());
            pstmt.executeUpdate();
            closeConnection();
        } catch (Exception e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }

    }

    public void changeLifePoints(MainCharacter character) {
        try {
            conn = getConnection();
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(
                    "UPDATE JavaquestCharacter SET LifePoints=? WHERE ID=?");

            pstmt.setInt(1, character.getHealth());
            pstmt.setInt(2, character.getID());
            pstmt.executeUpdate();
            closeConnection();
        } catch (Exception e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }

    private Connection getConnection(){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
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
            if(board.hasID()) {
                return updateBoard(board);
            } else {
                return createSaveBoard(board);
            }
        } catch (Exception e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
        return null;
    }

    private Integer updateBoard(Board board) {
        try {
            Integer id = 0;
            conn = getConnection();

            Gson gson = GsonConfig.getInstance();
            String boardJson = gson.toJson(board);

            conn = getConnection();
            PreparedStatement pstmt;

            pstmt = conn.prepareStatement(
                    "UPDATE JavaquestBoard SET Data=? WHERE ID=?", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, boardJson);
            pstmt.setInt(2, board.getId());

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

    private Integer createSaveBoard(Board board) {
        try {
            Integer id = 0;
            conn = getConnection();

            Gson gson = GsonConfig.getInstance();
            String boardJson = gson.toJson(board);

            conn = getConnection();
            PreparedStatement pstmt;

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


}