package fr.pierrickviret.javaquest.commun;
/**
 *<h2> Enum GameState</h2>
 * <p> Représente les différents états du jeu.
 * @author Pierrick
 * @version 1.0.
 */
public enum GameState {
        begin,
        waitingInformation,
        createCharacter,
        finishGame,
        startGame,
        playerTurn,
        exitGame,
        gameOver,
        selectMenu,
        checkIfCharacterIsAlreadyCreated,
        selectNameForCharacter,
        showCharacter,
        modifyCharacter,
        uploadGame,
        launchDice,
        fight,
        moveBack
}
