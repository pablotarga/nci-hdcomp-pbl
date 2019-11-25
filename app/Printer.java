public abstract class Printer{


  public static void printHistory(History h){
    Game[] games = h.getGames();

    printGames(games);
    printHistorySummary(games);
  }

  public static void printGames(Game[] games){
    // The history shows, for each game,
    //   the number of lines played,
    //   and the number of lines won,
    //   and the total winnings.
    for(int i = 0; i < games.length; i++){
      printGame(games[i], i);
    }
  }

  public static void printGame(Game game){
    printGame(game, -1);
  }

  public static void printGame(Game game, int i){
    int pLines = game.getAmountOfLinesPlayed(), wLines = game.getAmountOfLinesWon();
    double winnings = game.getTotalWinnings();
    if(i == -1) {
      System.out.println("--== Game ==--");
    } else {
      System.out.printf("--== Game %d ==--\n", i+1);
    }

    System.out.println("You have played " + p(pLines, "only one line", "%d lines"));

    if(wLines > 0){
      System.out.println("... and have winnings in " + p(wLines, "only one line", "%d lines"));
      System.out.printf("Total winnings for this game is %.2f\n", winnings);
    } else {
      System.out.println("Unfortunately you have no winnings in this game");
    }
  }

  public static void printHistorySummary(Game[] games){
    // Also, at the end of all the games, display a summary
    //   with the total number of games played
    //   and the average of winnings across all the games

    double sum = 0;
    int len = games.length;

    for(int i = 0; i < len; i++){
      sum += games[i].getTotalWinnings();
    }

    double avg = (len == 0 ? 0 : (double)sum/len);

    System.out.println("--== SUMMARY ==--");
    System.out.println("You have played " + p(len, "only one game", "%d games"));
    System.out.printf("Winnings avg across all games is %.2f", avg);
  }

  public static String p(int c, String singular, String plural){
    String chosen = (c == 1 ? singular : plural);
    return chosen.contains("%d") ? String.format(chosen, c) : chosen;
  }


}
