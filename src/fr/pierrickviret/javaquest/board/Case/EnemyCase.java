package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.character.Character;

public class EnemyCase extends Case {
    Character enemy;

    public EnemyCase(Character enemy) {
        this.enemy = enemy;
    }

    @Override
    public String toString() {

        return "oh non, un ennemi" + System.lineSeparator();
    }
}
