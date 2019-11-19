
import java.util.Random;
public class Game
{
    private int id;

    public Game()
    {
        Random r = new Random();
        id = r.nextInt(99999)+1;
        System.out.printf("Generating game %d\n", id);
    }

}
