package oop.coffeemachine.core;

import oop.coffeemachine.utils.UserInterface;
import oop.coffeemachine.utils.Messages;

/**
 * CoffeeMachine class represents a simple coffee vending machine.
 * It manages coffee inventory, ingredients (milk, sugar, cups), user interactions,
 * and payment processing through a wallet system. The class provides methods to:
 *      Check stock of coffee, milk, sugar, and cups.
 *      Validate user choices and order coffee.
 *      Calculate prices including optional sugar and milk.
 *      Process payments and dispense change.
 *      Restock the machine and display current inventory and wallet balance.
 */
public class CoffeeMachine {
    private final int ONE_COFFEE     = 7;
    private final int ONE_MILK       = 12;
    private final int ONE_SUGAR      = 5;
    private final int ONE_CUP        = 1;

    private final Inventory inventory;
    private final Wallet wallet;

    private UserInterface userInterface;

    //CONSTRUCTOR
    public CoffeeMachine() {
        this.inventory = new Inventory();
        this.wallet = new Wallet();
    }

    //METHODS
    /**
     * Sets the {@link UserInterface} instance that this CoffeeMachine will use
     * interact with the user.
     * @param userInterface the UserInterface object to associate with this CoffeeMachine
     */
    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    /**
     * Checks if the coffee machine has enough stock of the specified coffee type
     * to prepare a single serving.
     * @param type the type of coffee to check
     * @return true if there is enough coffee of the specified type, false otherwise
     */
    public boolean hasEnoughCoffee(CoffeeType type){
        if(type == CoffeeType.COLOMBIAN && inventory.getColombianCoffeeQty() >= ONE_COFFEE){
            return true;
        }else if(type == CoffeeType.FRENCH && inventory.getFrenchCoffeeQty() >= ONE_COFFEE){
            return true;
        }
        else return type == CoffeeType.HOUSE && inventory.getHouseCoffeeQty() >= ONE_COFFEE;
    }

    /**
     * Checks if the coffee machine has enough milk to prepare a single serving.
     * @return true if there is enough milk, false otherwise
     */
    public boolean hasEnoughMilk(){
        return inventory.getMilkQty() >= ONE_MILK;
    }

    /**
     * Checks if the coffee machine has enough sugar to prepare a single serving.
     * @return true if there is enough sugar, false otherwise
     */
    public boolean hasEnoughSugar(){
        return inventory.getSugarQty() >= ONE_SUGAR;
    }

    /**
     * Checks if the coffee machine has enough cups to prepare a single serving.
     * @return true if there is enough cups, false otherwise
     */
    public boolean hasEnoughCups(){
        return inventory.getCupsQty() >= ONE_CUP;
    }

    /**
     * Checks if the user's choice corresponds to a valid coffee type that has
     * enough stock to prepare a single serving.
     * @param choice the character entered by the user
     * @return true if the choice corresponds to a coffee type with sufficient stock, false otherwise
     */
    public boolean isValidChoice(char choice){
        choice = Character.toLowerCase(choice);
        if(choice == 'c' && hasEnoughCoffee(CoffeeType.COLOMBIAN)){
            return true;
        } else if(choice == 'h' && hasEnoughCoffee(CoffeeType.HOUSE)){
            return true;
        }else return choice == 'f' && hasEnoughCoffee(CoffeeType.FRENCH);
    }

    /**
     * Checks if the coffee machine is ready to serve at least one type of coffee.
     * The machine is considered "in order" if all the following conditions are met:
     * - There is enough milk.
     * - There is enough sugar.
     * - There are enough cups.
     * - There is enough coffee for at least one of the following types: COLOMBIAN, FRENCH or HOUSE
     * @return true if the machine has sufficient resources to serve at least one coffee type, false otherwise
     */
    public boolean isInOrder(){
        return hasEnoughMilk() && hasEnoughSugar() && hasEnoughCups()
                && (hasEnoughCoffee(CoffeeType.COLOMBIAN)
                || hasEnoughCoffee(CoffeeType.FRENCH)
                || hasEnoughCoffee(CoffeeType.HOUSE));
    }

    /**
     * Resets coins inside coffee machine
     */
    public void resetCoins(){
        wallet.resetUsedCoins();
    }

    /**
     * Calculates the total price of a coffee order including optional sugar and milk.
     * @param type the type of coffee being ordered
     * @param withSugar true if the user wants to add sugar, false otherwise
     * @param withMilk true if the user wants to add milk, false otherwise
     * @return the total price of the coffee order in cents
     */
    public int orderCoffee(CoffeeType type, boolean withSugar, boolean withMilk) {
        final int ONE_MILK_PRICE  = 10;
        final int ONE_SUGAR_PRICE = 5;

        if(type == null){
            userInterface.showMessageln("Unknown coffee type...");
            return 0;
        }
        int price = type.getPrice();

        if(withSugar){
            price += ONE_SUGAR_PRICE;
        }
        if(withMilk){
            price += ONE_MILK_PRICE;
        }
        return price;
    }

    /**
     * Restocks all available components of the coffee machine, such as inventory and wallet.
     */
    public void restock(){
        if(inventory != null){
            inventory.restock();
        }
        if(wallet != null){
            wallet.restock();
        }
        userInterface.showPressToContinueMessage();
    }

    /**
     * Displays the current inventory and wallet balance of the coffee machine.
     */
    public void displayInventory(){
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("COFFEE MACHINE INVENTORY AND BALANCE");
        System.out.println("------------------------------------");
        if(inventory != null){
            inventory.display();
        }
        if(wallet != null){
            wallet.display();
        }
        userInterface.showPressToContinueMessage();
    }

    /**
     * Processes the payment for a coffee order and updates the system accordingly.
     * This method continuously prompts the user to insert coins until the total
     * received amount equals or exceeds the coffee price. It validates each coin,
     * updates the wallet balance, and handles change if necessary. Once payment is
     * complete, the method deducts the required ingredients and cup from the
     * inventory, and dispenses the coffee.
     * @param type the {@link CoffeeType} selected by the user
     * @param withSugar true if the coffee includes sugar, false otherwise
     * @param withMilk true if the coffee includes milk, false otherwise
     * @param price the total price of the coffee in cents
     */
    public void calculatePrice(CoffeeType type, boolean withSugar, boolean withMilk, int price){
        while(wallet.getTotalReceivedCoins() < price){
            int coinValue = userInterface.validateEnteredCoin();
            userInterface.nextLine();

            if(!wallet.insertCoin(coinValue)){
                userInterface.showMessageln(Messages.MSG_ERR_PAYMENT);
                continue;
            }
        }

        if(wallet.shouldGiveChange(price)) {
            if (wallet.processChange(price) != 0) {
                wallet.cancelTheOrder();
                userInterface.showMessageln(Messages.MSG_ERR_CHANGE);
                return;
            }
        }
        inventory.useIngredients(type, withSugar, withMilk, ONE_COFFEE, ONE_MILK, ONE_SUGAR, ONE_CUP);
        dispenseChange();
        userInterface.showMessageln(Messages.MSG_COFFEE_READY);
    }

    /**
     * Dispenses the remaining change to the user after a coffee purchase.
     */
    public void dispenseChange(){
        wallet.giveChange();
    }
}
