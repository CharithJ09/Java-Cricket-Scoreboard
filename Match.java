import java.util.Scanner;
public class Match {
    Scanner scanner = new Scanner(System.in);
    int overLimit;
    int ballsPerOver;
    int WicketLimit;
    static Team battingTeam;
    static Team ballingTeam;
    int targetScore;
    public Match(int overLimit, int ballsPerOver, Team batTeam, Team ballTeam){
        this.overLimit = overLimit;
        this.ballsPerOver = ballsPerOver;
        battingTeam = batTeam;
        ballingTeam = ballTeam;
        WicketLimit = ballingTeam.getPlayersPerTeam()-1;
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
        Player nextBaller = null;
        int count = 1;
        for (int i=ballingTeam.batsmenPerTeam;i<=ballingTeam.playersPerTeam;i++){
            if(previousBaller == null){
                System.out.println(count+"."+ballingTeam.players[i].playerName);
            }else if(previousBaller != ballingTeam.players[i]) {
                
            }
            
        }
        return nextBaller;
    }
}
