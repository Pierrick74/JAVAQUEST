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
    private boolean isFlipped = false;

    public FlipCard(String backImagePath, String frontImagePath) {
        createCards(backImagePath, frontImagePath);
        setupAnimation();
        this.setPrefSize(120, 180);
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

                RotateTransition secondHalf = new RotateTransition(Duration.millis(300), this);
                secondHalf.setAxis(Rotate.Y_AXIS);
                secondHalf.setFromAngle(90);
                secondHalf.setToAngle(0);
                secondHalf.setOnFinished(event -> {
                    this.setRotate(0);
                });
                secondHalf.play();
            });
            flipTransition.play();
            isFlipped = true;
        }
    }
}
