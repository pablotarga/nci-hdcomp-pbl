
import java.util.Random;
public class Game
{
public static final int NUMBERS = 6;
public static final int RANGE_MIN = 1;
public static final int RANGE_MAX = 8;

private Deck secret;
private Line[] lines;
private boolean locked;
private boolean wonTheLottery;
private int index;
private int amountOfLinesWon;
private double totalWinnings;

public Game(){
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

                int howManyNumberWon = currLine.getAmountOfHits();

                switch(howManyNumberWon) {
                case 3:
                        amountOfLinesWon++;
                        totalWinnings += 125;
                        break;
                case 4:
                        amountOfLinesWon++;
                        totalWinnings += 300;
                        break;
                case 5:
                        amountOfLinesWon++;
                        totalWinnings += 1500;
                        break;
                case 6:
                        amountOfLinesWon++;
                        totalWinnings += 0;
                        wonTheLottery = true;
                        break;
                default:
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
