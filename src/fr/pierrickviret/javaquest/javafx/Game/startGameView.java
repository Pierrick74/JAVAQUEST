package fr.pierrickviret.javaquest.javafx.Game;

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

public class startGameView extends VBox {
    public startGameView() {
        super(15);
        Label titre = new Label("Start Game");
        titre.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 40.0));

     this.getChildren().addAll(titre);
        this.setAlignment(Pos.CENTER);

        ThemeConfig.applyDarkBackground(this);
    }
}
