public class SecondInningsScoreboard extends FirstInningsScoreboard{
    final int runTarget;
    float requiredRunRate;
    int runNeeded;

    //Decrement for each valid ball bowled
    int ballsRemaining;

    SecondInningsScoreboard(Match match){
        super(match);
        this.runTarget = match.targetScore;
        this.ballsRemaining = match.overLimit* match.ballsPerOver;
    }

    //Display ___ runs required off ___ balls after calculating RRR
    void calculateRequiredRunRate(){
        this.runNeeded = this.runTarget - this.runsScored;
        this.requiredRunRate = (float)this.runNeeded / (float)this.oversRemaining;

    }

    //Calculating the required run rate and the number of balls remaining
    void updateScoreBoard(Decision decision){
        super.updateScoreBoard(decision);
        calculateRequiredRunRate();
        switch(decision){
            case NO_BALL:
            case DOT_BALL: break;
            default:
                this.ballsRemaining--;
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
            System.out.printf("| CRR:  %-35.2f |%n", runRate);
            System.out.printf("+-------------------------------------------+%n");

            // Printing Batsman Scores
            System.out.printf("| > %-10s - %-3d Runs                   |%n", strikerBatsman.playerName,strikerScore);
            System.out.printf("|   %-10s - %-3d Runs                   |%n", nonStrikerBatsman.playerName, nonStrikerScore);
            System.out.printf("+-------------------------------------------+%n");

            // Printing Bowler Details
            System.out.printf("| %-12s  %-28s|%n",currentBowler.playerName, overProgression);
            System.out.printf("+-------------------------------------------+%n");

    }

    public int startSecondInningsScoreboard(){
        return 0;
    }

}
