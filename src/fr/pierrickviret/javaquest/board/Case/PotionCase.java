package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.equipement.DefensiveEquipement;
import fr.pierrickviret.javaquest.equipement.defensive.Potion;
import fr.pierrickviret.javaquest.equipement.offensive.Spell;

public class PotionCase extends Case implements saveableInDB{
    Spell potion;

    public PotionCase(Spell potion) {
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
