package fr.pierrickviret.javaquest.equipement.offensive;

public class Sword extends Weapon {
    public Sword() {
        super("Epée",5);
    }

    @Override
    public String toString() {
        return "une Epée de niveau 5" + System.lineSeparator();
    }
}
