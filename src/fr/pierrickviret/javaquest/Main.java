package fr.pierrickviret.javaquest;

import fr.pierrickviret.javaquest.javafx.StageRepositoryView;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Font.loadFont(getClass().getResourceAsStream(
                "/fr/pierrickviret/javaquest/commun/font/SNOW BLUE.ttf"), 12);
        primaryStage.initStyle(StageStyle.DECORATED);
        StageRepositoryView.getInstance().setStage(primaryStage);

        // Lancer le jeu dans un thread séparé
        Thread gameThread = new Thread(() -> {
            Game game = Game.getInstance();
            game.start();
        });
        gameThread.setDaemon(true); // Se termine avec l'application
        gameThread.start();
    }
}