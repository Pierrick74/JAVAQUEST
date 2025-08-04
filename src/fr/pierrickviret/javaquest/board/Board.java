package fr.pierrickviret.javaquest.board;

import fr.pierrickviret.javaquest.board.Case.*;
import fr.pierrickviret.javaquest.character.Dragon;
import fr.pierrickviret.javaquest.character.Gobelin;
import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.character.Sorcerer;
import fr.pierrickviret.javaquest.equipement.defensive.BigPotion;
import fr.pierrickviret.javaquest.equipement.defensive.SmallPotion;
import fr.pierrickviret.javaquest.equipement.defensive.Thunderclap;
import fr.pierrickviret.javaquest.equipement.offensive.*;

import java.util.Random;

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
    private final Case[] cases;
    private Integer Id;

    //init
    /**
     * Initialise la valeur du plateau à 64
     */
    public Board() {
        this.size = 64;
        this.cases = new Case[size+1];
        feedBoard();
    }

    public void setCaseToEmpty(int id) {
        cases[id] = new EmptyCase();
    }

    private void feedBoard() {
        for (int i = 0; i < cases.length; i++) {
            cases[i] = new EmptyCase();
        }

        // 5 Massues
        for (int i = 0; i < 5; i++) {
            cases[giveEmptyCase()] = new WeaponCase(new Club());
        }

        // 4 Épées
        for (int i = 0; i < 4; i++) {
            cases[giveEmptyCase()] = new WeaponCase(new Sword());
        }

        // 2 Arc
        for (int i = 0; i < 2; i++) {
            cases[giveEmptyCase()] = new WeaponCase(new Bow());
        }

        // 5 Sorts "éclair"
        for (int i = 0; i < 5; i++) {
            cases[giveEmptyCase()] = new SpellCase(new Lightning());
        }

        // 2 Sorts "boules de feu"
        for (int i = 0; i < 2; i++) {
            cases[giveEmptyCase()] = new SpellCase(new Fireball());
        }

        // 2 Sorts "Invisibilité"
        for (int i = 0; i < 2; i++) {
            cases[giveEmptyCase()] = new SpellCase(new Invisibility());
        }

        // 6 Potions standards
        for (int i = 0; i < 6; i++) {
            cases[giveEmptyCase()] = new PotionCase(new SmallPotion());
        }

        // 2 Grandes potions
        for (int i = 0; i < 6; i++) {
            cases[giveEmptyCase()] = new PotionCase(new BigPotion());
        }

        // 2 potions Coup de tonnerre
        for (int i = 0; i < 2; i++) {
            cases[giveEmptyCase()] = new PotionCase(new Thunderclap());
        }

        // CASES ENNEMIS (24 cases)

        // 10 Gobelins
        for (int i = 0; i < 10; i++) {
            cases[giveEmptyCase()] = new EnemyCase(new Gobelin());
        }

// 10 Sorciers
        for (int i = 0; i < 10; i++) {
            cases[giveEmptyCase()] = new EnemyCase(new Sorcerer());
        }

// 4 Dragons
        for (int i = 0; i < 4; i++) {
            cases[giveEmptyCase()] = new EnemyCase(new Dragon());
        }
    }

    private int giveEmptyCase() {
        Random rand = new Random();
        int place;
        do {
            place = rand.nextInt(1, size);
        } while (!(cases[place] instanceof EmptyCase));
        return place;
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
