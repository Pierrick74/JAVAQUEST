package fr.pierrickviret.javaquest;

import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.commun.fr.pierrickviret.javaquest.Board;

import static java.lang.System.exit;

public class Game {
    // information to display
    static String welcomeInformation = "Bienvenue dans le jeu\nJAVAQUEST";
    static String mainMenuInformation = "\nVeuillez choisir:\n1. création du personnage\n2. Démarrer la partie\n3. Quitter le jeu";
    static String createCharacterInformation = "Quel personnage voulez-vous créer ?\n1. Combattant\n2. Mage\n3. Quitter le jeu";
    static String askForCharacterName = "Quel est le nom de votre Personnage";
    static String showCharacterCreated = "\nVoici le personnage crée";
    static String askForCharacterModification = "\nVoulez vous modifier le personnage\n1. utiliser le personnage\n2. Modifier le personnage";
    static String finishGame = "\nVoulez avez fini le jeu, Bravo !\nVoulez vous:\n1. commencer un nouveau jeu en gardant le personage\n2. commencer un nouveau jeu avec un nouveau personnage\n3. Quitter le jeu";
    static String rollDice = "Vous avez lancé le dé : ";
    static String endGame = "Au revoir";

    //dependency
    Menu menu;
    MainCharacter character;
    Board board;
    Dice dice;
    Player player;

    //variable
    GameState gameState;

    //init
    Game(){
        this.menu = new Menu();
        this.gameState = GameState.begin;
        this.dice = new Dice();
        this.board = new Board();
    }

    //public
    public void start() {
        while (gameState != GameState.exitGame) {
            switch (gameState) {
                case begin:
                    menu.showInformation(welcomeInformation);
                    gameState = GameState.waitingInformation;
                    break;

                case waitingInformation:
                    menu.showInformation(mainMenuInformation);
                    switch (menu.listenResultBetween(1,3)) {
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

                case createCharacter:
                    if( character == null) {
                        createCharacter();
                        gameState = gameState == GameState.waitingInformation ? GameState.waitingInformation : GameState.startGame;
                    } else {
                        if(isModifyCharacter()){
                            this.character = null;
                        } else {
                            gameState = GameState.waitingInformation;
                        }
                    }
                    break;

                case startGame:
                    player = new Player();
                    gameState = GameState.playerTurn;
                    break;

                case playerTurn:
                    movePlayer();
                    if(player.getPosition() >= 64) {
                        gameState = GameState.finishGame;
                    } else {
                        System.out.println(player);
                    }
                    break;

                case finishGame:
                    menu.showInformation(finishGame);
                    switch (menu.listenResultBetween(1,3)) {
                        case 1:
                            gameState = GameState.startGame;
                        case 2:
                            character = null;
                            gameState = GameState.createCharacter;
                        case 3:
                            gameState = GameState.exitGame;
                    }
                    break;
                default:
                    break;
            }
        }
        exitGame();
    }

    //private
    private void exitGame() {
        menu.showInformation(endGame);
        exit(0);
    }

    private void createCharacter(){
        menu.showInformation(createCharacterInformation);
        int choice = menu.listenResultBetween(1,3);
        if(choice == 3 ) {exitGame();}
        MainCharacter.characterType type = choice == 1 ? MainCharacter.characterType.Warrior : MainCharacter.characterType.Wizard;

        menu.showInformation(askForCharacterName);
        String name = menu.listenString();

        this.character = new MainCharacter(type, name);
        showCharacterCreated();
    }

    private Boolean isModifyCharacter() {
        showCharacterCreated();
        menu.showInformation(askForCharacterModification);
        return menu.listenResultBetween(1,2) == 2;
    }

    private void showCharacterCreated() {
        menu.showInformation(showCharacterCreated);
        System.out.println(character);
    }

    private void movePlayer() {
        Integer turn = dice.getRoll();
        player.addPosition(turn);

        menu.showInformation(rollDice + turn.toString());
    }
}
