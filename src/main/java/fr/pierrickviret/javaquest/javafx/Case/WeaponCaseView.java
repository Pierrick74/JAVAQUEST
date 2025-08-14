package fr.pierrickviret.javaquest.javafx.Case;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.board.Case.Case;
import fr.pierrickviret.javaquest.board.Case.SpellCase;
import fr.pierrickviret.javaquest.board.Case.WeaponCase;
import fr.pierrickviret.javaquest.commun.GameState;
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
    private final Label description;
    private final FlipCard card;

    public WeaponCaseView(Case currentCase) {
        super(30);

        titre = new Label("Retournez la carte");
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 30));
        titre.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));

        card = new FlipCard("/fr/pierrickviret/javaquest/assets/box.png",
                getFrontImage(currentCase),
                () -> {
                    titre.setText(getTitleInformation(currentCase));
                    buttonBox.setVisible(true);
                    isCanTakeWeapon(currentCase);
                });

        card.setOnMouseClicked(e -> {
            if (!card.isFlipped) {
                card.flip();
            }
        });

        buttonBox = Game.getInstance().isCanTakeWeapon(currentCase) ? createButtonArea(currentCase) : createButton();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setVisible(false);

        description = new Label("Vous êtes un " + Game.getInstance().getCharacter().getType() + ", vous ne pouvez pas prendre" );
        description.setFont(Font.font("Almendra", FontWeight.BOLD, 20));
        description.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));
        description.setVisible(false);

        this.getChildren().addAll(titre, card, description, buttonBox);

        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }

    private String getFrontImage(Case currentCase) {
        if(currentCase instanceof WeaponCase){
            return ((WeaponCase) currentCase).getImagePath();
        } else {
            return ((SpellCase) currentCase).getImagePath();
        }
    }

    private HBox createButton() {
        HBox buttonArea = new HBox(10);
        Button button = new Button("Lancer le dé");
        ThemeConfig.applyButtonStyle(button);
        button.setOnAction(event -> Game.getInstance().setGameState(GameState.launchDice));
        buttonArea.getChildren().addAll(button);
        return buttonArea;
    }

    private HBox createButtonArea(Case currentCase) {
        HBox buttonArea = new HBox(10);

        Button inventaire1 = new Button("Inv. 1");
        ThemeConfig.applyButtonStyle(inventaire1);
        inventaire1.setOnAction(event -> Game.getInstance().getInteractionWithWeapon(currentCase, 1));

        Button inventaire2 = new Button("Inv. 2");
        ThemeConfig.applyButtonStyle(inventaire2);
        inventaire2.setOnAction(event -> Game.getInstance().getInteractionWithWeapon(currentCase, 2));

        Button leave = new Button("Ignorer et lancer le dé");
        ThemeConfig.applyButtonStyle(leave);
        leave.setOnAction(event -> Game.getInstance().getInteractionWithWeapon(currentCase, 3));

        buttonArea.getChildren().addAll(inventaire1,  inventaire2, leave);
        return buttonArea;
    }

    private String getTitleInformation(Case currentCase) {
        String base = "Vous tombez sur ";
        return switch (currentCase.toString().toLowerCase()) {
            case "un arc" -> base + "un arc de niveau 3";
            case "une massue" -> base + "une massue de niveau 2";
            case "un sort:des boules de feu" -> base + "un sort: boules de feu de niveau 2";
            case "un sort d'invisibilité" -> base + "un sort d'invisibilité de niveau 3";
            case "un sort: des éclaires" -> base + "un sort: des éclairs de niveau 1";
            default -> base + "une épée de niveau 1";
        };
    }

    private void isCanTakeWeapon(Case currentCase) {
        if(!Game.getInstance().isCanTakeWeapon(currentCase)) {
            description.setVisible(true);
        }
    }
}