package fr.pierrickviret.javaquest.javafx;


import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.commun.CharacterType;
import fr.pierrickviret.javaquest.commun.GameState;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class showCharacter extends VBox {

    public showCharacter(MainCharacter character) {
        super(15);

        Label titre = new Label("Votre personnage créé");
        titre.setFont(Font.font("SNOW BLUE", FontWeight.BOLD, 40.0));

        final String warriorImagePath = "fr/pierrickviret/javaquest/javafx/assets/warrior.PNG";
        final String wizardImagePath = "fr/pierrickviret/javaquest/javafx/assets/wizard.PNG";
        String imagePath = character.getType() == CharacterType.Warrior ? warriorImagePath : wizardImagePath;

        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);


        Label name = new Label("Votre Nom : " + character.getName());
        name.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label attack = new Label("Votre attaque : " + character.getAttackValue());
        attack.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label health = new Label("Vos Points de Vie : " + character.getHealth());
        health.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Button button = new Button("Retour au Menu");
        button.setOnAction(e -> Game.getInstance().setGameState(GameState.waitingInformation));

        this.getChildren().addAll(titre, imageView, name, attack, health, button);
        this.setAlignment(Pos.CENTER);
        this.setStyle(
                "-fx-background-color: #e8f1e8; " +
                        "-fx-alignment: center; "
        );
    }
}
