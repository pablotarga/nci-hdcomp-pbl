/**
 * Colorize Class
 *
 * This is a helper class that colorize the output to the console.
 * It is heavily used on the Printer class
 * @author Pablo Targa
 * @version 2019-12-07
 */
public class Colorize {
public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_BLACK = "\u001B[30m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_BLUE = "\u001B[34m";
public static final String ANSI_PURPLE = "\u001B[35m";
public static final String ANSI_CYAN = "\u001B[36m";
public static final String ANSI_WHITE = "\u001B[37m";

public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";

// format function that takes a color and char, and format the char as string with the color
public static String f(String color, char c){
        return f(color, Character.toString(c));
}

// format function that takes a color and string, and format the string with the color
public static String f(String color, String text){
        return String.format("%s%s%s", color, text, ANSI_RESET);
}

// format the string with red color to symbolize error
public static String error(String string){
        return f(ANSI_RED, string);
}

// format the string with cyan color to symbolize information
public static String info(String string){
        return f(ANSI_CYAN, string);
}

// format the string with green color to symbolize success
public static String success(String string){
        return f(ANSI_GREEN, string);
}

// format the string with yellow (ambar) color to symbolize warning
public static String warning(String string){
        return f(ANSI_YELLOW, string);
}

// format the string with light yellow color to symbolize gold
public static String gold(String string){
        return f(ANSI_BRIGHT_YELLOW, string);
}

// uses a string buffer to format each letter of the string with a different color
public static String rainbow(String string){
        String[] colors = {ANSI_RED, ANSI_YELLOW, ANSI_GREEN, ANSI_CYAN, ANSI_BLUE, ANSI_PURPLE};
        int cLen = colors.length;

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < string.length(); i++) {
                String colorizedLetter = f(colors[i%cLen], string.charAt(i));
                sb.append(colorizedLetter);
        }

        return sb.toString();
}


}
