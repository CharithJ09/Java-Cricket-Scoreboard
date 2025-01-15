import java.io.IOException;
import java.util.Scanner;

enum Decision{
    DOT_BALL,ONE_RUN,TWO_RUNS,THREE_RUNS,BOUNDARY,SIX,WIDE,WICKET,NO_BALL
}

public class Driver {
    public static int playersInATeam=0;
    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            //e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        Team team1=new Team();
        Team team2=new Team();

        //Setting the teams
        System.out.print("Enter the name of the first team: ");
        String team01Name=scanner.nextLine();
        scanner.nextLine();
        System.out.print("Enter the number of players in a team: ");
        playersInATeam=scanner.nextInt();
        scanner.nextLine();
        System.out.print("\n\nEnter the name of the second team: ");
        String team02Name=scanner.nextLine();
        scanner.nextLine();

        team1.setTeamName(team01Name);
        team1.setPlayersPerTeam(playersInATeam);
        team2.setTeamName(team02Name);
        team2.setPlayersPerTeam(playersInATeam);

        //Adding players to team1
        System.out.println("Please enter the names of team "+team1.teamName);
        for (int i=0;i<playersInATeam;i++){
            System.out.print((i+1)+"th player's name: ");
            String playerName=scanner.nextLine();
            scanner.nextLine();
            team1.addPlayer(new Player(playerName));
            System.out.println();
        }

        //Adding players to team2
        System.out.println("Please enter the names of team "+team2.teamName);
        for (int i=0;i<playersInATeam;i++){
            System.out.print((i+1)+"th player's name: ");
            String playerName=scanner.nextLine();
            scanner.nextLine();
            team2.addPlayer(new Player(playerName));
            System.out.println();
        }





    }
    /*void setTeams(){
        System.out.print("Enter the name of the first team: ");
        String team1Name=scanner.nextLine();
        scanner.nextLine();
        System.out.print("Enter the number of players in a team: ");
        playersInATeam=scanner.nextInt();
        scanner.nextLine();
        Team team01=new Team(team1Name,playersInATeam);

        System.out.print("\n\nEnter the name of the second team: ");
        String team2Name=scanner.nextLine();
        scanner.nextLine();
        Team team02=new Team(team2Name,playersInATeam);
        clear();
    }

    void setPlayers(){
        Player[] players=new Player[playersInATeam];
        
        System.out.println("Please enter the names of team ");

    }

    */
}
