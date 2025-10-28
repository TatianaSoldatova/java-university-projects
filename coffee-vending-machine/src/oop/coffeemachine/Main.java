package oop.coffeemachine;

import oop.coffeemachine.core.CoffeeMachine;
import oop.coffeemachine.core.CoffeeType;
import oop.coffeemachine.utils.Messages;
import oop.coffeemachine.utils.UserInterface;

public class Main {
    static void main() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        UserInterface userInterface = new UserInterface(coffeeMachine);

        coffeeMachine.setUserInterface(userInterface);
        userInterface.showMessageln(Messages.MSG_PRESENTATION);

        char choice;
        do{
            choice = userInterface.showMainMenu();
            switch (choice) {
                case '1':
                    if(coffeeMachine.isInOrder()){
                        CoffeeType type = userInterface.askForCoffeeType();
                        boolean withSugar = userInterface.askForSugarOrMilk(Messages.MSG_SUGAR, coffeeMachine.hasEnoughSugar());
                        boolean withMilk = userInterface.askForSugarOrMilk(Messages.MSG_MILK, coffeeMachine.hasEnoughMilk());
                        int price = coffeeMachine.orderCoffee(type, withSugar, withMilk);
                        userInterface.askForPayment(type, withSugar, withMilk, price);
                    } else{
                        userInterface.showMessageln(Messages.MSG_ERR_OUT_OF_ORDER);
                        userInterface.showPressToContinueMessage();
                    }
                    break;
                case '2':
                    coffeeMachine.restock();
                    break;
                case '3':
                    coffeeMachine.displayInventory();
                    break;
                case '4':
                    userInterface.showMessageln(Messages.MSG_END_OF_PROGRAM);
                    break;
                default:
                    userInterface.showMessageln(Messages.MSG_ERR_MENU_INPUT);
            }
        }while(choice != '4');

        userInterface.close();
    }
}
