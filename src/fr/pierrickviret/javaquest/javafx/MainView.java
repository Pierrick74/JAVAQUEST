package fr.pierrickviret.javaquest.javafx;
import fr.pierrickviret.javaquest.Game;
import fr.pierrickviret.javaquest.commun.GameState;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainView extends VBox {
    private Label bienvenue;
    private Label titre;
    private Button button;
    private ImageView imageView;

    public MainView() {
        super(10);
        initializeComponents();
        setupActions();

        this.getChildren().addAll(imageView,bienvenue, titre,button);

        ThemeConfig.applyDarkBackground(this);
    }

    private void initializeComponents()  {
        button = new Button("Commencer le jeu");
        button.setDefaultButton(true);
        button.setFont(Font.font("Almendra", FontWeight.LIGHT, 15.0));
        ThemeConfig.applyButtonStyle(button);

        bienvenue = new Label("Bienvenue dans");
        bienvenue.setFont(Font.font("Almendra", FontWeight.BOLD, 30.0));
        bienvenue.setAlignment(Pos.CENTER);
        bienvenue.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));

        titre = new Label("JavaQuest");
        titre.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));

        titre.setFont(Font.font("SNOW BLUE", FontWeight.BOLD, 50.0));
        titre.setAlignment(Pos.CENTER);

        Image image = new Image("fr/pierrickviret/javaquest/javafx/assets/home.jpg");
        imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
    }

    private void setupActions() {
        // Action sur le bouton - maintenant dans une méthode
        button.setOnAction(e -> Game.getInstance().setGameState(GameState.waitingInformation));
    }
}
