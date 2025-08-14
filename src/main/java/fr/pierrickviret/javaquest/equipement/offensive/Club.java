package fr.pierrickviret.javaquest.equipement.offensive;

public class Club extends Weapon{
    public Club() {
        super("massue", 3, 1, "fr/pierrickviret/javaquest/javafx/assets/OffensiveEquipement/Club.png");
    }

    @Override
    public String toString() {
        return "une Massue";
    }
}
