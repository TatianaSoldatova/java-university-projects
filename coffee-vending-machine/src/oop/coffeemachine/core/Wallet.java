package oop.coffeemachine.core;

import oop.coffeemachine.utils.UserInterface;
import oop.coffeemachine.utils.Messages;

/**
 * Represents a wallet used in a coffee vending machine to handle coins
 * This class manages the coin inventory for three denominations: 25 cents, 10 cents, and 5 cents.
 * It keeps track of coins inserted, change to give back, and supports restocking,
 * canceling orders, computing change, and displaying the wallet status.
 *      - object instantiation inside CoffeeMachine class
 */
public class Wallet {
    //Max coins quantities per machine
    private final int COIN_25_CENTS_MAX = 6;
    private final int COIN_10_CENTS_MAX = 10;
    private final int COIN_5_CENTS_MAX  = 10;

    //Initial coins quantities
    private int startingCoinsQty_25        = COIN_25_CENTS_MAX;
    private int startingCoinsQty_10        = COIN_10_CENTS_MAX;
    private int startingCoinsQty_5         = COIN_5_CENTS_MAX;
    private int givenCoinsQty_25           = 0;
    private int givenCoinsQty_10           = 0;
    private int givenCoinsQty_5            = 0;

    private int totalReceivedCoins         = 0;
    private int totalChangeCoinsToGiveBack = 0;

    /**
     * Resets the coins used for the current order.
     */
    public void resetUsedCoins(){
        totalChangeCoinsToGiveBack = 0;
        totalReceivedCoins         = 0;
        givenCoinsQty_25           = 0;
        givenCoinsQty_10           = 0;
        givenCoinsQty_5            = 0;
    }

    /**
     * Returns the total number of coins received so far.
     * @return the total amount of received coins
     */
    public int getTotalReceivedCoins(){
        return totalReceivedCoins;
    }

    /**
     * Adds the specified number of coins to the total received coins count.
     * @param coins the number of coins to add to the total
     */
    public void setTotalReceivedCoins(int coins){
        totalReceivedCoins += coins;
    }

    /**
     * Checks whether the wallet should give change for a given price.
     * @param price The price of the order in cents
     * @return true if the total received coins exceed the price (change is needed), false otherwise.
     */
    public boolean shouldGiveChange(int price){
        return totalReceivedCoins > price;
    }

    /**
     * Calculates how many coins of a specific value can be given as change.
     * Updates the remaining change to give back after giving these coins.
     * @param coinValue The value of the coin in cents (e.g., 25, 10, 5).
     * @param availableCoins The number of coins of this value available in the wallet.
     * @return The number of coins of this value to give as change.
     */
    private int giveCoins(int coinValue, int availableCoins) {
        int coinsToGive = Math.min(totalChangeCoinsToGiveBack / coinValue, availableCoins);
        totalChangeCoinsToGiveBack -= coinsToGive * coinValue;
        return coinsToGive;
    }

    /**
     * Computes the change to give back for a given price.
     * Updates the wallet's coin counts and stores how many coins of each value are given.
     * Prints the total change to give back.
     * @param price The price of the order in cents.
     * @return The remaining change that could not be given if there are not enough coins
     * of some denomination (0 if fully given).
     */
    public int processChange(int price){
        totalChangeCoinsToGiveBack = totalReceivedCoins - price;

        if (totalReceivedCoins > price) {
            System.out.println("\nYour change is: " + totalChangeCoinsToGiveBack + " cents");
        }
        givenCoinsQty_25 = giveCoins(25, startingCoinsQty_25);
        startingCoinsQty_25 -= givenCoinsQty_25;

        givenCoinsQty_10 = giveCoins(10, startingCoinsQty_10);
        startingCoinsQty_10 -= givenCoinsQty_10;

        givenCoinsQty_5 = giveCoins(5, startingCoinsQty_5);
        startingCoinsQty_5 -= givenCoinsQty_5;

        return totalChangeCoinsToGiveBack;
    }

    /**
     * Cancels the current order and restores the coins that went to prepare customer's change
     */
    public void cancelTheOrder(){
        startingCoinsQty_25 += givenCoinsQty_25;
        startingCoinsQty_10 += givenCoinsQty_10;
        startingCoinsQty_5 += givenCoinsQty_5;
    }

    /**
     * Processes a coin inserted by the customer and updates the total received coins.
     * If the coin value is invalid, the method does not update the total and returns false.
     * Valid coin values are:
     * 5, 10, 25  — added as cents
     * 1, 2       — added as dollars (converted to 100 and 200 cents respectively)
     * @param coinValue the value of the coin inserted by the user
     * @return true if the coin was valid and added to the wallet; false otherwise
     */
    public boolean insertCoin(int coinValue) {
        switch(coinValue){
            case 5, 10, 25 -> {
                setTotalReceivedCoins(coinValue);
                return true;
            }
            case 1, 2 ->{
                setTotalReceivedCoins(coinValue * 100);
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    /**
     * Displays the number of coins to be returned to the customer as change.
     * If at least one coin of any denomination (25, 10, or 5 cents) has been given,
     * this method prints to the console the quantity of each coin type that will be returned.
     * If no coins were given, nothing is printed
     */
    public void giveChange(){
        if (givenCoinsQty_25 != 0 || givenCoinsQty_10 != 0 || givenCoinsQty_5 != 0) {
            System.out.println("\n* " + givenCoinsQty_25 + " coin(s) of 25 cents.");
            System.out.println("* " + givenCoinsQty_10 + " coin(s) of 10 cents.");
            System.out.println("* " + givenCoinsQty_5 + " coin(s) of 5 cents.");
        }
    }

    /**
     * Restocks the machine with the maximum number of coins for each denomination.
     */
    public void restock(){
        startingCoinsQty_25 = COIN_25_CENTS_MAX;
        startingCoinsQty_10 = COIN_10_CENTS_MAX;
        startingCoinsQty_5  = COIN_5_CENTS_MAX;

        System.out.println("- " + startingCoinsQty_25 + " coins of 25 cents\n"
                         + "- " + startingCoinsQty_10 + " coins of 10 cents\n"
                         + "- " + startingCoinsQty_5 + " coins of 5 cents\n");
    }

    /**
     * Displays the current quantity of coins in the machine for each denomination.
     */
    public void display(){
        System.out.printf("%-20s : %2d%n", "25 cents coins", startingCoinsQty_25);
        System.out.printf("%-20s : %2d%n", "10 cents coins", startingCoinsQty_10);
        System.out.printf("%-20s : %2d%n", "5 cents coins", startingCoinsQty_5);
    }
}
