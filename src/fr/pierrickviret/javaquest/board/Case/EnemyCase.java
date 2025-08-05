package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.Dice;
import fr.pierrickviret.javaquest.Menu;
import fr.pierrickviret.javaquest.character.*;
import fr.pierrickviret.javaquest.character.Character;
import fr.pierrickviret.javaquest.equipement.OffensiveEquipement;
import fr.pierrickviret.javaquest.equipement.offensive.Bow;
import fr.pierrickviret.javaquest.equipement.offensive.Invisibility;

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

    /**
     *
     * @return return true si l'ennemie est encore en vie
     */
    @Override
    public Boolean interact(MainCharacter character) {

        if(character instanceof Wizard && enemy instanceof Orcs) {
            return true;
        }
        if(character instanceof Warrior && enemy instanceof EvilSpirits) {
            return true;
        }

        characterAttack(character);
        if(enemy.getCharacterHealthValue() <= 0){
            return false;
        }
        enemyAttack(character);
        return true;
    }

    public int getEnemieExperience() {
        return enemy.getExperience();
    }

    private void enemyAttack(MainCharacter character) {
        int attackValue = getAttackValueWithCriticalRules(enemy.getAttackValue());
        if(attackValue == 0){
            show(enemy.toString() + " ne peux pas vous attaquer");
            return;
        }

        int health = character.getHealth() - attackValue;
        if (health < 0) {
            health = 0;
        }
        character.setHealth(health);
        show(enemy.toString() + " vous attaque de " + attackValue);
        show("votre santé est à " + character.getHealth());
    }

    private void characterAttack(MainCharacter character) {
        int attackValue = checkAttackValue(character);

        if(attackValue == 0){
            show("Vous ne pouvez pas attaquer");
            return;
        }

        int health = enemy.getCharacterHealthValue() - attackValue;
        show(" vous attaquez " + enemy.toString() + " avec une force de " + attackValue);
        if (health > 0) {
            enemy.setCharacterHealth(health);
            show("la santé de "+ enemy.toString() + " passe à " + enemy.getCharacterHealthValue() );
        }
        else {
            enemy.setCharacterHealth(0);
            character.increaseExperience(enemy.getExperience());
            show(" Vous avez vaincu " + enemy.toString());
            show("Votre experience passe à " + character.getExperience());
        }
    }

    private Integer checkAttackValue(MainCharacter character) {
        int attackValue = character.getAttackValue();


        if(character.hasOffensiveEquipementsForHisLevel() != 0) {
            attackValue += getWeaponValue(character);
        } else if(character.hasOffensiveEquipement()) {
            show("Vous n'avez pas d'arme compatible avec votre niveau");
        }

        attackValue = getAttackValueWithCriticalRules(attackValue);

        if(character.getBoostAttackValue()){
            attackValue = attackValue * 2;
            character.resetBoostAttack();
        }
        return attackValue;
    }

    private int getWeaponValue(MainCharacter character) {
        OffensiveEquipement equipement = getOffensiveEquipement(character);
        int attackValue = equipement.getValue();
        show("Vous prenez " + equipement);

        if (enemy instanceof Dragon && equipement instanceof Bow) {
            attackValue = attackValue + 2;
            show("coup de chance, vous avez un arc, +2 d'attaque contre les dragons ");
        }

        if (enemy instanceof EvilSpirits && equipement instanceof Invisibility) {
            show("coup de chance, vous avez un sort d'invisibilité, +3 d'attaque contre les mauvais esprits ");
            attackValue = attackValue + 3;
        }
        return attackValue;
    }

    private OffensiveEquipement getOffensiveEquipement(MainCharacter character) {
        int result;
        if(character.hasOffensiveEquipementsForHisLevel() == 1 ) {
            if (character.getOffensiveEquipement(1) != null){
                result = 1;
            } else {
                result = 2;
            }
        } else {
            character.showOffensiveEquipement();
            show("Sélectionner votre arme");
            result = Menu.getInstance().listenResultBetween(1,2);
            if(character.getOffensiveEquipement(result).getLevel() > character.getLevel()){
                Menu.getInstance().showInformation("Vous ne pouvez pas prendre une arme de ce niveau actuellement");
                return getOffensiveEquipement(character);
            }
        }
        return character.getOffensiveEquipement(result);
    }

    private Integer getAttackValueWithCriticalRules(Integer attackValue) {
        Dice dice = new Dice();
        Integer number = dice.getRoll(20);
        return switch (number) {
            case 1 -> {
                show("Vous obtenez " + number + " avec le dé à 20 faces, dommage");
                yield 0;
            }
            case 20 -> {
                show("Vous obtenez " + number + " avec le dé à 20 faces, super");
                yield attackValue + 2;
            }
            default -> {
                show("Vous obtenez " + number + " avec le dé à 20 faces");
                yield attackValue;
            }
        };
    }
}
