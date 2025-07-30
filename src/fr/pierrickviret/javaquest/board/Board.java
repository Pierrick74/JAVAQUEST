package fr.pierrickviret.javaquest.board;

import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.character.dragon;
import fr.pierrickviret.javaquest.equipement.offensive.Sword;

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
    private Case[] cases;

    //init
    /**
    * Initialise la valeur du plateau à 64
     */
    public Board() {
        this.size = 64;
        this.cases = new Case[size+1];
        cases[2] = new dragon();
        cases[3] = new Sword();

    }

    /**
     * Retourne la taille du tableau
     * @return {@code integer} la taille du plateau
     */
    public Integer getSize() {
        return size;
    }
}
