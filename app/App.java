/**
 * App Class
 *
 * @author Pablo Targa assisted by Shane Gibney and Donald Hickey
 * @version 2019-12-07
 */
import java.util.Scanner;

public class App
{

public static void main(String[] args){
//declare a variable of type History to track user inputs and game play
        History history = play();
        //invoke method printHistory from abstract class Printer on variable history
        Printer.printHistory(history);
}
/* Requests a Yes or No response from user and invokes
Colorize method from abstract class Colorize to output text in different colors*/
public static boolean yn(String question, Scanner s){
        char ans;
        do {
                System.out.print(Colorize.warning(question+" [y/n] "));
                ans = s.next().trim().toLowerCase().charAt(0);
        } while(ans != 'n' && ans != 'y');

        return ans == 'y';
}
/* Requests a user to enter a number between 1-40 and handles invalid entries*/
public static void askDigit(Line l, Scanner s){
        System.out.print("Please enter a number from 1 to 40: ");
        int n = s.nextInt();
        if(l.add(n)){
                System.out.println(Colorize.success(n + " was added to the line!"));
        } else {
                System.out.println(Colorize.error("The number you entered is not valid please check if it is between 1 and 40 and not already in the line"));
                System.out.print("Line: ");
                Printer.printLine(l);
        }
}
/* Requests the user to play the lottery and handles invalid entries*/
public static History play(){
        Scanner s = new Scanner(System.in);
//instantiates new object history of type History
        History history = new History();
        //invokes greetings method and rules method from Printer abstract class
        boolean lotteryWon = false;

        Printer.greetings();
        Printer.rules();

        do{
          //instantiates new object game of type Game
                Game game = new Game();
                while(game.acceptsNewLine()){
                //invites user to play another gane
                        if(!yn("Do you want to play a line?", s)){
                                game.noMoreBets();
                                break;
                        }
                        //instantiates new object l of Type Line
                        Line l = new Line(6, 1, 40);
                        do{
                        //invokes method askDigit resquesting user to enter a number
                                askDigit(l, s);
                        } while(!l.isFull());

                        Printer.printLine(l);
                        //invokes boolean method yn to request user entry confirmation
                        if(yn("Is the line entered correct?", s)){
                                game.addLine(l);
                        }
                }
                //invoke wonTheLottery method which outputs a celebratory screen of text in rainbow colours
                lotteryWon = game.getWonTheLottery();
                history.store(game);
        } while(!lotteryWon && yn("Want to play another game?", s));

        return history;
}
}
