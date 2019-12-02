public abstract class Printer {

public static void printHistory(History h){
        Game[] games = h.getGames();

        printGames(games);
        printHistorySummary(games);
}

public static void printLine(Line l){
        String s = "";
        int[] slots = l.getSlots();

        for(int i = 0; i < slots.length; i++) {
                int n = slots[i];
                String out = lpad((i == 0 ? 2 : 4), Integer.toString(n));
                s += (l.isHit(i) ? Colorize.success(out) : out);
        }

        System.out.println("["+s+"]");
}

public static void printGames(Game[] games){
        // The history shows, for each game,
        //   the number of lines played,
        //   and the number of lines won,
        //   and the total winnings.
        for(int i = 0; i < games.length; i++) {
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

        if(wLines > 0) {
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

        for(int i = 0; i < len; i++) {
                sum += games[i].getTotalWinnings();
        }

        double avg = (len == 0 ? 0 : (double)sum/len);

        System.out.println("--== SUMMARY ==--");
        System.out.println("You have played " + p(len, "only one game", "%d games"));
        System.out.printf("Winnings avg across all games is %.2f", avg);
}

// HELPER METHODS
public static String p(int c, String singular, String plural){
        String chosen = (c == 1 ? singular : plural);
        return chosen.contains("%d") ? String.format(chosen, c) : chosen;
}

public static String lpad(int pad, String s){
        return String.format("%"+pad+"s", s);
}

public static void greetings(){
        System.out.println("===============================");
        System.out.println("Welcome to HDSDEV-SEP Lotery!\n");
}

public static void rules(){
        System.out.println(Colorize.info("======== Lotery Rules ========="));
        System.out.println(Colorize.info("Play as many times you like!"));
        System.out.println(Colorize.info("You can play up to 3 lines per game."));
        System.out.println(Colorize.info("You can pick any digit from 1 to 40."));
        System.out.println(Colorize.info("We can't accept the same digit twice."));
        System.out.println(Colorize.info("\n\n=========== Prizes ============"));
        System.out.println(Colorize.info("|3 numbers |             €125 |"));
        System.out.println(Colorize.info("|4 numbers |             €300 |"));
        System.out.println(Colorize.info("|5 numbers |           €1,500 |"));
        System.out.println(Colorize.info("|6 numbers |  Won the Lottery |"));
        System.out.println(Colorize.info("==============================="));
}
}
