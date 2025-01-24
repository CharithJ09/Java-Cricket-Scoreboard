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
        clear();
        Match match;
        FirstInningsScoreboard firstInningsScoreboard;
        SecondInningsScoreboard secondInningsScoreboard;
        Scanner scanner=new Scanner(System.in);
        int playersInATeam=0;
        int batsmenInATeam=0;

        //Setting the teams
        System.out.print("Enter the name of the first team: ");
        String team01Name=scanner.nextLine();

        System.out.print("Enter the name of the second team: ");
        String team02Name=scanner.nextLine();


        clear();

        do {
            System.out.print("Please enter the number of players in a team: ");
            playersInATeam=scanner.nextInt();
            if (playersInATeam<=11){
                break;
            }else {
                System.out.println("Number of players should be lesser or equal to 11. Please try again.");
            }
        }while (playersInATeam>11);


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
        System.out.println("Please enter the player names of team "+team1.teamName);
        team1.addPlayers();

        System.out.printf("---------------------------------------------%n");


        //Adding players to team2
        System.out.println("Please enter the player names of team "+team2.teamName);
        team2.addPlayers();

        clear();

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

        match = new Match(overLimit,ballsPerOver,playersInATeam);

        scanner.nextLine();
        String batTeamname;
        do {
            System.out.println("Please enter the name of the batting team: ");
            batTeamname=scanner.nextLine();

            if ((Objects.equals(batTeamname, team1.teamName))){
                match.setBattingTeam(team1);
                match.setBowlingTeam(team2);

                break;
            }else if ((Objects.equals(batTeamname, team2.teamName))){
                match.setBattingTeam(team2);
                match.setBowlingTeam(team1);

                break;
            }else {
                System.out.println("Invalid Team Name! Please try again.");
            }

        }while ((!Objects.equals(batTeamname, team1.teamName))&&(!Objects.equals(batTeamname, team2.teamName)));


        //Starting the Match
        firstInningsScoreboard = new FirstInningsScoreboard(match);
        int firstInningsScore  = firstInningsScoreboard.startFirstInningsScoreBoard();

        //Changing innings
        match.setSecondInningScore(firstInningsScore);
        match.changeInning(firstInningsScore);

        secondInningsScoreboard = new SecondInningsScoreboard(match);
        int secondInningsScore  = secondInningsScoreboard.startSecondInningsScoreBoard();
        match.setSecondInningScore(secondInningsScore);

        //Ending the Match
        match.matchSummary();



    }
}


