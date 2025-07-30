package fr.pierrickviret.javaquest.character;

import fr.pierrickviret.javaquest.commun.CharacterType;

public class Warrior extends MainCharacter {
    public Warrior(String name) {
        super(CharacterType.Warrior,name);
    }

    @Override
    public String toString() {

        return "Bonjour je suis " + this.name + " un Combatant" + System.lineSeparator()
                + "attaque = " + this.attack + ";" + System.lineSeparator()
                + "points de vie = " + this.health + ".";
    }
}
