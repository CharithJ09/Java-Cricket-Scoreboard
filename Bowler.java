public class Bowler extends Player{
    int totalWickets = 0 ;
    int totalDotBalls = 0;
    int ballsDelivered = 0;
    int runsConceded = 0;
    int noOfOversBowled=0;
    double economy = 0;


    public Bowler(String playerName){
        super(playerName);
    }

    //Updating player individual statistics
    public void updateBowlerStats(Decision decision){
        ballsDelivered++;

        switch (decision){
            case ONE_RUN :
                runsConceded++;
                break;
            case TWO_RUNS  :
                runsConceded+=2;
                break;
            case THREE_RUNS :
                runsConceded+=3;
                break;
            case BOUNDARY :
                totalBoundaries+=1 ;
                runsConceded+=4;
                break;
            case SIX  :
                totalSixes+=1;
                runsConceded+=6;
                break;
            case WIDE :
                runsConceded++;
                break;
            case WICKET  :
                totalWickets++;
                break;
            case DOT_BALL:
                totalDotBalls++;
                break;
        }
    }

    public void incrementOverCount(){
        noOfOversBowled++;
    }

    public double calculateEconomy(){
        if(noOfOversBowled==0){
            return (double)runsConceded ;
        }else{
            economy = (double)runsConceded/noOfOversBowled;
            return economy;
        }
    }

}



