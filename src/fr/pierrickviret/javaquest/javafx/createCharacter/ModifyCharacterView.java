package fr.pierrickviret.javaquest.javafx.createCharacter;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.commun.CharacterType;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ModifyCharacterView extends VBox {
    CharacterType type;
    Button warrior;
    Button wizard;

    public ModifyCharacterView(MainCharacter character) {
        super(15);

        Label titre = new Label("Modifier votre Personnage");
        titre.setFont(Font.font("MedievalSharp", FontWeight.BOLD, 40.0));
        titre.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));


        this.type = character.getType();
        warrior = createButton(ThemeConfig.warriorImagePath, type == CharacterType.Warrior , () -> {
            type = CharacterType.Warrior;
            warrior.setStyle("-fx-border-width: 3; -fx-border-style: solid; -fx-border-color: #FF0000 ");
            wizard.setStyle("-fx-border-width: 0; -fx-border-style: none; -fx-border-color: #FF0000 ");
        });
        wizard = createButton(ThemeConfig.wizardImagePath,type == CharacterType.Wizard,  () -> {
            type = CharacterType.Wizard;
            warrior.setStyle("-fx-border-width: 0; -fx-border-style: none; -fx-border-color: #FF0000 ");
            wizard.setStyle("-fx-border-width: 3; -fx-border-style: solid; -fx-border-color: #FF0000 ");
        });

        HBox picture = new HBox(15);
        picture.setAlignment(Pos.CENTER);
        picture.getChildren().addAll(warrior, wizard);

        Label label = new Label();
        label.setText("Modifier le nom");
        label.setFont(Font.font("Almendra", FontWeight.BOLD, 20));

        TextField name = new TextField();
        name.setMaxWidth(300);
        name.setText(character.getName());
        name.setAlignment(Pos.CENTER);

        Button button = new Button("Valider");

        button.setOnAction(e -> {
            if (!name.getText().isEmpty()) {
                Game.getInstance().createCharacter(type, name.getText());
                Game.getInstance().setGameState(GameState.showCharacter);
            }
        });

        this.getChildren().addAll(titre,picture, label, name, button);
        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }

    private Button createButton( String imagePath, Boolean actif,  Runnable action) {
        Button button = new Button();
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
        button.setStyle(
                "-fx-background-color: #000000; " +
                        "-fx-alignment: center; ");

        if(actif) {
            button.setStyle("-fx-border-width: 3; -fx-border-style: solid; -fx-border-color: #FF0000 ");
        } else {
            button.setStyle("-fx-border-width: 0; -fx-border-style: none; -fx-border-color: #FF0000 ");
        }

        button.setOnAction(e -> {
            if (action != null) action.run();
        } );
        return button;
    }
}
