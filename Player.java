

public abstract class Player {
    String playerName;
    int totalRuns = 0;
    int totalSixes = 0;
    int totalBoundaries = 0 ;


    public Player(){

    }

    public Player(String playerName){
        this.playerName = playerName;
    }



    //Updating player individual statistics
    public void updatePlayerStats(Decision decision){
        switch (decision){
            case ONE_RUN :
                totalRuns++;
                break;
            case TWO_RUNS  :
                totalRuns+=2;
                break;
            case THREE_RUNS :
                totalRuns+=3;
                break;
            case BOUNDARY :
                totalBoundaries+=1 ;
                totalRuns+=4;
                break;
            case SIX  :
                totalSixes+=1;
                totalRuns+=6;
                break;
        }
    }






}
