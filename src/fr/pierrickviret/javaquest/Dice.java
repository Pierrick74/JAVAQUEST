package fr.pierrickviret.javaquest;

import java.util.Random;
/**
 *<h2> class Dice</h2>
 * <p> Class représentant le dé pour le jeu </p>
 * @author Pierrick
 * @see Game
 * @version 1.0
 */
public class Dice {

    /**
     * Permet de récupérer la valeur du dé après un lancé.
     * @return {@code int} une valeur entre 1 et 6.
     */
    public Integer getRoll(){
        Random rand = new Random();
        return rand.nextInt(1, 7);
    }
}
