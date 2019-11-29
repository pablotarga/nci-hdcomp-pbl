import java.util.Random;

public class Deck extends Line{
  public Deck(int size, int min, int max){
    super(size, min, max);

    Random r = new Random();
    do{
      add(r.nextInt(max-min+1)+min);
    } while(!isFull());
  }
}
