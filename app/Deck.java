import java.util.Random;

public class Deck extends Line {
// Deck constructor
public Deck(int size, int min, int max){
        // The constructor of Line is called by Deck
        super(size, min, max);

        Random r = new Random();
        do {
                add(r.nextInt(max-min+1)+min);
        } while(!isFull());
}
}
