import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TreetopTreeHouse {
    public static void main(String[] args) {
        List<String> inputByLine = new ArrayList<String>();
        try {
            //File inputFile = new File("input.txt");
            File inputFile = new File("test.txt");
            Scanner myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                inputByLine.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        List<List<Integer>> rows = new ArrayList<>();
        for (String line: inputByLine) {
            rows.add(extractNumsFromLine(line));
        }
        System.out.println("bp");
    }

    public static List<Integer> extractNumsFromLine(String line) {
        //System.out.println(line);
        List<Integer> allMatches = new ArrayList<Integer>();
        Matcher m = Pattern.compile("(\\d)")
                .matcher(line);
        while (m.find()) {
            allMatches.add(Integer.parseInt(m.group()));
        }
        return allMatches;
    }
}
