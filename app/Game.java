
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
    public void print(){
      System.out.println("=================================");
      System.out.printf("Game has %d lines\n", index+1);
      for(int i = 0; i < index; i++){
        lines[i].print();
      }
      System.out.println("=================================");

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
}
