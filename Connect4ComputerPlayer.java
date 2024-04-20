package core;
import java.util.Random;

/**
 * This class represents a computer player in a Connect Four game.
 * It makes decisions for the computer's moves during the game.
 */
public class Connect4ComputerPlayer {
    private Connect4Logic gameLogic;
    private Random randomGenerator = new Random();

    /**
     * Constructor for Connect4ComputerPlayer.
     *
     * @param gameLogic The game logic object that this computer player will interact with.
     */
    public Connect4ComputerPlayer(Connect4Logic gameLogic) {
        this.gameLogic = gameLogic;
    }

    /**
     * Decides the next move for the computer player.
     * The move can be either mirroring the player's last move or a random column.
     *
     * @param playerMove The column index of the last move made by the human player.
     * @return The column index for the computer player's move.
     */
    public int decideMove(int playerMove) {
        int num1 = randGenZeroandOne();
        if (num1 == 0) {
            return playerMove;
        }
        return randGenOnetoSev();
    }

    /**
     * Generates a random number either 0 or 1.
     *
     * @return Randomly generated number (0 or 1).
     */
    public int randGenZeroandOne() {
        int num = this.randomGenerator.nextInt(2);
        return num;
    }

    /**
     * Generates a random number between 1 and 7, inclusive.
     * This represents a random column selection in the Connect Four game.
     *
     * @return Randomly generated number (between 1 and 7, inclusive).
     */
    public int randGenOnetoSev() {
        int num = this.randomGenerator.nextInt(7);
        return num;
    }
}
