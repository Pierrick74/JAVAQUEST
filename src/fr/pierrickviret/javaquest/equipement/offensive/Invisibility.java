package fr.pierrickviret.javaquest.equipement.offensive;

public class Invisibility extends Spell{
    public Invisibility() {
        super("Invisibilité", 5);
    }

    @Override
    public String toString() {
        return "une Invisibilité de niveau 5" + System.lineSeparator();
    }
}
