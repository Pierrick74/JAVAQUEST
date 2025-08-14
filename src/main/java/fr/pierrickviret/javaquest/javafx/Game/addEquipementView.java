package fr.pierrickviret.javaquest.javafx.Game;

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

public class addEquipementView extends VBox {
    public addEquipementView(Runnable diceAction) {
        super(30);

        ImageView imageView;
        Button actionButton;
        Label titre;

        titre = new Label("Vous mettez l'équipement dans votre sacoche");
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 30));
        titre.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));

        imageView = new ImageView(new Image("/fr/pierrickviret/javaquest/assets/bag.png"));
        imageView.setFitWidth(120);
        imageView.setPreserveRatio(true);

        actionButton = new Button("Lancer le dé");
        ThemeConfig.applyButtonStyle(actionButton);
        actionButton.setOnAction(event -> diceAction.run());

        this.getChildren().addAll(titre, imageView, actionButton);
        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }
}
