public class SecondInningsScoreboard extends FirstInningsScoreboard{
    final int runTarget;
    float requiredRunRate;
    int runsNeeded;

    //Decrement for each valid ball bowled
    int totalBallsRemaining;

    SecondInningsScoreboard(Match match){
        super(match);
        this.runTarget = match.targetScore;
        this.runsNeeded =this.runTarget;
        this.totalBallsRemaining= this.oversRemaining* match.ballsPerOver;
    }

    //Display ___ runs required off ___ balls after calculating RRR
    void calculateRequiredRunRate(){
        this.runsNeeded = this.runTarget - this.runsScored;
        this.requiredRunRate = (float)this.runsNeeded / (float)this.oversRemaining;

    }

    //Calculating the required run rate and the number of balls remaining
    void updateScoreBoard(Decision decision){
        super.updateScoreBoard(decision);
        calculateRequiredRunRate();
        switch(decision){
            case NO_BALL:
            case WIDE: break;
            default:
                this.totalBallsRemaining--;
        }
    }

    void displayScoreBoard(){

            // Printing the Scoreboard Header
            System.out.printf("+-------------------------------------------+%n");
            System.out.printf("|                SCOREBOARD                 |%n");
            System.out.printf("+-------------------------------------------+%n");

            // Printing Team Scores
            System.out.printf("| %19s vs %-18s |%n", battingTeam.teamName,bowlingTeam.teamName);
            System.out.printf("+-------------------------------------------+%n");
            System.out.printf("| Over:%6s |%6d - %-20d|%n", over,runsScored,wickets);
            System.out.printf("| CRR:  %-5.2f | RRR: %-22.2f |%n", runRate,requiredRunRate);
            System.out.printf("+-------------------------------------------+%n");
            System.out.printf("|      Need %3d runs in %3d balls           |%n",runsNeeded,totalBallsRemaining);
            System.out.printf("+-------------------------------------------+%n");

            // Printing Batsman Scores
            System.out.printf("| > %-10s - %-3d Runs                   |%n", strikerBatsman.playerName,strikerScore);
            System.out.printf("|   %-10s - %-3d Runs                   |%n", nonStrikerBatsman.playerName, nonStrikerScore);
            System.out.printf("+-------------------------------------------+%n");

            // Printing Bowler Details
            System.out.printf("| %-12s  %-28s|%n",currentBowler.playerName, overProgression);
            System.out.printf("+-------------------------------------------+%n");

    }

    //Starts the scoreboard for the second innings

    public int startSecondInningsScoreBoard()  {

        //Starting the match
        System.out.println("Starting Match...");
        delay(1); // 1-Second Delay

        //Looping until end of overs or all wickets are down or run target is reached
        while(this.oversRemaining > 0 && this.wickets < this.wicketLimit && this.runsNeeded > 0){
            Driver.clear();

            //Displaying the score
            this.displayScoreBoard();

            //Detecting the End of an Over
            if(this.ballsRemaining == 0){
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
                    this.currentBall=0;
                    this.calculateRunRate();
                    this.over = String.valueOf(this.currentOver)+"."+String.valueOf(this.currentBall);

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
        System.out.println("End of the Match...");
        super.displayScoreBoard();
        delay(3);
        return this.runsScored;
    }

}
