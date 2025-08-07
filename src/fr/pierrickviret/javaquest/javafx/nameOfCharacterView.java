package fr.pierrickviret.javaquest.javafx;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.commun.CharacterType;
import fr.pierrickviret.javaquest.commun.GameState;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class nameOfCharacterView extends VBox {

    public nameOfCharacterView(CharacterType type) {
        super(15);
        final String warriorImagePath = "fr/pierrickviret/javaquest/javafx/assets/warrior.PNG";
        final String wizardImagePath = "fr/pierrickviret/javaquest/javafx/assets/wizard.PNG";

        Label titre = new Label("Veuillez Choisir un nom");
        ImageView imageView =  new ImageView();
        TextField name = new TextField();
        Button button = new Button("Valider");

        String imagePath = type == CharacterType.Warrior ? warriorImagePath : wizardImagePath;

        titre.setFont(Font.font("SNOW BLUE", FontWeight.BOLD, 40.0));

        Image image = new Image(imagePath);
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        name.setMaxWidth(300);
        name.setPromptText("Veuillez choisir un nom");
        name.setAlignment(Pos.CENTER);

        button.setOnAction(e -> {
            if (!name.getText().isEmpty()) {
                Game.getInstance().createCharacter(type, name.getText());
                Game.getInstance().setGameState(GameState.showCharacter);
            }
        });

        this.getChildren().addAll(titre,imageView,  name, button);
        this.setAlignment(Pos.CENTER);
        this.setStyle(
                "-fx-background-color: #e8f1e8; " +
                        "-fx-alignment: center; "
        );
    }
}
