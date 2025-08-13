package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.character.Warrior;
import fr.pierrickviret.javaquest.equipement.OffensiveEquipement;

public class WeaponCase extends Case {
    OffensiveEquipement weapon;

    public WeaponCase(OffensiveEquipement weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return weapon.toString();
    }

    public Boolean interactWithCase(MainCharacter character, Integer choice) {
        return switch (choice) {
            case 1 -> {
                character.setOffensiveEquipement(weapon, 1);
                yield true;
            }
            case 2 -> {
                character.setOffensiveEquipement(weapon, 2);
                yield true;
            }
            case 3 -> false;
            default -> true;
        };
    }

    public Boolean isCharacterCanInteract(MainCharacter character){
        return character instanceof Warrior;
    }

    public String getImagePath() {
        return weapon.getImagePath();
    }

    @Override
    public void interact(MainCharacter character) {

    }
}
