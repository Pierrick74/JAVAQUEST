package fr.pierrickviret.javaquest;

import java.util.Scanner;
/**
 *<h2> class Board</h2>
 * <p> Permet de faire la liaison entre le jeu et le terminal<\p>
 * @author Pierrick
 * @see Game
 * @see java.util.Scanner
 * @version 1.0
 */
public class Menu {
    /**
     * Initialise la liaison avec le terminal
     */
    private final Scanner scanner = new Scanner(System.in);
    private static Menu instance;

    private Menu() {
    }

    public static Menu getInstance() {
        return instance==null?instance = new Menu():instance;
    }

    /**
     * Affiche le texte donné en parametre sur le terminal
     * @param data {@code String} donnée à afficher.
     */
    public void showInformation(String data) {
        System.out.println(data);
    }

    /**
     * Permet de lire les réponses utilisateurs sous forme de INT
     * Si l'utilisateur ne tappe pas dans les limites prévus, la fonction attends une bonne réponse avant de la renvoyer
     * @param min {@code int} valeur minimale pour la réponse
     * @param max {@code int} valeur maximale pour la réponse
     * @return {@code int} retourne la reponse comprise entre les bornes
     */
    public int listenResultBetween(int min, int max) {
        int result = 0;
        while ( result == 0 ) {
            try {
                result = Integer.parseInt(scanner.nextLine());
                if ( result > max || result < min ) {
                    result = 0;
                }
            } catch ( Exception e ) {
                scanner.nextLine();
            }
        }
        return result;
    }

    /**
     * Permet de lire les réponses utilisateurs sous forme de String
     * @return Renvoie la valeur récupérée
     */
    public String listenString() {
       return scanner.nextLine();
    }
}
