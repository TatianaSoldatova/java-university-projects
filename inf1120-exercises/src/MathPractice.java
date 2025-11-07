public class MathPractice {
    static void main() {
        System.out.println(monthlyPayment(6.5, 10500, 25));
        System.out.println(monthlyPayment(101, 10500, 25));
        System.out.println(monthlyPayment(6.5, 51, 25));
        System.out.println(monthlyPayment(6.5, -1, 25));
        System.out.println();
    }

    /**
     * Calculates the monthly payment for a loan based on the interest rate, loan amount, and duration.
     * The formula uses compound interest with semi-annual conversion and assumes monthly payments.
     * @param rate the annual interest rate (in percentage, between 0 and 100)
     * @param loan the total amount of the loan (must be greater than 0)
     * @param duration the loan duration in years (must be between 1 and 50)
     * @return the monthly payment amount, rounded to the nearest whole number;
     * returns 0 if input values are invalid
     */
    public static double monthlyPayment(double rate, double loan, int duration){
        double monthlyPayment = 0.0;
        double compoundingFactor;
        double totalNbrPayments;

        if(rate >= 0 && rate <= 100 && loan > 0 && duration >= 1 && duration <= 50){
            compoundingFactor = compoundingFactor(rate);
            totalNbrPayments = nbrTotalVersements(duration);

            monthlyPayment = loan * (Math.pow(compoundingFactor, totalNbrPayments)
                    * (compoundingFactor - 1)/(Math.pow(compoundingFactor, totalNbrPayments) - 1));
        }

        monthlyPayment = Math.round(monthlyPayment);
        return monthlyPayment;
    }

    /**
     * Calculates the equivalent monthly interest factor based on the annual interest rate.
     * The formula assumes semi-annual compounding converted to a monthly rate.
     * @param rate the annual interest rate (in percentage)
     * @return the equivalent monthly compounding factor
     */
    public static double compoundingFactor(double rate){
        return Math.pow((1 + (rate/200)), 1.0/6);
    }

    /**
     * Calculates the total number of monthly payments based on the loan duration in years.
     * @param duration the loan duration in years
     * @return the total number of monthly payments
     */
    public static double nbrTotalVersements(int duration){
        return duration * 12.0;
    }
}
