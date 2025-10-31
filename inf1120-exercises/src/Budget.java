import java.util.Scanner;

public class Budget {
    static Scanner input = new Scanner(System.in);

    final static String MSG_PROMPT_MENU_CHOICE  = "\nEnter your choice: ";
    final static String MSG_PROMPT_EXPENSE      = "\nEnter an expense (0 to cancel): ";
    final static String MSG_PROMPT_INCOME       = "\nEnter an income (0 to cancel): ";
    final static String MSG_ALERT               = "\nALERT!!! You are in the RED!\n";

    final static String ERR_INPUT_CHAR          = "\nError! The entered character is invalid!";
    final static String ERR_AMOUNT              = "\nError! The amount must be positive... Try again!";

    /**
     * Displays the main menu of the budget management program
     */
    public static void showMenu() {
        System.out.println("\n\n-----------------------"
                + "\nBUDGET\n"
                + "-----------------------");
        System.out.println("1. Add expenses");
        System.out.println("2. Add income");
        System.out.println("3. Show summary");
        System.out.println("4. Quit");
    }

    /**
     * Displays a message indicating that the program has ended normally.
     */
    public static void showEndProgram() {
        System.out.println("\n\nNormal end of the program.");
    }

    /**
     * Reads and validates a character within the given bounds.
     * @param msgPrompt the prompt message asking the user to enter a character
     * @param msgErr the error message displayed for an invalid character
     * @param charMin the minimum valid character bound
     * @param charMax the maximum valid character bound
     * @return the valid character entered by the user
     */
    public static char validateCharacter(String msgPrompt, String msgErr, char charMin, char charMax) {
        char car = ' ';
        do{
            System.out.print(msgPrompt);
            car = input.next().charAt(0);
            input.nextLine();
            if (car < '1' || car > '4') {
                System.out.print(msgErr);
            }
        }while(car < '1' || car > '4');
        return car;
    }

    /**
     * Reads and validates a real number within the given bounds.
     * @param msgPrompt the prompt message asking the user to enter a real number
     * @param msgErr the error message displayed for an invalid real number
     * @param min the minimum valid bound for the real number
     * @param max the maximum valid bound for the real number
     * @return the valid real number entered by the user
     */
    public static double validateRealNumber(String msgPrompt, String msgErr, double min, double max) {
        double reel = 0.0;
        do{
            System.out.print(msgPrompt);
            reel = input.nextDouble();
            input.nextLine();
            if (reel < min || reel > max) {
                System.out.print(msgErr);
            }
        }while(reel < min || reel > max);
        return reel;
    }

    /**
     * Calculates the sum of a series of amounts (double) entered by the user.
     * @param msgPrompt the prompt message asking the user to enter an amount
     * @param msgErr the error message displayed for an invalid amount
     * @return the total sum of the entered amounts
     */
    public static double sumTheAmounts(String msgPrompt, String msgErr){
        double amount = 0;
        double sum = 0;
        do{
            amount = validateRealNumber(msgPrompt, msgErr, 0.0, Double.MAX_VALUE);
            if(amount != 0){
                sum += amount;
            }
        }while(amount != 0);
        return sum;
    }

    /**
     * Displays the budget summary.
     * @param totalIncome the total sum of all income
     * @param totalExpenses the total sum of all expenses
     */
    public static void showSummary(double totalIncome, double totalExpenses){
        System.out.println("\nBUDGET\n"
                + "-----------------------");
        System.out.printf("%-10s%-2s%8.2f%3s",   "INCOME",  ":", totalIncome, "$");
        System.out.printf("%n%-10s%-2s%8.2f%3s", "EXPENSES", ":", totalExpenses, "$");
        System.out.println("\n-----------------------");
        System.out.printf("%-10s%-2s%8.2f%3s%n", "BALANCE",    ":", (totalIncome-totalExpenses), "$");

        if(totalIncome-totalExpenses < 0){
            System.out.print(MSG_ALERT);
        }
    }

    /**
     * This method pauses the program by asking the user
     * to press the [ENTER] key to continue.
     */
    public static void pause() {
        System.out.print("\nPress <ENTER> to continue...");
        input.nextLine();
    }

    public static void main (String[] args) {
        char menu = ' ';
        double totalDepenses = 0;
        double totalRevenus= 0;

        do{
            showMenu();
            menu = validateCharacter(MSG_PROMPT_MENU_CHOICE, ERR_INPUT_CHAR, '1', '4' );

            switch (menu) {
                case '1':
                    // DEPENSES
                    totalDepenses = sumTheAmounts(MSG_PROMPT_EXPENSE, ERR_AMOUNT);
                    break;
                case '2':
                    // REVENUS
                    totalRevenus = sumTheAmounts(MSG_PROMPT_INCOME, ERR_AMOUNT);
                    break;
                case  '3':
                    // BILAN
                    showSummary(totalRevenus, totalDepenses);
                    pause();
                    break;
                case '4':
                    showEndProgram();
                    break;
            }
        }while(menu != '4');
    }
}
