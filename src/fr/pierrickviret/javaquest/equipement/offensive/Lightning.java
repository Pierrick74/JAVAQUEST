package fr.pierrickviret.javaquest.equipement.offensive;

public class Lightning extends Spell{
    public Lightning() {
        super("Eclair", 3, 2);
    }

    @Override
    public String toString() {
        return "un sort Lance des éclaires de niveau 1" + System.lineSeparator();
    }
}
