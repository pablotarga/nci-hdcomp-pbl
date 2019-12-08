/**
 * Deck Class
 *
 * @author Pablo Targa assisted by Shane Gibney and Donald Hickey
 * @version 2019-12-07
 */
import java.util.Random;

public class Deck extends Line {
  // Deck constructor
  public Deck(){
    this(Game.NUMBERS, Game.RANGE_MIN, Game.RANGE_MAX);
  }

  public Deck(int size, int min, int max){
    // The constructor of Line is called by Deck
    super(size, min, max);
    autoPopulate();
  }

  private void autoPopulate(){
    Random r = new Random();
    do {
      int generatedNumber = r.nextInt(max-min+1)+min;
      add(generatedNumber);
    } while(!isFull());
  }
}
