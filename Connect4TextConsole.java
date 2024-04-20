package IO;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class handles text input and output for the Connect Four game.
 * It facilitates user interactions through the console.
 */
public class Connect4TextConsole {
    private Scanner scan;

    /**
     * Constructor for Connect4TextConsole.
     * Initializes a new Scanner object for input.
     */
    public Connect4TextConsole(){
        this.scan = new Scanner(System.in);
    }

    /**
     * Prompts the current player to choose a column number and validates the input.
     *
     * @param currentPlayer The current player's name.
     * @return The valid column number chosen by the player.
     */
    public int getIntFromUser(String currentPlayer) {
        int input = -1;
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.printf("%s - your turn. Choose a column number from 1-7: ", currentPlayer);
                input = scan.nextInt();

                if (isValidInt(input)) {
                    isValid = true;
                } else {
                    System.out.println("Invalid input! Please choose a number between 1 and 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number from 1-7.");
                scan.nextLine();
            }
        }
        return input;
    }

    /**
     * Gets the user's choice to play against another player or the computer.
     *
     * @return 'P' for playing against another player, 'C' for playing against the computer.
     */
    public String getPlayChoice() {
        String input = "";
        boolean isValid = false;

        while (!isValid) {
            System.out.println("Begin Game. Enter ‘P’ if you want to play against another player; enter ‘C’ to play against computer.");
            input = scan.next().toUpperCase();

            if (isValidString(input)) {
                isValid = true;
            } else {
                System.out.println("Invalid choice! Please enter ‘P’ or ‘C’.");
            }
        }
        return input;
    }

    /**
     * Validates if the given integer is within the range of 1 to 7.
     *
     * @param num The number to validate.
     * @return True if the number is valid, false otherwise.
     */
    public boolean isValidInt (int num) {
        if (num >= 1 && num <= 7) {
            return true;
        }
        return false;
    }

    /**
     * Validates if the given string is either 'P' or 'C'.
     *
     * @param choice The string to validate.
     * @return True if the string is valid, false otherwise.
     */
    public boolean isValidString(String choice) {
        if (choice.equals("P") || choice.equals("C")) {
            return true;
        }
        return false;
    }
}
