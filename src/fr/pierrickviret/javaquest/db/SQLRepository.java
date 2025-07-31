package fr.pierrickviret.javaquest.db;
import fr.pierrickviret.javaquest.Menu;
import fr.pierrickviret.javaquest.Player;
import fr.pierrickviret.javaquest.character.Character;
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

    public void createHeroes(String name, CharacterType type, Integer LifePoints, Integer Strength, OffensiveEquipmentType OffensiveEquipement, DefensiveEquipmentType DefensiveEquipment) {
        try {
            conn = getConnection();

            // Requête avec des paramètres (?)
            String query = "INSERT INTO JavaquestCharacter(Name, Type, LifePoints, Strength, OffensiveEquipment, DefensiveEquipment) VALUES(?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(query);

            // Attribution des paramètres
            pstmt.setString(1, name);
            pstmt.setString(2, type.toString());
            pstmt.setInt(3, LifePoints);
            pstmt.setInt(4, Strength);
            pstmt.setString(5, OffensiveEquipement.toString());
            pstmt.setString(6, DefensiveEquipment.toString());

            int rs = pstmt.executeUpdate();

            getHeroes();
            closeConnection();

        } catch(SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
            e.printStackTrace();
        } catch(Exception e) {
            System.out.println("Erreur générale : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void editHero(Character character) {

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