package fr.pierrickviret.javaquest.character;

import fr.pierrickviret.javaquest.Menu;
import fr.pierrickviret.javaquest.commun.CharacterType;
import fr.pierrickviret.javaquest.equipement.DefensiveEquipement;
import fr.pierrickviret.javaquest.equipement.OffensiveEquipement;

import java.util.ArrayList;
import java.util.Random;

/**
 *<h2> class Character</h2>
 * <p> Class abstraite représentant l'ensemble des personnages que le joueur peut créer
 * Cette class extends de <cite>Character</cite></p>
 * @author Pierrick
 * @see Character
 * @version 1.0
 */
public abstract class MainCharacter extends Character {
    //variable
    Integer maxHealth;
    CharacterType type;
    ArrayList<OffensiveEquipement> offensiveEquipements =  new ArrayList<OffensiveEquipement>();
    DefensiveEquipement  defensiveEquipement;
    Boolean boostAttack;

    //init
    public MainCharacter(CharacterType type, String name) {
        super(name, 0, 0, 0);
        this.type = type;
        this.health = setHealth(this.type);
        maxHealth = this.health;
        this.attack = setAttack(this.type);
        this.name = name;
        this.boostAttack =  false;
        this.experience = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterType getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Integer getMaxHealth() {return maxHealth;}

    public OffensiveEquipement getOffensiveEquipement(int index) {
        return offensiveEquipements.get(index);
    }

    public int getLevel() {
        if(experience >= 15) {
            return 3;
        }
        if(experience >= 10) {
            return 2;
        }
        return 1;
    }

    public ArrayList<OffensiveEquipement> getOffensiveEquipements() {
        return offensiveEquipements;
    }

    public void setOffensiveEquipement(OffensiveEquipement offensiveEquipement, int index) {
        while (offensiveEquipements.size() <= index) {
            offensiveEquipements.add(null);
        }
        offensiveEquipements.set(index, offensiveEquipement);
    }

    public void showOffensiveEquipement() {
        if (this.offensiveEquipements.isEmpty()) {
            Menu.getInstance().showInformation("Aucun équipement offensif disponible");
            return;
        }

        Menu.getInstance().showInformation("voici votre inventaire");
        for (int i = 1; i < 3; i++) {
            if(offensiveEquipements.get(i) != null) {
                Menu.getInstance().showInformation("Emplacement "+ (i) + " : " + offensiveEquipements.get(i).getName());
            } else {
                Menu.getInstance().showInformation("Emplacement " + (i) + " : Vide");
            }
        }
    }

    public DefensiveEquipement getDefensiveEquipement() {
        return defensiveEquipement;
    }

    public Boolean hasOffensiveEquipement() {
        return !offensiveEquipements.isEmpty();
    }

    public Boolean hasOffensiveEquipementsForHisLevel() {
        for (OffensiveEquipement offensiveEquipement : offensiveEquipements) {
            if (offensiveEquipement != null && offensiveEquipement.getLevel() <= this.getLevel()) {
                return true;
            }
        }
        return false;
    }

    public void resetCharacter(){
        health = maxHealth;
        offensiveEquipements.replaceAll(ignored -> null);
        defensiveEquipement= null;
    }

    public void setBoostAttack() {
        boostAttack = true;
    }

    public void resetBoostAttack() {
        boostAttack = false;
    }

    public Boolean getBoostAttackValue() {
        return boostAttack;
    }

    public void increaseExperience(int experience) {
        this.experience += experience;
    }

    public void decreaseExperience(int experience) {
        this.experience -= experience;
    }

    //private
    private int setHealth(CharacterType type) {
        Random rand = new Random();
        return switch (type) {
            case Warrior -> rand.nextInt(5, 10);
            case Wizard -> rand.nextInt(8, 15);
        };
    }

    private int setAttack(CharacterType type) {
        Random rand = new Random();
        return switch (type) {
            case Warrior -> rand.nextInt(8, 15);
            case Wizard -> rand.nextInt(3, 6);
        };
    }

    @Override
    public String toString() {

        return "Personnage" + System.lineSeparator()
                + "name = " + this.name + ";" + System.lineSeparator()
                + "type = " + this.type.toString() + ";" + System.lineSeparator()
                + "attaque = " + this.attack + ";" + System.lineSeparator()
                + "points de vie = " + this.health + ".";
    }
}

