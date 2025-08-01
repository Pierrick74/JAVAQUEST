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
        int health = character.getHealth() - enemy.getAttack();
        if (health < 0) {
            health = 0;
        }
        character.setHealth(health);
        show(enemy.toString() + " vous attaque");
        show("votre santé est à " + character.getHealth());
    }
}
