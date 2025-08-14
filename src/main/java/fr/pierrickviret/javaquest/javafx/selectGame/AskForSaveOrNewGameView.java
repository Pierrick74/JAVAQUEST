package fr.pierrickviret.javaquest.javafx.selectGame;

import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import fr.pierrickviret.javaquest.javafx.composants.BigMenu;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AskForSaveOrNewGameView extends VBox {
    public AskForSaveOrNewGameView() {
        super(15);
        Label titre = new Label("Une partie est sauvegardée ?");
        titre.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));
        titre.setFont(Font.font("Almendra", FontWeight.BOLD, 40.0));

        HBox creation = new BigMenu("Charger la partie\nsauvegardée", "/fr/pierrickviret/javaquest/assets/menu/saveGame.PNG", () -> Game.getInstance().setGameState(GameState.uploadGame));
        HBox game = new BigMenu("Créer une nouvelle\npartie", "/fr/pierrickviret/javaquest/assets/menu/newFile.PNG", () -> Game.getInstance().setGameState(GameState.startGame));
        this.getChildren().addAll(titre,creation, game);
        this.setAlignment(Pos.CENTER);

        ThemeConfig.applyDarkBackground(this);
    }
}
