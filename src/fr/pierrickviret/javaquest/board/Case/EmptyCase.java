package fr.pierrickviret.javaquest.board.Case;

public class EmptyCase extends Case implements saveableInDB {

    @Override
    public String toString() {

        return "case vide, dommage ! il ne se passe rien."+ System.lineSeparator();
    }

    @Override
    public String getInfoToSave() {
        return "";
    }
}
