public class Line {
  //instance variables
  private int[] slots;
  private int index;
  private int min, max;

  //constructor
  public Line(int size, int min, int max){
    slots = new int[size];
    index = 0;
    this.min = min;
    this.max = max;
  }

  // methods to check range/uniqueness
  public void add(int num){
    if (!isFull() && isValid(num)) {
      slots[index++] = num;
    }
  }

  public boolean isFull(){
    return index >= slots.length;
  }

  public int[] getSlots(){ return slots; }
  public int getMin(){ return min; }
  public int getMax(){ return max; }

  // TODO: will compare the contents of both lines (self and other) and store the outcomes
  public void check(Line other){
    /**
      self  = [34  12  39   5   9  26]
      other = [31  39  37  21  17   1] (this is the deck from the game class)

      // in this scenario we have only one match number 39 on the index 2
      hits = 1

      here we can store:
        boolean[] hitPositions = [false, false, true, false, false, false]
        boolean isHit = hitPositions[i]

        // or going mental and store...
        int hitPositions = 8; // integer representing 001000 or even beter
        int hitPositions = 4; // the reverse representation, which is the natural order to read (left to right)
        // and check the index with Bitwise operators (https://www.tutorialspoint.com/Java-Bitwise-Operators)
        // tranlating the index to power of 2, we have: 0^2 = 1, 1^2 => 2, 2^2 => 4, 3^2 => 8,...
        // isHit = (Math.pow(2, i) & hitPositions) > 0
    */
  }

  // TODO: implement this method, depending of decisions taken on the check method
  public boolean isHit(int index){
    return false;
  }

  // TODO: implement this method, validating if checked and amount of hits
  public int getHits(){
    return 0;
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
}
