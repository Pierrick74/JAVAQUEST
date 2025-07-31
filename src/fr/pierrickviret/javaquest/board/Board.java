package fr.pierrickviret.javaquest.board;

import fr.pierrickviret.javaquest.board.Case.*;
import fr.pierrickviret.javaquest.character.Dragon;
import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.equipement.defensive.potion;
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
    private Integer Id;

    //init
    /**
    * Initialise la valeur du plateau à 64
     */
    public Board() {
        this.size = 4;
        this.cases = new Case[size+1];
        cases[1] = new EmptyCase();
        cases[2] = new EnemyCase(new Dragon());
        cases[3] = new WeaponCase(new Sword());
        cases[4] = new PotionCase(new potion());
    }

    /**
     * Retourne la taille du tableau
     * @return {@code integer} la taille du plateau
     */
    public Integer getSize() {
        return size;
    }

    /**
     * permet de retourner l'objet de la case indiqué
     * @param index {@code Integer} position sur le plateau
     * @return l'objet sur la case
     */
    public Case getCase(int index) {
        return cases[index];
    }

    public void setId(int id) {
        Id = id;
    }

    public Integer getId() {
        return Id;
    }

    public Boolean hasID() {
        return Id != null;
    }
}
