/**
 * Deck Class
 *
 * Specialization of Line this class will be auto populated with random numbers respecting
 * the range and amount of slots provided when class instantiated.
 *
 * Perfect use for testing like in App.autoplay() or to withdraw the lottery results inside Game#calculateResults
 *
 * @author Pablo Targa
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

  // function that add random numbers from min to max to the slots
  private void autoPopulate(){
    Random r = new Random();
    do {
      int generatedNumber = r.nextInt(max-min+1)+min;
      add(generatedNumber);
    } while(!isFull());
  }
}
