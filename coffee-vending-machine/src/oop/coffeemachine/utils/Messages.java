package oop.coffeemachine.utils;

public class Messages {
    /**
     * Private constructor to prevent instantiation
     */
    private Messages() {}

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

    public static final String MSG_ERR_MENU_INPUT   = "\nInvalid choice! Try again!";
    public static final String MSG_ERR_OUT_OF_ORDER = "\n*** Coffee Machine is OUT OF ORDER! ***";
    public static final String MSG_ERR_PAYMENT      = "\nThis coin is not valid... try again!";
    public static final String MSG_ERR_CHANGE       = "\n*** Impossible to give exact change! ***\n    Your order was cancelled";

}
