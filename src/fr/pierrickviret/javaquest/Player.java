package fr.pierrickviret.javaquest;
/**
 *<h2> class Player</h2>
 * <p> Permet de gérer les données du joueur<\p>
 * @author Pierrick
 * @see Game
 * @see java.util.Scanner
 * @version 1.0
 */
public class Player {
    /**
     * Position actuelle du joueur
     */
    private Integer position;

    /**
     * Initialise le player à la position 0
     */
    public Player() {
        this.position = 0;
    }

    /**
     * Permet de récuperer la position actuel du joueur
     * @return {@code int} position du joueur
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * Permet de definir la position du joueur
     * @param position {@code int} la position futur du joueur
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Le Joueur" + System.lineSeparator() +
                "est en position = " + position + ";" + System.lineSeparator();
    }
}
