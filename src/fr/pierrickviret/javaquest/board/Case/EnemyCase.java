package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.character.Character;
import fr.pierrickviret.javaquest.character.MainCharacter;

public class EnemyCase extends Case {
    Character enemy;

    public EnemyCase(Character enemy) {
        this.enemy = enemy;
    }

    @Override
    public String toString() {

        return "oh non, un "
                + enemy.toString()
                + System.lineSeparator();
    }

    @Override
    public void interact(MainCharacter character) {
        character.setHealth(character.getHealth() - enemy.getAttack());
        show(enemy.toString() + " vous attaque");
        show("votre santée est à " + character.getHealth());
    }
}
