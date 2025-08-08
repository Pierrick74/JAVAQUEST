package fr.pierrickviret.javaquest.javafx.Game;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.character.Gobelin;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BoardView extends BorderPane {
    public BoardView() {
        CombatView combatView = new CombatView(Game.getInstance().getCharacter(), new Gobelin(), "Vous rencontrez un Gobelin");

        VBox titreContainer = setupTitleContainer("Journal de Combat");

        Pane spacerBottom = new Pane();
        spacerBottom.setStyle("-fx-background-color: #2C2416;");
        spacerBottom.setPrefHeight(100);

        this.setTop(titreContainer);
        this.setBottom(spacerBottom);
        this.setCenter(combatView);
    }

    private VBox setupTitleContainer(String title) {
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Almendra", FontWeight.BOLD, 40));
        titleLabel.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));
        VBox titleContainer = new VBox(titleLabel);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.setStyle("-fx-background-color: #2C2416;");
        titleContainer.setPrefHeight(100);
        return titleContainer;
    }
}
