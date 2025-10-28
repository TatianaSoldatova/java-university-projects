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
     * Processes the player's input in String format and returns her/his response.
     * @param message (prompt message)
     */
    public static String readString(Scanner input, String message) {
        System.out.println(message);
        return input.next();
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
