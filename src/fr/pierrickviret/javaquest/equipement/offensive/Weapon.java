package fr.pierrickviret.javaquest.equipement.offensive;

import fr.pierrickviret.javaquest.equipement.OffensiveEquipement;
/**
 *<h2> class weapon</h2>
 * <p> Class représentant les armes du combatant
 * Cette class extends de <cite>OffensiveEquipement</cite></p>
 * @author Pierrick
 * @see fr.pierrickviret.javaquest.equipement.OffensiveEquipement
 * @version 1.0
 */
public class Weapon extends OffensiveEquipement {
    public Weapon(String name, Integer value) {
        super(name, value);
    }

    public Integer getValue() {
        return value;
    }
}
