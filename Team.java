import java.util.List;
import java.util.Scanner;

public class Team {
    static int teamCount = 0;
    public int teamId;
    public String teamName;
    Player[] players;
    int teamScore;
    int playersPerTeam;

    Scanner scanner=new Scanner(System.in);
    // Constructor
    public Team(String teamName, int playersPerTeam) {
        this.teamName = teamName;
        this.playersPerTeam = playersPerTeam;
        this.players = new Player[playersPerTeam];
        this.teamScore = 0;
        teamCount++;
        this.teamId = teamCount;
    }

    // Default Constructor
    public Team(){

    }

    // Add a player to the team
    public void addPlayers(int playersPerTeam,int batsmenPerTeam) {
        for (int i=0;i<batsmenPerTeam;i++){
            System.out.print((i+1)+"th batsman's name: ");
            String playerName=scanner.nextLine();
            scanner.nextLine();
            this.players[i] = new Batsman(playerName);
            System.out.println();
        }
        for (int i=0;i<(playersPerTeam-batsmenPerTeam);i++){
            System.out.print((i+1)+"th bowler's name: ");
            String playerName=scanner.nextLine();
            scanner.nextLine();
            this.players[i] = new Bowler(playerName);
            System.out.println();
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

    // Display team details
    public void displayTeamDetails() {
        System.out.println("Team ID: " + teamId);
        System.out.println("Team Name: " + teamName);
        System.out.println("Players:");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println((i + 1) + ". " + player.playerName + " - Runs: " + player.totalRuns + ", Sixes: " + player.totalSixes + ", Boundaries: " + player.totalBoundaries);
        }
        System.out.println("Team Score: " + teamScore);
    }


    // Getters and setters
    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getPlayersPerTeam() {
        return playersPerTeam;
    }

    public void setPlayersPerTeam(int playersPerTeam) {
        this.playersPerTeam = playersPerTeam;
    }

    public static int getTeamCount() {
        return teamCount;
    }

    public int getTeamId() {
        return teamId;
    }

}

