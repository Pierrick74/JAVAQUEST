package fr.pierrickviret.javaquest.javafx.Game;

import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import fr.pierrickviret.javaquest.character.Character;

public class CombatView extends BorderPane {
    private final PlayerPanel playerPanel;
    public GameLogPanel gameLogPanel;
    private final PlayerPanel enemyPanel;

    public CombatView(MainCharacter character, Character enemy, String newspaper) {

        playerPanel = new PlayerPanel(character);
        gameLogPanel = new GameLogPanel(newspaper);
        enemyPanel = new PlayerPanel(enemy);

        setupLayout();
        ThemeConfig.applyDarkBackground(this);
    }

    private void setupLayout() {
        this.setLeft(playerPanel);
        this.setCenter(gameLogPanel);
        this.setRight(enemyPanel);

        BorderPane.setMargin(playerPanel, new Insets(10));
        BorderPane.setMargin(gameLogPanel, new Insets(10));
        BorderPane.setMargin(enemyPanel, new Insets(10));
    }
}

