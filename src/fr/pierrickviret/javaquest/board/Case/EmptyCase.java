package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.character.MainCharacter;

public class EmptyCase extends Case {

    @Override
    public String toString() {

        return "case vide, dommage ! il ne se passe rien."+ System.lineSeparator();
    }

    @Override
    public void interact(MainCharacter character) {
    }
}
