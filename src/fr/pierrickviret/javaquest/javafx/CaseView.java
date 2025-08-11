package fr.pierrickviret.javaquest.javafx;

import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import fr.pierrickviret.javaquest.javafx.Game.PlayerPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class CaseView extends BorderPane {
    private final PlayerPanel playerPanel;

    public CaseView(MainCharacter character, Boolean isStepBack, VBox caseToShow ) {
        playerPanel = new PlayerPanel(character);
        setupLayoutBorderPanel();
        this.setStyle("-fx-background-color: #2C2416;");

        String stepBack = isStepBack ? "Vous reculez, " : "";
        VBox titreContainer = setupTitleContainer(stepBack + character.positionToString());

        Pane spacerBottom = new Pane();
        spacerBottom.setStyle("-fx-background-color: #2C2416;");
        spacerBottom.setPrefHeight(100);

        this.setTop(titreContainer);
        this.setBottom(spacerBottom);
        this.setCenter(caseToShow);
        this.setLeft(playerPanel);
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

    private void setupLayoutBorderPanel() {
        BorderPane.setMargin(playerPanel, new Insets(10));
        playerPanel.setStyle("-fx-background-color: #2C2416;");
    }
}
