
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
        History history = play();
        Printer.printHistory(history);
}

public static boolean yn(String question, Scanner s){
        System.out.print(Colorize.warning(question+" [y/n] "));
        char ans = s.next().trim().toLowerCase().charAt(0);
        return ans == 'y';
}

public static void askDigit(Line l, Scanner s){
        System.out.print("Please inform a digit from 1 to 40: ");
        int n = s.nextInt();
        if(l.add(n)){
                System.out.println(Colorize.success(n + " was added to the line!"));
        } else {
                System.out.println(Colorize.error("Informed digit is not valid please check if between 1 and 40 and not included on the line"));
                System.out.print("Line: ");
                Printer.printLine(l);
        }
}

public static History play(){
        Scanner s = new Scanner(System.in);

        History history = new History();
        boolean lotteryWon = false;
        Printer.greetings();
        Printer.rules();

        do{
                Game game = new Game();
                while(game.acceptsNewLine()){
                        if(!yn("Do you want to play a line?", s)){
                                game.noMoreBets();
                                break;
                        }

                        Line l = new Line(6, 1, 40);
                        do{
                                askDigit(l, s);
                        } while(!l.isFull());

                        Printer.printLine(l);
                        if(yn("Is the line entered correct?", s)){
                                game.addLine(l);
                        }
                }
                lotteryWon = game.getWonTheLottery();
                history.store(game);
        } while(!lotteryWon && yn("Want to play another game?", s));

        return history;
}
}
