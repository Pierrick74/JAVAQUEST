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
    private final Runnable onFlipComplete;
    private int finishFace = 1;

    public FlipCard(String backImagePath, String frontImagePath, Runnable onFlipComplete) {
        createCards(backImagePath, frontImagePath);
        setupAnimation();
        this.onFlipComplete = onFlipComplete;
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

    public void setFinishFace(int finishFace) {
        this.finishFace = finishFace;
        frontCard.setImage(getFrontImage());
    }

    private Image getFrontImage() {
        return switch (finishFace) {
            case 1 -> new Image("fr/pierrickviret/javaquest/javafx/assets/dice/diceOne.png");
            case 2 -> new Image("fr/pierrickviret/javaquest/javafx/assets/dice/diceTwo.png");
            case 3 -> new Image("fr/pierrickviret/javaquest/javafx/assets/dice/diceTwo.png");
            case 4 -> new Image("fr/pierrickviret/javaquest/javafx/assets/dice/diceFour.png");
            case 5 -> new Image("fr/pierrickviret/javaquest/javafx/assets/dice/diceFive.png");
            case 6 -> new Image("fr/pierrickviret/javaquest/javafx/assets/dice/diceFive.png");
            default -> new Image("fr/pierrickviret/javaquest/javafx/assets/dice/dice.png");
        };
    }
}
