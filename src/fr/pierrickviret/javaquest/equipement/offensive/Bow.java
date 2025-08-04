package fr.pierrickviret.javaquest.equipement.offensive;

public class Bow extends Weapon{
    public Bow() {
        super("Arc", 4);
    }

    @Override
    public String toString() {
        return "un Arc de niveau 4" + System.lineSeparator();
    }
}
