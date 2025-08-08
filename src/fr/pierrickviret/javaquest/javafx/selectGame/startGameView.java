package fr.pierrickviret.javaquest.javafx.selectGame;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class startGameView extends VBox {
    public startGameView() {
        super(30);
        Label titre = new Label("Start Game");
        titre.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 40.0));

        Label description = new Label("Quel niveau voulez-vous ?");
        description.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));
        description.setFont(Font.font("Almendra", FontWeight.BOLD, 20.0));

        Button button1 = createButton("Level 1", () -> {
            Game.getInstance().initBoard(1);
            Game.getInstance().setGameState(GameState.launchDice);
        });

        Button button2 = createButton("Level 2", () -> {
            Game.getInstance().initBoard(2);
            Game.getInstance().setGameState(GameState.launchDice);
        });

        Button button3 = createButton("Level 3", () -> {
            Game.getInstance().initBoard(3);
            Game.getInstance().setGameState(GameState.launchDice);
        });

        HBox hbox = new HBox(30);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(button1, button2, button3);

        this.getChildren().addAll(titre, description, hbox);
        this.setAlignment(Pos.CENTER);

        ThemeConfig.applyDarkBackground(this);
    }

    private Button createButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setOnAction(e -> {
            if (action != null) action.run();
        } );
        ThemeConfig.applyButtonStyle(button);
        return button;
    }

}
