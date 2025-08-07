package fr.pierrickviret.javaquest.javafx;

import fr.pierrickviret.javaquest.javafx.composants.bigMenu;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class createCharacterMenu extends VBox {
    public createCharacterMenu() {
        super(15);
        Label titre = new Label("Veuillez choisir votre personnage");
        titre.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        bigMenu warrior = new bigMenu("Combatant", "fr/pierrickviret/javaquest/javafx/assets/warrior.PNG", new MainView(10) );
        bigMenu wizard = new bigMenu("Magicien", "fr/pierrickviret/javaquest/javafx/assets/wizard.PNG", new MainView(10) );
        this.getChildren().addAll(titre, warrior, wizard);
        this.setAlignment(Pos.CENTER);
        this.setStyle(
                "-fx-background-color: #3caf3c; " +
                        "-fx-alignment: center; "
        );
    }
}
