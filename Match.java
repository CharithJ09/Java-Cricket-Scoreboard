import java.util.Scanner;
public class Match {
    Scanner scanner = new Scanner(System.in);
    int overLimit;
    int oversPerBowler;
    int ballsPerOver;
    int WicketLimit = ballingTeam.getPlayersPerTeam();
    static Team battingTeam;
    static Team ballingTeam;
    int targetScore;
    public Match(int overLimit, int oversPerBowler, int ballsPerOver, Team batTeam, Team ballTeam){
        this.overLimit = overLimit;
        this.oversPerBowler = oversPerBowler;
        this.ballsPerOver = ballsPerOver;
        battingTeam = batTeam;
        ballingTeam = ballTeam;
    }

    int setOverLimit(){
        System.out.println("Enter Over Limit:");
       overLimit = scanner.nextInt();
       return overLimit;
    }
    int setOversPerBowler(){
        System.out.println("Enter Overs Per Bowler:");
        oversPerBowler = scanner.nextInt();
        return oversPerBowler;
    }
    int setBallsPerOver(){
        System.out.println("Enter Balls Per Over:");
        ballsPerOver = scanner.nextInt();
        return ballsPerOver;
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
}
