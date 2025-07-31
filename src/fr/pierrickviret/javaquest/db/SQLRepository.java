package fr.pierrickviret.javaquest.db;
import fr.pierrickviret.javaquest.Menu;
import fr.pierrickviret.javaquest.character.Character;
import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.commun.CharacterType;
import fr.pierrickviret.javaquest.commun.DefensiveEquipmentType;
import fr.pierrickviret.javaquest.commun.OffensiveEquipmentType;
import fr.pierrickviret.javaquest.equipement.OffensiveEquipement;

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
     * permet de recupérer le singleton
     * @return une instance de ConnectMySQL
     */
    public static SQLRepository getInstance() {
        return instance==null?instance = new SQLRepository():instance;
    }
    /**
     * Permet d'afficher les personnages en BD
     */
    public void getHeroes()
    {
        try
        {
            stmt = getStatement();
            res = stmt.executeQuery("SELECT * FROM JavaquestCharacter");

            menu.showInformation("voici les Heros du jeu");
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
     * @param type {@code CharactereType} le type du personnage
     * @param LifePoints {@code Integer} les points de vie
     * @param Strength {@code Integer} la force
     * @param OffensiveEquipement {@code OffensiveEquipment} les armes offensives, indiquer empty si null
     * @param DefensiveEquipment {@code DefensiveEquipment} les armes deffensives, indiquer empty si null
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

            getHeroes();
            closeConnection();
            return id;
        } catch(SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
            e.printStackTrace();
        } catch(Exception e) {
            System.out.println("Erreur générale : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void editHero(MainCharacter character) {
        try {
            conn = getConnection();
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(
                    "UPDATE JavaquestCharacter SET Name=?, Type=?, LifePoints=?, Strength=?, OffensiveEquipment=?, DefensiveEquipment=? WHERE ID=?");

            String characterOffensiveEquipment = character.getOffensiveEquipement() != null ? character.getOffensiveEquipement().getName() : OffensiveEquipmentType.empty.toString();
            String characterDefensiveEquipment = character.getDefensiveEquipement() != null ? character.getDefensiveEquipement().getName() : DefensiveEquipmentType.empty.toString();

            pstmt.setString(1, character.getName());
            pstmt.setString(2, character.getType().toString());
            pstmt.setInt(3, character.getHealth());
            pstmt.setInt(4, character.getAttack());
            pstmt.setString(5, characterOffensiveEquipment);
            pstmt.setString(6, characterDefensiveEquipment);
            pstmt.setInt(7, character.getID());
            int numUpd = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }

    private Statement getStatement(){
        try
        {
            conn = getConnection();
            return conn.createStatement();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
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
            conn.close();
            stmt.close();
            res.close();
        }
        catch(Exception e){
        }
    }
}