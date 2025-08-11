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
        switch(choice) {
            case 1:
                character.setOffensiveEquipement(weapon, 1);
                character.showOffensiveEquipement();
                return true;
            case 2:
                character.setOffensiveEquipement(weapon, 2);
                character.showOffensiveEquipement();
                return true;
            case 3:
                return false;
        }
        return true;
    }

    public Boolean isCharacterCanInteract(MainCharacter character){
        return character instanceof Warrior;
    }

    @Override
    public Boolean interact(MainCharacter character) {
        return null;
    }
}
