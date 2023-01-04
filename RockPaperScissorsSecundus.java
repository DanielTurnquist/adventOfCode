import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RockPaperScissorsSecundus {
    public static Map<String, Integer> outcomeAndScore = new HashMap<String, Integer>();
    public static Map<String, Integer> letterToPlayVal = new HashMap<String, Integer>();

    public static void main(String[] args) {
        int totalScore = 0;
        outcomeAndScore.put("X", 0);
        outcomeAndScore.put("Y", 3);
        outcomeAndScore.put("Z", 6);
//        letterToPlayVal.put("X", 0);
//        letterToPlayVal.put("Y", 1);
//        letterToPlayVal.put("Z", 2);
        letterToPlayVal.put("A", 0);
        letterToPlayVal.put("B", 1);
        letterToPlayVal.put("C", 2);
        List<String> matches = new ArrayList<String>();
        try {
            File inputFile = new File("rockPaperScissorsInput.txt");
            Scanner myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                matches.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        for (String match: matches) {
            totalScore = totalScore + getMatchAndPlayScore(match);
        }
        System.out.println(totalScore);
    }

    public static int getMatchAndPlayScore(String match){
        int score = 0;
        String[] plays = match.split(" ");
        score = score + outcomeAndScore.get(plays[1]);
        score = score + getMatchScore(plays);
        System.out.println(match + ": " + score);
        return score;
    }

    public static int getMatchScore(String[] plays){
        int opponent = letterToPlayVal.get(plays[0]);
        String outcome = plays[1];
        if (outcome.equals("X")) {
            //loss
            return ((opponent + 2) % 3) + 1;
        }
        if (outcome.equals("Y")) {
            //tie
            return opponent + 1;
        }
        if (outcome.equals("Z")) {
            //win
            return ((opponent + 1) % 3) + 1;
        }
        return -1; //error!
    }
}
