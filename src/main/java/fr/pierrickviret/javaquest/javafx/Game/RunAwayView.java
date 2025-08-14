package fr.pierrickviret.javaquest.javafx.Game;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RunAwayView extends VBox {

    public RunAwayView(Runnable buttonAction, Integer numberOfCase) {
        super(30);
        createComponents(buttonAction, numberOfCase);
        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }

    private void createComponents(Runnable buttonAction, Integer numberOfCase) {

        String caseDisplay = numberOfCase == 1 ? " case" :  " cases";
        Label titre = new Label("Vous prenez la fuite et reculez de " + numberOfCase + caseDisplay);
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 30));
        titre.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));

        Image image = new Image("/fr/pierrickviret/javaquest/assets/runAway.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        Label experience = new Label("Votre experience passe à " + Game.getInstance().getCharacter().getExperience());
        experience.setFont(Font.font("Almendra", FontWeight.BOLD, 30));
        experience.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));

        Button actionButton = new Button("lancer le dé");
        ThemeConfig.applyButtonStyle(actionButton);
        actionButton.setOnAction(event -> buttonAction.run());

        this.getChildren().addAll(titre, imageView, experience, actionButton);
    }
}
