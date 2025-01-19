public class ConsoleFormatting {
    public static void main(String[] args) {
        // Define data
        String team1Name = "Strikers";
        String team2Name = "Legends";
        String overs = "14.3";
        double runRate = 12.56666;
        int runs = 32;
        int wickets = 3;

        String strikerBatsmanName = "Alice";
        int batsman1Score = 45;

        String nonStrikerBatsmanName = "Bob";
        int batsman2Score = 32;

        String bowler = "Charlie";
        String overProgression = "O W 1 WD 0 6 4";

        // Printing the Scoreboard Header
        System.out.printf("+-------------------------------------------+%n");
        System.out.printf("|                SCOREBOARD                 |%n");
        System.out.printf("+-------------------------------------------+%n");

        // Printing Team Scores
        System.out.printf("| %19s vs %-18s |%n", team1Name,team2Name);
        System.out.printf("+-------------------------------------------+%n");
        System.out.printf("| Over:%6s |%6d - %-20d|%n", overs, runs,wickets);
        System.out.printf("| CRR:  %-35.2f |%n", runRate);
        System.out.printf("+-------------------------------------------+%n");

        // Printing Batsman Scores
        System.out.printf("| > %-10s - %-3d Runs                   |%n", strikerBatsmanName, batsman1Score);
        System.out.printf("|   %-10s - %-3d Runs                   |%n", nonStrikerBatsmanName, batsman2Score);
        System.out.printf("+-------------------------------------------+%n");

        // Printing Bowler Details
        System.out.printf("| %-12s  %-28s|%n", bowler, overProgression);
        System.out.printf("+-------------------------------------------+%n");

    }
}
