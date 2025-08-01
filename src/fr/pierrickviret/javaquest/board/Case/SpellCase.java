package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.character.Warrior;
import fr.pierrickviret.javaquest.character.Wizard;
import fr.pierrickviret.javaquest.equipement.OffensiveEquipement;

public class SpellCase extends Case {
    OffensiveEquipement spell;

    public SpellCase(OffensiveEquipement spell) {
        this.spell = spell;
    }

    @Override
    public String toString() {

        return "Coup de chance, une nouveau sort pour le magicien :"
                +spell.toString()
                + System.lineSeparator();
    }

    @Override
    public void interact(MainCharacter character) {
        if( character instanceof Wizard){
            if(character.getOffensiveEquipement() != null){
                Integer actualEquipementValue = character.getOffensiveEquipement().getValue();
                if (actualEquipementValue < this.spell.getValue()) {
                    takeObject(character);
                } else {
                    show("Votre arme est supérieure, vous laissez " + spell.getName());
                }
            } else {
                takeObject(character);
            }
        } else {
            show("Vous ne pouvez pas prendre de sort");
        }
    }

    private void takeObject(MainCharacter character) {
        character.setOffensiveEquipement(this.spell);
        show("Vous venez de prendre " + spell.toString());
    }
}