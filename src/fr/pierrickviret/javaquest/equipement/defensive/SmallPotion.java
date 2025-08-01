package fr.pierrickviret.javaquest.equipement.defensive;

import fr.pierrickviret.javaquest.equipement.offensive.Spell;

public class SmallPotion extends Spell {
    public SmallPotion() {
        super("Potion de vie standards", 2);
    }

    @Override
    public String toString() {
        return "Une potion standards" + System.lineSeparator();
    }
}
