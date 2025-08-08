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

public class movingView extends VBox {
    public movingView(String titreValue, String imagePath, String buttontextDisplay,  Runnable action) {
        super(15);

        Label titre = new Label(titreValue);
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 40.0));
        titre.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));

        ImageView imageView =  new ImageView();
        Image image = new Image(imagePath);
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        Button button = new Button(buttontextDisplay);
        ThemeConfig.applyButtonStyle(button);

        button.setOnAction(e -> {
            if (action != null) action.run();
        } );

        this.getChildren().addAll(titre,imageView , button);
        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }
}
