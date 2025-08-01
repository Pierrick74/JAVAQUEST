package fr.pierrickviret.javaquest.equipement.defensive;

import fr.pierrickviret.javaquest.equipement.DefensiveEquipement;


public class BigPotion extends DefensiveEquipement {
    public BigPotion() {
        super("Grande potion", 5);
    }

    @Override
    public String toString() {
        return "une grande potion " + System.lineSeparator();
    }
}
