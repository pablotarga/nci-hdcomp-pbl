
/**
 * Write a description of class App here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class App
{
    public static void main(String[] args){
        History h = new History();
        
        for(int i = 0; i < 25; i++){
            
            h.store( new Game() );
        }
    }
}
