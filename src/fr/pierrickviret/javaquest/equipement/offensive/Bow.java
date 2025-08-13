package fr.pierrickviret.javaquest.equipement.offensive;

public class Bow extends Weapon{
    public Bow() {
        super("Arc", 5, 3, "fr/pierrickviret/javaquest/javafx/assets/OffensiveEquipement/Bow.png");
    }

    @Override
    public String toString() {
        return "un Arc";
    }
}
