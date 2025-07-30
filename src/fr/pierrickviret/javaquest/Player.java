package fr.pierrickviret.javaquest;

public class Player {
    private Integer position;

    public Player() {
        this.position = 0;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Le Joueur" + System.lineSeparator() +
                "est en position = " + position + ";" + System.lineSeparator();
    }
}
