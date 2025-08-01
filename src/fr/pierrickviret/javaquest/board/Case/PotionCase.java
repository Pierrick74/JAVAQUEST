package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.equipement.defensive.Potion;

public class PotionCase extends Case {
    Potion potion;

    public PotionCase(Potion potion) {
        this.potion = potion;
    }

    @Override
    public String toString() {

        return "Yes, "
        + potion.toString()
        +", enfin" + System.lineSeparator();
    }

    @Override
    public Boolean interact(MainCharacter character) {
        int health = character.getHealth() + potion.getValue();
        if(character.getHealth() == character.getMaxHealth()) {
            return true;
        }

        if(health > character.getMaxHealth()) {
            character.setHealth(character.getMaxHealth());
            show("Votre vie est au maximum");
        } else {
            character.setHealth(health);
            show("Votre vie remonte à " + health);
        }
        return false;
    }
}
