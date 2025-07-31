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

    //init
    public Character(String name, Integer health, Integer attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

}
