package fr.pierrickviret.javaquest.equipement.offensive;

public class Invisibility extends Spell{
    public Invisibility() {
        super("Invisibilité", 2, 3, "/fr/pierrickviret/javaquest/assets/OffensiveEquipement/invisibilitySpell.png");
    }

    @Override
    public String toString() {
        return "un sort d'invisibilité";
    }
}
