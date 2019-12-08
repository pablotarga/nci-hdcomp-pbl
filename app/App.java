/**
 * App Class
 *
 * @author Pablo Targa assisted by Shane Gibney and Donald Hickey
 * @version 2019-12-07
 */
import java.util.Scanner;

public class App{

  public static void main(String[] args){
    // call the method play informing initial balance for the bank, this will return the
    // history object containing all played games and reference to the bank.
    History history = play(600000);

    //invoke method printHistory from abstract class Printer on variable history
    Printer.printHistory(history);
  }

  /* Requests a Yes or No response from user and invokes
  Colorize method from abstract class Colorize to output text in different colors*/
  public static boolean yn(String question, Scanner s){
    char ans;
    // loop that repeats the question until either n or y are provided
    do {
      // print the question and format appening [y/n] box at the end
      System.out.print(Colorize.warning(question+" [y/n] "));
      // remove spaces, convert to lowercase and get the first char
      ans = s.next().trim().toLowerCase().charAt(0);
    } while(ans != 'n' && ans != 'y');

    // check ans is equal to 'y' to be true anything else is false, as we only reach this point after a 'y' or a 'n' is informed there is not third options
    return ans == 'y';
  }

  // Requests a user to enter a number between ranges defined on the line and handles invalid entries
  public static void askDigit(Line l, Scanner s){
    int min = l.getMin();
    int max = l.getMax();
    System.out.printf("Please enter a number from %d to %d: ", min, max);
    int n = s.nextInt();
    if(l.add(n)){
      System.out.println(Colorize.success(n + " was added to the line!"));
    } else {
      String msg = String.format("The number you entered is not valid please check if it is between %d and %d and not already in the line", min, max);
      System.out.println(Colorize.error(msg));
      System.out.println("Line: " + Printer.formatLine(l, true));
    }
  }

  // Game flow and interactions with the user
  public static History play(double bankBalance){
    // scanner object to read from teh keyboard
    Scanner s = new Scanner(System.in);

    // bank object to keep track of the money and pay prizes
    LotteryBank bank = new LotteryBank(bankBalance);

    //instantiates new object history of type History
    // history stores played games, it auto scalates when memory array reaches its length, see History#extendList
    History history = new History(bank);

    // flag to store the state of having or not won the lottery
    // in case of winning the lottery the game is finished and celebratory message is displayed
    boolean lotteryWon = false;

    //invokes greetings method and rules method from Printer abstract class
    Printer.greetings();
    Printer.rules();

    // loop to ask if the user wants to keep playing, only possible if not won the lottery and bank has balance
    while(!lotteryWon && bank.hasBalance() && yn(Printer.questionsPlayAGame(history), s)){
      //instantiates new object game of type Game
      // each game will accepts up to 3 lines and draw the results
      Game game = new Game(bank);

      // loop that keeps asking for more lines while the game accepts more lines
      while(game.acceptsNewLine()){
        //invites user to play another line, skip on the first line, because we just ask if the user want to play a game
        if(!game.isFirstLine() && !yn(Printer.questionsAddNewLine(game), s))
          // if user responds negatively we close the game calling noMoreBets, and exit this loop
          game.noMoreBets();
          break;
        }

        //instantiates new object l of Type Line
        Line l = new Line();
        do{
          //invokes method askDigit resquesting user to enter a number
          askDigit(l, s);
        } while(!l.isFull()); // do while the line is not full (still have slots availabl)

        // display choosen numbers to ask for confirmation
        Printer.printLine(l);

        //invokes boolean method yn to request user entry confirmation
        if(yn("Are you happy with these numbers?", s)){
          game.addLine(l);
        }
      }

      //store the game into history and check if won the lottery if has played any line;
      if(game.getAmountOfLinesPlayed() > 0){
        lotteryWon = game.getWonTheLottery();
        history.store(game);
      }
    }

    return history;
  }
}
