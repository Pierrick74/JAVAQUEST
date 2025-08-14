package fr.pierrickviret.javaquest.javafx.composants;

import fr.pierrickviret.javaquest.commun.ThemeConfig;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class BigMenu extends HBox {
    private final Button button = new Button();
    private final ImageView imageView =  new ImageView();
    private final String imagePath;

    public BigMenu(String title, String imagePath, Runnable action) {
        this.imagePath = imagePath;
        Label label = new Label(title);
        label.setText(title);
        label.setFont(Font.font("Almendra", FontWeight.BOLD, 30));
        label.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));

        createButton(action);

        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(20);
        this.setPadding(new Insets(20));

        button.setPrefHeight(60);
        this.setMaxWidth(500);
        this.getChildren().addAll(button, label);
    }

    private void createButton( Runnable action) {
        Image image = new Image(imagePath);
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
        button.setStyle(
                "-fx-background-color: #000000; " +
                        "-fx-alignment: center; ");
        button.setOnAction(e -> {
            if (action != null) action.run();
        } );
    }
}
