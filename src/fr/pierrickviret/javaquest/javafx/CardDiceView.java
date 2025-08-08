package fr.pierrickviret.javaquest.javafx;

import fr.pierrickviret.javaquest.commun.ThemeConfig;
import fr.pierrickviret.javaquest.javafx.Game.movingView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class CardDiceView extends VBox {
    private FlipCard card;
    private movingView diceView;

    public CardDiceView(String cardBack, String cardFront, Runnable diceAction) {
        super(30);
        createComponents(cardBack, cardFront, diceAction);
        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }

    private void createComponents(String cardBack, String cardFront, Runnable diceAction) {
        card = new FlipCard(cardBack, cardFront);

        Button flipButton = new Button("Retourner la carte");
        ThemeConfig.applyButtonStyle(flipButton);
        flipButton.setOnAction(e -> {
            card.flip();
            // Attendre puis afficher le dé
            Timeline delay = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                showDice(diceAction);
            }));
            delay.play();
        });

        this.getChildren().addAll(card, flipButton);
    }

    private void showDice(Runnable diceAction) {
        diceView = new movingView("Lancez le dé", "", "Lancer le dé", diceAction);
        this.getChildren().clear();
        this.getChildren().add(diceView);
    }
}
