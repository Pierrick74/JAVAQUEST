package fr.pierrickviret.javaquest.javafx;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.commun.CharacterType;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.javafx.composants.bigMenu;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class createCharacterMenu extends VBox {
    private final String warriorImagePath = "fr/pierrickviret/javaquest/javafx/assets/warrior.PNG";
    private final String wizardImagePath = "fr/pierrickviret/javaquest/javafx/assets/wizard.PNG";

    public createCharacterMenu() {
        super(15);
        Label titre = new Label("Veuillez choisir votre personnage");
        titre.setFont(Font.font("SNOW BLUE", FontWeight.BOLD, 40.0));

        bigMenu warrior = new bigMenu("Combatant", warriorImagePath, () -> {
            Game.getInstance().SetSelectedCharacter(CharacterType.Warrior);
            Game.getInstance().setGameState(GameState.createCharacter);
        });
        bigMenu wizard = new bigMenu("Magicien", wizardImagePath, () -> {
            Game.getInstance().SetSelectedCharacter(CharacterType.Wizard);
            Game.getInstance().setGameState(GameState.createCharacter);
        });

        this.getChildren().addAll(titre, warrior, wizard);
        this.setAlignment(Pos.CENTER);
        this.setStyle(
                "-fx-background-color: #e8f1e8; " +
                        "-fx-alignment: center; "
        );
    }
}
