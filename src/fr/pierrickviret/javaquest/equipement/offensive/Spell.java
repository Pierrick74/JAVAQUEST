package fr.pierrickviret.javaquest.equipement.offensive;

import fr.pierrickviret.javaquest.equipement.OffensiveEquipement;
/**
 *<h2> class Spell</h2>
 * <p> Class représentant les sorts du magicien.
 * Cette class extends de <cite>OffensiveEquipement</cite></p>
 * @author Pierrick
 * @see fr.pierrickviret.javaquest.equipement.OffensiveEquipement
 * @version 1.0
 */
public class Spell extends OffensiveEquipement {
    public Spell(String name, Integer value,  int level) {
        super(name, value, level);
    }
}
