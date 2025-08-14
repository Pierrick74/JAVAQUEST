package fr.pierrickviret.javaquest.javafx.Game;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

// Panel central du log de jeu (parchemin)
public class GameLogPanel extends VBox {
    private ScrollPane scrollPane;
    private VBox logContent;

    public GameLogPanel(String content) {
        super(10);
        initializeComponents();
        setupLayout();
        addLogEntry(content);
        this.setPrefWidth(400);
    }

    private void initializeComponents() {
        // Zone de contenu avec meilleur contraste
        logContent = new VBox(8);
        logContent.setPadding(new Insets(20));
        logContent.setStyle(
                "-fx-background-color: rgba(255, 248, 220, 0.9);" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-color: #8B4513;" +
                        "-fx-border-width: 1;" +
                        "-fx-border-radius: 8;"
        );

        scrollPane = new ScrollPane(logContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // Background parchemin
        this.setStyle(
                "-fx-background-image: url('/fr/pierrickviret/javaquest/assets/parchemin.png');" +
                        "-fx-background-size: cover;" +
                        "-fx-background-repeat: no-repeat;"
        );
    }

    private void setupLayout() {
        this.setPadding(new Insets(90, 100, 90, 100)); // Plus d'espace pour respecter le parchemin
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
    }

    private void addLogEntry(String message) {
        TextFlow textFlow = new TextFlow();
        String[] lines = message.split("\n");
        for (String line : lines) {
            Text text = new Text(line + "\n");
            text.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
            text.setFill(Color.web("#2C1810"));
            textFlow.getChildren().add(text);
        }

        logContent.getChildren().add(textFlow);
        Platform.runLater(() -> scrollPane.setVvalue(1.0));
    }
}