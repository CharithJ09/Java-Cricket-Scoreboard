import java.util.List;
import java.util.Scanner;

public class Team {
    int currentBatsmenIndex=0;
    public String teamName;
    Player[] players;
    int teamScore;
    int playersPerTeam;
    int batsmenPerTeam;

    Scanner scanner=new Scanner(System.in);
    // Constructor
    public Team(String teamName, int playersPerTeam,int batsmenPerTeam) {
        this.teamName = teamName;
        this.playersPerTeam = playersPerTeam;
        this.players = new Player[playersPerTeam];
        this.teamScore = 0;
        this.batsmenPerTeam=batsmenPerTeam;
    }

    // Add a player to the team
    public void addPlayers() {
        for (int i=0;i<this.batsmenPerTeam;i++){
            System.out.print("Batsman "+(i+1)+": ");
            String playerName=scanner.nextLine();
            //scanner.nextLine();
            players[i] = new Batsman(playerName);
        }
        for (int i=this.batsmenPerTeam;i<this.playersPerTeam;i++){
            System.out.print("Bowler "+(i-(batsmenPerTeam-1))+": ");
            String playerName=scanner.nextLine();
            //scanner.nextLine();
            players[i] = new Bowler(playerName);
        }
    }


    public Player getNextBatmen(){
        Player nextBatsman = players[currentBatsmenIndex];
        currentBatsmenIndex++;
        return nextBatsman;

    }

}

