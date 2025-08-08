package fr.pierrickviret.javaquest.javafx;

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

        card = new FlipCard("fr/pierrickviret/javaquest/javafx/assets/warrior.PNG",
                "fr/pierrickviret/javaquest/javafx/assets/dice.png",
                () -> {
                    titre.setText("Vous obtenez");
                    actionButton.setText("Suivant");
                    actionButton.setOnAction(event -> diceAction.run());
                });

        actionButton = new Button("Retourner la carte");
        ThemeConfig.applyButtonStyle(actionButton);

        actionButton.setOnAction(e -> {
            if (!card.isFlipped) {
                card.flip();
            }
        });

        this.getChildren().addAll(titre, card, actionButton);
    }
}
