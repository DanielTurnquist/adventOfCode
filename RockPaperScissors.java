import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.PlainDocument;

public class RockPaperScissors {
    public static Map<String, Integer> playsAndScores = new HashMap<String, Integer>();
    public static Map<String, Integer> letterToPlayVal = new HashMap<String, Integer>();

    public static void main(String[] args) {
        int totalScore = 0;
        playsAndScores.put("X", 1);
        playsAndScores.put("Y", 2);
        playsAndScores.put("Z", 3);
        letterToPlayVal.put("X", 0);
        letterToPlayVal.put("Y", 1);
        letterToPlayVal.put("Z", 2);
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
        score = score + playsAndScores.get(plays[1]);
        score = score + getMatchScore(plays);
        System.out.println(match + ": " + score);
        return score;
    }

    public static int getMatchScore(String[] plays){
        int opponent = letterToPlayVal.get(plays[0]);
        int self = letterToPlayVal.get(plays[1]);
        if (opponent % 3 == ((self + 1) % 3)) {
            //loss
            return 0;
        }
        if (opponent == self) {
            //tie
            return 3;
        }
        if ((opponent + 1) % 3 == (self % 3)) {
            //win
            return 6;
        }
        return -1; //error!
    }


}
