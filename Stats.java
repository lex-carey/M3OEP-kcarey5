import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Calendar;

public class Stats {
    private int avgBombs, avgTime, gamesWon, logic, misclick, miscount, chance;
    private ArrayList<Minesweeper> games;
    private ArrayList<Minesweeper> topGames;
    private Minesweeper bestGame;

    public Stats() {
        //initializing games
        try {
            File inFile = new File("games.csv");
            Scanner fileScanner = new Scanner(inFile);
            while (fileScanner.hasNextLine()) {
                String[] minesweeper = fileScanner.nextLine().split(",");
                games.add(new Minesweeper(Integer.parseInt(minesweeper[0]), Integer.parseInt(minesweeper[1]), Integer.parseInt(minesweeper[2]), Integer.parseInt(minesweeper[3]), Integer.parseInt(minesweeper[4])));
            }
            fileScanner.close();
        }
        catch (FileNotFoundException exception) {}
        //initializing topGames
        try {
            File inFile = new File("top_games.csv");
            Scanner fileScanner = new Scanner(inFile);
            while (fileScanner.hasNextLine()) {
                String[] minesweeper = fileScanner.nextLine().split(",");
                topGames.add(new Minesweeper(Integer.parseInt(minesweeper[0]), Integer.parseInt(minesweeper[1]), Integer.parseInt(minesweeper[2]), Integer.parseInt(minesweeper[3]), Integer.parseInt(minesweeper[4])));
            }
            fileScanner.close();
        }
        catch (FileNotFoundException exception) {}
        //initializing integer vars
        for (Minesweeper game : games) {
            avgBombs += game.getBombs();
            avgTime += game.getTime();
            switch(game.getReason()) {
                case 0: break;
                case 1: gamesWon++; break;
                case 2: logic++; break;
                case 3: misclick++; break;
                case 4: miscount++; break;
                case 5: chance++; break;
                default: break;
            }
            avgBombs = avgBombs/games.size();
            avgTime = avgTime/games.size();
            //initializing best game
            bestGame = topGames.get(topGames.size() - 1);
        }
    }

    public void logGame(int b, int t, int r) throws IOException {
        Calendar cal = Calendar.getInstance();
        Minesweeper loggedGame = new Minesweeper(cal.get(Calendar.MONTH), cal.get(Calendar.YEAR), b, t, r);
        games.add(loggedGame);
        try (FileWriter log = new FileWriter("games.csv")) {
            int count = 0;
            for (Minesweeper game : games) {
                count++;
                if (count == games.size()) {
                    log.write(game.toString());
                }
                else {
                    log.write(game.toString() + "\n");
                }
            }
        }
        if (topGames.isEmpty()) {
            topGames.add(loggedGame);
            try (FileWriter topLog = new FileWriter("top_games.csv")) {
                topLog.write(loggedGame.toString());
            }
        }
        else if (loggedGame.compareTo(bestGame) == 1 || loggedGame.equals(bestGame)) {
            topGames.add(loggedGame);
            try (FileWriter topLog = new FileWriter("top_games.csv")) {
                int count = 0;
                for (Minesweeper game : topGames) {
                    count++;
                    if (count == topGames.size()) {
                        topLog.write(game.toString());
                    }
                    else {
                        topLog.write(game.toString() + "\n");
                    }
                }
            }
        }
    }

    public int getAvgBombs() {
        return avgBombs;
    }

    public int getAvgTime() {
        return avgTime;
    }

    public int getGamesWon() {
        return gamesWon;
    }
    public int getLogic() {
        return logic;
    }
    
    public int getMisclick() {
        return misclick;
    }

    public int getMiscount() {
        return miscount;
    }
    
    public int getChance() {
        return chance;
    }

    public int getBestMonth() {
        return bestGame.getMonth();
    }

    public int getBestYear() {
        return bestGame.getYear();
    }

    public int getBestBombs() {
        return bestGame.getBombs();
    }

    public int getBestTime() {
        return bestGame.getTime();
    }

    public int getBestReason() {
        return bestGame.getReason();
    }
}