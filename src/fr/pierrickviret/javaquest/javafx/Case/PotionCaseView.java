package fr.pierrickviret.javaquest.javafx.Case;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.board.Case.PotionCase;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import fr.pierrickviret.javaquest.javafx.FlipCard;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PotionCaseView extends VBox {
    private final Label titre;
    private final FlipCard card;
    private final Label interaction = new Label();
    private final Button button = new Button("Lancer le dé");

    public PotionCaseView(PotionCase currentCase, Runnable action) {
        super(30);

        titre = new Label("Retournez la carte");
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 30));
        titre.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));

        card = new FlipCard("fr/pierrickviret/javaquest/javafx/assets/box.png",
                ThemeConfig.potionImagePath,
                () -> {
                    titre.setText("Vous tombez sur " + currentCase.toString());
                    button.setVisible(true);
                    interaction.setText(Game.getInstance().getInteractionWithPotion(currentCase));
                    interaction.setVisible(true);
                });

        card.setOnMouseClicked(e -> {
            if (!card.isFlipped) {
                card.flip();
            }
        });

        ThemeConfig.applyButtonStyle(button);
        button.setOnAction(event -> action.run());
        button.setVisible(false);

        interaction.setWrapText(true);
        interaction.setFont(Font.font("Almendra", FontWeight.BOLD, 30));
        interaction.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));
        interaction.setVisible(false);


        this.getChildren().addAll(titre, card,interaction, button);

        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }
}
