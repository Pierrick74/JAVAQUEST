package fr.pierrickviret.javaquest.commun;

import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class ThemeConfig {
    // Couleurs
    public static final String TEXT_LIGHT = "#D4C4A8";
    public static final String TEXT_GOLD = "#D4AF37";
    public static final String BUTTON_BROWN = "#8B4513";

    // Styles CSS
    public static final String DARK_BACKGROUND_STYLE =
            "-fx-background-color: #2C2416;" +
                    "-fx-alignment: center; ";

    public static final String BUTTON_STYLE =
            "-fx-background-color: " + BUTTON_BROWN + ";" +
                    "-fx-text-fill: #F5F1E8;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;" +
                    "-fx-border-color: " + TEXT_GOLD + ";" +
                    "-fx-border-width: 2;";

    public static final String INPUT_STYLE =
            "-fx-border-color: " + TEXT_GOLD + ";" +
                    "-fx-border-width: 2;";

    // Méthodes utilitaires
    public static void applyDarkBackground(Region node) {
        node.setStyle(DARK_BACKGROUND_STYLE);
    }

    public static void applyButtonStyle(Button button) {
        button.setStyle(BUTTON_STYLE);
    }

    public static final String warriorImagePath = "fr/pierrickviret/javaquest/javafx/assets/warrior.PNG";
    public static final String wizardImagePath = "fr/pierrickviret/javaquest/javafx/assets/wizard.PNG";


}
