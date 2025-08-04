package fr.pierrickviret.javaquest.equipement;

/**
 *<h2> class Offensive Equipment</h2>
 * <p> Class abstraite représentant les équipements offensifs
 * @author Pierrick
 * @version 1.0
 */
public abstract class OffensiveEquipement {
    protected  Integer value;
    String name;
    int level;

    public OffensiveEquipement(String name, Integer value, int level) {
        this.name = name;
        this.value = value;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {return value;}

    public Integer getLevel() {return level;}
}
