/**
 * LineClass
 *
 * This class adds user inputted integers from teh App class into the slots array.
 * The add() method validates the numbers by checking three things:
 * 1. that the slots array is not full using the isFull() method,
 * 2. that the number is within the range 1 to 40 using the isValid() method,
 * 3. that it is a unique number and has not been chosen already using the isIncluded() method
 * This class also contains the check() method which compares a line with the secret.
 * The secret is the actual Lottery winning numbers which gets generated in the Game Class
 * check() then sets the instance variables hitsAmount and hitPositions
 * hitPositions stores the actual elements that match as a binary number
 * this can then be easily compared to one index at a time using the bitwise operator &
 * The other methods in this class are simply getter methods
 * Note this class is a superclass of Game as it is extended by Line
 *
 * @author Shane Gibney assisted by Pablo Targa
 * @version 2019-12-07
 */


import java.lang.Math;

public class Line {
  //instance variables
  private int[] slots;
  private int index;
  protected int min, max;
  //int is a primitive numbers, Integer is a reference type, contains a reference to the object instancewhich is a memnory location
  private int hitPositions;
  private int hitsAmount;

  //constructors
  public Line(){
    this(Game.NUMBERS, Game.RANGE_MIN, Game.RANGE_MAX);
  }

  public Line(int size, int min, int max){
    slots = new int[size];
    index = 0;
    this.min = min;
    this.max = max;
  }

  // methods to check range/uniqueness
  public boolean add(int num){
    if (!isFull() && isValid(num)) {
      slots[index++] = num;
      return true;
    }

    return false;
  }

  public boolean isFull(){
    return index >= slots.length;
  }

  public int[] getSlots(){
    return slots;
  }

  public void check(Line other){
    hitsAmount = hitPositions = 0;
    int[] otherList = other.getSlots();
    for (int i = 0; i < slots.length; i++) {
      for (int j = 0; j < otherList.length; j++) {
        if(slots[i] == otherList[j]) {
          hitsAmount += 1;
          hitPositions += (int)Math.pow(2, i);
          break;
        }
      }
    }
    /**
    self  = [34  12  39   5   9  26]
    other = [31  39  37  21  17   1] (this is the deck from the game class)

    // or going mental and store...
    int hitPositions = 8; // integer representing 001000 or even beter
    int hitPositions = 4; // the reverse representation, which is the natural order to read (left to right)
    // and check the index with Bitwise operators (https://www.tutorialspoint.com/Java-Bitwise-Operators)
    // tranlating the index to power of 2, we have: 0^2 = 1, 1^2 => 2, 2^2 => 4, 3^2 => 8,...
    // isHit = (Math.pow(2, i) & hitPositions) > 0
    */
  }

  public boolean isHit(int index){
    // bitwise operator &
    return ((hitPositions & (int)Math.pow(2,index)) > 0);
  }

  public int getAmountOfHits(){
    return hitsAmount;
  }

  private boolean isValid(int num){
    if(num < min || num > max) {
      return false;
    }else if(isIncluded(num)) {
      return false;
    }
    return true;
  }

  private boolean isIncluded(int num){
    for (int i = 0; i<index; i++) {
      if (num == slots[i]) {
        return true;
      }
    }
    return false;
  }

  public int getMin(){
    return min;
  }

  public int getMax(){
    return max;
  }
}
