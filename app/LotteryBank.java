public class LotteryBank {
  public static final double PRICE_PER_LINE = 2.5;
  double balance;
  double initialBalance;
  double securityMargin;
  int linesCashedIn;
  int prizesPaid;
  double paidSum;

  LotteryBank(double initialBalance){
    balance = this.initialBalance = initialBalance;

    securityMargin = balance * 0.05;
    if(securityMargin < 10000){
      securityMargin = 10000;
    }

    linesCashedIn = 0;
    paidSum = 0.0;
  }

  public void cashIn(){
    balance += PRICE_PER_LINE;
    linesCashedIn++;
  }

  public double pay(Line l){
    double value = getPrizeAmount(l);
    balance -= value;
    prizesPaid++;
    paidSum += value;

    return value;
  }

  public double getCachedInSum(){
    return linesCashedIn * PRICE_PER_LINE;
  }
  public double getPaidSum(){
    return paidSum;
  }

  public int getPrizesPaid(){
    return prizesPaid;
  }

  public double getBalance(){
    return balance;
  }

  public double getInitialBalance(){
    return initialBalance;
  }

  public int getLinesCachedIn(){
    return linesCashedIn;
  }

  public boolean hasBalance(){
    return balance > securityMargin;
  }

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
      value = balance;
      break;
    }

    return value;
  }
}
