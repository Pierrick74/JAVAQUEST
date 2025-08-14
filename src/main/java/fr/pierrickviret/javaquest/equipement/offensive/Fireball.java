package fr.pierrickviret.javaquest.equipement.offensive;

public class Fireball extends Spell{
    public Fireball() {
        super("Boule de feu", 2,1, "/fr/pierrickviret/javaquest/assets/OffensiveEquipement/Fireball.png");
    }

    @Override
    public String toString() {
        return "Un sort:des boules de feu";
    }
}
