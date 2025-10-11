public class PrimitiveDataTypes {

    public static void main() {
        showTypeConversionRules();
        typeOfExpressionExercise1();
        typeOfExpressionExercise2();
    }

    /**
     * Displays primitive types order of conversions
     */
    public static void showTypeConversionRules() {
        System.out.println("\nPrimitive Data Types conversions: \n");
        System.out.println("    byte, short and char -> int");
        System.out.println("    byte -> short -> int -> long -> float -> double");
        System.out.println("    char -> int -> long -> float -> double");
        System.out.println();
    }

    /**
     * Exercise 1: type of expression
     */
    public static void typeOfExpressionExercise1(){
        final char a     = 'A';
        final short b    = 10;
        final byte c     = 7;
        final long e     = 3;
        final float f    = 7.5f;

        int n            = 0;
        float k          = 0;

        System.out.println("Exercise No1:");
        n = a - b;
        System.out.println("1. ((char) - (short)) = " + ((Object)n).getClass().getSimpleName());
        n = b + c;
        System.out.println("2. ((short) + (byte)) = " + ((Object)n).getClass().getSimpleName());
        k = ((a - b) * (b + c)) / f;
        System.out.println("3. ((int)((a - b) * (b + c)) / (float)) = " + ((Object)k).getClass().getSimpleName());
        var result = ((a - b) * (b + c)) / f + e;
        System.out.println("4. ((int)((a - b) * (b + c)) / (float)) + (long) = " + ((Object)result).getClass().getSimpleName());
        System.out.println();
    }

    /**
     * Exercise 2: type of expression
     */
    public static void typeOfExpressionExercise2(){
        final char a     = 'A';
        final short b    = 10;

        int n = a + b;
        boolean k = (a + b) == 'K';
        System.out.println("Exercise No2:");
        System.out.println("1. (char)'A' + (short)10 = " + n);
        System.out.println("2. (char)'A' + (short)10 == 'K': " + k + "(" +((Object)k).getClass().getSimpleName() + ")");
        System.out.println();
    }
}
