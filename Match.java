import java.util.Scanner;
public class Match {
    Scanner scanner = new Scanner(System.in);
    int overLimit;
    int ballsPerOver;
    int WicketLimit;
    static Team battingTeam;
    static Team bowlingTeam;
    int secondInningScore;
    int targetScore;

    public Match(int overLimit, int ballsPerOver, Team batTeam, Team ballTeam){
        this.overLimit = overLimit;
        this.ballsPerOver = ballsPerOver;
        battingTeam = batTeam;
        bowlingTeam = ballTeam;
        WicketLimit = bowlingTeam.getPlayersPerTeam()-1;
    }
    //get second inning score from scoreboard
    void setSecondInningScore(int secondInningScore){
        this.secondInningScore = secondInningScore;
    }
    //change inning and show target score to second inning
    void changeInning(int previousInningScore){
        targetScore = previousInningScore;
        System.out.println(bowlingTeam.teamName+" has to score "+(targetScore++)+" runs off"+overLimit+" overs to win...");
        Team temp = battingTeam;
        battingTeam = bowlingTeam;
        bowlingTeam = temp;
        System.out.println("Press Enter to continue...");
        String input = scanner.nextLine();
        Driver.clear();
    }
    //send next bawler to scoreboard
    Player getNextBaller(Bowler previousBaller){
        Driver.clear();
        Player nextBaller = null;
        for (int i=bowlingTeam.batsmenPerTeam;i<=bowlingTeam.playersPerTeam;i++){
            if(previousBaller == null){
                System.out.println(i+"."+bowlingTeam.players[i].playerName);
            }else if(previousBaller != bowlingTeam.players[i]) {
                System.out.println(i+"."+bowlingTeam.players[i].playerName);
            }
        }
        System.out.println("Enter index of the bowler..");
        int index = scanner.nextInt();
        if ((index >= bowlingTeam.batsmenPerTeam)&&(index < bowlingTeam.playersPerTeam)){
            nextBaller = bowlingTeam.players[index];
        }else {
            System.out.println("Invalid index...");
        }
        Driver.clear();
        return nextBaller;
    }
    //show batting and balling details of each player
    void team1batting(){
        System.out.printf("|Player Name  |  Runs  |  Sixes  |  Boundaries  |  Balls Faced   |  Strike Rate|%n");
        System.out.printf("+------------------------------------------------------------------------------+%n");
        for (int i=0;i<bowlingTeam.batsmenPerTeam;i++){
                Batsman batsman = (Batsman) bowlingTeam.players[i];
                System.out.printf("|%-10s|%6d|%6d|%6d|%6d|%6.2f|",batsman.playerName,batsman.totalRuns,batsman.totalSixes,batsman.totalBoundaries,batsman.ballsFaced,batsman.strikeRate);
        }
    }

    void team1balling(){
        System.out.printf("|Player Name  |  Runs  |  Sixes  |  Boundaries  |  Overs Bawled   |  Wickets  |  Runs Conceded|%n");
        System.out.printf("+---------------------------------------------------------------------------------------------+%n");
        for (int i=bowlingTeam.batsmenPerTeam;i<bowlingTeam.playersPerTeam;i++){
                Bowler bowler = (Bowler) bowlingTeam.players[i];
                System.out.printf("|%-10s|%6d|%6d|%6d|%6d|%6d|%6d",bowler.playerName,bowler.totalRuns,bowler.totalSixes,bowler.totalBoundaries,bowler.noOfOversBowled,bowler.totalWickets,bowler.runsConceded);
        }
    }

    void team2batting(){
        System.out.printf("|Player Name  |  Runs  |  Sixes  |  Boundaries  |  Balls Faced   |  Strike Rate|%n");
        System.out.printf("+------------------------------------------------------------------------------+%n");
        for (int i=0;i<battingTeam.batsmenPerTeam;i++){
            Batsman batsman = (Batsman) battingTeam.players[i];
            System.out.printf("|%-10s|%6d|%6d|%6d|%6d|%6.2f|",batsman.playerName,batsman.totalRuns,batsman.totalSixes,batsman.totalBoundaries,batsman.ballsFaced,batsman.strikeRate);
        }
    }

    void team2balling(){
        System.out.printf("|Player Name  |  Runs  |  Sixes  |  Boundaries  |  Overs Bawled   |  Wickets  |  Runs Conceded|%n");
        System.out.printf("+---------------------------------------------------------------------------------------------+%n");
        for (int i=battingTeam.batsmenPerTeam;i<battingTeam.playersPerTeam;i++){
            Bowler bowler = (Bowler) battingTeam.players[i];
            System.out.printf("|%-10s|%6d|%6d|%6d|%6d|%6d|%6d",bowler.playerName,bowler.totalRuns,bowler.totalSixes,bowler.totalBoundaries,bowler.noOfOversBowled,bowler.totalWickets,bowler.runsConceded);
        }
    }
    //display summery of the Match
    void MatchSummery(){
        if(targetScore > secondInningScore){
            System.out.println("The wining team is "+bowlingTeam.teamName);
        }else {
            System.out.println("The wining team is "+battingTeam.teamName);
        }
        System.out.println();
        System.out.printf("+----------------------------------------------------------------------------------------+%n");
        System.out.printf("|                                    Match Summary                                       |%n");
        System.out.printf("+----------------------------------------------------------------------------------------+%n");
        System.out.printf("| %-10s                                                                      %6d |%n",bowlingTeam.teamName,targetScore);
        System.out.printf("+----------------------------------------------------------------------------------------+%n");
        team1batting();
        System.out.printf("+----------------------------------------------------------------------------------------+%n");
        team1balling();
        System.out.printf("+----------------------------------------------------------------------------------------+%n");
        System.out.printf("| %-10s                                                                      %6d |%n",battingTeam.teamName,secondInningScore);
        System.out.printf("+----------------------------------------------------------------------------------------+%n");
        team2batting();
        System.out.printf("+----------------------------------------------------------------------------------------+%n");
        team2balling();
        System.out.printf("+----------------------------------------------------------------------------------------+%n");
    }
}
