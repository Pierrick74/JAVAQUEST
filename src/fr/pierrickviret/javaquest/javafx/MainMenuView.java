package fr.pierrickviret.javaquest.javafx;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import fr.pierrickviret.javaquest.javafx.composants.BigMenu;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainMenuView extends VBox {

   public MainMenuView() {
       super(15);

       Label titre = new Label("Que voulez vous faire ?");
       titre.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));
       titre.setFont(Font.font("Almendra", FontWeight.BOLD, 40.0));

       BigMenu creation = new BigMenu("Création du personnage", "fr/pierrickviret/javaquest/javafx/assets/menu/createCharactere.PNG", () -> Game.getInstance().setGameState(GameState.checkIfCharacterIsAlreadyCreated));
       BigMenu game = new BigMenu("Démarer le jeu", "fr/pierrickviret/javaquest/javafx/assets/menu/startGame.PNG", () -> Game.getInstance().setGameState(GameState.selectMenu));
       this.getChildren().addAll(titre, creation, game);

       this.setAlignment(Pos.CENTER);

       ThemeConfig.applyDarkBackground(this);
   }
}
