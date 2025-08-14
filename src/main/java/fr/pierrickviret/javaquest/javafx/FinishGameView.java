package fr.pierrickviret.javaquest.javafx;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FinishGameView extends VBox {
    public FinishGameView() {
        super(30);
        Label titre = new Label("Vous avez gagné, félicitations !");
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 30));
        titre.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));

        ImageView picture = new ImageView(new Image("/fr/pierrickviret/javaquest/assets/chest.png"));
        picture.setFitWidth(120);
        picture.setPreserveRatio(true);

        Button actionButton = new Button("Recommencer");
        ThemeConfig.applyButtonStyle(actionButton);
        actionButton.setOnAction(event -> Game.getInstance().setGameState(GameState.waitingInformation));

        this.getChildren().addAll(titre, picture, actionButton);
        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }
}
