package fr.pierrickviret.javaquest.board.Case;

import fr.pierrickviret.javaquest.character.MainCharacter;

/**
 * Permet d'interagire avec les cases
 * retourne l'etat de l'objet dans la case
 */
public interface CaseInterface {
        Boolean interact(MainCharacter character);
}
