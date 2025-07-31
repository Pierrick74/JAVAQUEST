package fr.pierrickviret.javaquest.character;

import fr.pierrickviret.javaquest.commun.CharacterType;
import fr.pierrickviret.javaquest.equipement.DefensiveEquipement;
import fr.pierrickviret.javaquest.equipement.OffensiveEquipement;

import java.util.Random;

/**
 *<h2> class Character</h2>
 * <p> Class abstraite représentant l'ensemble des personnages que le joueur peut créer
 * Cette class extends de <cite>Character</cite></p>
 * @author Pierrick
 * @see Character
 * @version 1.0
 */
public abstract class MainCharacter extends Character {
    //variable
    CharacterType type;
    OffensiveEquipement offensiveEquipement;
    DefensiveEquipement  defensiveEquipement;
    private int ID;

    //init
    public MainCharacter(CharacterType type, String name) {
        super(name, 0, 0);
        this.type = type;
        this.health = setHealth(this.type);
        this.attack = setAttack(this.type);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterType getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public OffensiveEquipement getOffensiveEquipement() {
        return offensiveEquipement;
    }

    public DefensiveEquipement getDefensiveEquipement() {
        return defensiveEquipement;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    //private
    private int setHealth(CharacterType type) {
        Random rand = new Random();
        return switch (type) {
            case Warrior -> rand.nextInt(5, 10);
            case Wizard -> rand.nextInt(8, 15);
        };
    }

    private int setAttack(CharacterType type) {
        Random rand = new Random();
        return switch (type) {
            case Warrior -> rand.nextInt(8, 15);
            case Wizard -> rand.nextInt(3, 6);
        };
    }

    @Override
    public String toString() {

        return "Personnage" + System.lineSeparator()
                + "name = " + this.name + ";" + System.lineSeparator()
                + "type = " + this.type.toString() + ";" + System.lineSeparator()
                + "attaque = " + this.attack + ";" + System.lineSeparator()
                + "points de vie = " + this.health + ".";
    }
}

