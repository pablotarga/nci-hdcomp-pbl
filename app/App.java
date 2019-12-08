
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
        char ans;
        do {
                System.out.print(Colorize.warning(question+" [y/n] "));
                ans = s.next().trim().toLowerCase().charAt(0);
        } while(ans != 'n' && ans != 'y');

        return ans == 'y';
}

public static void askDigit(Line l, Scanner s){
        int min = l.getMin();
        int max = l.getMax();
        System.out.printf("Please inform a digit from %d to %d: ", min, max);
        int n = s.nextInt();
        if(l.add(n)){
                System.out.println(Colorize.success(n + " was added to the line!"));
        } else {
                String msg = String.format("Informed digit is not valid please check if between %d and %d and not included on the line", min, max);
                System.out.println(Colorize.error(msg));
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

        while(!lotteryWon && yn(Printer.questionsPlayAGame(history), s)){
                Game game = new Game();
                while(game.acceptsNewLine()){
                        if(!game.isFirstLine() && !yn(Printer.questionsAddNewLine(game), s)){
                                game.noMoreBets();
                                break;
                        }

                        Line l = new Line();
                        do{
                                askDigit(l, s);
                        } while(!l.isFull());

                        Printer.printLine(l);
                        if(yn("Are you happy with these numbers?", s)){
                                game.addLine(l);
                        }
                }

                if(game.getAmountOfLinesPlayed() > 0){
                        lotteryWon = game.getWonTheLottery();
                        history.store(game);
                }
        }

        return history;
}
}
