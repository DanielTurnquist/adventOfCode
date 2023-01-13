import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CathodeRayTube {

    static int x=1;
    static int cycle = 0;
    static int numToAdd = 0;
    static int addCyclesRemaining = 0;
//    static int total = 0;
    static Scanner myReader;
    static StringBuilder output = new StringBuilder();
    public static void main(String[] args) {
        try {
            File inputFile = new File("input.txt");
            //File inputFile = new File("test.txt");
            myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                runCycle();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //System.out.println(total);
        System.out.println(output.toString());
    }

    static void executeCommand(String cmd){
        String[] cmdSplit = cmd.split(" ");
        if (cmdSplit[0].equals("addx")){
            addCyclesRemaining = 2;
            numToAdd = Integer.parseInt(cmdSplit[1]);
        }
    }

    static void runCycle(){
        if (addCyclesRemaining == 0){
            x += numToAdd;
            addCyclesRemaining--;
            executeCommand(myReader.nextLine());
        }
        else if (addCyclesRemaining < 0){
            executeCommand(myReader.nextLine());
        }
//        if ((cycle + 1 -20) % 40 == 0){
//            total += x * (cycle + 1);
//        }
        if (abs(x - cycle%40) < 2){
            output.append('#');
        } else {
            output.append(' ');
        }
        if ((cycle + 1) % 40 == 0){
            output.append('\n');
        }

        addCyclesRemaining--;
        cycle++;
    }

    public static int abs(int a){
        if (a < 0){
            a *= -1;
        }
        return a;
    }
}
