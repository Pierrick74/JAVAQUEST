package fr.pierrickviret.javaquest.board;

import fr.pierrickviret.javaquest.board.Case.*;
import fr.pierrickviret.javaquest.character.Dragon;
import fr.pierrickviret.javaquest.character.Gobelin;
import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.character.Sorcerer;
import fr.pierrickviret.javaquest.equipement.defensive.BigPotion;
import fr.pierrickviret.javaquest.equipement.defensive.SmallPotion;
import fr.pierrickviret.javaquest.equipement.offensive.Club;
import fr.pierrickviret.javaquest.equipement.offensive.Fireball;
import fr.pierrickviret.javaquest.equipement.offensive.Lightning;
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

    private void feedBoard() {
        for (int i = 0; i < cases.length; i++) {
            cases[i] = new EmptyCase();
        }

// 5 Massues (cases 2, 11, 5, 22, 38)
        cases[2] = new WeaponCase(new Club());
        cases[5] = new WeaponCase(new Club());
        cases[11] = new WeaponCase(new Club());
        cases[22] = new WeaponCase(new Club());
        cases[38] = new WeaponCase(new Club());

// 4 Épées (cases 19, 26, 42 et 53)
        cases[19] = new WeaponCase(new Sword());
        cases[26] = new WeaponCase(new Sword());
        cases[42] = new WeaponCase(new Sword());
        cases[53] = new WeaponCase(new Sword());

// 5 Sorts "éclair" (cases 1, 4, 8, 17 et 23)
        cases[1] = new SpellCase(new Lightning());
        cases[4] = new SpellCase(new Lightning());
        cases[8] = new SpellCase(new Lightning());
        cases[17] = new SpellCase(new Lightning());
        cases[23] = new SpellCase(new Lightning());

// 2 Sorts "boules de feu" (cases 48 et 49)
        cases[48] = new SpellCase(new Fireball());
        cases[49] = new SpellCase(new Fireball());

// 6 Potions standards (cases 7, 13, 31, 33, 39, 43)
        cases[7] = new PotionCase(new SmallPotion());
        cases[13] = new PotionCase(new SmallPotion());
        cases[31] = new PotionCase(new SmallPotion());
        cases[33] = new PotionCase(new SmallPotion());
        cases[39] = new PotionCase(new SmallPotion());
        cases[43] = new PotionCase(new SmallPotion());

// 2 Grandes potions (cases 28, 41)
        cases[28] = new PotionCase(new BigPotion());
        cases[41] = new PotionCase(new BigPotion());

// === CASES ENNEMIS (24 cases) ===

// 10 Gobelins (cases 3, 6, 9, 12, 15, 18, 21, 24, 27 et 30)
        cases[3] = new EnemyCase(new Gobelin());
        cases[6] = new EnemyCase(new Gobelin());
        cases[9] = new EnemyCase(new Gobelin());
        cases[12] = new EnemyCase(new Gobelin());
        cases[15] = new EnemyCase(new Gobelin());
        cases[18] = new EnemyCase(new Gobelin());
        cases[21] = new EnemyCase(new Gobelin());
        cases[24] = new EnemyCase(new Gobelin());
        cases[27] = new EnemyCase(new Gobelin());
        cases[30] = new EnemyCase(new Gobelin());

// 10 Sorciers (cases 10, 20, 25, 32, 35, 36, 37, 40, 44 et 47)
        cases[10] = new EnemyCase(new Sorcerer());
        cases[20] = new EnemyCase(new Sorcerer());
        cases[25] = new EnemyCase(new Sorcerer());
        cases[32] = new EnemyCase(new Sorcerer());
        cases[35] = new EnemyCase(new Sorcerer());
        cases[36] = new EnemyCase(new Sorcerer());
        cases[37] = new EnemyCase(new Sorcerer());
        cases[40] = new EnemyCase(new Sorcerer());
        cases[44] = new EnemyCase(new Sorcerer());
        cases[47] = new EnemyCase(new Sorcerer());

// 4 Dragons (cases 45, 52, 56 et 62)
        cases[45] = new EnemyCase(new Dragon());
        cases[52] = new EnemyCase(new Dragon());
        cases[56] = new EnemyCase(new Dragon());
        cases[62] = new EnemyCase(new Dragon());
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
