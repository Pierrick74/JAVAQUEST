package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.equipement.defensive.Potion;
import fr.pierrickviret.javaquest.equipement.defensive.Thunderclap;

public class PotionCase extends Case {
    Potion potion;
    String descriptionOfInteraction;

    public PotionCase(Potion potion) {
        this.potion = potion;
    }

    @Override
    public String toString() {
        return potion.toString();
    }

    @Override
    public void interact(MainCharacter character) {
        if(potion instanceof Thunderclap) {
            character.setBoostAttack();
            Game.getInstance().setActualyCaseToEmpty();
        }
        treatTheSick(character);
    }

    public String getDescriptionOfInteraction() {
        return descriptionOfInteraction;
    }

    private void treatTheSick(MainCharacter character) {
        int health = character.getHealth() + potion.getValue();
        if(character.getHealth() == character.getMaxHealth()) {
            descriptionOfInteraction =  "Vous renversez la potion, quelle chance d'avoir la vie au maximum !";
            Game.getInstance().setActualyCaseToEmpty();
            return;
        }

        if(health > character.getMaxHealth()) {
            character.setHealth(character.getMaxHealth());
            descriptionOfInteraction = "Votre vie est au maximum";
        } else {
            character.setHealth(health);
            descriptionOfInteraction = "Votre vie remonte à " + health;
        }
        Game.getInstance().setActualyCaseToEmpty();
    }
}
