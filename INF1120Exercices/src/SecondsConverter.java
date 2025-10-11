import java.util.Scanner;

/**
 * This program converts a number of seconds in number of days, hours, minutes and seconds.
 */
public class SecondsConverter {
    static Scanner input = new Scanner(System.in);
    //-----------
    // CONSTANTS
    //-----------
    public static final int NBR_SECONDS_MIN         = 0;
    public static final int NBR_SECONDS_PER_MINUTE  = 60;
    public static final int NBR_MINUTES_PER_HOUR    = 60;
    public static final int NBR_HOURS_PER_DAY       = 24;

    public static final String MSG_PRESENTATION     = "\nConvert seconds into days, hours, minutes and seconds";
    public static final String MSG_ASK_NBR_SECONDS  = "\nEnter a number of seconds: ";
    public static final String MSG_ERR_NBR_SECONDS  = "Error. The number of seconds must be greater than " + NBR_SECONDS_MIN;
    public static final String MSG_END              = "END OF PROGRAM";

    public static void main(String[] args) {
        //-----------
        // VARIABLES
        //-----------
        int nbrSeconds  = 0;
        int nbrMinutes  = 0;
        int nbrHours    = 0;
        int nbrDays     = 0;

        System.out.println(MSG_PRESENTATION);
        nbrSeconds = UserInputUtils.readInt(input, MSG_ASK_NBR_SECONDS, "Invalid input!");

        // Exit the program when user input == 0
        while(nbrSeconds != NBR_SECONDS_MIN) {
            if(nbrSeconds < NBR_SECONDS_MIN) {
                System.out.println(MSG_ERR_NBR_SECONDS);

            } else{
                // Compute minutes
                nbrMinutes  = nbrSeconds / NBR_SECONDS_PER_MINUTE;
                nbrSeconds = nbrSeconds % NBR_SECONDS_PER_MINUTE;

                // Compute hours
                nbrHours = nbrMinutes / NBR_MINUTES_PER_HOUR;
                nbrMinutes = nbrMinutes % NBR_MINUTES_PER_HOUR;

                // Compute days
                nbrDays = nbrHours / NBR_HOURS_PER_DAY;
                nbrHours = nbrHours % NBR_HOURS_PER_DAY;

                // Display the result
                System.out.println(" Number of days : " + nbrDays
                                    + "\n Number of hours : " + nbrHours
                                    + "\n Number of minutes : " + nbrMinutes
                                    + "\n Number of seconds : " + nbrSeconds + ".");
            }
            nbrSeconds = UserInputUtils.readInt(input, MSG_ASK_NBR_SECONDS, "Invalid input!");
        }
        System.out.println(MSG_END);

        input.close();
    }
}
