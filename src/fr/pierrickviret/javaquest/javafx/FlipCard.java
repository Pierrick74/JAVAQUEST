package fr.pierrickviret.javaquest.javafx;

import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class FlipCard extends StackPane {
    private ImageView frontCard;
    private ImageView backCard;
    private RotateTransition flipTransition;
    public boolean isFlipped = false;
    private Runnable onFlipComplete;

    public FlipCard(String backImagePath, String frontImagePath) {
        createCards(backImagePath, frontImagePath);
        setupAnimation();
        this.setPrefSize(120, 180);
    }

    public void setOnFlipComplete(Runnable callback) {
        this.onFlipComplete = callback;
    }

    private void createCards(String backPath, String frontPath) {
        // Carte arrière (visible au début)
        backCard = new ImageView(new Image(backPath));
        backCard.setFitWidth(120);
        backCard.setPreserveRatio(true);

        // Carte avant (cachée au début)
        frontCard = new ImageView(new Image(frontPath));
        frontCard.setFitWidth(120);
        frontCard.setPreserveRatio(true);
        frontCard.setVisible(false);

        this.getChildren().addAll(backCard, frontCard);
    }

    private void setupAnimation() {
        flipTransition = new RotateTransition(Duration.millis(300), this);
        flipTransition.setAxis(Rotate.Y_AXIS);
    }

    public void flip() {
        if (!isFlipped) {
            flipTransition.setFromAngle(0);
            flipTransition.setToAngle(90);
            flipTransition.setOnFinished(e -> {
                backCard.setVisible(false);
                frontCard.setVisible(true);

                RotateTransition flip2 = new RotateTransition(Duration.millis(300), this);
                flip2.setAxis(Rotate.Y_AXIS);
                flip2.setFromAngle(90);
                flip2.setToAngle(0);
                flip2.setOnFinished(event -> {
                    isFlipped = true;
                    // Callback pour notifier la fin
                    if (onFlipComplete != null) onFlipComplete.run();
                });
                flip2.play();
            });
            flipTransition.play();
        }
    }
}
