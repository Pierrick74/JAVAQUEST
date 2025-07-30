package fr.pierrickviret.javaquest.equipement;

import fr.pierrickviret.javaquest.board.Case;

/**
 *<h2> class OffensiveEquipement</h2>
 * <p> Class abstraite représentant les équipements offensifs
 * @author Pierrick
 * @version 1.0
 */
public abstract class OffensiveEquipement {
    Integer value;
    String name;

    public OffensiveEquipement(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
}
