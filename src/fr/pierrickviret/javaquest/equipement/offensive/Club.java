package fr.pierrickviret.javaquest.equipement.offensive;

public class Club extends Weapon{
    public Club() {
        super("massue", 3);
    }

    @Override
    public String toString() {
        return "une massue de niveau 3" + System.lineSeparator();
    }
}
