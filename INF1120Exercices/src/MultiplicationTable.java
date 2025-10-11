import java.util.Scanner;

public class MultiplicationTable {
    static Scanner input = new Scanner(System.in);

    public static void main(String args[]){
        generateMultiplicationTableFromNumber();
        input.close();
    }

    /**
     * Generates a formatted multiplication table for the number entered by the user
     */
    public static void generateMultiplicationTableFromNumber(){
        final String ANSI_BLUE      = "\u001B[34m";
        final String ANSI_RED       = "\u001B[31m";
        final String ANSI_GREEN     = "\u001B[32m";
        final String ANSI_YELLOW    = "\u001B[33m";
        final String ANSI_RESET     = "\u001B[0m";

        final String MSG_TITLE              = "\n*** MULTIPLICATION TABLE ***";
        final String MSG_INPUT          = "\nEnter an integer between 1 and 12: ";
        final String MSG_ERR_INPUT      = "Error, your number should be between 1 and 12!";
        final String MSG_ERR_INPUT_INT  = "Error, invalid input!";

        int number = 0;

        System.out.println(MSG_TITLE);
        // Check the input
        do{
            number = UserInputUtils.readInt(input, MSG_INPUT, MSG_ERR_INPUT_INT);
            if(number < 1 || number > 12){
                System.out.println(MSG_ERR_INPUT);
            }
        }while(number < 1 || number > 12);

        // First line in RED
        for(int i = 0; i <= number; i++){
            if(i == 0){
                System.out.printf(ANSI_RED + "%5s%2s" + ANSI_RESET,  " ", "|");
            }else if(i > 0){
                System.out.printf(ANSI_RED + "%5s%2s" + ANSI_RESET,  i, "|");
            }
            if(i == number){
                System.out.println();
            }
        }
        for(int j = 0; j <= number; j++){
            System.out.print(" ----- ");
        }
        System.out.println();

        // Multiplication Table
        for(int i = 1; i <= number; i++){
            // First column in YELLOW
            System.out.printf(ANSI_YELLOW + "%5s%2s" + ANSI_RESET,  i, "|");

            for(int j = 1; j <= number; j++){
                // Table
                System.out.printf("%5s%2s",  (j * i), "|");
                if(j == number){
                    System.out.println();
                }
            }
            for(int j = 0; j <= number; j++){
                System.out.print(" ----- ");
            }
            System.out.println();
        }
    }
}
