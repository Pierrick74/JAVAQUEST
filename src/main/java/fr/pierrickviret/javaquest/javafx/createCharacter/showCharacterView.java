package fr.pierrickviret.javaquest.javafx.createCharacter;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.commun.GameState;
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

public class showCharacterView extends VBox {

    public showCharacterView(MainCharacter character) {
        super(15);

        Label titre = new Label("Votre personnage a été créé");
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 40.0));
        titre.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));

        Image image = new Image(character.getImagePath());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        Label name = new Label("Votre Nom : " + character.getName());
        setupText(name);

        Label attack = new Label("Votre attaque : " + character.getAttackValue());
        setupText(attack);

        Label health = new Label("Vos Points de Vie : " + character.getHealth());
        setupText(health);

        Button button = new Button("Retour au Menu");
        button.setOnAction(e -> Game.getInstance().setGameState(GameState.waitingInformation));
        ThemeConfig.applyButtonStyle(button);

        this.getChildren().addAll(titre, imageView, name, attack, health, button);
        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }

    private void setupText(Label label) {
        label.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));
        label.setFont(Font.font("Almendra", FontWeight.BOLD, 25));
    }
}
