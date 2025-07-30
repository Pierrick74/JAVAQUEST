package fr.pierrickviret.javaquest;

import fr.pierrickviret.javaquest.character.MainCharacter;

/**
 *<h2> class Board</h2>
 * <p> Class représentant le plateau de jeu
 * @author Pierrick
 * @see Character
 * @see MainCharacter
 * @version 1.0
 */
public class Board {
    //variable
    /**
    * Taille maximum du plateau
     */
    private final Integer size;

    //init
    /**
    * initialise la valeur du plateau à 64
     */
    public Board() {
        this.size = 64;
    }

    /**
     * Retourne la taille du tableau
     * @return {@code integer} la taille du plateau
     */
    public Integer getSize() {
        return size;
    }
}
