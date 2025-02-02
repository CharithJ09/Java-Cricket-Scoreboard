import java.util.Scanner;
public class Match {
    Scanner scanner = new Scanner(System.in);
    int overLimit;
    int ballsPerOver;
    int WicketLimit;
    Team battingTeam;
    Team bowlingTeam;
    int secondInningScore;
    int targetScore;

    public Match(int overLimit, int ballsPerOver, int playersInATeam){
        this.overLimit = overLimit;
        this.ballsPerOver = ballsPerOver;
        WicketLimit = playersInATeam-1;
    }

    public void setBattingTeam(Team battingTeam) {
        this.battingTeam = battingTeam;
    }

    public void setBowlingTeam(Team bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }

    //get second inning score from scoreboard
    void setSecondInningScore(int secondInningScore){
        this.secondInningScore = secondInningScore;
    }

    //change inning and show target score to second inning
    void changeInning(int previousInningScore){
        targetScore = previousInningScore+1;
        System.out.println(bowlingTeam.teamName+" has to score "+(targetScore)+" runs off "+overLimit+" overs to win...");
        Team temp = battingTeam;
        this.battingTeam = bowlingTeam;
        this.bowlingTeam = temp;
        scanner.nextLine();
        System.out.println("Press Enter to continue...");
        String input = scanner.nextLine();
        Driver.clear();
    }

    //send next bowler to scoreboard
    Player getNextBaller(Bowler previousBaller){
        Driver.clear();
        System.out.println("Select the Next Bowler: ");
        Player nextBaller = null;
        int x=0;
        int[] remainingBallerIndexes= new int[(battingTeam.playersPerTeam- battingTeam.batsmenPerTeam)];

        //Modify the function to display bowlers 1...., 2...., 3.... and loop until user enters a valid index
        for (int i=bowlingTeam.batsmenPerTeam; i<=(bowlingTeam.playersPerTeam-1); i++){
            if(previousBaller != bowlingTeam.players[i]) {
                remainingBallerIndexes[x]=i;
                x++;
                System.out.println(x+"."+bowlingTeam.players[i].playerName);
            }
        }

        int index;
        do {

            System.out.println("Enter index of the bowler...");
            index = scanner.nextInt();
            if ((index >= 1) && (index < remainingBallerIndexes.length)) {
                nextBaller = bowlingTeam.players[remainingBallerIndexes[index-1]];
                break;

            } else {
                System.out.println("Invalid index. Please try again.");
            }

        }while (!((index >= 1) && (index < remainingBallerIndexes.length)));
        return nextBaller;
    }
    
    //display summery of the Match
    void matchSummary(){
        Driver.clear();
        if( secondInningScore == targetScore-1){
            System.out.println("The Match is Drawn");
        }
        else if(targetScore > secondInningScore){
            System.out.println("The Winning Team is "+bowlingTeam.teamName);
        }else {
            System.out.println("The Winning Team is "+battingTeam.teamName);
        }
        System.out.println();
        System.out.printf("+------------------------------------------------------------------+%n");
        System.out.printf("|                          Match Summary                           |%n");
        System.out.printf("+------------------------------------------------------------------+%n");
        System.out.printf("| %-10s                                                %6d |%n",bowlingTeam.teamName,targetScore-1);
        System.out.printf("+------------------------------------------------------------------+%n");
        System.out.printf("|Player Name         |  Runs|    Sixes|     Boundaries| Strike Rate|%n");
        System.out.printf("+------------------------------------------------------------------+%n");
        for (int i=0;i<bowlingTeam.batsmenPerTeam;i++){
            Batsman batsman = (Batsman) bowlingTeam.players[i];
            System.out.printf("|%-20s|%6d|%9d|%15d|%12.2f|%n",batsman.playerName,batsman.totalRuns,batsman.totalSixes,batsman.totalBoundaries,batsman.calculateStrikeRate());

        }
        System.out.printf("+------------------------------------------------------------------+%n");
        System.out.printf("|Player Name         |  Runs|  Wickets|  Runs Conceded|     Economy|%n");
        System.out.printf("+------------------------------------------------------------------+%n");
        for (int i=bowlingTeam.batsmenPerTeam;i<bowlingTeam.playersPerTeam;i++){
            Player player = bowlingTeam.players[i];
            Bowler bowler = (Bowler) player;
            System.out.printf("|%-20s|%6d|%9d|%15d|%12.2f|%n",bowler.playerName,bowler.totalRuns,bowler.totalWickets,bowler.runsConceded,bowler.calculateEconomy());
        }
        System.out.printf("+------------------------------------------------------------------+%n");
        System.out.printf("| %-10s                                                %6d |%n",battingTeam.teamName,secondInningScore);
        System.out.printf("+------------------------------------------------------------------+%n");
        System.out.printf("|Player Name         |  Runs|    Sixes|     Boundaries| Strike Rate|%n");
        System.out.printf("+------------------------------------------------------------------+%n");
        for (int i=0;i<battingTeam.batsmenPerTeam;i++){
            Player player = battingTeam.players[i];
            Batsman batsman = (Batsman) player;
            System.out.printf("|%-20s|%6d|%9d|%15d|%12.2f|%n",batsman.playerName,batsman.totalRuns,batsman.totalSixes,batsman.totalBoundaries,batsman.calculateStrikeRate());
        }
        System.out.printf("+------------------------------------------------------------------+%n");
        System.out.printf("|Player Name         |  Runs|  Wickets|  Runs Conceded|     Economy|%n");
        System.out.printf("+------------------------------------------------------------------+%n");
        for (int i=battingTeam.batsmenPerTeam;i<battingTeam.playersPerTeam;i++){
            Player player = battingTeam.players[i];
            Bowler bowler = (Bowler) player;
            System.out.printf("|%-20s|%6d|%9d|%15d|%12.2f|%n",bowler.playerName,bowler.totalRuns,bowler.totalWickets,bowler.runsConceded,bowler.calculateEconomy());
        }
        System.out.printf("+------------------------------------------------------------------+%n");
    }
}
