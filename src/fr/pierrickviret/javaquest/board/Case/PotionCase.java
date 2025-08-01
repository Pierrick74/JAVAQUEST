package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.equipement.DefensiveEquipement;

public class PotionCase extends Case implements saveableInDB{
    DefensiveEquipement potion;

    public PotionCase(DefensiveEquipement potion) {
        this.potion = potion;
    }

    @Override
    public String toString() {

        return "Yes, "
        + potion.toString()
        +", enfin" + System.lineSeparator();
    }

    @Override
    public String getInfoToSave() {
        return potion.toString();
    }
}
