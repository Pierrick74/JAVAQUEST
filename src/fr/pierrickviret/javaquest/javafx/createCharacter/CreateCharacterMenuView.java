package fr.pierrickviret.javaquest.javafx.createCharacter;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.commun.CharacterType;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import fr.pierrickviret.javaquest.javafx.composants.BigMenu;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CreateCharacterMenuView extends VBox {

    public CreateCharacterMenuView() {
        super(15);
        Label titre = new Label("Veuillez choisir votre personnage");
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 40.0));
        titre.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));

        BigMenu warrior = new BigMenu("Combatant", "fr/pierrickviret/javaquest/javafx/assets/Character/warrior.PNG", () -> {
            Game.getInstance().SetSelectedCharacter(CharacterType.Warrior);
            Game.getInstance().setGameState(GameState.createCharacter);
        });
        BigMenu wizard = new BigMenu("Magicien","fr/pierrickviret/javaquest/javafx/assets/Character/wizard.PNG", () -> {
            Game.getInstance().SetSelectedCharacter(CharacterType.Wizard);
            Game.getInstance().setGameState(GameState.createCharacter);
        });

        this.getChildren().addAll(titre, warrior, wizard);
        this.setAlignment(Pos.CENTER);
        ThemeConfig.applyDarkBackground(this);
    }
}
