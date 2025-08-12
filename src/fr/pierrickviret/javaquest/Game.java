package fr.pierrickviret.javaquest;

import fr.pierrickviret.javaquest.board.Board;
import fr.pierrickviret.javaquest.board.Case.*;
import fr.pierrickviret.javaquest.character.*;
import fr.pierrickviret.javaquest.commun.CharacterType;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.commun.exception.OutOfBoardException;
import fr.pierrickviret.javaquest.db.SQLRepository;
import fr.pierrickviret.javaquest.javafx.*;
import fr.pierrickviret.javaquest.javafx.Case.EmptyCaseView;
import fr.pierrickviret.javaquest.javafx.Case.EnemyCaseView;
import fr.pierrickviret.javaquest.javafx.Case.PotionCaseView;
import fr.pierrickviret.javaquest.javafx.Case.WeaponCaseView;
import fr.pierrickviret.javaquest.javafx.Game.RunAwayView;
import fr.pierrickviret.javaquest.javafx.Game.addEquipementView;
import fr.pierrickviret.javaquest.javafx.selectGame.startGameView;
import fr.pierrickviret.javaquest.javafx.createCharacter.*;
import fr.pierrickviret.javaquest.javafx.selectGame.AskForSaveOrNewGameView;
import javafx.application.Platform;

import java.util.Objects;
import java.util.Random;

import static java.lang.System.exit;

/**
 *<h2> class Game</h2>
 * <p> Class qui contient toute la logique du jeu
 * le joueur va se voir proposer de créer un personnage : Combatant ou magicien,
 * il pourra ensuite commencer la partie.
 * Le dé sera lancé et le joueur avancera sur un plateau.</p>
 *
 * <p> À la fin, il pourra rejouer ou sortir du jeu
 * S'il veut rejouer, il pourra refaire une partie avec le meme personnage ou en recréer un autre.</p>
 * @author Pierrick
 * @see Board
 * @version 1.0
 */

public class Game {
    // information to display
    static String welcomeInformation = "Bienvenue dans le jeu\nJAVA QUEST";
    static String showCharacterCreated = "\nVoici le personnage crée";
    static String finishGame = "\nVous avez fini le jeu, Bravo !";
    static String endGame = "Au revoir";
    static String gameOver = "\nVous êtes mort, Game Over";

    //dependency
    /**
     * Plateau pour ce jeu
     */
    Board board;
    /**
     * Permet de lancer le dé
     */
    Dice dice;
    int diceValue;

    MainCharacter character;
    CharacterType selectedType;

    /**
     * Represent l'état du jeu
     * @see GameState
     */
    private GameState gameState;
    private GameState oldGameState;
    private Boolean isSomethingToShow = true;

    public void setGameState(GameState gameState) {
        this.oldGameState = this.gameState;
        this.gameState = gameState;
        isSomethingToShow = true;
    }

    private static Game instance;

    public static Game getInstance() {
        return instance==null?instance = new Game():instance;
    }
    /**
     * Permet d'initialiser les dépendances
     */
    private Game(){
        this.gameState = GameState.begin;
        this.oldGameState = GameState.waitingInformation;
        this.dice = new Dice();
    }

    //public

    /**
     * Fonction principale de la class
     * gère le jeu en déroulant les différents états du jeu.
     * @see GameState
     */
    public void start() {
        while (gameState != GameState.exitGame) {
            if (isSomethingToShow) {
                switch (gameState) {
                    case begin:
                        Platform.runLater(() -> StageRepository.getInstance().replaceScene(new MainView()));
                        Menu.getInstance().showInformation(welcomeInformation);
                        isSomethingToShow = false;
                        break;

                    case waitingInformation:
                        Platform.runLater(() -> StageRepository.getInstance().replaceScene(new MainMenuView()));
                        isSomethingToShow = false;
                        break;

                    case checkIfCharacterIsAlreadyCreated:
                        if (character == null) {
                            Platform.runLater(() -> StageRepository.getInstance().replaceScene(new CreateCharacterMenuView()));
                        } else {
                            Platform.runLater(() -> StageRepository.getInstance().replaceScene(new AskIfUserWantToChangeCharacterView()));
                        }
                        isSomethingToShow = false;
                        break;

                    case createCharacter:
                        Platform.runLater(() -> StageRepository.getInstance().replaceScene(new NameOfCharacterView(selectedType)));
                        isSomethingToShow = false;
                        break;

                    case showCharacter:
                        Platform.runLater(() -> StageRepository.getInstance().replaceScene(new showCharacterView(character)));
                        isSomethingToShow = false;
                        break;

                    case modifyCharacter:
                        Platform.runLater(() -> StageRepository.getInstance().replaceScene(new ModifyCharacterView(character)));
                        isSomethingToShow = false;
                        break;

                    case selectMenu:
                        if (SQLRepository.getInstance().hasBoard()) {
                            Platform.runLater(() -> StageRepository.getInstance().replaceScene(new AskForSaveOrNewGameView()));
                            isSomethingToShow = false;
                            break;
                        }
                        setGameState(GameState.startGame);
                        break;

                    case uploadGame:
                        board = SQLRepository.getInstance().getBoard();
                        character = SQLRepository.getInstance().getCharacter(1);
                        setGameState(GameState.launchDice);
                        break;

                    case startGame:
                        if (character == null) {
                            setGameState(GameState.checkIfCharacterIsAlreadyCreated);
                            break;
                        } else {
                            resetCharacter();
                        }
                        Platform.runLater(() -> StageRepository.getInstance().replaceScene(new startGameView()));
                        isSomethingToShow = false;
                        break;

                    case launchDice:
                        Platform.runLater(() -> StageRepository.getInstance().replaceScene(new CardDiceView(() -> Game.getInstance().setGameState(GameState.playerTurn)) ));
                        isSomethingToShow = false;
                        break;

                    case playerTurn:
                        changePlayerPosition();
                        isSomethingToShow = false;
                        break;

                    case moveBack:
                        if(board.getCase(character.getPosition()) instanceof EnemyCase){
                            Game.getInstance().movePlayerBackward((EnemyCase) board.getCase(character.getPosition()));
                        }
                        isSomethingToShow = false;
                        break;

                    case fight:
                        startFight();
                        isSomethingToShow = false;
                        break;

                    case finishGame:

                        Menu.getInstance().showInformation(finishGame);
                        break;

                    case gameOver:
                        Menu.getInstance().showInformation(gameOver);
                        setGameState(GameState.waitingInformation);
                        break;

                    default:
                        isSomethingToShow = false;
                        break;
                }
            }
            // éviter de bloquer le CPU
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        exitGame();
    }

    public void SetSelectedCharacter(CharacterType type) {
        this.selectedType = type;
    }

    public void deleteCharacter() {
        this.character = null;
    }

    public void initBoard(int difficultyLevel) {
        board = new Board(difficultyLevel);
    }

    public MainCharacter getCharacter() {
        return character;
    }

    public int rollDice(){
        diceValue = dice.getRoll(6);
        return diceValue;
    }

    public Board getBoard() {
        return board;
    }

    private void resetCharacter() {
        character.resetCharacter();
    }

    /**
     * Gere la sortie du jeu
     * affiche un message de sortie
     */
    private void exitGame() {
        Menu.getInstance().showInformation(endGame);
        exit(0);
    }

    /**
     * Création du personnage.
     * @param type indique le type du personnage
     * @param name indique le nom du personnage
     */
    public void createCharacter( CharacterType type, String name ){
        switch (type) {
            case Warrior:
                character = new Warrior(name, 1);
                break;
            case Wizard:
                character = new Wizard(name, 1);
                break;
        }
        SQLRepository.getInstance().save(character);
    }

    /**
     * Permet d'afficher les informations du personnage crée
     * @see MainCharacter
     */
    private void showCharacterCreated() {
        Menu.getInstance().showInformation(showCharacterCreated);
        Menu.getInstance().showInformation(character.toString());
        Menu.getInstance().showInformation("");
    }

    /**
     * Permet d'avancer le joueur de la valeur du lancé de dé
     * Si la position dépasse la limite du Board, le joueur recule
     * @see Dice
     * @see MainCharacter
     * @see Board
     */
    private void changePlayerPosition() {
        Boolean isStepBack = false;
        try {
            movePlayer(character.getPosition() + diceValue);
        } catch (OutOfBoardException | Exception e) {
            isStepBack =  true;
            int offset = board.getSize() - (character.getPosition() + diceValue );
            character.setPosition(board.getSize() + offset);
        }

        if(Objects.equals(character.getPosition(), board.getSize())) {
            setGameState(GameState.finishGame);
            return;
        }
        checkCase(isStepBack);
    }

    /**
     * Regarde la case sur laquelle le joueur est arrivé et effectue l'action
     */
    private void checkCase(Boolean isStepBack) {
        Case currentCase = board.getCase(character.getPosition());
        if (currentCase instanceof EmptyCase) {
            Platform.runLater(() -> StageRepository.getInstance().replaceScene(new CaseView(character, isStepBack, new EmptyCaseView(()-> Game.getInstance().setGameState(GameState.launchDice)))));
        }

        if( currentCase instanceof EnemyCase) {
            Runnable fightAction = () -> {
                Game.getInstance().setGameState(GameState.fight);
            };

            Runnable runAwayAction = () -> {
                Game.getInstance().setGameState(GameState.moveBack);
            };

            Platform.runLater(() -> StageRepository.getInstance().replaceScene(new CaseView(character, isStepBack, new EnemyCaseView((EnemyCase) currentCase,fightAction, runAwayAction))));
        }

        if( currentCase instanceof PotionCase) {
                Platform.runLater(() -> StageRepository.getInstance().replaceScene(new CaseView(character, isStepBack, new PotionCaseView((PotionCase) currentCase, () -> Game.getInstance().setGameState(GameState.launchDice)))));
        }

        if( currentCase instanceof SpellCase) {
            Platform.runLater(() -> StageRepository.getInstance().replaceScene(new CaseView(character, isStepBack, new EmptyCaseView(()-> Game.getInstance().setGameState(GameState.launchDice)))));
        }

        if (currentCase instanceof WeaponCase || currentCase instanceof SpellCase) {
            Platform.runLater(() -> StageRepository.getInstance().replaceScene(new CaseView(character, isStepBack, new WeaponCaseView(currentCase))));
        }
    }


    public Boolean isCanTakeWeapon(Case currentCase) {
        if(currentCase instanceof WeaponCase && character.getType() == CharacterType.Warrior) {return true;}
        if(currentCase instanceof SpellCase && character.getType() == CharacterType.Wizard) {return true;}
        return false;
    }

    private void startFight() {
        Case currentCase = board.getCase(character.getPosition());
        if(currentCase instanceof EnemyCase) {
            fightWithEnemy((EnemyCase)   currentCase);
        }
    }

    /**
     * Permet de combattre avec un enemy
     * @param currentCase la case actuel
     * @return si un combat peut encore avoir lieux
     */
    private void fightWithEnemy(EnemyCase currentCase) {
        currentCase.interact(character);

        if(character.getHealth() <= 0) {
            setActualyCaseToEmpty();
        }
    }

    /**
     * Permet de faire reculer le joueur d’un nombre de cases aléatoires (entre 1 et 6)
     * lors d'une fuite de combat
     */
    private void movePlayerBackward(EnemyCase currentCase) {
        this.character.decreaseExperience((currentCase).getEnemieExperience());

        Random rand = new Random();
        int number = rand.nextInt(1, 7);
        if(character.getPosition() - number > 0) {
            character.setPosition(character.getPosition() - number);
        } else {
            character.setPosition(0);
        }
        Platform.runLater(() -> StageRepository.getInstance().replaceScene(new RunAwayView(()->Game.getInstance().setGameState(GameState.launchDice), number)));
    }

    /**
     * Avance le joueur à la position donnée
     * @param position {@code int} position du joueur
     * @throws OutOfBoardException si le joueur dépasse la valeur max du board
     */
    private void movePlayer(Integer position) throws OutOfBoardException {
        if(position > board.getSize()) {
            throw new OutOfBoardException("Vous ne pouvez pas avancer plus que de case du plateau\nVous reculez\n");
        }
        character.setPosition(position);
    }

    public String getInteractionWithPotion(PotionCase currentCase) {
         currentCase.interact(character);
         String result = currentCase.getDescriptionOfInteraction();
         return result;
    }

    public void getInteractionWithWeapon(Case currentCase, Integer choice) {
        Boolean stateCase = null;
        if (currentCase instanceof WeaponCase) {
            stateCase = ((WeaponCase) currentCase).interactWithCase(character, choice);
        } else if (currentCase instanceof SpellCase) {
            stateCase = ((SpellCase) currentCase).interactWithCase(character, choice);
        }

        if (!stateCase) {
            board.setCaseToEmpty(character.getPosition());
        }

        if(choice == 3) {
            setGameState(GameState.launchDice);
        } else {
            Platform.runLater(() -> StageRepository.getInstance().replaceScene(new addEquipementView(() -> Game.getInstance().setGameState(GameState.launchDice))));
        }
    }

    public void setActualyCaseToEmpty() {
        board.setCaseToEmpty(character.getPosition());
    }
}
