package oop.coffeemachine.core;

/**
 * Inventory class represents the stock management system of a coffee machine.
 * It keeps track of the quantities of various ingredients and supplies such as
 * different types of coffee, sugar, milk, and cups.
 * The inventory is initialized at maximum capacity when an Inventory object is created.
 *      - object instantiation inside CoffeeMachine class
 */
public class Inventory {

    //Max ingredients and coins quantities per machine
    private final int COFFEE_MAX_QTY = 10;
    private final int SUGAR_MAX_QTY  = 5;
    private final int MILK_MAX_QTY   = 15;
    private final int CUPS_MAX_QTY   = 3;

    private int colombianCoffeeQty   = COFFEE_MAX_QTY;
    private int frenchCoffeeQty      = COFFEE_MAX_QTY;
    private int houseCoffeeQty       = COFFEE_MAX_QTY;
    private int milkQty              = MILK_MAX_QTY;
    private int sugarQty             = SUGAR_MAX_QTY;
    private int cupsQty              = CUPS_MAX_QTY;

    //METHODS
    /**
     * Returns the current quantity of Colombian coffee available.
     * @return the number of units of Colombian coffee
     */
    public int getColombianCoffeeQty(){
        return colombianCoffeeQty;
    }

    /**
     * Returns the current quantity of French coffee available.
     * @return the number of units of French coffee
     */
    public int getFrenchCoffeeQty(){
        return frenchCoffeeQty;
    }

    /**
     * Returns the current quantity of House coffee available.
     * @return the number of units of House coffee
     */
    public int getHouseCoffeeQty(){
        return houseCoffeeQty;
    }

    /**
     * Returns the current quantity of milk available.
     * @return the number of units of milk
     */
    public int getMilkQty(){
        return milkQty;
    }

    /**
     * Returns the current quantity of sugar available.
     * @return the number of units of sugar
     */
    public int getSugarQty(){
        return sugarQty;
    }

    /**
     * Returns the current quantity of cups available.
     * @return the number of units of cups
     */
    public int getCupsQty() {
        return cupsQty;
    }

    /**
     * Decreases the current quantity of Colombian coffee by the specified amount.
     * @param qty the number of units to subtract from the current quantity
     */
    public void setColombianCoffeeQty(int qty){
        colombianCoffeeQty -= qty;
    }

    /**
     * Decreases the current quantity of French coffee by the specified amount.
     * @param qty the number of units to subtract from the current quantity
     */
    public void setFrenchCoffeeQty(int qty){
        frenchCoffeeQty -= qty;
    }

    /**
     * Decreases the current quantity of House coffee by the specified amount.
     * @param qty the number of units to subtract from the current quantity
     */
    public void setHouseCoffeeQty(int qty){
        houseCoffeeQty -= qty;
    }

    /**
     * Decreases the current quantity of sugar by the specified amount.
     * @param qty the number of units to subtract from the current quantity
     */
    public void setSugarQty(int qty){
        sugarQty -= qty;
    }

    /**
     * Decreases the current quantity of milk by the specified amount.
     * @param qty the number of units to subtract from the current quantity
     */
    public void setMilkQty(int qty){
        milkQty -= qty;
    }

    /**
     * Decreases the current quantity of cups by the specified amount (always 1).
     * @param qty the number of units to subtract from the current quantity
     */
    public void setCupsQty(int qty) {
        cupsQty -= qty;
    }

    /**
     * Deducts the quantities of ingredients from the inventory to prepare a coffee order.
     * This method decreases the stock of the selected coffee type, optionally deducts sugar and milk
     * if requested and available, and deducts one cup for the order.
     * @param type the type of coffee being prepared
     * @param withSugar true if the user requested sugar, false otherwise
     * @param withMilk true if the user requested milk, false otherwise
     * @param oneCoffee the quantity of coffee to deduct for one serving
     * @param oneMilk the quantity of milk to deduct for one serving
     * @param oneSugar the quantity of sugar to deduct for one serving
     * @param oneCup the quantity of cups to deduct for one serving
     */
    public void useIngredients(CoffeeType type, boolean withSugar, boolean withMilk,
                                    int oneCoffee, int oneMilk, int oneSugar, int oneCup) {
        switch (type) {
            case COLOMBIAN:
                setColombianCoffeeQty(oneCoffee);
                break;
            case HOUSE:
                setHouseCoffeeQty(oneCoffee);
                break;
            case FRENCH:
                setFrenchCoffeeQty(oneCoffee);
                break;
        }

        if (withSugar && getSugarQty() >= oneSugar) {
            setSugarQty(oneSugar);
        }

        if (withMilk && getMilkQty() >= oneMilk) {
            setMilkQty(oneMilk);
        }
        setCupsQty(oneCup);
    }

    /**
     * Restocks the coffee machine to its maximum capacity for all ingredients and cups.
     */
    public void restock(){
        colombianCoffeeQty      = COFFEE_MAX_QTY;
        frenchCoffeeQty         = COFFEE_MAX_QTY;
        houseCoffeeQty          = COFFEE_MAX_QTY;
        milkQty                 = MILK_MAX_QTY;
        sugarQty                = SUGAR_MAX_QTY;
        cupsQty                 = CUPS_MAX_QTY;

        System.out.println("\nThe coffee machine now contains :\n"  + "- " + COFFEE_MAX_QTY + " grams of every type of coffee\n"
                + "- " + milkQty    + " grams of milk\n"
                + "- " + sugarQty   + " grams of sugar\n"
                + "- " + cupsQty    + " cups\n");
    }

    /**
     * Displays the current stock of the coffee machine.
     * The method prints to the console the quantities of each type of coffee (Colombian, House, French),
     * sugar, milk, and the number of cups, formatted in aligned columns for readability.
     */
    public void display(){
        System.out.printf("%-20s : %2d gram(s)%n", "Colombian coffee", colombianCoffeeQty);
        System.out.printf("%-20s : %2d gram(s)%n", "House coffee", houseCoffeeQty);
        System.out.printf("%-20s : %2d gram(s)%n", "French coffee", frenchCoffeeQty);
        System.out.printf("%-20s : %2d gram(s)%n", "Sugar", sugarQty);
        System.out.printf("%-20s : %2d gram(s)%n", "Milk", milkQty);
        System.out.printf("%-20s : %2d%n", "Cups", cupsQty);
    }
}
