package fr.pierrickviret.javaquest.equipement.offensive;

public class Fireball extends Spell{
    public Fireball() {
        super("Boule de feu", 2,1);
    }

    @Override
    public String toString() {
        return "un sort:lance des boules de feu de niveau 2" + System.lineSeparator();
    }
}
