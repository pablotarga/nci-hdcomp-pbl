/**
 * Colorize Class
 *
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


public static String f(String color, char c){
        return f(color, Character.toString(c));
}

public static String f(String color, String text){
        return String.format("%s%s%s", color, text, ANSI_RESET);
}

public static String error(String string){
        return f(ANSI_RED, string);
}

public static String info(String string){
        return f(ANSI_CYAN, string);
}

public static String success(String string){
        return f(ANSI_GREEN, string);
}

public static String warning(String string){
        return f(ANSI_YELLOW, string);
}

public static String gold(String string){
        return f(ANSI_BRIGHT_YELLOW, string);
}

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
