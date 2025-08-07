package fr.pierrickviret.javaquest.javafx.composants;

import fr.pierrickviret.javaquest.javafx.StageRepository;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class bigMenu extends HBox {
    private final Button button = new Button();
    private final ImageView imageView =  new ImageView();
    private final String imagePath;

    public bigMenu(String title, String imagePath,Parent nextView) {
        this.imagePath = imagePath;
        Label label = new Label(title);
        label.setText(title);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        createButton(nextView);

        TilePane tileLayout = new TilePane();
        tileLayout.setHgap(5);
        tileLayout.setVgap(5);
        tileLayout.setPrefTileWidth(300);
        tileLayout.setAlignment(Pos.TOP_CENTER);
        tileLayout.setTileAlignment(Pos.CENTER_LEFT);
        tileLayout.getChildren().addAll(button, label);

        this.getChildren().addAll(tileLayout);
    }

    private void createButton(Parent nextView) {
        Image image = new Image(imagePath);
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
        button.setStyle(
                "-fx-background-color: #000000; " +
                        "-fx-alignment: center; ");
        button.setOnAction(e -> StageRepository.getInstance().replaceScene(nextView));
    }
}
