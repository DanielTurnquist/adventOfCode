import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplyStacks {
    public static List<Stack<Character>> stacks = new ArrayList<Stack<Character>>();

    public static void main(String[] args) {
        List<String> inputLines = new ArrayList<String>();
        try {
            File inputFile = new File("SupplyStacksInput.txt");
            //File inputFile = new File("test.txt");
            Scanner myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                inputLines.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //List<Stack<Character>> stacks = new ArrayList<Stack<Character>>();
        for (int i = 0; i < 9; i++) {
            stacks.add(new Stack<Character>());
        }
        setupStacks();
        //System.out.println(stacks.get(4));
        for (String line : inputLines) {
            executeInstruction(extractNumsFromLine(line));
        }
        System.out.println(stacks.get(2));
    }   
    public static List<Integer> extractNumsFromLine(String line){
        //System.out.println(line);
        List<Integer> allMatches = new ArrayList<Integer>();
        Matcher m = Pattern.compile("(\\d+)")
            .matcher(line);
        while (m.find()) {
          allMatches.add(Integer.parseInt(m.group()));
        }

        //System.out.println(allMatches);
        return allMatches;
    } 
    public static void setupStacks(){
        String[] stackStrings = new String[]{
            "JZGVTDBN",
            "FPWDMRS",
            "ZSRCV",
            "GHPZHTR",
            "FQZDNJCT",
            "MFSGWPVN",
            "QPBVCG",
            "NPBZ",
            "JPW"
        };
        for (int i = 0; i < stackStrings.length; i++) {
            for (int j = 0; j < stackStrings[i].length(); j++) {
                stacks.get(i).add(stackStrings[i].charAt(j));
            }
        }
    }
    public static void executeInstruction(List<Integer> nums){
        for (int i = 0; i < nums.get(0); i++) {
            stacks.get(nums.get(2) - 1).add(stacks.get(nums.get(1) - 1).pop());
        }
    }
}
