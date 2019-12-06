
import java.util.Random;
public class Game
{
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
        secret = new Deck(6,1,40);
        // amountOfLinesWon = totalWinnings = 0;

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

}
