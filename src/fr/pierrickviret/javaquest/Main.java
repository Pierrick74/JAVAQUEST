package fr.pierrickviret.javaquest;

import fr.pierrickviret.javaquest.javafx.MainView;
import fr.pierrickviret.javaquest.javafx.StageRepository;
import javafx.application.Application;
import javafx.scene.layout.VBox;
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
        StageRepository.getInstance().setStage(primaryStage);

        // Organisation des éléments
        VBox root = new MainView(20);

        StageRepository.getInstance().replaceScene(root);
    }
}