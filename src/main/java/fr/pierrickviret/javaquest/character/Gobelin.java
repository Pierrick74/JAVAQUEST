package fr.pierrickviret.javaquest.character;

public class Gobelin extends Character{
    public Gobelin() {
        super("Gobelin", 6, 3, 3, "/fr/pierrickviret/javaquest/assets/Character/Gobelin.png");
    }

    @Override
    public String toString() {
        return "gobelin";
    }
}
