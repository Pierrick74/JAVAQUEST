package fr.pierrickviret.javaquest.javafx.Case;

import fr.pierrickviret.javaquest.board.Case.EnemyCase;
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

public class EnemyCaseView extends VBox {
    private final Label titre;
    private HBox buttonBox;
    private VBox infoArea;
    private final FlipCard card;

    public EnemyCaseView(EnemyCase currentCase, Runnable fightAction, Runnable runAwayAction) {
        super(20);

        titre = new Label("Retournez la carte");
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 30));
        titre.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));

        card = new FlipCard("fr/pierrickviret/javaquest/javafx/assets/box.png",
                currentCase.getEnemyImagePath(),
                () -> {
                    titre.setText(getTitleInformation(currentCase));
                    buttonBox.setVisible(true);
                    infoArea.setVisible(true);
                });

        card.setOnMouseClicked(e -> {
            if (!card.isFlipped) {
                card.flip();
            }
        });

        infoArea = createEnemyInformation(currentCase);
        infoArea.setAlignment(Pos.CENTER);
        infoArea.setVisible(false);

        buttonBox = createButtonArea(fightAction, runAwayAction);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setVisible(false);

        this.getChildren().addAll(titre, card, infoArea, buttonBox);

        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }

    private HBox createButtonArea(Runnable fightAction, Runnable runAwayAction) {
        HBox buttonArea = new HBox(10);

        Button runAwayButton = new Button("S'enfuire");
        ThemeConfig.applyButtonStyle(runAwayButton);
        runAwayButton.setOnAction(event -> runAwayAction.run());

        Button fightButton = new Button("Combattre");
        ThemeConfig.applyButtonStyle(fightButton);
        fightButton.setOnAction(event -> fightAction.run());

        buttonArea.getChildren().addAll(runAwayButton, fightButton);
        return buttonArea;
    }

    private VBox createEnemyInformation(EnemyCase currentCase) {
        Label attaque = new Label("Attaque: " + currentCase.getEnemy().getAttackValue());
        attaque.setFont(Font.font("Almendra", FontWeight.BOLD, 20));
        attaque.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));

        Label health = new Label("Points de vie: " + currentCase.getEnemy().getHealth());
        health.setFont(Font.font("Almendra", FontWeight.BOLD, 20));
        health.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));

        VBox area = new VBox(10);
        area.getChildren().addAll(attaque, health);
        return area;
    }

    private String getTitleInformation(EnemyCase currentCase) {
        String base = "Vous tombez sur ";
        return switch (currentCase.toString().toLowerCase()) {
            case "dragon" -> base + "un Dragon";
            case "mauvais esprits" -> base + "des Mauvais esprits";
            case "sorcerer" -> base + "un Sorcier";
            case "orcs" -> base + "un Orcs";
            default -> base + "un Gobelin";
        };
    }
}
