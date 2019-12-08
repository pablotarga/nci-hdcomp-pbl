/**
 * LotteryBank Class
 *
 * This class is responsible for keeping track of balance of the lottery
 * Increasing the balance on every line played (LotteryBank#cashIn)
 * and deducting the value of the prize when line is a winner (LotteryBank#pay)
 *
 * @author Pablo Targa
 * @version 2019-12-07
 */
public class LotteryBank {
  public static final double PRICE_PER_LINE = 2.5;
  private double balance;
  private double initialBalance;
  private double securityMargin;

  private int linesCashedIn;
  private int prizesPaid;
  private double paidSum;

  // receives the initial balance and calculate a safety margin to not overdraft in case of winner line with low balance
  // safety margin is 5% of the amount at the minimum of 10k, whatever is higher
  LotteryBank(double initial){
    balance = initialBalance = initial;

    securityMargin = balance * 0.05;
    if(securityMargin < 10000) {
      securityMargin = 10000;
    }

    prizesPaid = linesCashedIn = 0;
    paidSum = 0.0;
  }

// input: None
// process: Add the balance with the constant value PRICE_PER_LINE and increase the amount of lines played
// output: nothing
public void cashIn(){
        balance += PRICE_PER_LINE;
        linesCashedIn++;
}

// input: Receives a line
// process: Get the value of the prize from LotteryBank#getPrizeAmount, deduct from the balance
// output: return the value paid in prize
public double pay(Line l){
        double value = getPrizeAmount(l);
        balance -= value;
        prizesPaid++;
        paidSum += value;

        return value;
}

// Getters
// this is the total amount deposited by playing lines
public double getCachedInSum(){
        return linesCashedIn * PRICE_PER_LINE;
}

// this is the total amount paid in prizes
public double getPaidSum(){
        return paidSum;
}

// this is the amount of times we have prizes paid
public int getPrizesPaid(){
        return prizesPaid;
}

// balance getter
public double getBalance(){
        return balance;
}

// initial balance getter
public double getInitialBalance(){
        return initialBalance;
}

// this is the amount of lines played
public int getLinesCachedIn(){
        return linesCashedIn;
}

// the bank will have balance if balance is greater than the safety margin
public boolean hasBalance(){
        return balance > securityMargin;
}

// input: Received a line from the game
// process: Based on the amount of hits on the line calculate the prize
// output: value due to the prize
private double getPrizeAmount(Line l){
        int hits = l.getAmountOfHits();
        double value = 0;

        switch(hits) {
        case 3:
                value = 125;
                break;
        case 4:
                value = 300;
                break;
        case 5:
                value = 1500;
                break;
        case 6:
                // pay the balance to the winner
                value = balance;
                break;
        }

        return value;
}
}
