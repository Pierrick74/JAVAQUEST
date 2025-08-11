package fr.pierrickviret.javaquest.equipement.defensive;

public class BigPotion extends Potion {
    public BigPotion() {
        super("Grande potion", 15);
    }

    @Override
    public String toString() {
        return "une Grande potion";
    }
}
