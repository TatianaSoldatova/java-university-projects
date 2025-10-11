import java.util.Scanner;

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
     * Takes user's char input and ensures to clear the input buffer
     * @return character input
     */
    public static char readChar(Scanner input) {
        char c = input.next().charAt(0);
        input.nextLine(); // clear leftover newline
        return c;
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
