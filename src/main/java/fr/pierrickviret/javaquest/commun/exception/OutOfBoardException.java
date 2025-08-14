package fr.pierrickviret.javaquest.commun.exception;

/**
 *<h2> class OutOfBoardException</h2>
 * <p> Creation d'une exception personnalisée quand le joueur dépasse la valeur max du plateau
 * @author Pierrick
 * @see java.lang.Throwable
 * @version 1.0
 */
public class OutOfBoardException extends Throwable {
    public OutOfBoardException(String message) {
        super(message);
    }
}
