package fr.pierrickviret.javaquest.equipement.defensive;

import fr.pierrickviret.javaquest.equipement.DefensiveEquipement;
import fr.pierrickviret.javaquest.equipement.offensive.Spell;


public class BigPotion extends Potion {
    public BigPotion() {
        super("Grande potion", 5);
    }

    @Override
    public String toString() {
        return "une grande potion ";
    }
}
