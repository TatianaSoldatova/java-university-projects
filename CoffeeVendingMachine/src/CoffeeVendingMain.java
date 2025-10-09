import java.util.Scanner;

/**
 * This program manages the operation of a small coffee vending machine
 * No OOP, no arrays permitted
 *
 * @author tatiana soldatova
 * @version 2025
 */
public class CoffeeVendingMain {
    static Scanner input = new Scanner(System.in);

    //----------
    //CONSTANTS
    //----------
    public static final String MSG_PRESENTATION     = "\n*** COFFEE VENDING MACHINE ***";
    public static final String MSG_END_OF_PROGRAM   = "\n\nEnd of program";
    public static final String MSG_MAIN_MENU        = "\n---------\nMAIN MENU\n---------\n"
            + "1. Order a coffee\n2. Restock the coffee machine\n"
            + "3. Display the inventory and balance\n"
            + "4. Exit"
            + "\n\nEnter your choice: ";
    public static final String MSG_CONTINUE         = "\nPress <ENTER> to continue...";

    public static final String MSG_COFFEE_TYPES     = "\nTYPES OF COFFEE:";
    public static final String MSG_COFFEE_CHOICE    = "\nMake your coffee choice: ";
    public static final String MSG_SUGAR            = "With sugar? (y/n) : ";
    public static final String MSG_MILK             = "With milk? (y/n) : ";
    public static final String MSG_COLOMBIAN        = "(C)olombian";
    public static final String MSG_HOUSE            = "(H)ouse";
    public static final String MSG_FRENCH           = "(F)rench";
    public static final String MSG_ASK_FOR_MONEY    = "Enter a coin (2$, 1$, 25¢, 10¢, 5¢): ";
    public static final String MSG_COFFEE_READY     = "\nYour coffee is ready!";

    public static final String MSG_ERR_MENU_INPUT = "\nInvalid choice! Try again!";
    public static final String MSG_ERR_OUT_OF_ORDER = "\n*** Coffee Machine is OUT OF ORDER! ***";
    public static final String MSG_ERR_PAYMENT      = "\nThis coin is not valid... try again!";
    public static final String MSG_ERR_CHANGE       = "\n*** Impossible to give exact change! ***\n    Your order was cancelled";

    //Per coffee ingredients quantities
    public static final int ONE_COFFEE              = 7;
    public static final int ONE_MILK                = 12;
    public static final int ONE_SUGAR               = 5;
    public static final int ONE_CUP                 = 1;

    //Ingredients price in cents
    public static final int ONE_COFFEE_PRICE        = 100;
    public static final int ONE_MILK_PRICE          = 10;
    public static final int ONE_SUGAR_PRICE         = 5;

    //Max ingredients and coins quantities per machine
    public static final int COFFEE_MAX_QTY          = 10;
    public static final int SUGAR_MAX_QTY           = 5;
    public static final int MILK_MAX_QTY            = 15;
    public static final int CUPS_MAX_QTY            = 3;
    public static final int COIN_25_CENTS_MAX       = 6;
    public static final int COIN_10_CENTS_MAX       = 10;
    public static final int COIN_5_CENTS_MAX        = 10;

    public static void main(String[] args) {
        //----------
        //Variables
        //----------
        char menuChoice                     = ' ';
        char coffeeChoice                   = ' ';
        char sugarChoice                    = ' ';
        char milkChoice                     = ' ';

        boolean isValidChoice               = false;

        float priceInDollars                = 0.0f;
        int priceInCents                    = 0;
        int totalReceivedCoins              = 0;
        int totalChangeCoinsToGiveBack      = 0;

        //Initial ingredients quantities
        int colombianCoffeeQty              = COFFEE_MAX_QTY;
        int frenchCoffeeQty                 = COFFEE_MAX_QTY;
        int houseCoffeeQty                  = COFFEE_MAX_QTY;
        int milkQty                         = MILK_MAX_QTY;
        int sugarQty                        = SUGAR_MAX_QTY;
        int cupsQty                         = CUPS_MAX_QTY;

        //Initial coins quantities
        int remainingCoinsQty_25            = COIN_25_CENTS_MAX;
        int remainingCoinsQty_10            = COIN_10_CENTS_MAX;
        int remainingCoinsQty_5             = COIN_5_CENTS_MAX;
        int givenCoinsQty_25                = 0;
        int givenCoinsQty_10                = 0;
        int givenCoinsQty_5                 = 0;

        int perCoffeePaymentReceived        = 0;

        displayMessageLn(MSG_PRESENTATION);

        //Quit the program if user's menu choice = '4'
        do{
            displayMessage(MSG_MAIN_MENU);
            menuChoice = readUsersChoice();

            switch(menuChoice){
                case '1':
                    //Order the coffee
                    if((colombianCoffeeQty < ONE_COFFEE && houseCoffeeQty < ONE_COFFEE && frenchCoffeeQty < ONE_COFFEE)
                            || (sugarQty < ONE_SUGAR || milkQty < ONE_MILK) || cupsQty < ONE_CUP){

                        displayMessageLn(MSG_ERR_OUT_OF_ORDER);
                        displayPressToContinueMessage();
                    } else{
                        //Reset machine's default values
                        isValidChoice               = false;
                        priceInCents                = 0;
                        totalReceivedCoins          = 0;
                        totalChangeCoinsToGiveBack  = 0;
                        givenCoinsQty_25            = 0;
                        givenCoinsQty_10            = 0;
                        givenCoinsQty_5             = 0;

                        do{
                            displayCoffeeTypesToChooseFrom(colombianCoffeeQty, houseCoffeeQty, frenchCoffeeQty);
                            coffeeChoice = Character.toUpperCase(readUsersChoice());

                            switch(coffeeChoice){
                                case 'C':
                                    if(colombianCoffeeQty < ONE_COFFEE){
                                        displayMessageLn(MSG_ERR_MENU_INPUT);
                                    }else{
                                        isValidChoice = true;
                                        //add to the price
                                        priceInCents = priceInCents + ONE_COFFEE_PRICE;
                                    }
                                    break;
                                case 'H':
                                    if(houseCoffeeQty < ONE_COFFEE){
                                        displayMessageLn(MSG_ERR_MENU_INPUT);
                                    }else{
                                        isValidChoice = true;
                                        //add to the price
                                        priceInCents = priceInCents + ONE_COFFEE_PRICE;
                                    }
                                    break;
                                case 'F':
                                    if(frenchCoffeeQty < ONE_COFFEE){
                                        displayMessageLn(MSG_ERR_MENU_INPUT);
                                    }else{
                                        isValidChoice = true;
                                        //add to the price
                                        priceInCents = priceInCents + ONE_COFFEE_PRICE;
                                    }
                                    break;
                                default:
                                    displayMessageLn(MSG_ERR_MENU_INPUT);
                                    break;
                            }

                            //Add sugar and/or milk
                            if(isValidChoice){
                                if(sugarQty >= ONE_SUGAR){
                                    do{
                                        displayMessage(MSG_SUGAR);
                                        sugarChoice = Character.toUpperCase(readUsersChoice());

                                        switch(sugarChoice){
                                            case 'Y':
                                                //add to the price
                                                priceInCents = priceInCents + ONE_SUGAR_PRICE;
                                                break;
                                            case 'N':
                                                break;
                                            default:
                                                displayMessageLn(MSG_ERR_MENU_INPUT);
                                        }
                                    } while(sugarChoice != 'Y' && sugarChoice != 'N');
                                }

                                if(milkQty > 0){
                                    do{
                                        displayMessage(MSG_MILK);
                                        milkChoice = Character.toUpperCase(readUsersChoice());

                                        switch(milkChoice){
                                            case 'Y':
                                                //add to the price
                                                priceInCents = priceInCents + ONE_MILK_PRICE;
                                                break;
                                            case 'N':
                                                break;
                                            default:
                                                displayMessageLn(MSG_ERR_MENU_INPUT);
                                        }
                                    } while(milkChoice != 'Y' && milkChoice != 'N');
                                }
                            }
                        } while((colombianCoffeeQty >= ONE_COFFEE || houseCoffeeQty >= ONE_COFFEE
                                || frenchCoffeeQty >= ONE_COFFEE) && !isValidChoice);

                        //convert the price in dollars
                        priceInDollars = priceInCents / 100.0f;
                        System.out.printf("\nCoffee price : %.2f$\n", priceInDollars);

                        //Compute the order payment
                        while(totalReceivedCoins < priceInCents){

                            //Ask and compute the payment for coffee
                            displayMessage(MSG_ASK_FOR_MONEY);

                            while (!input.hasNextInt()) {
                                System.out.println("Invalid input! Please enter an integer.");
                                input.next(); // consume the invalid input
                                System.out.print(MSG_ASK_FOR_MONEY);
                            }

                            perCoffeePaymentReceived = input.nextInt();
                            input.nextLine();

                            if(perCoffeePaymentReceived == 2){
                                totalReceivedCoins += 200;
                            } else if(perCoffeePaymentReceived == 1){
                                totalReceivedCoins += 100;
                            }else if(perCoffeePaymentReceived == 25){
                                totalReceivedCoins +=  25;
                            }else if(perCoffeePaymentReceived == 10){
                                totalReceivedCoins += 10;
                            }else if(perCoffeePaymentReceived == 5){
                                totalReceivedCoins +=  5;
                            }else{
                                displayMessageLn(MSG_ERR_PAYMENT);
                            }

                            if(totalReceivedCoins >= priceInCents){
                                //Compute the change
                                totalChangeCoinsToGiveBack = totalReceivedCoins - priceInCents;

                                if(totalReceivedCoins > priceInCents){
                                    System.out.println("\nYour change is: " + totalChangeCoinsToGiveBack + " cents");
                                }

                                if(remainingCoinsQty_25 > 0 && totalChangeCoinsToGiveBack >= 25){
                                    givenCoinsQty_25 = totalChangeCoinsToGiveBack/25;

                                    if(remainingCoinsQty_25 >= givenCoinsQty_25){
                                        givenCoinsQty_25 = totalChangeCoinsToGiveBack/25;
                                        totalChangeCoinsToGiveBack = totalChangeCoinsToGiveBack % 25;
                                        remainingCoinsQty_25 -= givenCoinsQty_25;
                                    } else{
                                        givenCoinsQty_25 = remainingCoinsQty_25;
                                        remainingCoinsQty_25 -= givenCoinsQty_25;
                                        totalChangeCoinsToGiveBack -= (givenCoinsQty_25 * 25);
                                    }
                                }

                                if(remainingCoinsQty_10 > 0 && totalChangeCoinsToGiveBack >= 10){
                                    givenCoinsQty_10 = totalChangeCoinsToGiveBack/10;

                                    if(remainingCoinsQty_10 >= givenCoinsQty_10){
                                        givenCoinsQty_10 = totalChangeCoinsToGiveBack/10;
                                        totalChangeCoinsToGiveBack = totalChangeCoinsToGiveBack % 10;
                                        remainingCoinsQty_10 -= givenCoinsQty_10;
                                    } else{
                                        givenCoinsQty_10 = remainingCoinsQty_10;
                                        remainingCoinsQty_10 -= givenCoinsQty_10;
                                        totalChangeCoinsToGiveBack -= (givenCoinsQty_10 * 10);
                                    }
                                }

                                if(remainingCoinsQty_5 > 0 && totalChangeCoinsToGiveBack >= 5){
                                    givenCoinsQty_5 = totalChangeCoinsToGiveBack/5;

                                    if(remainingCoinsQty_5 >= givenCoinsQty_5){
                                        givenCoinsQty_5 = totalChangeCoinsToGiveBack/5;
                                        totalChangeCoinsToGiveBack = totalChangeCoinsToGiveBack % 5;
                                        remainingCoinsQty_5 -= givenCoinsQty_5;
                                    } else{
                                        givenCoinsQty_5 = remainingCoinsQty_5;
                                        remainingCoinsQty_5 -= givenCoinsQty_5;
                                        totalChangeCoinsToGiveBack -= (givenCoinsQty_5 * 5);
                                    }
                                }

                                //Check if there is enough coins to give the change
                                if(totalChangeCoinsToGiveBack != 0){
                                    //Cancel the order
                                    remainingCoinsQty_25 += givenCoinsQty_25;
                                    remainingCoinsQty_10 += givenCoinsQty_10;
                                    remainingCoinsQty_5 += givenCoinsQty_5;
                                    displayMessageLn(MSG_ERR_CHANGE);
                                }else{
                                    //Deduce the quantities
                                    switch(coffeeChoice){
                                        case 'C':
                                            colombianCoffeeQty -= ONE_COFFEE;
                                            break;
                                        case 'H':
                                            houseCoffeeQty -= ONE_COFFEE;
                                            break;
                                        case 'F':
                                            frenchCoffeeQty -= ONE_COFFEE;
                                            break;
                                    }

                                    if(sugarQty >= ONE_SUGAR && sugarChoice == 'Y'){
                                        sugarQty -= ONE_SUGAR;
                                    }

                                    if(milkQty >= ONE_MILK && milkChoice == 'Y'){
                                        milkQty -= ONE_MILK;
                                    }

                                    //Deduce cup quantity used for to prepare the order
                                    cupsQty = cupsQty - ONE_CUP;

                                    if(givenCoinsQty_25 != 0 || givenCoinsQty_10 != 0 || givenCoinsQty_5 != 0){
                                        System.out.println("\n* " +  givenCoinsQty_25 + " coin(s) of 25 cents.");
                                        System.out.println("* " +  givenCoinsQty_10 + " coin(s) of 10 cents.");
                                        System.out.println("* " +  givenCoinsQty_5 + " coin(s) of 5 cents.");
                                    }
                                    displayMessageLn(MSG_COFFEE_READY);
                                }
                            }
                        }
                        displayPressToContinueMessage();
                    }
                    break;
                case '2':
                    //Reset machine's ingredients and balance
                    colombianCoffeeQty      = COFFEE_MAX_QTY;
                    frenchCoffeeQty         = COFFEE_MAX_QTY;
                    houseCoffeeQty          = COFFEE_MAX_QTY;
                    milkQty                 = MILK_MAX_QTY;
                    sugarQty                = SUGAR_MAX_QTY;
                    cupsQty                 = CUPS_MAX_QTY;

                    remainingCoinsQty_25  = COIN_25_CENTS_MAX;
                    remainingCoinsQty_10  = COIN_10_CENTS_MAX;
                    remainingCoinsQty_5   = COIN_5_CENTS_MAX;

                    displayAvailableIngredientsAndBalance(COFFEE_MAX_QTY, milkQty, sugarQty, cupsQty,
                            remainingCoinsQty_25, remainingCoinsQty_10, remainingCoinsQty_5);

                    displayPressToContinueMessage();
                    break;
                case '3':
                    displayCoffeeMachineInventoryAndBalance(colombianCoffeeQty,houseCoffeeQty,frenchCoffeeQty,
                            sugarQty,milkQty,cupsQty, remainingCoinsQty_25, remainingCoinsQty_10,
                            remainingCoinsQty_5);
                    break;
                case '4':
                    displayMessageLn(MSG_END_OF_PROGRAM);
                    break;
                default:
                    displayMessageLn(MSG_ERR_MENU_INPUT);
                    break;
            }

        } while(menuChoice != '4');

        input.close();
    }

    //-------
    //METHODS
    //-------
    public static void displayMessage(String message){
        System.out.print(message);
    }

    public static void displayMessageLn(String message){
        System.out.println(message);
    }

    public static char readUsersChoice(){
        char choice = input.next().charAt(0);
        input.nextLine();
        return choice;
    }

    public static void displayCoffeeTypesToChooseFrom(int colombianCoffeeQty, int houseCoffeeQty, int frenchCoffeeQty){
        System.out.println(MSG_COFFEE_TYPES);

        if(colombianCoffeeQty >= ONE_COFFEE){
            System.out.println(MSG_COLOMBIAN);
        }

        if (houseCoffeeQty >= ONE_COFFEE){
            System.out.println(MSG_HOUSE);
        }

        if (frenchCoffeeQty >= ONE_COFFEE){
            System.out.println(MSG_FRENCH);
        }
        System.out.print(MSG_COFFEE_CHOICE);
    }

    public static void displayCoffeeMachineInventoryAndBalance(int colombianCoffee, int houseCoffee,
                                                               int frenchCoffee, int sugar, int milk,
                                                               int cup, int remainingCoinsQty_25,
                                                               int remainingCoinsQty_10,  int remainingCoinsQty_5) {
        // Display the general condition of coffee machine
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("COFFEE MACHINE INVENTORY AND BALANCE");
        System.out.println("------------------------------------");
        System.out.printf("%-20s : %2d gram(s)%n", "Colombian coffee", colombianCoffee);
        System.out.printf("%-20s : %2d gram(s)%n", "House coffee", houseCoffee);
        System.out.printf("%-20s : %2d gram(s)%n", "French coffee", frenchCoffee);
        System.out.printf("%-20s : %2d gram(s)%n", "Sugar", sugar);
        System.out.printf("%-20s : %2d gram(s)%n", "Milk", milk);

        System.out.printf("%-20s : %2d%n", "Cups", cup);
        System.out.printf("%-20s : %2d%n", "25 cents coins", remainingCoinsQty_25);
        System.out.printf("%-20s : %2d%n", "10 cents coins", remainingCoinsQty_10);
        System.out.printf("%-20s : %2d%n", "5 cents coins", remainingCoinsQty_5);

        displayPressToContinueMessage();
    }

    public static void displayAvailableIngredientsAndBalance(int coffeeQty, int milkQty, int sugarQty, int cupsQty,
                                                             int coins_25, int coins_10, int coins_5){

        System.out.println("\nThe coffee machine now contains :\n"  + "- " + coffeeQty + " grams of every type of coffee\n"
                + "- " + milkQty    + " grams of milk\n"
                + "- " + sugarQty   + " grams of sugar\n"
                + "- " + cupsQty    + " cups\n"
                + "- " + coins_25   + " coins of 25 cents\n"
                + "- " + coins_10   + " coins of 10 cents\n"
                + "- " + coins_5    + " coins of 5 cents\n");
    }

    public static void displayPressToContinueMessage() {
        System.out.println(MSG_CONTINUE);
        input.nextLine();
    }
}
