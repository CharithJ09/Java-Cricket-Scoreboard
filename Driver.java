import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

enum Decision{
    DOT_BALL,ONE_RUN,TWO_RUNS,THREE_RUNS,BOUNDARY,SIX,WIDE,WICKET,NO_BALL
}

public class Driver {

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

        //Setting the teams
        System.out.print("Enter the name of the first team: ");
        String team01Name=scanner.nextLine();
        //scanner.nextLine();

        System.out.print("Enter the name of the second team: ");
        String team02Name=scanner.nextLine();
        //scanner.nextLine();

        System.out.print("\nPlease enter the number of players in a team: ");
        int playersInATeam=scanner.nextInt();
        //scanner.nextLine();

        int batsmenInATeam=0;
        do {
            System.out.print("Please enter the number of batsmen in a team");
            batsmenInATeam=scanner.nextInt();
            if ((playersInATeam-batsmenInATeam)>1){
                break;
            }
            System.out.println("There should be at least 2 bowlers");
        } while (((playersInATeam-batsmenInATeam)<=1));


        Team team1=new Team(team01Name,playersInATeam,batsmenInATeam);
        Team team2=new Team(team02Name,playersInATeam,batsmenInATeam);

        //Adding players to team1
        System.out.println("\nPlease enter the player names of team "+team1.teamName);
        team1.addPlayers(playersInATeam,batsmenInATeam);


        //Adding players to team2
        System.out.println("Please enter the player names of team "+team2.teamName);
        team2.addPlayers(playersInATeam,batsmenInATeam);


        System.out.println("Please enter the over limit of the match");
        int overLimit=scanner.nextInt();


        }
}


