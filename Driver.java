import java.io.IOException;
import java.util.Objects;
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

        System.out.print("Enter the name of the second team: ");
        String team02Name=scanner.nextLine();


        clear();

        int playersInATeam=0;
        do {
            System.out.print("Please enter the number of players in a team: ");
            playersInATeam=scanner.nextInt();
            if (playersInATeam<=11){
                break;
            }else {
                System.out.println("Number of players should be lesser or equal to 11. Please try again.");
            }
        }while (playersInATeam>11);


        int batsmenInATeam=0;
        do {
            System.out.print("Please enter the number of batsmen in a team: ");
            batsmenInATeam=scanner.nextInt();
            if ((playersInATeam-batsmenInATeam)>1){
                break;
            }
            System.out.println("There should be at least 2 bowlers. Please try again.");
        } while (((playersInATeam-batsmenInATeam)<=1));


        Team team1=new Team(team01Name,playersInATeam,batsmenInATeam);
        Team team2=new Team(team02Name,playersInATeam,batsmenInATeam);

        clear();


        //Adding players to team1
        System.out.println("\nPlease enter the player names of team "+team1.teamName);
        team1.addPlayers(playersInATeam,batsmenInATeam);


        //Adding players to team2
        System.out.println("Please enter the player names of team "+team2.teamName);
        team2.addPlayers(playersInATeam,batsmenInATeam);

        //Setting the match and Scoreboard
        System.out.println("Please enter the over limit of the match: ");
        int overLimit=scanner.nextInt();

        int ballsPerOver;
        do {
            System.out.println("Please enter the number of balls per over: ");
            ballsPerOver=scanner.nextInt();
            if (ballsPerOver<=6){
                break;
            }else {
                System.out.println("Balls per over can't exceed 6. Please try again.");
            }
            clear();
        } while (ballsPerOver>6);

        String batTeamname;
        do {
            System.out.println("Please enter the name of the batting team: ");
            batTeamname=scanner.nextLine();

            if ((Objects.equals(batTeamname, team1.teamName))){
                Match match1=new Match(overLimit,ballsPerOver,team1,team2);
                FirstInningsScoreboard firstInningsScoreboard=new FirstInningsScoreboard(match1);
                break;
            }else if ((Objects.equals(batTeamname, team2.teamName))){
                Match match1=new Match(overLimit,ballsPerOver,team2,team1);
                FirstInningsScoreboard firstInningsScoreboard=new FirstInningsScoreboard(match1);
                break;
            }else {
                System.out.println("No any matches found. Please try again.");
            }
            clear();
        }while ((!Objects.equals(batTeamname, team1.teamName))&&(!Objects.equals(batTeamname, team2.teamName)));



    }
}


