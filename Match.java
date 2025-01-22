import java.util.Scanner;
public class Match {
    Scanner scanner = new Scanner(System.in);
    int overLimit;
    int ballsPerOver;
    int WicketLimit;
    static Team battingTeam;
    static Team ballingTeam;
    int secondInningScore;
    int targetScore;
    public Match(int overLimit, int ballsPerOver, Team batTeam, Team ballTeam){
        this.overLimit = overLimit;
        this.ballsPerOver = ballsPerOver;
        battingTeam = batTeam;
        ballingTeam = ballTeam;
        WicketLimit = ballingTeam.getPlayersPerTeam()-1;
    }
    void setsecondInningScore(int sesecondInningScore){
        this.secondInningScore = secondInningScore;
    }
    void changeInning(int previousInningScore){
        targetScore = previousInningScore;
        System.out.println(ballingTeam.teamName+" has to score "+(targetScore++)+" runs off"+overLimit+" overs to win...");
        Team temp = battingTeam;
        battingTeam = ballingTeam;
        ballingTeam = temp;
        System.out.println("Press Enter to continue...");
        String input = scanner.nextLine();
        Driver.clear();
    }
    Player getNextBaller(Bowler previousBaller){
        Driver.clear();
        Player nextBaller = null;
        for (int i=ballingTeam.batsmenPerTeam;i<=ballingTeam.playersPerTeam;i++){
            if(previousBaller == null){
                System.out.println(i+"."+ballingTeam.players[i].playerName);
            }else if(previousBaller != ballingTeam.players[i]) {
                System.out.println(i+"."+ballingTeam.players[i].playerName);
            }
        }
        System.out.println("Enter index of the bowler..");
        int index = scanner.nextInt();
        if ((index >= ballingTeam.batsmenPerTeam)&&(index < ballingTeam.playersPerTeam)){
            nextBaller = ballingTeam.players[index];
        }else {
            System.out.println("Invalid index...");
        }
        Driver.clear();
        return nextBaller;
    }
    void team1batting(){
        System.out.printf("|Player Name  |  Runs  |  Sixes  |  Boundaries  |  Balls Faced   |  Strike Rate|%n");
        System.out.printf("+------------------------------------------------------------------------------+%n");
        for (int i=0;i<=ballingTeam.playersPerTeam;i++){
                Batsman batsman = (Batsman) ballingTeam.players[i];
                System.out.printf("|%-10s|%6d|%6d|%6d|%6d|%6.2f|",batsman.playerName,batsman.totalRuns,batsman.totalSixes,batsman.totalBoundaries,batsman.ballsFaced,batsman.strikeRate);
        }
    }
    void team1balling(){
        System.out.printf("|Player Name  |  Runs  |  Sixes  |  Boundaries  |  Overs Bawled   |  Wickets  |  Runs Conceded|%n");
        System.out.printf("+---------------------------------------------------------------------------------------------+%n");
        for (int i=ballingTeam.batsmenPerTeam;i<=ballingTeam.playersPerTeam;i++){
                Bowler bowler = (Bowler) ballingTeam.players[i];
                System.out.printf("|%-10s|%6d|%6d|%6d|%6d|%6d|%6d",bowler.playerName,bowler.totalRuns,bowler.totalSixes,bowler.totalBoundaries,bowler.totalWickets,bowler.runsConceded);
        }
    }
    void team2batting(){
        System.out.printf("|Player Name  |  Runs  |  Sixes  |  Boundaries  |  Balls Faced   |  Strike Rate|%n");
        System.out.printf("+------------------------------------------------------------------------------+%n");
        for (int i=0;i<=battingTeam.playersPerTeam;i++){
            Batsman batsman = (Batsman) battingTeam.players[i];
            System.out.printf("|%-10s|%6d|%6d|%6d|%6d|%6.2f|",batsman.playerName,batsman.totalRuns,batsman.totalSixes,batsman.totalBoundaries,batsman.ballsFaced,batsman.strikeRate);
        }
    }
    void team2balling(){
        System.out.printf("|Player Name  |  Runs  |  Sixes  |  Boundaries  |  Overs Bawled   |  Wickets  |  Runs Conceded|%n");
        System.out.printf("+---------------------------------------------------------------------------------------------+%n");
        for (int i=battingTeam.batsmenPerTeam;i<=battingTeam.playersPerTeam;i++){
            Bowler bowler = (Bowler) battingTeam.players[i];
            System.out.printf("|%-10s|%6d|%6d|%6d|%6d|%6d|%6d",bowler.playerName,bowler.totalRuns,bowler.totalSixes,bowler.totalBoundaries,bowler.totalWickets,bowler.runsConceded);
        }
    }
    void MatchSummery(){
        System.out.printf("+----------------------------------------------+%n");
        System.out.printf("|                Match Summery                 |%n");
        System.out.printf("+----------------------------------------------+%n");
        System.out.println();
        System.out.printf("+----------------------------------------------+%n");
        System.out.printf("|    %-10s                         %6d|%n",ballingTeam.teamName,targetScore);
        System.out.printf("+----------------------------------------------+%n");
        team1batting();
        System.out.printf("+----------------------------------------------+%n");
        team1balling();
        System.out.printf("+----------------------------------------------+%n");
        System.out.printf("|    %-10s                         %6d|%n",battingTeam.teamName,secondInningScore);
        System.out.printf("+----------------------------------------------+%n");
        team2batting();
        System.out.printf("+----------------------------------------------+%n");
        team2balling();
        System.out.printf("+-------------------------------------------+%n");
    }
}
