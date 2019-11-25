/**
* Write a description of class History here.
*
* @author Pablo Targa
* @version 2019-11-19
*/
public class History {
  private Game[] list;
  private int pos;

  public History(){
    list = new Game[10]; // start with 10 positions
    pos = 0;
  }

  public void store(Game g){
    // postfix, use the curr value on pos and increment it later
    // first time it should list[0] = i; if(1 == list.length){...};
    list[pos++] = g;

    if(pos == list.length) {
      extend_list();
    }
  }

  /**
  * Get the list of games, as we expand 10 at a time the full list may contain empty positions
  * truncate list returning only the meaningful part of it.
  */
  public Game[] getGames(){
    Game[] games = new Game[pos];

    for(int i = 0; i < games.length; i++){
      games[i] = list[i];
    }
    return games;
  }

  private void extend_list(){
    extend_list(10);
  }

  private void extend_list(int size){
    Game[] old = list;
    list = new Game[old.length + size];
    copy(old);
  }

  private void copy(Game[] old){
    for(int i = 0; i < old.length; i++) {
      list[i] = old[i];
    }
  }
}
