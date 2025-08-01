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

    //init
    public Character(String name, Integer health, Integer attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
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

    public void setCharacterHealth(Integer health) {
        this.health = health;
    }
}
