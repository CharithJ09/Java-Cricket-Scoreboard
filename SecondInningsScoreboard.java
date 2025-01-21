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

    void updateScoreBoard(Decision decision){
    }

    void displayScoreBoard(){

    }

    public int startSecondInningsScoreboard(){
        return 0;
    }

}
