package fr.pierrickviret.javaquest.javafx;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StageRepositoryView {
    private static StageRepositoryView instance;
    private Stage stage;

    // Dimensions communes
    private static final double SCENE_WIDTH = 1200;
    private static final double SCENE_HEIGHT = 700;
    private static final Color BACKGROUND_COLOR = Color.BLACK;

    private StageRepositoryView() {}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public static StageRepositoryView getInstance() {
        return instance==null?instance=new StageRepositoryView():instance;
    }

    public void replaceScene(javafx.scene.Parent scene) {
        stage.setScene(createScene(scene));
        stage.show();
    }

    public Scene createScene(javafx.scene.Parent root) {
        return new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, BACKGROUND_COLOR);
    }
}
