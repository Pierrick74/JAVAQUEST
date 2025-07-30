package fr.pierrickviret.javaquest.character;

import fr.pierrickviret.javaquest.commun.CharacterType;
/**
 *<h2> class Warrior</h2>
 * <p> Class représentant un combatant
 * Cette class extends de <cite>MainCharacter</cite></p>
 * @author Pierrick
 * @see Character
 * @see MainCharacter
 * @version 1.0
 */
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
