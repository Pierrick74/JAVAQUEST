package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.character.Wizard;
import fr.pierrickviret.javaquest.equipement.OffensiveEquipement;

public class SpellCase extends Case {
    OffensiveEquipement spell;

    public SpellCase(OffensiveEquipement spell) {
        this.spell = spell;
    }

    @Override
    public String toString() {
        return spell.toString();
    }

    public Boolean interactWithCase(MainCharacter character, Integer choice) {
        return switch (choice) {
            case 1 -> {
                character.setOffensiveEquipement(spell, 1);
                yield true;
            }
            case 2 -> {
                character.setOffensiveEquipement(spell, 2);
                yield true;
            }
            case 3 -> false;
            default -> true;
        };
    }

    public Boolean isCharacterCanInteract(MainCharacter character){
        return character instanceof Wizard;
    }

    public String getImagePath() {
        return spell.getImagePath();
    }

    @Override
    public void interact(MainCharacter character) {
    }
}