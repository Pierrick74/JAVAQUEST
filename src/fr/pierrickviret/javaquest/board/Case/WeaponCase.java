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

        return "Coup de chance, une nouvelle arme pour le Combatant"
                + weapon.toString()
                + System.lineSeparator();
    }

    @Override
    public Boolean interact(MainCharacter character) {
        if( character instanceof Warrior){
            if(character.getOffensiveEquipement() != null){
                Integer actualEquipementValue = character.getOffensiveEquipement().getValue();
                if (actualEquipementValue < this.weapon.getValue()) {
                    takeObject(character);
                    return false;
                } else {
                    show("Votre arme est supérieure, vous laissez " + weapon.getName());
                    return true;
                }
            } else {
                takeObject(character);
                return false;
            }
        } else {
            show("Vous ne pouvez pas prendre d'armement");
            return true;
        }
    }

    private void takeObject(MainCharacter character) {
        character.setOffensiveEquipement(this.weapon);
        show("Vous venez de prendre " + weapon.toString());
    }
}
