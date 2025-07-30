package fr.pierrickviret.javaquest;

import fr.pierrickviret.javaquest.character.MainCharacter;
import static java.lang.System.exit;

public class Game {
    // information to display
    static String [] welcomeInformation = {"Bienvenue dans le jeu", "JAVAQUEST"};
    static String [] mainMenuInformation = {"\n","veuillez choisir:", "1. création du personnage","2. Démarrer la partie","3. Quitter le jeu"};
    static String[] createCharacterInformation = {"Quel personnage voulez-vous créer ?", "1. Combattant", "2. Mage", "3. Quitter le jeu"};
    static String [] askForCharacterName = {"Quel est le nom de votre Personnage"};
    static String [] showCharacterCreated = {"\n","Voici le personnage crée"};
    static String [] askForCharacterModification = {"\n","Voulez vous modifier le personnage","1. utiliser le personnage","2. Modifier le personnage"};

    //dependency
    Menu menu;
    MainCharacter character;

    //variable
    private enum GameState {
        begin,
        waitingInformation,
        createCharacter,
        finishGame,
        startGame,
    }

    GameState gameState;

    //init
    Game(){
        this.menu = new Menu();
        this.gameState = GameState.begin;
    }

    //public
    public void start() {
        while (gameState != GameState.finishGame) {
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
                        case 2:
                            gameState = GameState.startGame;
                        case 3:
                            gameState = GameState.finishGame;
                    }
                    break;

               case createCharacter:
                   if( character == null) {
                       createCharacter();
                       gameState = GameState.waitingInformation;
                   } else {
                       if(isModifyCharacter()){
                           this.character = null;
                       } else {
                           gameState = GameState.waitingInformation;
                       }
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
        menu.showInformation(new String[]{"au revoir"});
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
}
