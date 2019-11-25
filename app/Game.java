
import java.util.Random;
public class Game
{
    private Line secret;
    private Line[] lines;
    private boolean locked;
    private int index;

    public Game(){
      lines = new Line[3];
      locked = false;
      index = 0;
    }

    public boolean addLine(Line l){
      if (locked){
        return false;
      }

      lines[index++] = l;
      if (lines.length <= index){
        noMoreBets();
      }
      return true;
    }

    public void noMoreBets(){
        locked = true;

        //...
    }

    public int getAmountOfLinesPlayed(){
      return index;
    }

    // TODO: pending method
    public int getAmountOfLinesWon(){
      return 0;
    }

    // TODO: pending method
    public double getTotalWinnings(){
      return 0.0;
    }

}
