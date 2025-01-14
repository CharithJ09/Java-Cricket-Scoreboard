import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

enum Decision{
    DOT_BALL,ONE_RUN,TWO_RUNS,THREE_RUNS,BOUNDARY,SIX,WIDE,WICKET,NO_BALL
}
public class Scoreboard {
    int runsScored = 0;
    int wickets = 0;
    int oversRemaining;
    int ballsRemaining;

    //Converting a string decision into an enum
    private static Decision convertStringToDecision(String decision) {
        switch (decision) {
            case "0":
                return Decision.DOT_BALL;

            case "1":
                return Decision.ONE_RUN;

            case "2":
                return Decision.TWO_RUNS;

            case "3":
                return Decision.THREE_RUNS;

            case "4":
                return Decision.BOUNDARY;

            case "6":
                return Decision.SIX;

            case "W":
                return Decision.WICKET;

            case "WD":
                return Decision.WIDE;

            case "NB":
                return Decision.NO_BALL;
        }
        return null;
    }

    //Updating the Score based on the decision
    private void updateScore(Decision decision) {
        switch (decision) {
            case DOT_BALL:
                this.ballsRemaining--;
                break;

            case ONE_RUN:
                this.runsScored++;
                this.ballsRemaining--;
                break;

            case TWO_RUNS:
                this.runsScored+=2;
                this.ballsRemaining--;
                break;

            case THREE_RUNS:
                this.runsScored+=3;
                this.ballsRemaining--;
                break;

            case BOUNDARY:
                this.runsScored+=4;
                this.ballsRemaining--;
                break;

            case SIX:
                this.runsScored+=6;
                this.ballsRemaining--;
                break;

            case WICKET:
                this.wickets++;
                this.ballsRemaining--;
                break;

            case NO_BALL:
            case WIDE:
                this.runsScored++;
                break;
        }
    }



    public static void main(String[] args) throws InterruptedException {
        Scoreboard scoreboard = new Scoreboard();
        Scanner scanner = new Scanner(System.in);

        //Getting Over limit
        System.out.print("Enter Number of Overs: ");
        scoreboard.oversRemaining = scanner.nextInt();

        //Getting balls per over
        System.out.print("Enter Number of balls per over: ");
        int ballsPerOver = scanner.nextInt();
        scoreboard.ballsRemaining = ballsPerOver;

        //Starting the match
        System.out.println("Starting Match...");
        TimeUnit.SECONDS.sleep(2); // Delay for 2 seconds
        Driver.clear();
        scanner.nextLine();

        //Looping until end of overs or all wickets are down
        while(scoreboard.oversRemaining > 0 && scoreboard.wickets < 10){

            Driver.clear();

            //Resetting balls remaining
            if(scoreboard.ballsRemaining == 0){
                System.out.println("End of Over...");
                TimeUnit.SECONDS.sleep(2); // Delay for 2 seconds
                scoreboard.ballsRemaining = ballsPerOver;
                scoreboard.oversRemaining--;
                if (scoreboard.oversRemaining == 0) {break;}
            }

            //Displaying the score
            System.out.println(scoreboard.runsScored+"-"+scoreboard.wickets);
            System.out.println("Overs Remaining: "+scoreboard.oversRemaining);
            System.out.println("Balls remaining: "+scoreboard.ballsRemaining);

            //Taking umpire decision
            System.out.print("Enter Decision(0/1/2/3/4/6/W/WD/NB): ");
            String decision = scanner.nextLine();

            //Updating the scoreboard based on decision
            scoreboard.updateScore(convertStringToDecision(decision));


        }
        Driver.clear();
        System.out.println("End of Innings...");
        System.out.println(scoreboard.runsScored+"-"+scoreboard.wickets);
    }
}
