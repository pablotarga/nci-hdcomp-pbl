/**
 * Printer Class
 *
 * @author Pablo Targa
 * @version 2019-12-07
 */
// this is an abstract class and so can never be instantiated to an instance,
// in other words an object can never be created
// but we can and do use the members in the class because they are static
public abstract class Printer {

public static void printHistory(History h){
        Game[] games = h.getGames();

        printGames(games);

        int len = games.length;
        if(len > 0) {
                Game lastGame = games[len-1];

                if(lastGame.getWonTheLottery()) {
                        printWonTheLottery(lastGame);
                }
        }

        printHistorySummary(games);
        printBankSummary(h.getBank());
}

public static void printLines(Line[] lines){
        for(int i = 0; i < lines.length; i++) {
                Line curr = lines[i];
                printLine(curr);
        }
}

public static void printLine(Line l){
        System.out.println(formatLine(l, true));
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

        printLines(game.getLines());

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

        double avg, sum = 0.0;
        int len = games.length;

        switch(len) {
        case 0:
                avg = sum = 0.0;
                break;
        case 1:
                avg = sum = games[0].getTotalWinnings();
                break;
        default:
                for(int i = 0; i < len; i++) {
                        sum += games[i].getTotalWinnings();
                }

                avg = (double)sum/len;
        }

        System.out.println("--== SUMMARY ==--");
        System.out.println("You have played " + p(len, "only one game", "%d games"));
        System.out.printf("Total Winnings is %.2f\n", sum);
        System.out.printf("Winnings avg across all games is %.2f\n", avg);
}

public static void printBankSummary(LotteryBank bank){
        System.out.println("--== BANK SUMMARY ==--");
        System.out.printf("The bank has paid %.2f in prizes\n", bank.getPaidSum());
        System.out.printf("In total prizes were paid %d times\n", bank.getPrizesPaid());
        System.out.printf("The current balance is %.2f (initial balance: %.2f)\n", bank.getBalance(), bank.getInitialBalance());
        System.out.printf("This section has deposited %d lines summing the amount of %.2f\n", bank.getLinesCachedIn(), bank.getCachedInSum());
}

public static void greetings(){
        System.out.println("===============================");
        System.out.println("Welcome to HDSDEV-SEP Lottery!\n");
}

public static void rules(){
        System.out.println(Colorize.info("======== Lottery Rules ========="));
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

public static void printWonTheLottery(Game game){
        Line l = game.getLotteryWinnerLine();
        if(l == null) {
                return;
        }

        System.out.println("==========================================");
        System.out.println("============ " + Colorize.rainbow("CONGRATULATIONS") + " =============");
        System.out.println("====" + Colorize.rainbow("YOU ARE THE BIG WINNER OF THE WEEK") + "====");
        System.out.println("==========================================");
        System.out.println("======== "+Colorize.rainbow("YOUR WINNING NUMBERS ARE")+" ========");
        System.out.println("==========================================");
        System.out.println("======== "+Colorize.gold(formatLine(l, false))+" ========");
        System.out.println("==========================================");
}

// HELPER METHODS
public static String p(int c, String singular, String plural){
        String chosen = (c == 1 ? singular : plural);
        return chosen.contains("%d") ? String.format(chosen, c) : chosen;
}

public static String lpad(int pad, String s){
        return String.format("%"+pad+"s", s);
}

public static String formatLine(Line l, boolean colorize){
        String s = "";
        int[] slots = l.getSlots();

        for(int i = 0; i < slots.length; i++) {
                int n = slots[i];
                String out = lpad((i == 0 ? 2 : 4), Integer.toString(n));
                s += ((colorize && l.isHit(i)) ? Colorize.success(out) : out);
        }

        return "["+s+"]";
}

public static String questionsAddNewLine(Game g){
        if(g.isFirstLine()) {
                return "Do you want to enter a line of numbers?";
        } else{
                return "Do you want to enter another line of numbers?";
        }
}

public static String questionsPlayAGame(History h){
        if(h.isFirstGame()) {
                return "Want to play a lottery game?";
        } else {
                return "Want to play another lottery game?";
        }
}
}
