package fr.pierrickviret.javaquest;

import fr.pierrickviret.javaquest.db.SQLRepository;
import fr.pierrickviret.javaquest.javafx.StageRepository;
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
        SQLRepository.getInstance().initializeDatabase();
        getFont();

        primaryStage.initStyle(StageStyle.DECORATED);
        StageRepository.getInstance().setStage(primaryStage);

        // Lancer le jeu dans un thread séparé
        Thread gameThread = new Thread(() -> {
            Game game = Game.getInstance();
            game.start();
        });
        gameThread.setDaemon(true); // Se termine avec l'application
        gameThread.start();
    }

    private void getFont() {
        Font.loadFont(getClass().getResourceAsStream(
                "/fr/pierrickviret/javaquest/font/SNOW BLUE.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream(
                "/fr/pierrickviret/javaquest/font/MedievalSharp-Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream(
                "/fr/pierrickviret/javaquest/font/Almendra-Bold.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream(
                "/fr/pierrickviret/javaquest/font/Almendra-BoldItalic.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream(
                "/fr/pierrickviret/javaquest/font/Almendra-Italic.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream(
                "/fr/pierrickviret/javaquest/font/Almendra-Regular.ttf"), 12);
    }
}