package fr.pierrickviret.javaquest.equipement.offensive;

public class fireball extends Spell{
    public fireball() {
        super("Boule de feu", 2);
    }

    @Override
    public String toString() {
        return "lance des boules de feu" + System.lineSeparator();
    }
}
