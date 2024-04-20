package core;
import IO.Connect4TextConsole;
/**
 * This class contains the logic for a Connect Four game. It is responsible for maintaining
 * the state of the game board, handling player moves, and checking for a winning condition.
 *
 * @author Nicholas Dobrikov
 * @version 1.0
 */
public class Connect4Logic {
    // Constants for the game board dimensions and maximum number of chips.
    private static final int ROWS = 6; // rows in board
    private static final int COLUMNS = 7; // columns in board
    private static final int maxChips = 42; // used for tracking a draw
    private static int turnCount = 0; // to track for a draw once turnCount == maxChips
    private static final String playerX = "Player X"; // one player
    private static final String playerO = "Player O"; // other player
    private static Connect4ComputerPlayer computerPlayer;
    private static String currentPlayer = playerX; // current turn player
    private char[][] board = new char[ROWS][COLUMNS]; // Connect4 board

    /**
     * Constructs a new Connect Four logic controller and initializes the game board.
     */
    public Connect4Logic() {
        initializeBoard();
    }

    /**
     * Initializes the game board to an empty state.
     */
    private void initializeBoard() {
        for (int row = 0; row < this.ROWS; row++) {
            for (int col = 0; col < this.COLUMNS; col++) {
                board[row][col] = ' ';
            }
        }
    }

    /**
     * Prints the current state of the game board to the console.
     */
    public void printBoard() {
        for (int row = 0; row < this.ROWS; row++) {
            for (int col = 0; col < this.COLUMNS; col++) {
                System.out.print("| " + this.board[row][col] + " ");
            }
            System.out.println("|");
        }

        for (int col = 0; col < this.COLUMNS; col++) {
            System.out.print("----");
        }
        System.out.println("-");
    }

    /**
     * Checks the entire game board for a Connect Four condition.
     *
     * @return {@code true} if there is a Connect Four, {@code false} otherwise.
     */
    public boolean isConnectFour() {
        int count = 0;

        char currentChip = 'X';

        //check the whole board

        //horizontal check
        for (int i = 0; i < this.ROWS; i++) {
            for (int j = 0; j < this.COLUMNS; j++) {
                if (count == 4) {
                    return true;
                }
                if (this.board[i][j] == currentChip) {
                    count++;
                }
                else {
                    count = 0;
                    if (currentChip == 'X') {
                        currentChip = 'O';
                    }
                    else {
                        currentChip = 'X';
                    }
                }
            }
            count = 0;
        }
        count = 0;
        // vertical check
        for (int i = 0; i < this.COLUMNS; i++) {
            for (int j = this.ROWS - 1; j >= 0; j--) {
                if (count == 4) {
                    return true;
                }
                if (this.board[j][i] == currentChip) {
                    count++;
                }
                else {
                    count = 1;
                    if (currentChip == 'X') {
                        currentChip = 'O';
                    }
                    else {
                        currentChip = 'X';
                    }
                }
            }
            count = 0;
        }
        // Diagonal check from top left to bottom right
        for (int i = 0; i <= this.ROWS - 4; i++) {
            for (int j = 0; j <= this.COLUMNS - 4; j++) {
                if (board[i][j] != ' ' && board[i][j] == board[i+1][j+1] && board[i+1][j+1] == board[i+2][j+2] &&
                        board[i+2][j+2] == board[i+3][j+3]) {
                    return true;
                }
            }
        }
        // Diagonal check from top right to bottom left
        for (int i = 0; i <= this.ROWS - 4; i++) {
            for (int j = this.COLUMNS - 4; j < this.COLUMNS; j++) {
                if (board[i][j] != ' ' && board[i][j] == board[i+1][j-1] && board[i+1][j-1] == board[i+2][j-2] &&
                        board[i+2][j-2] == board[i+3][j-3]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Advances the game to the next player's turn.
     */
    public void nextTurn() {
        if (this.currentPlayer == this.playerO) {
            this.currentPlayer = this.playerX;
        }
        else{
            this.currentPlayer = this.playerO;
        }
    }

    /**
     * Attempts to place a chip in the specified column for the current player.
     *
     * @param column The column index where the player wants to place their chip.
     * @return {@code true} if the chip was successfully placed, {@code false} if the column is full.
     */
    public boolean placeChip(int column) {
        int row = this.ROWS - 1;
        char chipToPlace = ' ';

        if (this.currentPlayer == this.playerX) {
            chipToPlace = 'X';
        }
        else {
            chipToPlace = 'O';
        }

        while (row >= 0) {
            if (this.board[row][column] == ' ') {
                this.board[row][column] = chipToPlace;
                return true;
            }
            row--;
        }
        return false;
    }

    /**
     * Runs the Connect Four game between two human players.
     *
     * @param Connect_Four The instance of the Connect4Logic to use for running the game.
     */
    public void playGame(Connect4Logic Connect_Four) {
        System.out.println("Begin Game.");
        Connect_Four.printBoard();

        while (!isConnectFour()) {

            if (this.turnCount == this.maxChips) {
                System.out.println("Game ended in a draw!");
                return;
            }

            Connect4TextConsole userInput = new Connect4TextConsole();
            int columnChosen = (userInput.getIntFromUser(this.currentPlayer)) - 1;

            if (!placeChip(columnChosen) || columnChosen < 0 || columnChosen > 7) {
                System.out.println("Column is full or number is not between 1 - 7, choose another number!");
                columnChosen = (userInput.getIntFromUser(this.currentPlayer)) - 1;
                placeChip(columnChosen);
            }

            Connect_Four.printBoard();
            this.turnCount++;
            if (isConnectFour()) {
                System.out.printf("%s Won The Game!", this.currentPlayer);
            }
            nextTurn();
        }
    }

    /**
     * Runs the Connect Four game between a human player and a computer player.
     *
     * @param Connect_Four The instance of the Connect4Logic to use for running the game.
     */
    public void playGameComputer(Connect4Logic Connect_Four) {
        System.out.println("Start Game Against Computer.");
        Connect_Four.printBoard();
        int lastMove = -1;

        while (!isConnectFour()) {
            if (this.turnCount == this.maxChips) {
                System.out.println("Game ended in a draw!");
                return;
            }

            Connect4TextConsole userInput = new Connect4TextConsole();
            int columnChosen;

            if (this.currentPlayer.equals(this.playerX)) {
                columnChosen = (userInput.getIntFromUser(this.currentPlayer)) - 1;
                placeChip(columnChosen);
                lastMove = columnChosen;
            } else {
                System.out.println("Computer Choosing...");
                columnChosen = this.computerPlayer.decideMove(lastMove);
                while (!placeChip(columnChosen)) {
                    columnChosen = this.computerPlayer.decideMove(lastMove);
                }
            }

            Connect_Four.printBoard();
            this.turnCount++;
            if (isConnectFour()) {
                if (this.currentPlayer == this.playerO) {
                    System.out.println("The Computer Won The Game!");
                }
                else {
                    System.out.printf("You Won The Game!");
                }
            }
            nextTurn();
        }
    }

    /**
     * Starts the Connect Four game based on the player's choice of opponent.
     *
     * @param Connect_Four The instance of the Connect4Logic to use for running the game.
     */
    public void startGame(Connect4Logic Connect_Four) {
        Connect4TextConsole userInput = new Connect4TextConsole();
        String gameChoice = userInput.getPlayChoice();

        if (gameChoice.equals("C")) {
            this.computerPlayer = new Connect4ComputerPlayer(Connect_Four);
        }

        if (gameChoice.equals("P")) {
            Connect_Four.playGame(Connect_Four);
        } else if (gameChoice.equals("C")) {
            Connect_Four.playGameComputer(Connect_Four);
        }
    }

    /**
     * The main method that creates a game instance and starts the game.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args){
        Connect4Logic game = new Connect4Logic();
        game.startGame(game);
    }
}
