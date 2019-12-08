/**
* History stores the games played into an array of Games
* it increases the size of the array into another 10 positions or any other arbitrary amount when limit is reached
* it also includes the bank to present its data on the final report Printer.printHistory()
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
    // first time it should list[0] = g; if(1 == list.length){...};
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

  // getter methos to return the stored bank reference
  public LotteryBank getBank(){
    return bank;
  }

  // default calling will increase the array into 10 more positions
  private void extendList(){
    extendList(10);
  }

  // input: Amount of extra positions to expand
  // process: create new array with expected size and copy previous values into this new array
  // output: new array with previous games and new length
  private void extendList(int size){
    Game[] old = list;
    list = new Game[old.length + size];
    copy(old);
  }

  // copy element from old array into list
  private void copy(Game[] old){
    for(int i = 0; i < old.length; i++) {
      list[i] = old[i];
    }
  }

  // pos starts in zero and gets incremented everytime a game is added
  public int amountOfGamesPlayed(){
    return pos;
  }

  // check if there is any game into the array pos == 0
  public boolean isFirstGame(){
    return pos == 0;
  }
}
