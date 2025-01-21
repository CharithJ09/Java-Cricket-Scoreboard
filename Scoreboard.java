import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.util.Arrays;
//batsmen -- updatePlayerStats(Decision decision)
//bowler -- updateBowlerStats(Decision decision)
//Change Batsman, Bowler references to Player
public class Scoreboard {
    String teamA;
    String teamB;

    Batsman strikerBatsman;
    int strikerScore;

    Batsman nonStrikerbatsman;
    int nonStrikerScore;

    //To temporary hold values when swapping
    Batsman switchBatsman;
    int switchScore;

    Bowler currentBowler;
    float runRate;
    int runsScored = 0;
    int wickets = 0;
    int ballsPerOver;
    int oversRemaining;
    int ballsRemaining;
    int currentOver = 0;
    int currentBall = 0;
    String overProgression = "";
    String over = "0.0";


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
                this.strikerScore++;
                this.switchStriker();
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "1 ";
                break;

            case TWO_RUNS:
                this.runsScored+=2;
                this.strikerScore+=2;
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "2 ";
                break;

            case THREE_RUNS:
                this.runsScored+=3;
                this.strikerScore+=3;
                this.switchStriker();
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "3 ";
                break;

            case BOUNDARY:
                this.runsScored+=4;
                this.strikerScore+=4;
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "4 ";
                break;

            case SIX:
                this.runsScored+=6;
                this.strikerScore+=6;
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "6 ";
                break;

            case WICKET:
                this.wickets++;
                this.strikerScore = 0;
                //*****Get the next Batsman to strikerBatsman
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
        over = String.valueOf(this.currentOver)+"."+String.valueOf(this.currentBall);
    }

    //To calculate the run rate at the end of each over
    private void calculateRunRate() {
        if(this.currentOver != 0){
            this.runRate = (float) this.runsScored /this.currentOver ;
        }
    }

    //To switch the Striker and Non striker batsman (when 1 run, 3 runs or end of over occurs)
    private void switchStriker(){
        //Swapping the batsman objects
        switchBatsman = strikerBatsman;
        strikerBatsman = nonStrikerbatsman;
        nonStrikerbatsman = switchBatsman;

        //swapping the scores
        switchScore =strikerScore;
        strikerScore = nonStrikerScore;
        nonStrikerScore = switchScore;
    }

    //Displaying the formatted Scoreboard
    private void displayScoreBoard() {
        // Printing the Scoreboard Header
        System.out.printf("+-------------------------------------------+%n");
        System.out.printf("|                SCOREBOARD                 |%n");
        System.out.printf("+-------------------------------------------+%n");

        // Printing Team Scores
        System.out.printf("| %19s vs %-18s |%n", teamA,teamB);
        System.out.printf("+-------------------------------------------+%n");
        System.out.printf("| Over:%6s |%6d - %-20d|%n", over,runsScored,wickets);
        System.out.printf("| CRR:  %-35.2f |%n", runRate);
        System.out.printf("+-------------------------------------------+%n");

        // Printing Batsman Scores
        System.out.printf("| > %-10s - %-3d Runs                   |%n", strikerBatsman.playerName,strikerScore);
        System.out.printf("|   %-10s - %-3d Runs                   |%n", nonStrikerbatsman.playerName, nonStrikerScore);
        System.out.printf("+-------------------------------------------+%n");

        // Printing Bowler Details
        System.out.printf("| %-12s  %-28s|%n",currentBowler.playerName, overProgression);
        System.out.printf("+-------------------------------------------+%n");
    }

    //Getting the final decision from the user
    private static Decision getDecision() {
        //Storing the valid decisions
        String[] decisions = {"0","1","2","3","4","6","WD","W","NB"};
        Decision decisionFinal = Decision.NO_BALL;
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%n| DOT_BALL - 0 | ONE_RUN - 1 | TWO_RUNS - 2 | THREE_RUNS - 3 | BOUNDARY - 4 | SIX - 6 | WIDE - WD | WICKET - W | NO_BALL - NB |%n");
        System.out.print("Enter Decision: ");
        String decision = scanner.nextLine();
        //Checking if user entered a valid decision
        while (!Arrays.asList(decisions).contains(decision)){
            System.out.print("Invalid Decision! Try again: ");
            decision = scanner.nextLine();
        }
        switch (decision) {
            case "0":
                decisionFinal =  Decision.DOT_BALL;
                break;

            case "1":
                decisionFinal =  Decision.ONE_RUN;
                break;

            case "2":
                decisionFinal =  Decision.TWO_RUNS;
                break;

            case "3":
                decisionFinal =  Decision.THREE_RUNS;
                break;

            case "4":
                decisionFinal =  Decision.BOUNDARY;
                break;

            case "6":
                decisionFinal =  Decision.SIX;
                break;

            case "W":
                decisionFinal =  Decision.WICKET;
                break;

            case "WD":
                decisionFinal =  Decision.WIDE;
                break;

            case "NB":
                break;
        }
        return decisionFinal;
    }

    //Starting the scoreboard for an innings
    public void startScoreBoard()  throws InterruptedException {

        //Starting the match
        System.out.println("Starting Match...");
        TimeUnit.SECONDS.sleep(1); // Delay for 1 seconds
        Driver.clear();

        //Looping until end of overs or all wickets are down
        while(this.oversRemaining > 0 && this.wickets < 10){
            Driver.clear();

            //Displaying the score
            this.displayScoreBoard();

            //Resetting balls remaining
            if(this.ballsRemaining == 0){
                //*****Get the next Bowler
                this.switchStriker();
                Driver.clear();

                //Displaying the score
                this.displayScoreBoard();
                TimeUnit.SECONDS.sleep(1); // Delay for 1 seconds
                Driver.clear();

                //Resetting Values at the end of the over
                System.out.println("End of Over...");
                TimeUnit.SECONDS.sleep(1); // Delay for 1 seconds
                this.ballsRemaining = this.ballsPerOver;
                this.overProgression = "";
                this.oversRemaining--;
                this.currentOver++;
                this.currentBall=0;
                this.calculateRunRate();
                this.over = String.valueOf(this.currentOver)+"."+String.valueOf(this.currentBall);
                this.displayScoreBoard();
                if (this.oversRemaining == 0) {break;}
            }

            //Updating the scoreboard based on decision
            this.updateScoreBoard(getDecision());


        }
        Driver.clear();
        System.out.println("End of Innings...");
        this.displayScoreBoard();
    }






    public static void main(String[] args) throws InterruptedException {

        Player batsman1 = new Batsman("Warner");
        Player batsman2 = new Batsman("Mitch");
        Player bowler1 = new Bowler("Boult");

        Scoreboard scoreboard = new Scoreboard();
        Scanner scanner = new Scanner(System.in);
        scoreboard.strikerBatsman = (Batsman) batsman1;
        scoreboard.nonStrikerbatsman = (Batsman) batsman2;
        scoreboard.currentBowler = (Bowler) bowler1;
        scoreboard.teamA = "Aus";
        scoreboard.teamB = "NZ";


        //Getting Over limit
        System.out.print("Enter Number of Overs: ");
        scoreboard.oversRemaining = scanner.nextInt();

        //Getting balls per over
        System.out.print("Enter Number of balls per over: ");
        scoreboard.ballsPerOver = scanner.nextInt();
        scoreboard.ballsRemaining = scoreboard.ballsPerOver;

        scoreboard.startScoreBoard();
    }
}
