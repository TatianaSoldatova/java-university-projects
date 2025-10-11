import java.util.Scanner;

public class LoopsExercises {
    static Scanner input = new Scanner(System.in);

    public static void main() {
        sumOfIntegers();
        promptToContinueDoWhile();
        promptToContinueWhile();
        input.close();
    }

    /**
     * Computes the sum of integers (negative, positive or zero) provided by the user
     * -1 marks the end of the user's input
     */
    public static void sumOfIntegers(){
        final String MSG_PROMPT = "Enter an integer (-1 to stop the input): ";
        final String MSG_ERR    = "Invalid input!\n";
        final String MSG_RESULT = "The sum of integers is: ";

        int sum = 0;
        int num = 0;

        do{
            num = UserInputUtils.readInt(input, MSG_PROMPT, MSG_ERR);
            if(num != -1){
                sum += num;
            }
        }while(num != -1);

        System.out.println(MSG_RESULT + sum);
        System.out.println();
    }

    /**
     * Asks the user if he wants to continue and waits for the user to pres Enter to do so
     * If no, exits the program
     * do while case
     */
    public static void promptToContinueDoWhile(){
        final String MSG_TITLE      = "1. do while case: ";
        final String ERR_INPUT      = "Error, you should answer y(es) or n(o)!\n";
        final String MSG_QUESTION   = "Do you want to continue? [(y)es or (n)o]: ";
        final String YES_CONTINUE   = "\nYou have chosen to continue...\n";

        char answer = ' ';
        boolean toContinue = true;

        System.out.print(MSG_TITLE);
        do{
            System.out.print(MSG_QUESTION);
            answer = UserInputUtils.readChar(input);

            if(answer != 'n' && answer != 'y' && answer != 'N' && answer != 'Y'){
                System.out.println(ERR_INPUT);
            }else if(answer == 'y' || answer == 'Y'){
                System.out.print(YES_CONTINUE);
                UserInputUtils.readPressToContinue(input);
            }else{
                toContinue = false;
            }
        }while(answer != 'n' && answer != 'y' && answer != 'N' && answer != 'Y'
                || toContinue);
        System.out.println("END\n");
    }

    /**
     * Asks the user if he wants to continue and waits for the user to pres Enter to do so
     * If no, exits the program
     * while case
     */
    public static void promptToContinueWhile(){
        final String MSG_TITLE      = "2. while case: ";
        final String ERR_INPUT      = "Error, you should answer y(es) or n(o)!\n";
        final String MSG_QUESTION   = "Do you want to continue? [(y)es or (n)o]: ";
        final String YES_CONTINUE   = "\nYou have chosen to continue...\n";

        char answer = ' ';
        boolean toContinue = true;

        System.out.print(MSG_TITLE);
        System.out.print(MSG_QUESTION);
        answer = UserInputUtils.readChar(input);

        while(answer != 'n' && answer != 'y' && answer != 'N' && answer != 'Y'
                || toContinue){

            if(answer != 'n' && answer != 'y' && answer != 'N' && answer != 'Y'){
                System.out.println(ERR_INPUT);
                System.out.print(MSG_QUESTION);
                answer = UserInputUtils.readChar(input);
            }else if(answer == 'y' || answer == 'Y'){
                System.out.print(YES_CONTINUE);
                UserInputUtils.readPressToContinue(input);
                System.out.print(MSG_QUESTION);
                answer = UserInputUtils.readChar(input);
            }else{
                toContinue = false;
            }
        }
        System.out.println("END\n");
    }

}
