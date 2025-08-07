package fr.pierrickviret.javaquest.javafx;

import fr.pierrickviret.javaquest.javafx.composants.bigMenu;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainMenuView extends VBox {

   public MainMenuView() {
       super(15);

       HBox creation = new bigMenu("Création du personnage", "fr/pierrickviret/javaquest/javafx/assets/createCharactere.PNG", new createCharacterMenu());
       HBox game = new bigMenu("Démarer le jeu", "fr/pierrickviret/javaquest/javafx/assets/startGame.PNG", new MainView(10) );
       this.getChildren().addAll(creation, game);
       this.setAlignment(Pos.CENTER);
       this.setStyle(
               "-fx-background-color: #3caf3c; " +
                       "-fx-alignment: center; "
       );
   }
}
