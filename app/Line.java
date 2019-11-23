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

public void print(){
  System.out.print("[");
  for(int i = 0; i < index; i++){
    System.out.print(slots[i]+" ");

  }
  System.out.println("]");

}
}
