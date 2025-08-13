package fr.pierrickviret.javaquest.character;

/**
*<h2> class Character</h2>
* <p> Class abstraite représentant l'ensemble des personnages vivant du jeu</p>
* @author Pierrick
* @version 1.0
 */

public abstract class Character {
    //variable
    String name;
    Integer health;
    Integer attack;
    Integer ID;
    int experience;
    Integer maxHealth;
    String imagePath;

    //init
    public Character(String name, Integer health, Integer attack, int experience, String imagePath) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.experience = experience;
        maxHealth = this.health;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Integer getAttackValue() {
        return attack;
    }

    public Integer getCharacterHealthValue() {
        return health;
    }
    public int getHealth() {
        return health;
    }

    public String getImagePath() {return imagePath;}
    public void setCharacterHealth(Integer health) {
        this.health = health;
    }

    public Integer getExperience() {
        return experience;
    }

    public void decreaseExperience(int experience) {
        this.experience -= experience;
    }

    public Integer getMaxHealth() {return maxHealth;}
}
