package fr.pierrickviret.javaquest.javafx;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CardDiceView extends VBox {
    private FlipCard card;
    private Button actionButton;
    private Label titre;


    public CardDiceView(Runnable diceAction) {
        super(30);
        createComponents(diceAction);
        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }

    private void createComponents(Runnable diceAction) {
        titre = new Label("Retournez la carte");
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 30));
        titre.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));

        card = new FlipCard(ThemeConfig.diceImagePath, ThemeConfig.diceImagePath,
                () -> {
                    titre.setText("Vous obtenez");
                    actionButton.setVisible(true);
                });

        card.setOnMouseClicked(e -> {
            if (!card.isFlipped) {
                card.setFinishFace(Game.getInstance().rollDice());
                card.flip();
            }
        });

        actionButton = new Button("Avancez");
        ThemeConfig.applyButtonStyle(actionButton);
        actionButton.setVisible(false);
        actionButton.setOnAction(event -> diceAction.run());

        this.getChildren().addAll(titre, card, actionButton);
    }
}
