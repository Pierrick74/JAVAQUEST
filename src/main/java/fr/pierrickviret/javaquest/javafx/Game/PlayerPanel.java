package fr.pierrickviret.javaquest.javafx.Game;

import fr.pierrickviret.javaquest.character.MainCharacter;
import fr.pierrickviret.javaquest.commun.ThemeConfig;
import fr.pierrickviret.javaquest.equipement.OffensiveEquipement;
import fr.pierrickviret.javaquest.equipement.offensive.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import fr.pierrickviret.javaquest.character.Character;

public class PlayerPanel extends BorderPane {
    private ImageView picture;
    private Label  playerName;
    private ProgressBar healthBar;
    private Label healthText;
    private Label level;
    private Label experience;

    public PlayerPanel(Character character) {
        initializeComponents(character);

        VBox root = new VBox(10);
        root.getChildren().addAll(picture, playerName, healthBar, healthText, level, experience);
        root.setAlignment(Pos.CENTER);

        if(character instanceof MainCharacter) {
            HBox equipments = createEquipementBox((MainCharacter) character);
            root.getChildren().addAll(equipments);
        }
        this.setPrefWidth(250);
        ThemeConfig.applyPlayerTheme(this);
        this.setPadding(new Insets(15));
        this.setCenter(root);
    }

    private void initializeComponents(Character character) {

        // init picture
        Image image = new Image(character.getImagePath());
        picture = new ImageView(image);
        picture.setFitWidth(100);
        picture.setPreserveRatio(true);

        // player name
        playerName = new Label(character.getName());
        playerName.setFont(Font.font("Almendra", FontWeight.BOLD, 18));
        playerName.setTextFill(Color.web(ThemeConfig.TEXT_GOLD));

        // health
        double characterHealth = character.getHealth();
        double maxCharacterHealth = character.getMaxHealth();
        double ratio = characterHealth /maxCharacterHealth;

        healthBar = new ProgressBar(ratio);
        healthBar.setPrefWidth(200);
        if(ratio < 0.5) {
            healthBar.setStyle("-fx-accent: #dc0e0e;");
        } else {
            healthBar.setStyle("-fx-accent: #43b643;");
        }

        healthText = new Label(characterHealth + " / " + maxCharacterHealth + " PV");
        healthText.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));

        level = new Label("Level: " + getLevel(character));
        level.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));

        experience = new Label("Experience: " + character.getExperience());
        experience.setTextFill(Color.web(ThemeConfig.TEXT_LIGHT));
    }

    private int getLevel(Character character) {
        if(character instanceof MainCharacter) {
            return ((MainCharacter) character).getLevel();
        }
        return switch (character.getName().toLowerCase()) {
            case "dragon", "mauvais esprits" -> 3;
            case "sorcerer", "orcs" -> 2;
            default -> 1;
        };
    }

    private HBox createEquipementBox(MainCharacter character) {
        ImageView imageView1 = createEquipementPicture(character, 1);
        ImageView imageView2 = createEquipementPicture(character, 2);
        HBox equipments = new HBox(10);
        equipments.setAlignment(Pos.CENTER);
        equipments.getChildren().addAll(imageView1, imageView2);
        return equipments;
    }

    private ImageView createEquipementPicture(MainCharacter character, Integer index) {
        OffensiveEquipement equipement = character.getOffensiveEquipement(index);
        String imagePath = equipement == null ? "/fr/pierrickviret/javaquest/assets/bag.png" : getImagePath(equipement);

        Image image = new Image(imagePath);
        ImageView equipmentImage = new ImageView(image);
        equipmentImage.setFitWidth(70);
        equipmentImage.setFitHeight(83);
        equipmentImage.setPreserveRatio(false);
        return equipmentImage;
    }

    private String getImagePath(OffensiveEquipement equipement) {
        if(equipement instanceof Bow) {
            return "/fr/pierrickviret/javaquest/assets/OffensiveEquipement/Bow.png";
        }
        if(equipement instanceof Club) {
            return "/fr/pierrickviret/javaquest/assets/OffensiveEquipement/Club.png";
        }
        if(equipement instanceof Fireball) {
            return "/fr/pierrickviret/javaquest/assets/OffensiveEquipement/Fireball.png";
        }
        if(equipement instanceof Invisibility) {
            return "/fr/pierrickviret/javaquest/assets/OffensiveEquipement/invisibilitySpell.png";
        }
        if(equipement instanceof Lightning) {
            return "/fr/pierrickviret/javaquest/assets/OffensiveEquipement/Lightning.png";
        }

        //default
        return "/fr/pierrickviret/javaquest/assets/OffensiveEquipement/Sword.png";
    }
}
