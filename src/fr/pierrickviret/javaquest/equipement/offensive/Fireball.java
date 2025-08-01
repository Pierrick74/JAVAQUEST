package fr.pierrickviret.javaquest.equipement.offensive;

public class Fireball extends Spell{
    public Fireball() {
        super("Boule de feu", 2);
    }

    @Override
    public String toString() {
        return "lance des boules de feu" + System.lineSeparator();
    }
}
