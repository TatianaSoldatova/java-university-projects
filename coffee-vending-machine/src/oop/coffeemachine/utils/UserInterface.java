package oop.coffeemachine.utils;

import java.util.Scanner;
import oop.coffeemachine.core.CoffeeMachine;
import oop.coffeemachine.core.CoffeeType;

/**
 * UserInterface class handles all interactions between the user
 * and the coffee machine application through the console.
 */
public class UserInterface {
    private final Scanner input = new Scanner(System.in);
    private final CoffeeMachine coffeeMachine;

    public UserInterface(CoffeeMachine coffeemachine) {
        this.coffeeMachine = coffeemachine;
    }

    /**
     * Displays a message on the console without adding a newline at the end.
     * @param message message the message to display
     */
    private void showMessage(String message){
        System.out.print(message);
    }

    /**
     * Displays a message on the console followed by a newline.
     * @param  message the message to display
     */
    public void showMessageln(String message){
        System.out.println(message);
    }

    /**
     * Displays a "press enter to continue" message and waits for the user to press Enter.
     */
    public void showPressToContinueMessage() {
        System.out.println(Messages.MSG_CONTINUE);
        nextLine();
    }

    /**
     * Reads and discards the next line of input from the user.
     * Used to pause the program or to clear the input buffer after reading other types of input.
     */
    public void nextLine() {
        input.nextLine();
    }

    /**
     * Closes the underlying Scanner used for user input.
     */
    public void close() {
        input.close();
    }

    /**
     * Reads a single character from the user input.
     * @return the first character of the next user input token
     */
    public char readUsersChoice(){
        char choice = input.next().charAt(0);
        nextLine();
        return choice;
    }

    /**
     * Displays the main menu and reads the user's choice.
     * @return the character representing the user's menu choice
     */
    public char showMainMenu(){
        showMessage(Messages.MSG_MAIN_MENU);
        return readUsersChoice();
    }

    /**
     * Asks the user to select a coffee type from the available options.
     * @return  the {@link CoffeeType} chosen by the user
     */
    public CoffeeType askForCoffeeType() {
        coffeeMachine.resetCoins();
        char choice;
        do{
            showMessageln(Messages.MSG_COFFEE_TYPES);
            if(coffeeMachine.hasEnoughCoffee(CoffeeType.COLOMBIAN)){
                showMessageln(Messages.MSG_COLOMBIAN);
            }
            if(coffeeMachine.hasEnoughCoffee(CoffeeType.HOUSE)){
                showMessageln(Messages.MSG_HOUSE);
            }
            if(coffeeMachine.hasEnoughCoffee(CoffeeType.FRENCH)){
                showMessageln(Messages.MSG_FRENCH);
            }
            showMessage(Messages.MSG_COFFEE_CHOICE);
            choice = readUsersChoice();

            if(!coffeeMachine.isValidChoice(choice)){
                showMessageln(Messages.MSG_ERR_MENU_INPUT);
            }
        }while(!coffeeMachine.isValidChoice(choice));
        return CoffeeType.fromChar(choice);
    }

    /**
     * Asks the user whether they want to add sugar or milk to their coffee.
     * @param message the prompt message to display to the user
     * @param hasEnough true if the ingredient is available, otherwise false
     * @return true if the user chooses to add the ingredient ('y'), false otherwise
     */
    public boolean askForSugarOrMilk(String message, boolean hasEnough){
        char choice;
        boolean with = false;
        if(hasEnough){
            do{
                showMessageln(message);
                choice = Character.toLowerCase(readUsersChoice());
                switch(choice){
                    case 'y':
                        with = true;
                        break;
                    case  'n':
                        break;
                    default:
                        showMessageln(Messages.MSG_ERR_MENU_INPUT);
                }
            }while(choice != 'y' && choice != 'n');
        }
        return with;
    }

    /**
     * Prompts the user for payment and processes the coffee purchase.
     * This method displays the price of the coffee, calls the coffee machine to
     * calculate the total price based on the coffee type and any added sugar or milk,
     * and then shows a "press enter to continue" message to pause the program.
     * @param type the type of coffee selected
     * @param withSugar true if the user wants sugar, false otherwise
     * @param withMilk true if the user wants milk, false otherwise
     * @param price the base price of the coffee in cents
     */
    public void askForPayment(CoffeeType type, boolean withSugar, boolean withMilk, int price){
        System.out.printf("\nCoffee price : %.2f$\n", price/100.0f);
        coffeeMachine.calculatePrice(type, withSugar, withMilk, price);
        showPressToContinueMessage();
    }

    /**
     * Prompts the user to enter a coin amount and validates the input.
     * This method repeatedly asks the user to enter an integer until a valid integer
     * is provided. Non-integer input is rejected with an error message.
     * @return the integer value entered by the user
     */
    public int validateEnteredCoin(){
        showMessageln(Messages.MSG_ASK_FOR_MONEY);
        while (!input.hasNextInt()) {
            System.out.println("Invalid input! Please enter an integer.");
            input.next(); // consume the invalid input
            System.out.print(Messages.MSG_ASK_FOR_MONEY);
        }
        return input.nextInt();
    }
}
