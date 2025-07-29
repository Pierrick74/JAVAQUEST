package fr.pierrickviret.javaquest.character;

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
        return switch (type) {
            case Warrior -> (int) (Math.random() * (10 - 5 + 1)) + 5;
            case Wizard -> (int) (Math.random() * (15 - 8 + 1)) + 8;
            default -> 0;
        };
    }

    private int getAttack(MainCharacter.characterType type) {
        return switch (type) {
            case Warrior -> (int) (Math.random() * (15 - 8 + 1)) + 8;
            case Wizard -> (int) (Math.random() * (6 - 3 + 1)) + 3;
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

