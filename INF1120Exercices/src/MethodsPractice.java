import java.text.SimpleDateFormat;

public class MethodsPractice {

    public static void main() {
        showMenu();

        harmonicNumber(0);
        harmonicNumber(1);
        harmonicNumber(2);

        harmonicTable10();

        System.out.println();
        System.out.println(dateToString(2025, 10, 25));
        System.out.println(dateToString(2024, 2, 6));
        System.out.println(dateToString(2026, 5, 12));

        System.out.println();
        drawRectangle(7, 4);
        drawSquare(5);
        drawTriangleIsoLeft(5);
        drawTriangleIsoRight(5);
        drawTriangleEquilateral(5);
    }

    /**
     * Displays a menu on screen
     */
    public static void showMenu(){
        final String MENU = "\n1. Add\n2. Subtract\n3. Multiply\n4. Divide";
        System.out.println(MENU);
    }

    /**
     * Calculates the n(th) harmonic number Hn
     * Formula: Hn = 1 + 1/2 + 1/3 + ... + 1/n
     * @param n integer value to calculate Hn
     */
    public static void harmonicNumber(int n){
        double harmonicNumber = 0;
        if(n <= 0){
            System.out.println("\nLess than 0 or 0 is not a valid n value!");
        }else{
            for(int i = 1; i <= n; i++){
                harmonicNumber += (double)(1)/i;
            }
            System.out.println("If n = " + n + ", Hn = " + harmonicNumber);
        }
    }

    /**
     * Shows first 10 harmonic numbers
     */
    public static void harmonicTable10(){
        System.out.println("\nHarmonic Table 10: ");
        for(int i = 1; i <= 10; i++){
            harmonicNumber(i);
        }
    }

    /**
     * Takes 3 integers (year, month and day) and return a String date
     */
    public static String dateToString(int year, int month, int day){
        String monthName = switch (month) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "Invalid month!";
        };
        return day + " " + monthName + " " + year;
    }

    //------------------------------------------------------------

    /**
     * Draws a rectangle with given width and height using '*' symbol
     * Outer loop moves top → bottom (rows)
     * Inner loop moves left → right (columns)
     *  a11 a12 a13
     *  a21 a22 a23
     *  a31 a32 a33
     *  a41 a42 a43
     */
    public static void drawRectangle(int width, int height){
        for(int row = 1; row <= height; row++){             // 7
            for(int column = 1; column <= width; column++){ // 4
                if(column == 1 || column == width || row == 1 || row == height){
                    System.out.print("* ");
                }else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Draws a square with given side value
     */
    public static void drawSquare(int side){
        drawRectangle(side, side);
    }

    /**
     * Draw  triangles
     * a11 a12 a13
     * a21 a22 a23
     * a31 a32 a33
     * a41 a42 a43
     */
    public static void drawTriangleIsoLeft(int height){
        for(int row = 1; row <= height; row++){
            for(int column = 1; column <= height; column++){
                if(column == row || row == height || column == 1){
                    System.out.print(" *");
                }
                else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void drawTriangleIsoRight(int height){
        for(int row = 1; row <= height; row++){
            for(int column = 1; column <= height; column++){
                if(row == height || column == height || (row + column) == (height + 1)){
                    System.out.print(" *");
                }
                else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void drawTriangleEquilateral(int height){
        for(int row = 1; row <= height; row++){
            for(int column = 1; column <= (height * 2) - 1; column++){
                if(row == height || (row + column) == (height + 1) || (column - row)  == (height - 1)){
                    System.out.print(" *");
                }else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
