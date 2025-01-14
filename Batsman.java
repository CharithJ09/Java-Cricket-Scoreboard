public class Batsman extends Player{
    int totalCenturies = 0 ;
    int totalHalfCenturies = 0 ;
    int ballsFaced = 0;
    double strikeRate = 0;


    public Batsman(){
        super();
    }

    public void updatePlayerStats(Decision decision){
        ballsFaced++;
        super.updatePlayerStats(decision);


        if(totalRuns == 50){
            totalHalfCenturies++;
        } else if (totalRuns == 100){
            totalHalfCenturies++;
            totalCenturies++;
        }
    }

    public double calculateStrikeRate(){
        strikeRate = (double) totalRuns / ballsFaced;
        return strikeRate;
    }
}
