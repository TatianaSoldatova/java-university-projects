public class StringPractice {
    static void main() {
        String s1 = "alphabet";
        String s2 = "beta";
        System.out.println(s1.compareTo(s2));
        System.out.println();

        System.out.println("Zoo starts with a capital letter: " + startsWithCapitalLetter("Zoo"));
        System.out.println("moo starts with a capital letter: " + startsWithCapitalLetter("moo"));
        System.out.println("ZOO starts with a capital letter: " + startsWithCapitalLetter("ZOO"));
        System.out.println("zOO starts with a capital letter: " + startsWithCapitalLetter("zOO"));
        System.out.println("9zOO starts with a capital letter: " + startsWithCapitalLetter("9zOO"));
        System.out.println();

        System.out.println(nbrWhiteSpaces("I love apples"));
        System.out.println(nbrWhiteSpaces("Zoo"));
        System.out.println(nbrWhiteSpaces(" asd fgt hjuy jui dfg\n"));
        System.out.println(nbrWhiteSpaces(null));
        System.out.println(nbrWhiteSpaces(""));
        System.out.println(nbrWhiteSpaces(" "));
        System.out.println();

        System.out.println(removeCharE("Excellent economy progress"));
        System.out.println(removeCharE("beautiful melody"));
        System.out.println(removeCharE(null));
        System.out.println(removeCharE(""));
        System.out.println();

        System.out.println("'maison' is contained in 'Le manteau est en vison': "
                + isInsideNoOrder("Le manteau est en vison", "maison"));
        System.out.println();

        System.out.println("'house' is contained in 'Le manteau est en vison': "
                + isInsideNoOrder("Le manteau est en vison", "house"));
        System.out.println();

        System.out.println("'' is contained in ' ': "
                + isInsideNoOrder("", ""));
        System.out.println();

        System.out.println("'rester' is contained in 'Rien ne sert de courir, il faut partir a point': "
                + isInsideNoOrder("Rien ne sert de courir, il faut partir a point", "rester"));
        System.out.println();

        System.out.println("'repas' is contained in 'Plus on mange, moins on a faim': "
                + isInsideNoOrder("Plus on mange, moins on a faim", "repas"));
        System.out.println();
    }

    /**
     * Determines if the string starts with a capital letter or not
     * @param str the string to test if it starts with a capital letter
     * @return true if the first letter is capital, false otherwise
     */
    public static boolean startsWithCapitalLetter(String str){
        boolean isMaj = false;
        if(str != null && !str.isEmpty()){
            char first = str.charAt(0);
            if(first >= 'A' && first <= 'Z'){
                isMaj = true;
            }
        }
        return isMaj;
    }

    /**
     * Returns the number of white spaces in a string
     */
    public static int nbrWhiteSpaces(String str){
        int count = 0;
        if(str != null && !str.isEmpty()){
            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) == ' '){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Removes all 'e' characters from a given string
     */
    public static String removeCharE(String s){
        String result = "";
        if(s != null && !s.isEmpty()){
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) != 'e' && s.charAt(i) != 'E'){
                    result += s.charAt(i);
                }
            }
        }
        return result;
    }

    /**
     * Checks if all the characters of the second string appear inside the first string
     * Order of appearance is irrelevant
     */
    public static boolean isInsideNoOrder(String s1, String s2){
        if(s1 != null && !s2.isEmpty()){
            for(int i = 0; i < s2.length(); i++){
                String s = "" + s2.charAt(i);
                //System.out.println("" + s2.charAt(i));
                if(s1.contains(s)){
                    int index = s1.indexOf(s);
                    s1 = s1.substring(0, index) + s1.substring(index + s.length());
                    //System.out.println(s1);
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}
