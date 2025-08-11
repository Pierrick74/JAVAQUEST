package fr.pierrickviret.javaquest;

import fr.pierrickviret.javaquest.board.Board;
import fr.pierrickviret.javaquest.board.Case.*;
import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.character.Warrior;
import fr.pierrickviret.javaquest.character.Wizard;
import fr.pierrickviret.javaquest.commun.CharacterType;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.commun.exception.OutOfBoardException;
import fr.pierrickviret.javaquest.db.SQLRepository;
import fr.pierrickviret.javaquest.javafx.*;
import fr.pierrickviret.javaquest.javafx.Case.EmptyCaseView;
import fr.pierrickviret.javaquest.javafx.Case.EnemyCaseView;
import fr.pierrickviret.javaquest.javafx.Case.PotionCaseView;
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
    static String askForFight = "\nQue voulez vous faire?\n1. Attaquer\n2. Fuir\n3. Quitter le jeu";

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
                        if (character.getHealth() <= 0) {
                            setGameState(GameState.gameOver);
                        }
                        isSomethingToShow = false;
                        break;

                    case finishGame:
                        Menu.getInstance().showInformation(finishGame);
                        setGameState(GameState.waitingInformation);
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
                Menu.getInstance().showInformation("fight");
            };

            Runnable runAwayAction = () -> {
                Menu.getInstance().showInformation("runAway");
            };

            Platform.runLater(() -> StageRepository.getInstance().replaceScene(new CaseView(character, isStepBack, new EnemyCaseView((EnemyCase) currentCase,fightAction, runAwayAction))));
        }

        if( currentCase instanceof PotionCase) {
                Platform.runLater(() -> StageRepository.getInstance().replaceScene(new CaseView(character, isStepBack, new PotionCaseView((PotionCase) currentCase, () -> Game.getInstance().setGameState(GameState.launchDice)))));
        }

        if( currentCase instanceof SpellCase || currentCase instanceof WeaponCase) {
            Platform.runLater(() -> StageRepository.getInstance().replaceScene(new CaseView(character, isStepBack, new EmptyCaseView(()-> Game.getInstance().setGameState(GameState.launchDice)))));
        }
    }

    private void startFight(EnemyCase currentCase) {
        Menu.getInstance().showInformation(askForFight);
        int result = Menu.getInstance().listenResultBetween(1,3);
        switch (result) {
            case 1:
                boolean haveFighter = fightWithEnemy(currentCase);
                if (haveFighter) {
                    startFight(currentCase);
                }
                break;
            case 2:
                this.character.decreaseExperience(((EnemyCase) currentCase).getEnemieExperience());
                movePlayerBackward();
                break;
            case 3:
                exitGame();
                break;
        }
    }

    /**
     * Permet de combattre avec un enemy
     * @param currentCase la case actuel
     * @return si un combat peut encore avoir lieux
     */
    private boolean fightWithEnemy(Case currentCase) {
        Boolean stateCase = currentCase.interact(character);
        if (!stateCase) {
            board.setCaseToEmpty(character.getPosition());
        }
        if(character.getHealth() <= 0) {
            stateCase = false;
        }
        return stateCase;
    }

    /**
     * Permet de faire reculer le joueur d’un nombre de cases aléatoires (entre 1 et 6)
     * lors d'une fuite de combat
     */
    private void movePlayerBackward(){
        Random rand = new Random();
        int number = rand.nextInt(1, 7);
        character.setPosition(character.getPosition() - number);
        Menu.getInstance().showInformation("\nVous reculez de "+ number + " cases\n" + character.positionToString());
        Menu.getInstance().showInformation("Votre experience passe à " + character.getExperience());
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
         Boolean stateCase = currentCase.interact(character);
         String result = currentCase.getDescriptionOfInteraction();
         if (!stateCase) {
             board.setCaseToEmpty(character.getPosition());
         }
         return result;
    }

}
