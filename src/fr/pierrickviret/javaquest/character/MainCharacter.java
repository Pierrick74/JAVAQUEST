package fr.pierrickviret.javaquest.character;

import java.util.Random;

public class MainCharacter extends Character {
    //enum
    public enum characterType {
        Warrior,
        Wizard
    }

    //variable
    MainCharacter.characterType type;

    //init
    public MainCharacter(MainCharacter.characterType type, String name) {
        super();
        this.type = type;
        this.health = getHealth(this.type);
        this.attack = getAttack(this.type);
        this.name = name;
    }

    //private
    private int getHealth(MainCharacter.characterType type) {
        Random rand = new Random();
        return switch (type) {
            case Warrior -> rand.nextInt(5, 10);
            case Wizard -> rand.nextInt(8, 15);
            default -> 0;
        };
    }

    private int getAttack(MainCharacter.characterType type) {
        Random rand = new Random();
        return switch (type) {
            case Warrior -> rand.nextInt(8, 15);
            case Wizard -> rand.nextInt(3, 6);
            default -> 0;
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

