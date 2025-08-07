package fr.pierrickviret.javaquest.javafx;
import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.commun.GameState;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainView extends VBox {
    private Label label;
    private Label titre;
    private Button button;
    private ImageView imageView;

    public MainView(double spacing) {
        super(spacing);
        initializeComponents();
        setupActions();

        this.getChildren().addAll(imageView,label, titre,button);

        this.setStyle(
                "-fx-background-color: #e8f1e8; " +
                        "-fx-alignment: center; "
        );
    }

    private void initializeComponents()  {
        button = new Button("Commencer le jeu");
        button.setDefaultButton(true);
        button.setFont(Font.font("impact", FontWeight.LIGHT, 15.0));

        label = new Label("Bienvenue dans");
        label.setFont(Font.font("SNOW BLUE", FontWeight.BOLD, 30.0));
        label.setAlignment(Pos.CENTER);

        titre = new Label("JavaQuest !");
        titre.setFont(Font.font("SNOW BLUE", FontWeight.BOLD, 50.0));
        titre.setAlignment(Pos.CENTER);

        Image image = new Image("fr/pierrickviret/javaquest/javafx/assets/epeetbouclier.png");
        imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
    }

    private void setupActions() {
        // Action sur le bouton - maintenant dans une méthode
        button.setOnAction(e -> Game.getInstance().setGameState(GameState.waitingInformation));
    }
}
