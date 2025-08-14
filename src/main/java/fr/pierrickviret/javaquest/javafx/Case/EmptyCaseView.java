package fr.pierrickviret.javaquest.javafx.Case;

import fr.pierrickviret.javaquest.commun.ThemeConfig;
import fr.pierrickviret.javaquest.javafx.FlipCard;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EmptyCaseView extends VBox {
    private Label titre;
    private Button actionButton;
    private FlipCard card;

    public EmptyCaseView(Runnable buttonAction) {
        super(30);
        createComponents(buttonAction);
        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }

    private void createComponents(Runnable buttonAction) {
        titre = new Label("Retournez la carte");
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 30));
        titre.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));

        card = new FlipCard("/fr/pierrickviret/javaquest/assets/box.png",
                "/fr/pierrickviret/javaquest/assets/emptyBox.png",
                () -> {
                    titre.setText("Dommage, la case est vide");
                    actionButton.setVisible(true);
                });

        card.setOnMouseClicked(e -> {
            if (!card.isFlipped) {
                card.flip();
            }
        });

        actionButton = new Button("lancer le dé");
        ThemeConfig.applyButtonStyle(actionButton);
        actionButton.setVisible(false);
        actionButton.setOnAction(event -> buttonAction.run());

        this.getChildren().addAll(titre, card, actionButton);
    }
}
