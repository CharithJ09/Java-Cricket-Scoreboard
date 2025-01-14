public class Bowler extends Player{
    int totalWickets = 0 ;
    int totalDotBalls = 0;
    int ballsDelivered = 0;

    public Bowler(){
        super();
    }

    //Updating player individual statistics
    public void updatePlayerStats(Decision decision){
        ballsDelivered++;
        super.updatePlayerStats(decision);
        switch (decision){
            case WIDE :
                totalRuns++;
                break;
            case WICKET  :
                totalWickets++;
                break;
            case DOT_BALL:
                totalDotBalls++;
                break;
        }
    }
}



