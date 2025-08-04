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
    public Boolean interact(MainCharacter character) {
        characterAttack(character);
        if(enemy.getCharacterHealthValue() <= 0){
            return false;
        }
        enemyAttack(character);
        return true;
    }

    private void enemyAttack(MainCharacter character) {
        int health = character.getHealth() - enemy.getAttackValue();
        if (health < 0) {
            health = 0;
        }
        character.setHealth(health);
        show(enemy.toString() + " vous attaque");
        show("votre santé est à " + character.getHealth());
    }

    private void characterAttack(MainCharacter character) {
        int health = enemy.getCharacterHealthValue() - character.getAttackValue();
        if (health > 0) {
            enemy.setCharacterHealth(health);
            show(" vous attaquez " + enemy.toString());
            show("la santé de "+ enemy.toString() + " passe à " + enemy.getCharacterHealthValue() );
        }
        else {
            enemy.setCharacterHealth(0);
            show(" vous attaquez " + enemy.toString());
            show(" Vous avez vaincu " + enemy.toString());

        }

    }
}
