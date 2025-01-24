public class Batsman extends Player{
    int ballsFaced = 0;
    double strikeRate = 0;


    public Batsman(String playerName){
        super(playerName);
    }

    //Updating player individual statistics
    public void updatePlayerStats(Decision decision){
        ballsFaced++;
        super.updatePlayerStats(decision);

    }

    public double calculateStrikeRate(){
        strikeRate = (double) totalRuns / ballsFaced*100;
        return strikeRate;
    }
}
