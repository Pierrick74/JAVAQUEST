package fr.pierrickviret.javaquest.equipement.defensive;

public class SmallPotion extends Potion {
    public SmallPotion() {
        super("Potion de vie standard", 8);
    }

    @Override
    public String toString() {
        return "Une potion standard";
    }
}
