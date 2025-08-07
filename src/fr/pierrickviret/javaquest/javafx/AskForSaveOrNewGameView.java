package fr.pierrickviret.javaquest.javafx;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.board.Board;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.javafx.composants.BigMenu;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AskForSaveOrNewGameView extends VBox {
    public AskForSaveOrNewGameView() {
        super(15);
        Label titre = new Label("Une partie est sauvegardée ?");
        titre.setFont(Font.font("SNOW BLUE", FontWeight.BOLD, 40.0));

        HBox creation = new BigMenu("Charger la partie sauvegardée", "fr/pierrickviret/javaquest/javafx/assets/saveGame.PNG", () -> Game.getInstance().setGameState(GameState.waitingInformation));
        HBox game = new BigMenu("Créer une nouvelle partie", "fr/pierrickviret/javaquest/javafx/assets/newFile.PNG", () -> {
            Game.getInstance().setGameState(GameState.waitingInformation);
        });
        this.getChildren().addAll(titre,creation, game);
        this.setAlignment(Pos.CENTER);
        this.setStyle(
                "-fx-background-color: #e8f1e8; " +
                        "-fx-alignment: center; "
        );
    }
}
