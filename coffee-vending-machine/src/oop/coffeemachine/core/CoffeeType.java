package oop.coffeemachine.core;

/**
 * CoffeeType enumeration represents the different types of coffee
 * available in the coffee machine. Each coffee type is associated with a fixed price.
 */
public enum CoffeeType {
    COLOMBIAN(100),
    HOUSE(100),
    FRENCH(100);

    private final int price;

    CoffeeType(int price){
        this.price = price;
    }

    /**
     * Returns the price associated with this coffee type.
     * @return the price of one coffee
     */
    public int getPrice(){
        return price;
    }

    /**
     * Converts a character into the corresponding CoffeeType.
     * @param c the character representing a coffee type
     * @return the matching CoffeeType, or null if none matches
     */
    public static CoffeeType fromChar(char c) {
        c = Character.toUpperCase(c);
        return switch (c) {
            case 'C' -> COLOMBIAN;
            case 'H' -> HOUSE;
            case 'F' -> FRENCH;
            default -> null;
        };
    }
}
