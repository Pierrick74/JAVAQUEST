package fr.pierrickviret.javaquest.equipement.offensive;

public class Bow extends Weapon{
    public Bow() {
        super("Arc", 3, 2);
    }

    @Override
    public String toString() {
        return "un arc de niveau 3" + System.lineSeparator();
    }
}
