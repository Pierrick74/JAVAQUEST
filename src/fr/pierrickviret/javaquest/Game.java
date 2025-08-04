package fr.pierrickviret.javaquest;

import com.google.gson.Gson;
import fr.pierrickviret.javaquest.board.Board;
import fr.pierrickviret.javaquest.board.Case.Case;
import fr.pierrickviret.javaquest.board.Case.EnemyCase;
import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.character.Warrior;
import fr.pierrickviret.javaquest.character.Wizard;
import fr.pierrickviret.javaquest.commun.CharacterType;
import fr.pierrickviret.javaquest.commun.DefensiveEquipmentType;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.commun.OffensiveEquipmentType;
import fr.pierrickviret.javaquest.commun.exception.OutOfBoardException;
import fr.pierrickviret.javaquest.db.GsonConfig;
import fr.pierrickviret.javaquest.db.SQLRepository;

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
    static String mainMenuInformation = "\nVeuillez choisir:\n1. création du personnage\n2. Démarrer la partie\n3. Quitter le jeu";
    static String createCharacterInformation = "Quel personnage voulez-vous créer ?\n1. Combattant\n2. Mage\n3. Quitter le jeu";
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
     * Personnage principal du jeu
     */
    MainCharacter character;
    /**
     * Plateau pour ce jeu
     */
    Board board;
    /**
     * Permet de lancer le dé
     */
    Dice dice;
    /**
     * Représente le joueur
     */
    Player player;

    /**
     * Represent l'état du jeu
     * @see GameState
     */
    GameState gameState;

    /**
     * Permet de gere la BDD
     */
    SQLRepository mysql;

    /**
     * Permet d'initialiser les dépendances
     */
    Game(){
        this.gameState = GameState.begin;
        this.dice = new Dice();
        this.mysql = SQLRepository.getInstance();
    }

    //public

    /**
     * Fonction principale de la class
     * gère le jeu en déroulant les différents états du jeu.
     * @see GameState
     */
    public void start() {
        while (gameState != GameState.exitGame) {
            switch (gameState) {
                case begin:
                    Menu.getInstance().showInformation(welcomeInformation);
                    gameState = GameState.waitingInformation;
                    break;

                case waitingInformation:
                    manageWaitingInformation();
                    break;

                case createCharacter:
                    if( character == null) {
                        createCharacter();
                    } else {
                        if(isModifyCharacter()){
                            modifyCharacter();
                        }
                    }
                    gameState = GameState.waitingInformation;
                    break;

                case startGame:
                    if ( character == null) {
                        gameState = GameState.createCharacter;
                        Menu.getInstance().showInformation(mustCreateCharacter);
                        break;
                    } else {
                        resetCharacter();
                    }
                    initGame();
                    gameState = GameState.playerTurn;
                    break;

                case playerTurn:
                    changePlayerPosition();
                    if(character.getHealth() <= 0) {
                        gameState = GameState.gameOver;
                    }
                    break;

                case finishGame:
                    Menu.getInstance().showInformation(finishGame);
                    gameState = GameState.waitingInformation;
                    break;

                case gameOver:
                    Menu.getInstance().showInformation(gameOver);
                    gameState = GameState.waitingInformation;
                    break;

                default:
                    break;
            }
        }
        exitGame();
    }

    //private

    private void initGame(){
        board = new Board();
        Integer id = mysql.saveBoard(board);
        board.setId(id);

        //TODO for test
        Gson gson = GsonConfig.getInstance();
        String boardJson = gson.toJson(board);
        mysql.saveBoard(board);
        board = gson.fromJson(boardJson, Board.class);

        player = new Player();
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
     * Récupère les informations du joueur et appel la création du personnage.
     * @see MainCharacter
     * @see Menu
     */
    private void createCharacter(){
        Menu.getInstance().showInformation(createCharacterInformation);
        int choice = Menu.getInstance().listenResultBetween(1,3);
        if(choice == 3 ) {exitGame();}
        CharacterType type = choice == 1 ? CharacterType.Warrior : CharacterType.Wizard;

        Menu.getInstance().showInformation(askForCharacterName);
        String name = Menu.getInstance().listenString();

        createCharacter(type, name);
        showCharacterCreated();
    }

    private void modifyCharacter(){

        MainCharacter oldCharacter = character;

        // change type
        String information = character.getType() == CharacterType.Warrior ? askIfUserWantToChangeCharacterTypeToWizard : askIfUserWantToChangeCharacterTypeToWarrior;
        Menu.getInstance().showInformation(information);
        int choice = Menu.getInstance().listenResultBetween(1,3);
        if(choice == 3 ) {exitGame();}
        if(choice == 2 ) {
            character = character.getType() == CharacterType.Warrior ? new Wizard(oldCharacter.getName()) : new Warrior(oldCharacter.getName());
            character.setID(oldCharacter.getID());
        }

        //change name
        Menu.getInstance().showInformation(askIfUserWantToChangeName);
        choice = Menu.getInstance().listenResultBetween(1,3);
        if(choice == 3 ) {exitGame();}
        if(choice == 2 ) {
            Menu.getInstance().showInformation(askForCharacterName);
            String name = Menu.getInstance().listenString();
            character.setName(name);
        }

        showCharacterCreated();
        //save in database
        mysql.editHero(character);
    }

    /**
     * Création du personnage.
     * @param type indique le type du personnage
     * @param name indique le nom du personnage
     */
    private void createCharacter( CharacterType type, String name ){
        switch (type) {
            case Warrior:
                character = new Warrior(name);
                break;
            case Wizard:
                character = new Wizard(name);
                break;
        }
        int id = mysql.createHeroes(name, type, character.getAttackValue() , character.getHealth(), OffensiveEquipmentType.empty, DefensiveEquipmentType.empty);

        character.setID(id);
    }

    /**
     * Demande à l'utilisateur s'il veut modifier le personnage qu'il a créé.
     * @return boolean  si il veut modifier
     */
    private Boolean isModifyCharacter() {
        showCharacterCreated();
        Menu.getInstance().showInformation(askForCharacterModification);
        return Menu.getInstance().listenResultBetween(1,2) == 2;
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
     * @see Player
     * @see Board
     */
    private void changePlayerPosition() {
        Menu.getInstance().showInformation("\nAppuyer sur entrée pour lancer le dé");
        Menu.getInstance().listenString();
        Integer turnDice = dice.getRoll(6);
        Menu.getInstance().showInformation("\n"+ rollDice + turnDice.toString() + "\n");

        try {
            movePlayer(player.getPosition() + turnDice);
        } catch (OutOfBoardException | Exception e) {
            Menu.getInstance().showInformation(e.getMessage());
            int offset = board.getSize() - (player.getPosition() + turnDice );
            player.setPosition(board.getSize() + offset);
        }

        if(Objects.equals(player.getPosition(), board.getSize())) {
            gameState = GameState.finishGame;
            return;
        }

        Menu.getInstance().showInformation(player.toString());
        checkCase();
    }

    /**
     * Regarde la case sur laquelle le joueur est arrivé et effectue l'action
     */
    private void checkCase() {
        Case currentCase = board.getCase(player.getPosition());
        Menu.getInstance().showInformation("\n"+ currentCase.toString());
        if( currentCase instanceof EnemyCase) {
            StartFight(currentCase);
        } else {
            Boolean stateCase = currentCase.interact(character);
            if (!stateCase) {
                board.setCaseToEmpty(player.getPosition());
            }
        }
    }

    private void StartFight(Case currentCase) {
        Menu.getInstance().showInformation(askForFight);
        int result = Menu.getInstance().listenResultBetween(1,3);
        switch (result) {
            case 1:
                boolean haveFighter = fightWithEnemy(currentCase);
                if (haveFighter) {
                    StartFight(currentCase);
                }
                break;
            case 2:
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
            board.setCaseToEmpty(player.getPosition());
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
        player.setPosition(player.getPosition() - number);
        Menu.getInstance().showInformation("\nVous reculez de "+ number + " cases\n" + player.toString());
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
        player.setPosition(position);
    }

    /**
     *
     */
    private void manageWaitingInformation() {
        Menu.getInstance().showInformation(mainMenuInformation);
        switch (Menu.getInstance().listenResultBetween(1,3)) {
            case 1:
                gameState = GameState.createCharacter;
                break;
            case 2:
                gameState = GameState.startGame;
                break;
            case 3:
                gameState = GameState.exitGame;
                break;
        }
    }
}
