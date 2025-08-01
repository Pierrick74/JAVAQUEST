package fr.pierrickviret.javaquest.equipement.offensive;

public class lightning extends Spell{
    public lightning() {
        super("Eclair", 3);
    }

    @Override
    public String toString() {
        return "Lance des éclaires" + System.lineSeparator();
    }
}
