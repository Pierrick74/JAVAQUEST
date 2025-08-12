package fr.pierrickviret.javaquest.commun;

import javafx.scene.control.Button;

public class ButtonInformationView {
    String displayInformation;
    Runnable action;

    public ButtonInformationView(String displayInformation, Runnable action) {
        this.displayInformation = displayInformation;
        this.action = action;
    }

    public Button createButton() {
        Button button = new Button(displayInformation);
        ThemeConfig.applyButtonStyle(button);
        button.setOnAction(e -> action.run());
        return button;
    }
}
