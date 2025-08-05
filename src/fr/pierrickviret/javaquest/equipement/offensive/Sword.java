package fr.pierrickviret.javaquest.equipement.offensive;

public class Sword extends Weapon {
    public Sword() {
        super("Epée", 4, 2);
    }

    @Override
    public String toString() {
        return "une épée de niveau 1" + System.lineSeparator();
    }
}
