package fr.pierrickviret.javaquest.javafx;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.javafx.composants.bigMenu;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class askIfUserWantToChangeCharacter extends VBox {
    public askIfUserWantToChangeCharacter() {
        super(15);
        Label titre = new Label("Que voulez vous faire ?");
        titre.setFont(Font.font("SNOW BLUE", FontWeight.BOLD, 40.0));

        HBox creation = new bigMenu("Modifier le personnage actuel", "fr/pierrickviret/javaquest/javafx/assets/editCharacter.PNG", () -> {
            Game.getInstance().setGameState(GameState.waitingInformation);
        });
        HBox game = new bigMenu("Créer un nouveau", "fr/pierrickviret/javaquest/javafx/assets/newCharacter.PNG", () -> {
            Game.getInstance().setGameState(GameState.selectCharacterToCreate);
        });
        this.getChildren().addAll(titre,creation, game);
        this.setAlignment(Pos.CENTER);
        this.setStyle(
                "-fx-background-color: #e8f1e8; " +
                        "-fx-alignment: center; "
        );
    }
}
