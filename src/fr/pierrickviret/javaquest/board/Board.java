package fr.pierrickviret.javaquest.board;

import fr.pierrickviret.javaquest.board.Case.*;
import fr.pierrickviret.javaquest.character.Dragon;
import fr.pierrickviret.javaquest.character.Gobelin;
import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.character.Sorcerer;
import fr.pierrickviret.javaquest.db.SQLRepository;
import fr.pierrickviret.javaquest.equipement.defensive.BigPotion;
import fr.pierrickviret.javaquest.equipement.defensive.SmallPotion;
import fr.pierrickviret.javaquest.equipement.defensive.Thunderclap;
import fr.pierrickviret.javaquest.equipement.offensive.*;
import  fr.pierrickviret.javaquest.character.Character;

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
    public Board(int difficultyLevel) {
        this.size = 64;
        this.cases = new Case[size+1];
        feedBoard(difficultyLevel);
        SQLRepository.getInstance().saveBoard(this);
    }

    public void setCaseToEmpty(int id) {
        cases[id] = new EmptyCase();
    }

    public Boolean hasSaveBoard(){
        return SQLRepository.getInstance().hasBoard();
    }

    private void feedBoard(int difficultyLevel) {
        for (int i = 0; i < cases.length; i++) {
            cases[i] = new EmptyCase();
        }

        addWeapons(2);
        addSpecialWeapons(2);

        addPotions();

        addEnemies(difficultyLevel);
    }

    private void addWeapons(int count) {
        for (int i = 0; i < count; i++) {
            cases[giveEmptyCase()] = new WeaponCase(new Club());
            cases[giveEmptyCase()] = new WeaponCase(new Sword());
            cases[giveEmptyCase()] = new SpellCase(new Lightning());
            cases[giveEmptyCase()] = new SpellCase(new Fireball());
        }
    }

    private void addSpecialWeapons(int count) {
        for (int i = 0; i < count; i++) {
            cases[giveEmptyCase()] = new WeaponCase(new Bow());
            cases[giveEmptyCase()] = new SpellCase(new Invisibility());
        }
    }

    private void addPotions() {
        // 6 petites potions
        for (int i = 0; i < 6; i++) {
            cases[giveEmptyCase()] = new PotionCase(new SmallPotion());
        }
        // 2 grandes potions
        for (int i = 0; i < 2; i++) {
            cases[giveEmptyCase()] = new PotionCase(new BigPotion());
        }
        // 2 potions boost
        for (int i = 0; i < 2; i++) {
            cases[giveEmptyCase()] = new PotionCase(new Thunderclap());
        }
    }

    private void addEnemies(int difficulty) {
        switch (difficulty) {
            case 1:
                addEnemyType(12, new Gobelin());
                addEnemyType(6, new Sorcerer());
                addEnemyType(2, new Dragon());
                break;
            case 2:
                addEnemyType(12, new Gobelin());
                addEnemyType(10, new Sorcerer());
                addEnemyType(4, new Dragon());
                break;
            case 3:
                addEnemyType(14, new Gobelin());
                addEnemyType(12, new Sorcerer());
                addEnemyType(6, new Dragon());
                break;
        }
    }

    private void addEnemyType(int count, Character enemyType) {
        for (int i = 0; i < count; i++) {
            cases[giveEmptyCase()] = new EnemyCase(enemyType);
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
