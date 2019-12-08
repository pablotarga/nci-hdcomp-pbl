/**
* Write a description of class History here.
*
* @author Pablo Targa
* @version 2019-11-19
*/
public class History {
  private Game[] list;
  private int pos;
  private LotteryBank bank;

  public History(LotteryBank bank){
    this.bank = bank;
    list = new Game[10]; // start with 10 positions
    pos = 0;
  }

  public void store(Game g){
    // postfix, use the curr value on pos and increment it later
    // first time it should list[0] = i; if(1 == list.length){...};
    list[pos++] = g;

    if(pos == list.length) {
      extendList();
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

  public LotteryBank getBank(){
    return bank;
  }

  private void extendList(){
    extendList(10);
  }

  private void extendList(int size){
    Game[] old = list;
    list = new Game[old.length + size];
    copy(old);
  }

  private void copy(Game[] old){
    for(int i = 0; i < old.length; i++) {
      list[i] = old[i];
    }
  }

  public int amountOfGamesPlayed(){
    return pos;
  }

  public boolean isFirstGame(){
    return pos == 0;
  }
}
