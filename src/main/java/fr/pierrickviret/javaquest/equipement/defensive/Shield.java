package fr.pierrickviret.javaquest.equipement.defensive;

import fr.pierrickviret.javaquest.equipement.DefensiveEquipement;
/**
 *<h2> class shield</h2>
 * <p> Class représentant les protections du Combatant
 * Cette class extends de <cite>DefensiveEquipement</cite></p>
 * @author Pierrick
 * @see fr.pierrickviret.javaquest.equipement.DefensiveEquipement
 * @version 1.0
 */
public class Shield extends DefensiveEquipement {
    public Shield() {
        super("bouclier", 4);
    }
}
