import java.util.Objects;
import java.util.Scanner;
public class Match {
    Scanner scanner = new Scanner(System.in);
    int overLimit;
    int oversPerBowler;
    int ballsPerOver;
    int playersPerTeam;

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
    Team team1;
    Team team2;
    static Team battingTeam;
    static Team ballingTeam;
    String team;
    double currentOvers;
    int currentWickets;
    int currentRuns;
    static boolean secondInning = false;
    void toss(){
        System.out.println("Enter the batting team: ");
        team = scanner.nextLine();
        if (Objects.equals(team, "Team1")){
            battingTeam = team1;
            ballingTeam = team2;
        }else {
            battingTeam = team2;
            ballingTeam = team1;
        }
    }

    void CurrentMatch(int currentRuns, double currentOvers, int currentWickets){
        this.currentRuns = currentRuns;
        this.currentOvers = currentOvers;
        this.currentWickets = currentWickets;
        changeInning();
    }

    void changeInning(){
        if((currentWickets == playersPerTeam)||(currentOvers == overLimit)){
            if(!secondInning){
                System.out.println("First inning is over...");
                System.out.println("Total Runs: "+currentRuns);
                System.out.println("Target is: "+(currentRuns++));
                System.out.println("Required Run rate is: "+(currentRuns/overLimit));
                secondInning = true;
            }else{
                System.out.println("Match is Over...");
            }
        }
    }
}