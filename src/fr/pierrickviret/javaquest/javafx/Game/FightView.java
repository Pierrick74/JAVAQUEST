package fr.pierrickviret.javaquest.javafx.Game;

import fr.pierrickviret.javaquest.commun.ButtonInformationView;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FightView extends BorderPane {
    public FightView(CombatView Combat, ButtonInformationView button1, ButtonInformationView button2) {

        VBox titreContainer = setupTitleContainer("Journal de Combat");

        Pane spacerBottom = new Pane();
        spacerBottom.setStyle("-fx-background-color: #2C2416;");
        spacerBottom.setPrefHeight(100);

        this.setTop(titreContainer);
        this.setBottom(setButtonView(button1, button2));
        this.setCenter(Combat);
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

    private HBox setButtonView(ButtonInformationView button1, ButtonInformationView button2) {
        HBox buttonContainer = new HBox(10);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setStyle("-fx-background-color: #2C2416;");
        buttonContainer.setPrefHeight(100);

        if (button1 != null) {
            buttonContainer.getChildren().addAll(button1.createButton());

        }
        if (button2 != null) {
            buttonContainer.getChildren().addAll(button2.createButton());

        }
        return buttonContainer;
    }
}
