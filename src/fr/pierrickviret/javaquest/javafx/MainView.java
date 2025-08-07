package fr.pierrickviret.javaquest.javafx;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainView extends VBox {
    private Label label;
    private Button button;
    private ImageView imageView;

    public MainView(double spacing) {
        super(spacing);
        initializeComponents();
        setupActions();

        this.getChildren().addAll(imageView,label,button);

        this.setStyle(
                "-fx-background-color: #16cc16; " +
                        "-fx-alignment: center; "
        );
    }

    private void initializeComponents()  {
        button = new Button("Commencer le jeu");
        button.setDefaultButton(true);
        button.setFont(Font.font("impact", FontWeight.LIGHT, 15.0));

        label = new Label("Bienvenue dans JavaQuest !");
        label.setFont(Font.font("impact", FontWeight.BOLD, 30.0));

        Image image = new Image("fr/pierrickviret/javaquest/javafx/assets/epeetbouclier.png");
        imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
    }

    private void setupActions() {
        // Action sur le bouton - maintenant dans une méthode
        button.setOnAction(e -> StageRepository.getInstance().replaceScene(new MainMenuView()));
    }
}
