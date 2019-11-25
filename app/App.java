
/**
 * Write a description of class App here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.util.Random;

public class App
{
public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        // History h = new History();
        //
        // for(int i = 0; i < 25; i++) {
        //
        //         h.store( new Game() );
        // }
        Random r = new Random();
        Line l = new Line(6, 1, 40);
        do {
                System.out.println("Choose a number from 1 - 40.");
                l.add(r.nextInt(39)+1);
                // l.add(input.nextInt());
                l.print();
        } while(!l.isFull());

        Game g = new Game();

        g.addLine(l);
        Printer.printGame(g);

        g.addLine(l);
        Printer.printGame(g);

        g.addLine(l);
        Printer.printGame(g);
        // l.add(3);
}
}
