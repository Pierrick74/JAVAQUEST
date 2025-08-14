package fr.pierrickviret.javaquest.equipement.offensive;

public class Sword extends Weapon {
    public Sword() {
        super("Epée", 4, 2, "/fr/pierrickviret/javaquest/assets/OffensiveEquipement/Sword.png" );
    }

    @Override
    public String toString() {
        return "une Epée";
    }
}
