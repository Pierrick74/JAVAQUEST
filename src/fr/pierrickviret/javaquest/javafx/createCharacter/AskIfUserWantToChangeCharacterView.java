package fr.pierrickviret.javaquest.javafx.createCharacter;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import fr.pierrickviret.javaquest.javafx.composants.BigMenu;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AskIfUserWantToChangeCharacterView extends VBox {
    public AskIfUserWantToChangeCharacterView() {
        super(15);
        Label titre = new Label("Que voulez vous faire ?");
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 40.0));
        titre.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));

        HBox creation = new BigMenu("Modifier le personnage actuel", "fr/pierrickviret/javaquest/javafx/assets/editCharacter.PNG", () -> Game.getInstance().setGameState(GameState.modifyCharacter));
        HBox game = new BigMenu("Créer un nouveau", "fr/pierrickviret/javaquest/javafx/assets/newCharacter.PNG", () -> {
            Game.getInstance().deleteCharacter();
            Game.getInstance().setGameState(GameState.checkIfCharacterIsAlreadyCreated);
        });
        this.getChildren().addAll(titre,creation, game);
        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }
}
