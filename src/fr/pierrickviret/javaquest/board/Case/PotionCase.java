package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.equipement.DefensiveEquipement;

public class PotionCase extends Case {
    DefensiveEquipement potion;

    public PotionCase(DefensiveEquipement potion) {
        this.potion = potion;
    }

    @Override
    public String toString() {

        return "Yes, une potion , enfin" + System.lineSeparator();
    }
}
