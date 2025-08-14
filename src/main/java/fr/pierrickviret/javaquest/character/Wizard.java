package fr.pierrickviret.javaquest.character;

import fr.pierrickviret.javaquest.commun.CharacterType;
/**
 *<h2> class Wizard</h2>
 * <p> Class représentant un magicien
 * Cette class extends de <cite>MainCharacter</cite></p>
 * @author Pierrick
 * @see Character
 * @see MainCharacter
 * @version 1.0
 */
public class Wizard extends MainCharacter {
    public Wizard(String name, int id) {
        super(CharacterType.Wizard, name, id, "/fr/pierrickviret/javaquest/assets/Character/wizard.PNG");
    }

    @Override
    public String toString() {

        return "Bonjour je suis " + this.name + ", un magicien" + System.lineSeparator()
                + "attaque = " + this.attack + ";" + System.lineSeparator()
                + "points de vie = " + this.health + ".";
    }
}
