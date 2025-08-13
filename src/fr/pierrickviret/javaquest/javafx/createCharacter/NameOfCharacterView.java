package fr.pierrickviret.javaquest.javafx.createCharacter;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.commun.CharacterType;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static fr.pierrickviret.javaquest.commun.ThemeConfig.INPUT_STYLE;


public class NameOfCharacterView extends VBox {

    public NameOfCharacterView(CharacterType type) {
        super(15);
        final String warriorImagePath = "fr/pierrickviret/javaquest/javafx/assets/warrior.PNG";
        final String wizardImagePath = "fr/pierrickviret/javaquest/javafx/assets/wizard.PNG";

        Label titre = new Label("Veuillez choisir un nom");
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 40.0));
        titre.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));

        ImageView imageView =  new ImageView();
        TextField name = new TextField();
        Button button = new Button("Valider");
        ThemeConfig.applyButtonStyle(button);

        String imagePath = type == CharacterType.Warrior ? warriorImagePath : wizardImagePath;

        Image image = new Image(imagePath);
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        name.setMaxWidth(300);
        name.setPromptText("Veuillez choisir un nom");
        name.setStyle(INPUT_STYLE);
        name.setAlignment(Pos.CENTER);

        button.setOnAction(e -> {
            if (!name.getText().isEmpty()) {
                Game.getInstance().createCharacter(type, name.getText());
                Game.getInstance().setGameState(GameState.showCharacter);
            }
        });

        this.getChildren().addAll(titre,imageView,  name, button);
        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }
}
