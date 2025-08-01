package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.equipement.OffensiveEquipement;

public class SpellCase extends Case {
    OffensiveEquipement spell;

    public SpellCase(OffensiveEquipement spell) {
        this.spell = spell;
    }

    @Override
    public String toString() {

        return "Coup de chance, une nouveau sort pour le magicien :"
                +spell.toString()
                + System.lineSeparator();
    }
}