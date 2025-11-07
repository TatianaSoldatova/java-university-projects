import java.util.Scanner;
import utils.UserInputUtils;

/**
 * This program allows preparing invoices and calculating revenues for a small
 * car wash business: La Boite à Savon.
 * No OOP, no methods, no arrays permitted.
 * @author Tatiana Soldatova
 * @version 10.2025
 */
public class BillingService {
    static Scanner input = new Scanner(System.in);

    //----------
    //CONSTANTS
    //----------
    public static final String MSG_PRESENTATION         = "***********************\n"
                                                            + " T H E  S O A P  B O X\n"
                                                            + "***********************\n\n"
                                                            + "   ** INVOICING **   ";

    public static final String MSG_MENU                 = "\n----\nMENU\n----\n"
                                                            + "1. Prepare an invoice\n"
                                                            + "2. Display the total revenue\n"
                                                            + "3. Reset the total revenue\n"
                                                            + "4. Exit the program"
                                                            + "\n\nEnter your choice: ";

    public static final String MSG_INVOICING            = "\n---------\n"
                                                            + "INVOICING\n"
                                                            + "---------\n";

    public static final String MSG_REVENUES             = "\n---------\n"
                                                            + "REVENUE\n"
                                                            + "---------\n";

    public static final String MSG_RESET_REVENUES       = "\n-------------------\n"
                                                            + "RESETTING REVENUES\n"
                                                            + "-------------------\n";

    public static final String PROMPT_CLIENT_NAME       = "Enter the client's name: ";
    public static final String PROMPT_CLIENT_ADDRESS    = "Enter the client's address: ";
    public static final String PROMPT_INVOICE_DATE      = "Enter the billing date: ";
    public static final String PROMPT_NBR_CARS          = "Enter the number of cars: ";
    public static final String MSG_CAR_WASH_MENU        = "%nCar #%d%n----------%n"
                                                            + "A) Exterior wash%n"
                                                            + "B) Interior wash%n"
                                                            + "C) Exterior and interior wash%n%n";
    public static final String PROMPT_WASH_CHOICE       = "Enter the type of wash: ";
    public static final String PROMPT_WAX               = "Would you like a hand wax application (Y/N)? ";
    public static final String PROMPT_RESET_REVENUES    = "Do you really want to reset the total revenue amount (Y/N)? ";
    public static final String MSG_SUCCESS_RESET_REVENUES  = "\n*** The total revenue amount has been successfully reset. ";
    public static final String MSG_CANCELED_RESET_REVENUES = "\n*** Operation canceled. ";

    public static final String WASH_INTER               = "Interior wash";
    public static final String WASH_EXTER               = "Exterior wash";
    public static final String WASH_EXTER_WAX           = "Exterior wash (with waxing)";

    public static final String WASH_EXTER_INTER         = "Exterior and interior wash";
    public static final String WASH_EXTER_INTER_WAX     = "Exterior and interior wash (with waxing)";

    public static final String INVOICE_FORMAT           = "%-45s%-1s%10.2f%2s";
    public static final String INVOICE_FORMAT_LN        = "%n%-45s%-1s%10.2f%2s";

    public static final String MSG_END_OF_PROGRAM       = "\n\nE N D  O F  P R O G R A M";

    public static final String ERR_MENU                 = "\nERROR! Invalid menu choice. Please try again...";
    public static final String ERR_NBR_CARS             = "\nERROR! Enter a number between 1 and 4. Please try again...";
    public static final String ERR_WASH_CHOICE          = "\nERROR! Enter A, B, or C. Please try again...";
    public static final String ERR_YES_NO_INPUT         = "\nERROR! Please answer with Y or N. Please try again...";

    public static final double PRICE_WASH_EXTER         = 25.50;
    public static final double PRICE_WASH_INTER         = 29.95;
    public static final double PRICE_WASH_EXTER_INTER   = 50.00;
    public static final double PRICE_MANUAL_WAXING      = 28.65;

    static void main() {
        //---------
        //VARIABLES
        //---------
        char menuChoice               = ' '; // 1 2 3 4
        char washTypeChoice           = ' '; // A B C
        char waxChoice                = ' '; // Y/N
        char washChoice1              = ' ';
        char washChoice2              = ' ';
        char washChoice3              = ' ';
        char washChoice4              = ' ';

        char waxChoice1               = ' ';
        char waxChoice2               = ' ';
        char waxChoice3               = ' ';
        char waxChoice4               = ' ';
        char resetRevenuesChoice      = ' '; // Y/N

        String clientName;
        String clientAddress;
        String invoiceDate;

        int nbrCars                 = 0;
        int invoiceNumber           = -1;

        double revenuesWithoutTaxes = 0;
        double subtotalWashPrice    = 0; // should be reset
        double totalWashPrice       = 0; // should be reset
        double tps                  = 0; // should be reset
        double tvq                  = 0; // should be reset

        //Present the program
        System.out.println(MSG_PRESENTATION);

        //Quit the program if user's menu choice = '4'
        do{
            System.out.print(MSG_MENU);
            menuChoice = UserInputUtils.readChar(input);

            switch (menuChoice) {
                case '1':
                    //PREPARER THE INVOICE
                    System.out.println(MSG_INVOICING);
                    clientName = UserInputUtils.readStringLine(input, PROMPT_CLIENT_NAME);
                    clientAddress = UserInputUtils.readStringLine(input, PROMPT_CLIENT_ADDRESS);
                    invoiceDate = UserInputUtils.readStringLine(input, PROMPT_INVOICE_DATE);

                    //Validate the number of cars entered
                    do{
                        System.out.print(PROMPT_NBR_CARS);
                        nbrCars = input.nextInt();
                        if(nbrCars < 1 || nbrCars > 4){
                            System.out.println(ERR_NBR_CARS);
                        }
                    }while(nbrCars < 1 || nbrCars > 4);

                    //Validate the wash and add it to the subtotal price
                    for(int i = 1; i <= nbrCars; i++){
                        //Validate the input for the wash type choice
                        System.out.printf(MSG_CAR_WASH_MENU, i);
                        do{
                            System.out.print(PROMPT_WASH_CHOICE);
                            washTypeChoice = UserInputUtils.readChar(input);
                            switch(washTypeChoice){
                                case 'A':
                                case 'a':
                                    subtotalWashPrice += PRICE_WASH_EXTER;
                                    break;
                                case 'B':
                                case 'b':
                                    subtotalWashPrice += PRICE_WASH_INTER;
                                    break;
                                case 'C':
                                case 'c':
                                    subtotalWashPrice += PRICE_WASH_EXTER_INTER;
                                    break;
                                default:
                                    System.out.println(ERR_WASH_CHOICE);
                            }
                        }while(washTypeChoice != 'A' && washTypeChoice != 'B' && washTypeChoice != 'C'
                                && washTypeChoice != 'a' && washTypeChoice != 'b' && washTypeChoice != 'c');

                        //Validate the input for the wax choice
                        if(washTypeChoice == 'a' || washTypeChoice == 'A'
                                || washTypeChoice == 'c' || washTypeChoice == 'C'){
                            do{
                                System.out.print(PROMPT_WAX);
                                waxChoice = UserInputUtils.readChar(input);

                                switch(waxChoice){
                                    case 'Y':
                                    case 'y':
                                        subtotalWashPrice += PRICE_MANUAL_WAXING;
                                        break;
                                    case 'N':
                                    case 'n':
                                        break;
                                    default:
                                        System.out.println(ERR_YES_NO_INPUT);
                                }
                            }while(waxChoice != 'Y' && waxChoice != 'N' && waxChoice != 'y' && waxChoice != 'n');
                        }

                        // Store the wash type in appropriate variables
                        // for displaying on the invoice later
                        if (i == 1) {
                            washChoice1 = washTypeChoice;
                            waxChoice1 = waxChoice;
                        } else if (i == 2) {
                            washChoice2 = washTypeChoice;
                            waxChoice2 = waxChoice;
                        } else if (i == 3) {
                            washChoice3 = washTypeChoice;
                            waxChoice3 = waxChoice;
                        } else if (i == 4) {
                            washChoice4 = washTypeChoice;
                            waxChoice4 = waxChoice;
                        }
                    }

                    //Calculate the revenues and taxes
                    revenuesWithoutTaxes += subtotalWashPrice;
                    tps = subtotalWashPrice * 5 / 100;
                    tps = Math.round((int)(tps * 1000) / 10.0) / 100.0;

                    tvq = subtotalWashPrice * 9.975 / 100;
                    tvq = Math.round((int)(tvq * 1000) / 10.0) / 100.0;

                    totalWashPrice = subtotalWashPrice + tps + tvq;

                    //Display the invoice
                    invoiceNumber++;
                    System.out.printf("%n%nINVOICE%n___________________________________________________________");
                    System.out.printf("%n%n%s%n", clientName);
                    System.out.printf("%s%n", clientAddress);
                    System.out.printf("%nINVOICE NUMBER : 100%d%n", invoiceNumber);
                    System.out.printf("DATE           : %s%n", invoiceDate);

                    System.out.printf("%n%n%-45S%-1S%12S", "DESCRIPTION", "|", "AMOUNT");
                    System.out.println("\n___________________________________________________________");

                    for(int i = 1; i <= nbrCars; i++){
                        if (i == 1) {
                            switch(washChoice1){
                                case 'A':
                                case 'a':
                                    if(waxChoice1 == 'y' || waxChoice1 == 'Y'){
                                        System.out.printf(INVOICE_FORMAT, WASH_EXTER_WAX, "|", (PRICE_WASH_EXTER + PRICE_MANUAL_WAXING), "$");
                                    }else {
                                        System.out.printf(INVOICE_FORMAT, WASH_EXTER, "|", PRICE_WASH_EXTER, "$");
                                    }
                                    break;
                                case 'B':
                                case 'b':
                                    System.out.printf(INVOICE_FORMAT, WASH_INTER, "|", PRICE_WASH_INTER, "$");
                                    break;
                                case 'C':
                                case 'c':
                                    if(waxChoice1 == 'y' || waxChoice1 == 'Y'){
                                        System.out.printf(INVOICE_FORMAT, WASH_EXTER_INTER_WAX, "|", (PRICE_WASH_EXTER_INTER + PRICE_MANUAL_WAXING), "$");
                                    }else {
                                        System.out.printf(INVOICE_FORMAT, WASH_EXTER_INTER, "|", PRICE_WASH_EXTER_INTER, "$");
                                    }
                                    break;
                            }
                        } else if (i == 2) {
                            switch(washChoice2){
                                case 'A':
                                case 'a':
                                    if(waxChoice2 == 'y' || waxChoice2 == 'Y'){
                                        System.out.printf(INVOICE_FORMAT_LN, WASH_EXTER_WAX, "|", (PRICE_WASH_EXTER + PRICE_MANUAL_WAXING), "$");
                                    }else {
                                        System.out.printf(INVOICE_FORMAT_LN, WASH_EXTER, "|", PRICE_WASH_EXTER, "$");
                                    }
                                    break;
                                case 'B':
                                case 'b':
                                    System.out.printf(INVOICE_FORMAT_LN, WASH_INTER, "|", PRICE_WASH_INTER, "$");
                                    break;
                                case 'C':
                                case 'c':
                                    if(waxChoice2 == 'y' || waxChoice2 == 'Y'){
                                        System.out.printf(INVOICE_FORMAT_LN, WASH_EXTER_INTER_WAX, "|", (PRICE_WASH_EXTER_INTER + PRICE_MANUAL_WAXING), "$");
                                    }else {
                                        System.out.printf(INVOICE_FORMAT_LN, WASH_EXTER_INTER, "|", PRICE_WASH_EXTER_INTER, "$");
                                    }
                                    break;
                            }
                        } else if (i == 3) {
                            switch(washChoice3){
                                case 'A':
                                case 'a':
                                    if(waxChoice3 == 'y' || waxChoice3 == 'Y'){
                                        System.out.printf(INVOICE_FORMAT_LN, WASH_EXTER_WAX, "|", (PRICE_WASH_EXTER + PRICE_MANUAL_WAXING), "$");
                                    }else {
                                        System.out.printf(INVOICE_FORMAT_LN, WASH_EXTER, "|", PRICE_WASH_EXTER, "$");
                                    }
                                    break;
                                case 'B':
                                case 'b':
                                    System.out.printf(INVOICE_FORMAT_LN, WASH_INTER, "|", PRICE_WASH_INTER, "$");
                                    break;
                                case 'C':
                                case 'c':
                                    if(waxChoice3 == 'y' || waxChoice3 == 'Y'){
                                        System.out.printf(INVOICE_FORMAT_LN, WASH_EXTER_INTER_WAX, "|", (PRICE_WASH_EXTER_INTER + PRICE_MANUAL_WAXING), "$");
                                    }else {
                                        System.out.printf(INVOICE_FORMAT_LN, WASH_EXTER_INTER, "|", PRICE_WASH_EXTER_INTER, "$");
                                    }
                                    break;
                            }
                        } else if (i == 4) {
                            switch(washChoice4){
                                case 'A':
                                case 'a':
                                    if(waxChoice4 == 'y' || waxChoice4 == 'Y'){
                                        System.out.printf(INVOICE_FORMAT_LN, WASH_EXTER_WAX, "|", (PRICE_WASH_EXTER + PRICE_MANUAL_WAXING), "$");
                                    }else {
                                        System.out.printf(INVOICE_FORMAT_LN, WASH_EXTER, "|", PRICE_WASH_EXTER, "$");
                                    }
                                    break;
                                case 'B':
                                case 'b':
                                    System.out.printf(INVOICE_FORMAT_LN, WASH_INTER, "|", PRICE_WASH_INTER, "$");
                                    break;
                                case 'C':
                                case 'c':
                                    if(waxChoice4 == 'y' || waxChoice4 == 'Y'){
                                        System.out.printf(INVOICE_FORMAT_LN, WASH_EXTER_INTER_WAX, "|", (PRICE_WASH_EXTER_INTER + PRICE_MANUAL_WAXING), "$");
                                    }else {
                                        System.out.printf(INVOICE_FORMAT_LN, WASH_EXTER_INTER, "|", PRICE_WASH_EXTER_INTER, "$");
                                    }
                                    break;
                            }
                        }
                    }

                    System.out.println();
                    System.out.printf(INVOICE_FORMAT_LN, "Subtotal", "|", subtotalWashPrice, "$");
                    System.out.printf(INVOICE_FORMAT_LN, "TPS", "|", tps, "$");
                    System.out.printf(INVOICE_FORMAT_LN, "TVQ", "|", tvq, "$");
                    System.out.println("\n___________________________________________________________");
                    System.out.printf(INVOICE_FORMAT_LN, "TOTAL", "|", totalWashPrice, "$");
                    System.out.println();

                    //Reset the variables
                    subtotalWashPrice  = 0;
                    totalWashPrice     = 0;
                    tps                = 0;
                    tvq                = 0;

                    System.out.println();
                    UserInputUtils.readPressToContinue(input);
                    break;
                case '2':
                    //DISPLAY THE AMOUNT OF REVENUES
                    System.out.println(MSG_REVENUES);
                    System.out.printf("%-10s%10.2f %s", "TOTAL : ",  revenuesWithoutTaxes, "$");

                    System.out.println("\n");
                    UserInputUtils.readPressToContinue(input);
                    break;
                case '3':
                    //RESET THE AMOUNT OF REVENUES
                    System.out.println(MSG_RESET_REVENUES);

                    do{
                        System.out.print(PROMPT_RESET_REVENUES);
                        resetRevenuesChoice = UserInputUtils.readChar(input);

                        switch(resetRevenuesChoice){
                            case 'Y':
                            case 'y':
                                revenuesWithoutTaxes = 0;
                                System.out.println(MSG_SUCCESS_RESET_REVENUES);
                                break;
                            case 'N':
                            case 'n':
                                System.out.println(MSG_CANCELED_RESET_REVENUES);
                                break;
                            default:
                                System.out.println(ERR_YES_NO_INPUT);
                        }
                    }while(resetRevenuesChoice != 'Y' && resetRevenuesChoice != 'N'
                            && resetRevenuesChoice != 'y' && resetRevenuesChoice != 'n');

                    UserInputUtils.readPressToContinue(input);
                    break;
                case '4':
                    //QUIT THE PROGRAM
                    System.out.println(MSG_END_OF_PROGRAM);
                    break;
                default:
                    //Display input error
                    System.out.println(ERR_MENU);
            }
        }while(menuChoice != '4');

        input.close();
    }
}
