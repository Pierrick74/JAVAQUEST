package fr.pierrickviret.javaquest.javafx.Case;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.board.Case.WeaponCase;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import fr.pierrickviret.javaquest.javafx.FlipCard;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WeaponCaseView  extends VBox {
    private final Label titre;
    HBox buttonBox;
    private final FlipCard card;

    public WeaponCaseView(WeaponCase currentCase) {
        super(30);

        titre = new Label("Retournez la carte");
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 30));
        titre.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));

        card = new FlipCard("fr/pierrickviret/javaquest/javafx/assets/box.png",
                getFrontImage(currentCase),
                () -> {
                    titre.setText(getTitleInformation(currentCase));
                    buttonBox.setVisible(true);
                });

        card.setOnMouseClicked(e -> {
            if (!card.isFlipped) {
                card.flip();
            }
        });

        buttonBox = createButtonArea(currentCase);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setVisible(false);

        this.getChildren().addAll(titre, card, buttonBox);

        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }

    private String getFrontImage(WeaponCase currentCase) {
        return switch (currentCase.toString().toLowerCase()) {
            case "un arc" -> ThemeConfig.bowImagePath;
            case "une massue" -> ThemeConfig.clubImagePath;
            default -> ThemeConfig.swordImagePath;
        };
    }

    private HBox createButtonArea(WeaponCase currentCase) {
        HBox buttonArea = new HBox(10);

        Button inventaire1 = new Button("Récupérer dans l'inventaire 1");
        ThemeConfig.applyButtonStyle(inventaire1);
        inventaire1.setOnAction(event -> Game.getInstance().getInteractionWithWeapon(currentCase, 1));

        Button inventaire2 = new Button("Récupérer dans l'inventaire 2");
        ThemeConfig.applyButtonStyle(inventaire2);
        inventaire2.setOnAction(event -> Game.getInstance().getInteractionWithWeapon(currentCase, 2));

        Button leave = new Button("Laissez l'arme et lancer le dé");
        ThemeConfig.applyButtonStyle(leave);
        leave.setOnAction(event -> Game.getInstance().getInteractionWithWeapon(currentCase, 3));

        buttonArea.getChildren().addAll(inventaire1,  inventaire2, leave);
        return buttonArea;
    }

    private String getTitleInformation(WeaponCase currentCase) {
        String base = "Vous tombez sur ";
        return switch (currentCase.toString().toLowerCase()) {
            case "un arc" -> base + "un Arc de niveau 3";
            case "une massue" -> base + "une Massue de niveau 2";
            default -> base + "une Epée de niveau 1";
        };
    }
}
