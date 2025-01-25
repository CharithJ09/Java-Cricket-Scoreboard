import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.util.Arrays;

public class FirstInningsScoreboard {
    Match match;
    Team battingTeam;
    Team bowlingTeam;

    Player strikerBatsman;
    int strikerScore = 0;

    Player nonStrikerBatsman;
    int nonStrikerScore = 0;

    //To temporary hold values when swapping
    Player switchBatsman;
    int switchScore;

    Player currentBowler;
    float runRate = 0.0f;
    int runsScored = 0;
    int wickets = 0;
    int wicketLimit;
    int ballsPerOver;
    int oversRemaining;
    int ballsRemaining;
    int currentOver = 0;
    int currentBall = 0;
    String overProgression = "";
    String over = "0.0";

    FirstInningsScoreboard(Match match) {
        this.match = match;
        this.battingTeam = this.match.battingTeam;
        this.bowlingTeam = this.match.bowlingTeam;
        this.strikerBatsman = this.battingTeam.getNextBatmen();
        this.nonStrikerBatsman = this.battingTeam.getNextBatmen();
        //Selecting the first bowler
        this.currentBowler = this.match.bowlingTeam.players[this.match.bowlingTeam.batsmenPerTeam];
        this.ballsPerOver = match.ballsPerOver;
        this.oversRemaining = match.overLimit;
        this.wicketLimit = match.WicketLimit;
        this.ballsRemaining = ballsPerOver;
    }


    void delay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    //Updating the Score based on the decision
    void updateScoreBoard(Decision decision) {
        //Updating the player specific stats based on the decision
        strikerBatsman.updatePlayerStats(decision);
        ((Bowler) currentBowler).updateBowlerStats(decision);

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
                this.runsScored += 2;
                this.strikerScore += 2;
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "2 ";
                break;

            case THREE_RUNS:
                this.runsScored += 3;
                this.strikerScore += 3;
                this.switchStriker();
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "3 ";
                break;

            case BOUNDARY:
                this.runsScored += 4;
                this.strikerScore += 4;
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "4 ";
                break;

            case SIX:
                this.runsScored += 6;
                this.strikerScore += 6;
                this.ballsRemaining--;
                this.currentBall++;
                this.overProgression += "6 ";
                break;

            case WICKET:
                this.wickets++;
                this.strikerScore = 0;
                //Getting the next batsman ( checking if either the Striker or Non Striker is the Last)
                if(this.strikerBatsman != this.battingTeam.players[this.battingTeam.playersPerTeam-1] && this.nonStrikerBatsman != this.battingTeam.players[this.battingTeam.playersPerTeam-1]) {
                    this.strikerBatsman = this.battingTeam.getNextBatmen();
                }
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
        over = String.valueOf(this.currentOver) + "." + String.valueOf(this.currentBall);
    }

    //To calculate the run rate at the end of each over
    void calculateRunRate() {
        if (this.currentOver != 0) {
            this.runRate = (float) this.runsScored / this.currentOver;
        }
    }

    //To switch the Striker and Non striker batsman (when 1 run, 3 runs or end of over occurs)
    void switchStriker() {
        //Swapping the batsman objects
        switchBatsman = strikerBatsman;
        strikerBatsman = nonStrikerBatsman;
        nonStrikerBatsman = switchBatsman;

        //swapping the scores
        switchScore = strikerScore;
        strikerScore = nonStrikerScore;
        nonStrikerScore = switchScore;
    }

    //Displaying the formatted Scoreboard
    void displayScoreBoard() {
        // Printing the Scoreboard Header
        System.out.printf("+-------------------------------------------+%n");
        System.out.printf("|                SCOREBOARD                 |%n");
        System.out.printf("+-------------------------------------------+%n");

        // Printing Team Scores
        System.out.printf("| %19s vs %-18s |%n", battingTeam.teamName, bowlingTeam.teamName);
        System.out.printf("+-------------------------------------------+%n");
        System.out.printf("| Over:%6s |%6d - %-20d|%n", over, runsScored, wickets);
        System.out.printf("| CRR:  %-35.2f |%n", runRate);
        System.out.printf("+-------------------------------------------+%n");

        // Printing Batsman Scores
        System.out.printf("| > %-10s - %-3d Runs                   |%n", strikerBatsman.playerName, strikerScore);
        System.out.printf("|   %-10s - %-3d Runs                   |%n", nonStrikerBatsman.playerName, nonStrikerScore);
        System.out.printf("+-------------------------------------------+%n");

        // Printing Bowler Details
        System.out.printf("| %-12s  %-28s|%n", currentBowler.playerName, overProgression);
        System.out.printf("+-------------------------------------------+%n");
    }

    //Getting the final decision from the user
    static Decision getDecision() {
        //Storing the valid decisions
        String[] decisions = {"0", "1", "2", "3", "4", "6", "WD", "W", "NB"};
        Decision decisionFinal = Decision.NO_BALL;
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%n| DOT_BALL - 0 | ONE_RUN - 1 | TWO_RUNS - 2 | THREE_RUNS - 3 | BOUNDARY - 4 | SIX - 6 | WIDE - WD | WICKET - W | NO_BALL - NB |%n");
        System.out.print("Enter Decision: ");
        String decision = scanner.nextLine();
        //Checking if user entered a valid decision
        while (!Arrays.asList(decisions).contains(decision)) {
            System.out.print("Invalid Decision! Try again: ");
            decision = scanner.nextLine();
        }
        switch (decision) {
            case "0":
                decisionFinal = Decision.DOT_BALL;
                break;

            case "1":
                decisionFinal = Decision.ONE_RUN;
                break;

            case "2":
                decisionFinal = Decision.TWO_RUNS;
                break;

            case "3":
                decisionFinal = Decision.THREE_RUNS;
                break;

            case "4":
                decisionFinal = Decision.BOUNDARY;
                break;

            case "6":
                decisionFinal = Decision.SIX;
                break;

            case "W":
                decisionFinal = Decision.WICKET;
                break;

            case "WD":
                decisionFinal = Decision.WIDE;
                break;

            case "NB":
                break;
        }
        return decisionFinal;
    }

    //Starting the scoreboard for an innings
    //Returns the runs scored during the first innings
    public int startFirstInningsScoreBoard() {

        //Starting the match
        System.out.println("Starting Match...");
        delay(1); // 1-Second Delay


        //Looping until end of overs or all wickets are down
        while (this.oversRemaining > 0 && this.wickets < this.wicketLimit) {
            Driver.clear();

            //Displaying the score
            this.displayScoreBoard();

            //Detecting the End of an Over
            if (this.ballsRemaining == 0) {
                Driver.clear();
                System.out.println("End of Over...");
                delay(1);// Delay for 1 seconds

                //Displaying the score
                this.displayScoreBoard();
                delay(1);// Delay for 1 seconds


                this.oversRemaining--;
                if (this.oversRemaining == 0) {
                    break;
                }else{
                    //Resetting Values at the end of the over
                    this.ballsRemaining = this.ballsPerOver;
                    this.overProgression = "";
                    this.currentOver++;
                    this.currentBall = 0;
                    this.calculateRunRate();
                    this.over = String.valueOf(this.currentOver) + "." + String.valueOf(this.currentBall);

                    //Incrementing Overs bowled by the current bowler
                    ((Bowler) currentBowler).incrementOverCount();
                    this.currentBowler = this.match.getNextBaller((Bowler) currentBowler);
                    this.switchStriker();
                    Driver.clear();
                    this.displayScoreBoard();
                }
            }

            //Updating the scoreboard based on decision
            this.updateScoreBoard(getDecision());


        }
        Driver.clear();
        System.out.println("End of Innings...");
        delay(3);
        this.displayScoreBoard();
        return this.runsScored;
    }
}
