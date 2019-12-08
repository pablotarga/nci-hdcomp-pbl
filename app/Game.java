/**
* Game Class
*
* @author Donald Hickey assisted by Pablo Targa and Donald Hickey
* @version 2019-12-07
*/

import java.util.Random;

public class Game{
  public static final int NUMBERS = 6;
  public static final int RANGE_MIN = 1;
  public static final int RANGE_MAX = 40;
  private LotteryBank bank;
  private Deck secret;
  private Line[] lines;
  private boolean locked;
  private boolean wonTheLottery;
  private int index;
  private int amountOfLinesWon;
  private double totalWinnings;

  public Game(LotteryBank bank){
    this.bank = bank;
    lines = new Line[3];
    locked = false;
    index = 0;
    wonTheLottery = false;
  }

  public boolean addLine(Line l){
    if (locked) {
      return false;
    }

    lines[index++] = l;
    bank.cashIn();
    if (lines.length <= index) {
      noMoreBets();
    }
    return true;
  }

  public void noMoreBets(){
    locked = true;
    calculateResults();
  }

  private void calculateResults(){
    secret = new Deck(NUMBERS, RANGE_MIN, RANGE_MAX);
    amountOfLinesWon = 0;
    totalWinnings = 0.0;

    for(int i = 0; i < index; i++) {
      Line currLine = lines[i];
      currLine.check(secret);

      int hits = currLine.getAmountOfHits();
      if(hits >= 3){
        amountOfLinesWon++;
        totalWinnings += bank.pay(currLine);
        if(hits == 6){
          wonTheLottery = true;
        }
      }
    }
  }

  public int getAmountOfLinesPlayed(){
    return index;
  }

  public boolean isFirstLine(){
    return index == 0;
  }

  public int getAmountOfLinesWon(){
    return amountOfLinesWon;
  }

  public double getTotalWinnings(){
    return totalWinnings;
  }

  public boolean acceptsNewLine(){
    return !locked;
  }

  public boolean getWonTheLottery(){
    return wonTheLottery;
  }

  public Line getLotteryWinnerLine(){
    for(int i = 0; i < index; i++){
      Line curr = lines[i];
      if(curr.getAmountOfHits() == 6){
        return curr;
      }
    }
    return null;
  }

  // copy and return played lines;
  public Line[] getLines(){
    Line[] l = new Line[index];

    for(int i = 0; i < l.length; i++){
      l[i] = lines[i];
    }

    return l;
  }
}
