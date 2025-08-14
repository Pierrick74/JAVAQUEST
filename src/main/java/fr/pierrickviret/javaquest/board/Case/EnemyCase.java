package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.Dice;
import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.character.*;
import fr.pierrickviret.javaquest.character.Character;
import fr.pierrickviret.javaquest.commun.ButtonInformationView;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.equipement.OffensiveEquipement;
import fr.pierrickviret.javaquest.equipement.offensive.Bow;
import fr.pierrickviret.javaquest.equipement.offensive.Invisibility;
import fr.pierrickviret.javaquest.javafx.Game.CombatView;
import fr.pierrickviret.javaquest.javafx.Game.FightView;
import fr.pierrickviret.javaquest.javafx.StageRepository;
import javafx.application.Platform;

public class EnemyCase extends Case {
    Character enemy;
    private String fightNewspaper = "";

    public EnemyCase(Character enemy) {
        this.enemy = enemy;
    }

    @Override
    public String toString() {
        return enemy.toString();
    }

    private void setFightNewspaper(String fightNewspaper) {
        this.fightNewspaper += "\n" + fightNewspaper;
    }

    private void resetFightNewspaper() {
        this.fightNewspaper = "";
    }

    public int getEnemieExperience() {
        return enemy.getExperience();
    }

    public String getEnemyImagePath() {
        return enemy.getImagePath();
    }

    public Character getEnemy() {
        return enemy;
    }

    /**
     */
    @Override
    public void interact(MainCharacter character) {
        resetFightNewspaper();

        if(character instanceof Wizard && enemy instanceof Orcs) {
            Platform.runLater(() -> StageRepository.getInstance().replaceScene(new FightView(
                            new CombatView(character, enemy, "Un Orc, vos sorts ne peuvent pas l'atteindre"),
                            new ButtonInformationView("Vous partez", ()-> Game.getInstance().setGameState(GameState.moveBack)),
                    null
                    )));
            return;
        }
        if(character instanceof Warrior && enemy instanceof EvilSpirits) {
            Platform.runLater(() -> StageRepository.getInstance().replaceScene(new FightView(
                    new CombatView(character, enemy, "Les mauvais esprits ne peuvent pas être touchés avec vos armes"),
                    new ButtonInformationView("Vous partez", ()-> Game.getInstance().setGameState(GameState.moveBack)),
                    null
                    )));
            return;
        }
        if(!character.hasOffensiveEquipement()) {
            setFightNewspaper("Vous n'avez pas d'arme dans votre sac, vous attaquez à main nue");
            startFight(character, 0);
            return;
        }

        if(character.hasOffensiveEquipementsForHisLevel() == 0) {
            setFightNewspaper("Vous n'avez pas d'arme compatible avec votre niveau dans votre sac, vous attaquez à main nue");
            startFight(character, 0);
            return;
        }

        if(character.hasOffensiveEquipementsForHisLevel() == 1 ) {
            if (character.getOffensiveEquipement(1) != null){
                startFight(character, 1);
                return;
            } else {
                startFight(character, 2);
                return;
            }
        }
        setFightNewspaper("Quelle arme voulez vous utiliser?");
        Platform.runLater(() -> StageRepository.getInstance().replaceScene(new FightView(
                new CombatView(character, enemy, fightNewspaper),
                new ButtonInformationView(character.getOffensiveEquipement(1).getName(), ()-> startFight(character, 1)),
                new ButtonInformationView(character.getOffensiveEquipement(1).getName(), ()-> startFight(character, 2))
                )));
    }

    private void startFight(MainCharacter character, Integer equipementNumber) {
        characterAttack(character, equipementNumber);
        if(enemy.getCharacterHealthValue() <= 0){
            setFightNewspaper("Vous avez vaincu " + enemy.getName());
            Game.getInstance().setActualyCaseToEmpty();
            Platform.runLater(() -> StageRepository.getInstance().replaceScene(new FightView(
                    new CombatView(character, enemy, fightNewspaper),
                    new ButtonInformationView("Retour au plateau", ()-> Game.getInstance().setGameState(GameState.launchDice)),
                    null
                    )));
            return;
        }
        enemyAttack(character);
        if (character.getHealth() <= 0) {
            Platform.runLater(() -> StageRepository.getInstance().replaceScene(new FightView(
                    new CombatView(character, enemy, fightNewspaper),
                    new ButtonInformationView("Retour au plateau", ()-> Game.getInstance().setGameState(GameState.gameOver)),
                    null
            )));
            return;
        }

        Platform.runLater(() -> StageRepository.getInstance().replaceScene(new FightView(
                new CombatView(character, enemy, fightNewspaper),
                new ButtonInformationView("Réattaquer", ()-> startFight(character, equipementNumber)),
                new ButtonInformationView("Vous enfuire",()-> Game.getInstance().setGameState(GameState.moveBack)
        ))));
    }

    private void enemyAttack(MainCharacter character) {
        int attackValue = getAttackValueWithCriticalRules(enemy.getAttackValue());
        if(attackValue == 0){
            setFightNewspaper(enemy.toString() + " ne peux pas vous attaquer");
            return;
        }

        int health = character.getHealth() - attackValue;
        if (health < 0) {
            health = 0;
        }
        character.setHealth(health);
        setFightNewspaper(enemy.toString() + " vous attaque de " + attackValue);
        setFightNewspaper("Votre santé est à " + character.getHealth());
    }

    private void characterAttack(MainCharacter character, Integer equipementNumber) {
        int attackValue = checkAttackValue(character, equipementNumber);

        if(attackValue == 0){
            setFightNewspaper("Vous ne pouvez pas attaquer");
            return;
        }

        int health = enemy.getCharacterHealthValue() - attackValue;
        setFightNewspaper("Vous attaquez " + enemy.toString() + " avec une force de " + attackValue);
        if (health > 0) {
            enemy.setCharacterHealth(health);
            setFightNewspaper("La santé de "+ enemy.toString() + " passe à " + enemy.getCharacterHealthValue() );
        }
        else {
            enemy.setCharacterHealth(0);
            character.increaseExperience(enemy.getExperience());
            setFightNewspaper("Votre experience passe à " + character.getExperience());
        }
    }

    private Integer checkAttackValue(MainCharacter character, Integer equipementNumber) {
        int attackValue = character.getAttackValue();

        if(equipementNumber != 0 ) {
            attackValue += getWeaponValue(character, equipementNumber);
        }

        attackValue = getAttackValueWithCriticalRules(attackValue);

        if(character.getBoostAttackValue()){
            attackValue = attackValue * 2;
            character.resetBoostAttack();
        }
        return attackValue;
    }

    private int getWeaponValue(MainCharacter character, Integer equipementNumber) {
        OffensiveEquipement equipement = character.getOffensiveEquipement(equipementNumber);
        int attackValue = equipement.getValue();
        setFightNewspaper("Vous prenez " + equipement);

        if (enemy instanceof Dragon && equipement instanceof Bow) {
            attackValue = attackValue + 2;
            setFightNewspaper("Coup de chance, vous avez un arc, +2 d'attaque contre les dragons ");
        }

        if (enemy instanceof EvilSpirits && equipement instanceof Invisibility) {
            setFightNewspaper("Coup de chance, vous avez un sort d'invisibilité, +3 d'attaque contre les mauvais esprits ");
            attackValue = attackValue + 3;
        }
        return attackValue;
    }

    private Integer getAttackValueWithCriticalRules(Integer attackValue) {
        Dice dice = new Dice();
        Integer number = dice.getRoll(20);
        return switch (number) {
            case 1 -> {
                setFightNewspaper("Vous obtenez " + number + " avec le dé à 20 faces. Dommage");
                yield 0;
            }
            case 20 -> {
                setFightNewspaper("Vous obtenez " + number + " avec le dé à 20 faces. Super !");
                yield attackValue + 2;
            }
            default -> {
                setFightNewspaper("Vous obtenez " + number + " avec le dé à 20 faces");
                yield attackValue;
            }
        };
    }
}
