package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.Menu;
import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.character.Warrior;
import fr.pierrickviret.javaquest.equipement.OffensiveEquipement;

public class SpellCase extends Case {
    OffensiveEquipement spell;

    public SpellCase(OffensiveEquipement spell) {
        this.spell = spell;
    }

    @Override
    public String toString() {

        return "Coup de chance, une nouveau sort pour le magicien :"
                +spell.toString();
    }

    @Override
    public Boolean interact(MainCharacter character) {
        Menu menu = Menu.getInstance();
        if (character instanceof Warrior) {
            show("Vous ne pouvez pas prendre de sort, vous êtes un Combatant");
            return true;
        }

        character.showOffensiveEquipement();
        menu.showInformation("Que voulez vous faire :\n1. Récuperer l'arme et la mettre dans l'inventaire 1\n2. Récuperer l'arme et la mettre dans l'inventaire 2\n3. Laissez l'arme par terre");
        int result = menu.listenResultBetween(1, 3);

        switch(result) {
            case 1:
                character.setOffensiveEquipement(spell, 1);
                menu.showInformation("Vous prennez l'arme");
                character.showOffensiveEquipement();
                return true;
            case 2:
                character.setOffensiveEquipement(spell, 2);
                menu.showInformation("Vous laissez l'arme");
                character.showOffensiveEquipement();
                return true;
            case 3:
                menu.showInformation("Vous laissez l'arme");
                return true;
        }
        return true;
    }
}