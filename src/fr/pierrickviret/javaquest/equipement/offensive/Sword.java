package fr.pierrickviret.javaquest.equipement.offensive;

public class Sword extends Weapon {
    public Sword() {
        super("Epée",5, 1);
    }

    @Override
    public String toString() {
        return "une épée de niveau 1" + System.lineSeparator();
    }
}
