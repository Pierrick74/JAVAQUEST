package fr.pierrickviret.javaquest.equipement.offensive;

public class Lightning extends Spell{
    public Lightning() {
        super("Eclair", 3);
    }

    @Override
    public String toString() {
        return "Lance des éclaires" + System.lineSeparator();
    }
}
