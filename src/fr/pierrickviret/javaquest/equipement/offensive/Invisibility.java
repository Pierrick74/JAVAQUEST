package fr.pierrickviret.javaquest.equipement.offensive;

public class Invisibility extends Spell{
    public Invisibility() {
        super("Invisibilité", 2, 3);
    }

    @Override
    public String toString() {
        return "un sort d'invisibilité" + System.lineSeparator();
    }
}
