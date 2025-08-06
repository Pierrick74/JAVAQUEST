package fr.pierrickviret.javaquest.javafx;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainView extends VBox {
    private Label label;
    private Button button;
    private final Stage stage;

    private String nomImage="/assets/epeetbouclier.jpg";
    private ImageView imageView;

    public MainView(double spacing, Stage primaryStage) {
        super(spacing);
        this.stage = primaryStage;
        initializeComponents();
        setupActions();

        ObservableList components = this.getChildren();
        components.addAll(imageView,label,button);

        this.setStyle(
                "-fx-background-color: #16cc16; " +
                        "-fx-alignment: center; "
        );
    }

    private void initializeComponents() {
        button = new Button("Commencer le jeu");
        button.setDefaultButton(true);
        button.setFont(Font.font("impact", FontWeight.LIGHT, 15.0));

        label = new Label("Bienvenue dans JavaQuest !");
        label.setFont(Font.font("impact", FontWeight.BOLD, 30.0));

        System.out.println(nomImage);


        Image image = new Image(getClass().getResource(nomImage).toExternalForm());
        System.out.println(getClass().getResource(nomImage).toExternalForm());
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        System.out.println(imageView);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
    }

    private void setupActions() {
        // Action sur le bouton - maintenant dans une méthode
        button.setOnAction(e -> {
            VBox nextView = new MainMenuView(stage);
            StageRepository.getInstance().replaceScene(nextView);
        });
    }
}
