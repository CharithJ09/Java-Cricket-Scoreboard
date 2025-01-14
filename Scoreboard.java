import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Scoreboard {
    Batsman strikerBatsman;
    Batsman nonStrikerbatsman;
    Bowler currentBowler;
    float runRate;
    int runsScored = 0;
    int wickets = 0;
    int oversRemaining;
    int ballsRemaining;
    int currentOver = 0;
    int currentBall = 0;
    String overProgression = "";

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
    private void updateScoreBoard(Decision decision) {
        switch (decision) {
            case DOT_BALL:
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "0 ";
                break;

            case ONE_RUN:
                this.runsScored++;
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "1 ";
                break;

            case TWO_RUNS:
                this.runsScored+=2;
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "2 ";
                break;

            case THREE_RUNS:
                this.runsScored+=3;
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "3 ";
                break;

            case BOUNDARY:
                this.runsScored+=4;
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "4 ";
                break;

            case SIX:
                this.runsScored+=6;
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "6 ";
                break;

            case WICKET:
                this.wickets++;
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "W ";
                break;

            case WIDE:
                this.runsScored++;
                this.overProgression += "WD ";
                break;

            case NO_BALL:
                this.runsScored++;
                this.overProgression += "NB ";
                break;

        }
    }

    private void calculateRunRate() {
        if(this.currentOver != 0){
            this.runRate = (float) this.runsScored /this.currentOver ;
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
        TimeUnit.SECONDS.sleep(1); // Delay for 1 seconds
        Driver.clear();
        scanner.nextLine();

        //Looping until end of overs or all wickets are down
        while(scoreboard.oversRemaining > 0 && scoreboard.wickets < 10){

            Driver.clear();

            //Displaying the score
            System.out.println(scoreboard.runsScored+"-"+scoreboard.wickets);
            System.out.println("Overs Remaining: "+scoreboard.oversRemaining);
            System.out.println("Balls remaining: "+scoreboard.ballsRemaining);
            System.out.printf("Run Rate: %.2f\n",scoreboard.runRate);
            System.out.println(scoreboard.currentOver+"."+scoreboard.currentBall+" | "+scoreboard.overProgression);

            //Resetting balls remaining
            if(scoreboard.ballsRemaining == 0){
                Driver.clear();
                //Displaying the score
                System.out.println(scoreboard.runsScored+"-"+scoreboard.wickets);
                System.out.println("Overs Remaining: "+scoreboard.oversRemaining);
                System.out.println("Balls remaining: "+scoreboard.ballsRemaining);
                System.out.printf("Run Rate: %.2f\n",scoreboard.runRate);
                System.out.println(scoreboard.currentOver+"."+scoreboard.currentBall+" | "+scoreboard.overProgression);
                TimeUnit.SECONDS.sleep(1); // Delay for 1 seconds
                Driver.clear();


                System.out.println("End of Over...");
                TimeUnit.SECONDS.sleep(1); // Delay for 1 seconds
                scoreboard.ballsRemaining = ballsPerOver;
                scoreboard.overProgression = "";
                scoreboard.oversRemaining--;
                scoreboard.currentOver++;
                scoreboard.currentBall=0;
                scoreboard.calculateRunRate();
                if (scoreboard.oversRemaining == 0) {break;}
            }


            //Taking umpire decision
            System.out.print("Enter Decision(0/1/2/3/4/6/W/WD/NB): ");
            String decision = scanner.nextLine();

            //Updating the scoreboard based on decision
            scoreboard.updateScoreBoard(convertStringToDecision(decision));


        }
        Driver.clear();
        System.out.println("End of Innings...");
        System.out.println(scoreboard.runsScored+"-"+scoreboard.wickets);
    }
}
