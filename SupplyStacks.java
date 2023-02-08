import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
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
        for (String line : inputLines) {
            executeInstruction2(extractNumsFromLine(line));
            System.out.println(line);
            printStacks();
            System.out.println("----------------------");
        }
        
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
            "GHPZJTR",
            "FQZDNJCT",
            "MFSGWPVN",
            "QPBVCG",
            "NPBZ",
            "JPW"
        };
        for (int i = 0; i < stackStrings.length; i++) {
            for (int j = 0; j < stackStrings[i].length(); j++) {
                stacks.get(i).add(stackStrings[i].charAt(stackStrings[i].length() - 1 - j));
            }
        }
    }
    public static void executeInstruction(List<Integer> nums){
        for (int i = 0; i < nums.get(0); i++) {
            stacks.get(nums.get(2) - 1).add(stacks.get(nums.get(1) - 1).pop());
        }
    }
    public static void executeInstruction2(List<Integer> nums){
        Stack<Character> grabbed = new Stack<Character>();        
        for (int i = 0; i < nums.get(0); i++) {
            grabbed.add(stacks.get(nums.get(1)- 1).pop());
        }
        while(grabbed.size() > 0){
            stacks.get(nums.get(2) - 1).add(grabbed.pop());
        }

    }
    static void printStacks(){
        for (int i = 0; i < stacks.size(); i++) {
            System.out.println(i+1 + " " + stacks.get(i));
        }
    } 
}
