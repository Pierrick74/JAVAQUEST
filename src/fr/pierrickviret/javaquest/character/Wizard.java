package fr.pierrickviret.javaquest.character;

import fr.pierrickviret.javaquest.commun.CharacterType;

public class Wizard extends MainCharacter {
    public Wizard(String name) {
        super(CharacterType.Wizard, name);
    }

    @Override
    public String toString() {

        return "Bonjour je suis " + this.name + " un magicien" + System.lineSeparator()
                + "attaque = " + this.attack + ";" + System.lineSeparator()
                + "points de vie = " + this.health + ".";
    }
}
