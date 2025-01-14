import java.io.IOException;
import java.util.Scanner;

enum Decision{
    DOT_BALL,ONE_RUN,TWO_RUNS,THREE_RUNS,BOUNDARY,SIX,WIDE,WICKET,NO_BALL
}

public class Driver {
    Scanner scanner=new Scanner(System.in);
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

    }
    void setTeams(){
        System.out.println("");
    }
}
