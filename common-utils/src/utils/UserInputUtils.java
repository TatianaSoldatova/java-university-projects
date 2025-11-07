package utils;

import java.util.Scanner;

/**
 * Open the Project Structure
 * Go to Modules → [select one of your other modules] → Dependencies tab
 * Click + → Module Dependency, then choose your common-utils module.
 * This tells IntelliJ (and the compiler) that this module can see the code inside common-utils.
 * Repeat thi step for each module that needs to use your utility code.
 */
public class UserInputUtils {
    /**
     * Validates integer input with prompt
     * @return integer input
     */
    public static int readInt(Scanner input, String messagePrompt, String messageError) {
        while (true) {
            System.out.print(messagePrompt);
            if(input.hasNextInt()) {
                return input.nextInt();
            }else{
                System.out.println(messageError);
                input.next(); // discard invalid input
            }
        }
    }

    /**
     * Validates long input with prompt
     * @return long input
     */
    public static long readLong(Scanner input, String messagePrompt, String messageError) {
        while (true) {
            System.out.print(messagePrompt);
            if(input.hasNextLong()) {
                return input.nextLong();
            }else{
                System.out.println(messageError);
                input.next(); // discard invalid input
            }
        }
    }

    /**
     * Takes user's char input and ensures to clear the input buffer
     * @return character input
     */
    public static char readChar(Scanner input) {
        char c = input.next().charAt(0);
        input.nextLine(); // clear leftover newline
        return c;
    }

    /**
     * Prompts the user with a message and reads a single word from the input.
     * This method prints the provided message to the console and then reads
     * the next token (word) entered by the user, stopping at any whitespace.
     * It does not read an entire line, only the first word typed
     * @param input the Scanner object used to read input from the user
     * @param message (prompt message with new line)
     * @return the next word entered by the user as a String
     */
    public static String readStringWordLn(Scanner input, String message) {
        System.out.println(message);
        return input.next();
    }

    /**
     * Prompts the user with a message and reads a single word from the input.
     * This method prints the provided message to the console and then reads
     * the next token (word) entered by the user, stopping at any whitespace.
     * It does not read an entire line, only the first word typed
     * @param input the Scanner object used to read input from the user
     * @param message (prompt message without new line)
     * @return the next word entered by the user as a String
     */
    public static String readStringWord(Scanner input, String message) {
        System.out.print(message);
        return input.next();
    }

    /**
     * Prompts the user with a message and reads an entire line of input.
     * This method prints the provided message to the console and then reads
     * the full line entered by the user, including spaces, until the user
     * presses Enter. It is useful for capturing multi-word input such as names,
     * addresses, or sentences.
     * @param input the Scanner object used to read input from the user
     * @param message the message to display to prompt the user (with new line)
     * @return the full line entered by the user as a String
     */
    public static String readStringLineLn(Scanner input, String message) {
        System.out.println(message);
        return input.nextLine();
    }

    /**
     * Prompts the user with a message and reads an entire line of input.
     * This method prints the provided message to the console and then reads
     * the full line entered by the user, including spaces, until the user
     * presses Enter. It is useful for capturing multi-word input such as names,
     * addresses, or sentences.
     * @param input the Scanner object used to read input from the user
     * @param message the message to display to prompt the user (without new line)
     * @return the full line entered by the user as a String
     */
    public static String readStringLine(Scanner input, String message) {
        System.out.print(message);
        return input.nextLine();
    }

    /**
     * Waits for the user to press ENTER to continue the execution
     */
    public static void readPressToContinue(Scanner input) {
        final String MSG_CONTINUE = "Press ENTER to continue...";
        System.out.println(MSG_CONTINUE);
        input.nextLine();
    }
}
