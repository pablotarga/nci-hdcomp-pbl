
/**
 * Write a description of class App here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;

public class App
{
public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        History h = new History();

        for(int i = 0; i < 25; i++) {

                h.store( new Game() );
        }

        Line l = new Line(6, 1, 40);
        do {
                System.out.println("Choose a number from 1 - 40.");
                l.add(input.nextInt());
        } while(!l.isFull());
        // l.add(3);
}
}
