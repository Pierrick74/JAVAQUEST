package fr.pierrickviret.javaquest;

import fr.pierrickviret.javaquest.character.MainCharacter;
import static java.lang.System.exit;

public class Game {
    // information to display
    static String [] welcomeInformation = {"Bienvenue dans le jeu", "JAVAQUEST"};
    static String [] mainMenuInformation = {"veuillez choisir:", "1. création du personnage","2. Quitter le jeu"};
    static String[] createCharacterInformation = {"Quel personnage voulez-vous créer ?", "1. Combattant", "2. Mage", "3. Quitter le jeu"};
    static String [] askForCharacterName = {"Quel est le nom de votre Personnage"};

    //dependency
    Menu menu;
    MainCharacter character;

    //variable

    //init
    Game(){
        this.menu = new Menu();
    }

    //public
    public void start() {
        startedMenu();
        createCharacter();
    }

    //private
    private void exitGame() {
        menu.showInformation(new String[]{"au revoir"});
        exit(0);
    }

    private void startedMenu() {
        menu.showInformation(welcomeInformation);
        menu.showInformation(mainMenuInformation);
        if(menu.listenResultBetween(1,2) == 2) {
            exitGame();
        }
    }

    private void createCharacter(){
        menu.showInformation(createCharacterInformation);
        int choice = menu.listenResultBetween(1,3);
        if(choice == 3 ) {exitGame();}
        MainCharacter.characterType type = choice == 1 ? MainCharacter.characterType.Warrior : MainCharacter.characterType.Wizard;

        menu.showInformation(askForCharacterName);
        String name = menu.listenString();

        this.character = new MainCharacter(type, name);
        System.out.println(character);
    }
}
