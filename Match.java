import java.util.Objects;
import java.util.Scanner;
public class Match {
    Scanner scanner = new Scanner(System.in);
    int overLimit;
    int oversPerBowler;
    int ballsPerOver;

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
}

class Inning extends Match{
    static Team battingTeam;
    static Team ballingTeam;
    double currentOvers;
    int currentWickets;
    int currentRuns;
    static boolean secondInning = false;
    void toss( Team batTeam, Team ballTeam){
       battingTeam = batTeam;
        ballingTeam = ballTeam;
    }

    void CurrentMatch(int currentRuns, double currentOvers, int currentWickets){
        this.currentRuns = currentRuns;
        this.currentOvers = currentOvers;
        this.currentWickets = currentWickets;
        changeInning();
    }

    void changeInning(){
        if((currentWickets == (battingTeam.getPlayersPerTeam()-1))||(currentOvers == overLimit)){
            if(!secondInning){
                System.out.println("First inning is over...");
                System.out.println("Total Runs: "+currentRuns);
                System.out.println("Target is: "+(currentRuns++));
                System.out.println("Required Run rate is: "+(currentRuns/overLimit));
                Team temp = ballingTeam;
                ballingTeam = battingTeam;
                battingTeam = temp;
                secondInning = true;
            }else{
                System.out.println("Match is Over...");
            }
        }
    }

    void strikerBatsman(){
        System.out.println("Enter Next Batsman:");
        System.out.println(battingTeam.);
    }
}