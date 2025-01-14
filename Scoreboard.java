import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Scoreboard {
    int runsScored = 0;
    int wickets = 0;
    int oversRemaining;
    int ballsRemaining;

    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scoreboard scoreboard = new Scoreboard();
        Scanner scanner = new Scanner(System.in);

        //Getting Over limit
        System.out.print("Enter Number of Overs: ");
        scoreboard.oversRemaining = scanner.nextInt();

        //Getting balls per over
        System.out.print("Enter Number of balls per over: ");
        int ballsPerOver = scanner.nextInt();
        scoreboard.ballsRemaining = ballsPerOver;

        //Starting the match
        System.out.println("Starting Match...");
        TimeUnit.SECONDS.sleep(2); // Delay for 2 seconds
        clear();
        scanner.nextLine();

        //Looping until end of overs or all wickets are down
        while(scoreboard.oversRemaining > 0 && scoreboard.wickets < 10){

            clear();

            //Resetting balls remaining
            if(scoreboard.ballsRemaining == 0){
                System.out.println("End of Over...");
                TimeUnit.SECONDS.sleep(2); // Delay for 2 seconds
                scoreboard.ballsRemaining = ballsPerOver;
                scoreboard.oversRemaining--;
                if (scoreboard.oversRemaining == 0) {break;}
            }

            //Displaying the score
            System.out.println(scoreboard.runsScored+"-"+scoreboard.wickets);
            System.out.println("Overs Remaining: "+scoreboard.oversRemaining);
            System.out.println("Balls remaining: "+scoreboard.ballsRemaining);

            //Taking umpire decision
            System.out.print("Enter Decision(0/1/2/3/4/6/W/WD): ");
            String decision = scanner.nextLine();

            //Updating the scoreboard based on decision
            switch (decision) {
                case "0":
                    scoreboard.ballsRemaining--;
                    break;
                case "1":
                    scoreboard.runsScored++;
                    scoreboard.ballsRemaining--;
                    break;
                case "2":
                    scoreboard.runsScored+=2;
                    scoreboard.ballsRemaining--;
                    break;
                case "3":
                    scoreboard.runsScored+=3;
                    scoreboard.ballsRemaining--;
                    break;
                case "4":
                    scoreboard.runsScored+=4;
                    scoreboard.ballsRemaining--;
                    break;
                case "6":
                    scoreboard.runsScored+=6;
                    scoreboard.ballsRemaining--;
                    break;
                case "W":
                    scoreboard.wickets++;
                    scoreboard.ballsRemaining--;
                    break;
                case "WD":
                    scoreboard.runsScored++;
                    break;
                default:
                    System.out.println("Invalid Decision!");
                    TimeUnit.SECONDS.sleep(1);


            }


        }
        clear();
        System.out.println("End of Innings...");
        System.out.println(scoreboard.runsScored+"-"+scoreboard.wickets);
    }
}
//Was this change made