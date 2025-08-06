package fr.pierrickviret.javaquest.javafx;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuView extends VBox {
    private final Stage stage;
    private Label title;

   public MainMenuView(Stage primaryStage) {
       super(15);
       this.stage = primaryStage;

       title = new Label("Coucou page 2");
       this.getChildren().add(title);

       this.setStyle(
               "-fx-background-color: #a7f1a7; " +
                       "-fx-alignment: center; "
       );
   }

}
