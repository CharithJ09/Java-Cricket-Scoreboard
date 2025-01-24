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

    // Update team score
    public void updateTeamScore(int runs) {
        this.teamScore += runs;
    }

    // Get team score
    public int getTeamScore() {
        return teamScore;
    }


    // Getters and setters
    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getPlayersPerTeam() {
        return this.playersPerTeam;
    }

    public void setPlayersPerTeam(int playersPerTeam) {
        this.playersPerTeam = playersPerTeam;
    }


    public Player getNextBatmen(){
        Player nextBatsman = players[currentBatsmenIndex];
        currentBatsmenIndex++;
        return nextBatsman;

    }

}

