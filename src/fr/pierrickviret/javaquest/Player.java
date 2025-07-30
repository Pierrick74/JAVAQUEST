package fr.pierrickviret.javaquest;

public class Player {
    private Integer position;

    public Player() {
        this.position = 0;
    }

    public Integer getPosition() {
        return position;
    }

    public void addPosition(Integer number) {
        this.position = this.position + number ;
    }

    @Override
    public String toString() {
        return "Le Joueur" + System.lineSeparator() +
                "est en position = " + position + ";" + System.lineSeparator();
    }
}
