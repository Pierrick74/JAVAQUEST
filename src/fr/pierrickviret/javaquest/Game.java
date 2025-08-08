package fr.pierrickviret.javaquest;

import fr.pierrickviret.javaquest.board.Board;
import fr.pierrickviret.javaquest.board.Case.Case;
import fr.pierrickviret.javaquest.board.Case.EnemyCase;
import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.character.Warrior;
import fr.pierrickviret.javaquest.character.Wizard;
import fr.pierrickviret.javaquest.commun.CharacterType;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.commun.exception.OutOfBoardException;
import fr.pierrickviret.javaquest.db.SQLRepository;
import fr.pierrickviret.javaquest.javafx.*;
import fr.pierrickviret.javaquest.javafx.Game.startGameView;
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
    static String askForCharacterName = "Quel est le nom de votre Personnage";
    static String showCharacterCreated = "\nVoici le personnage crée";
    static String askForCharacterModification = "\nVoulez vous modifier le personnage\n1. utiliser le personnage\n2. Modifier le personnage";
    static String finishGame = "\nVous avez fini le jeu, Bravo !";
    static String rollDice = "Vous avez lancé le dé : ";
    static String endGame = "Au revoir";
    static String askIfUserWantToChangeCharacterTypeToWarrior = "\nVoulez vous modifier le type du personnage\n1. utiliser le type actuel\n2. Transformer en Combatant\n3. Quitter le jeu";
    static String askIfUserWantToChangeCharacterTypeToWizard = "\nVoulez vous modifier le type du personnage\n1. utiliser le type actuel\n2. Transformer en Magicien\n3. Quitter le jeu";
    static String askIfUserWantToChangeName = "\nVoulez vous modifier le nom du personnage\n1. utiliser le nom actuel\n2. Changer le nom\n3. Quitter le jeu";
    static String gameOver = "\nVous êtes mort, Game Over";
    static String mustCreateCharacter = "\nVous devez créer un personnage avant";
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
                        setGameState(GameState.startGame);
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

                    case playerTurn:
                        Platform.runLater(() -> StageRepository.getInstance().replaceScene(new MainMenuView()));
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
        Menu.getInstance().showInformation("\nAppuyer sur entrée pour lancer le dé");
        Menu.getInstance().listenString();
        Integer turnDice = dice.getRoll(6);
        Menu.getInstance().showInformation("\n"+ rollDice + turnDice.toString() + "\n");

        try {
            movePlayer(character.getPosition() + turnDice);
        } catch (OutOfBoardException | Exception e) {
            Menu.getInstance().showInformation(e.getMessage());
            int offset = board.getSize() - (character.getPosition() + turnDice );
            character.setPosition(board.getSize() + offset);
        }

        if(Objects.equals(character.getPosition(), board.getSize())) {
            setGameState(GameState.finishGame);
            return;
        }

        Menu.getInstance().showInformation(character.positionToString());
        checkCase();
    }

    /**
     * Regarde la case sur laquelle le joueur est arrivé et effectue l'action
     */
    private void checkCase() {
        Case currentCase = board.getCase(character.getPosition());
        Menu.getInstance().showInformation("\n"+ currentCase.toString());
        if( currentCase instanceof EnemyCase) {
            startFight((EnemyCase) currentCase);
        } else {
            Boolean stateCase = currentCase.interact(character);
            if (!stateCase) {
                board.setCaseToEmpty(character.getPosition());
            }
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
                character.decreaseExperience(currentCase.getEnemieExperience());
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

}
