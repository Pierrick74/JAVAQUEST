package fr.pierrickviret.javaquest.board.Case;

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
}
