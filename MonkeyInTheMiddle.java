import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class monkey{
    String operator;
    String arg2;
    List<Integer> items;
    int testDivisor;
    int trueMonkey;
    int falseMonkey;
    int inspections = 0;
    public void setOperation(String line){
        String[] lineArr = line.trim().split(" ");
        this.operator = lineArr[4];
        this.arg2 = lineArr[5];
    }
    public void takeTurn(){
        while (items.size() > 0){
            this.processItem(items.remove(0));
        }
    }
    public void processItem(Integer item){
        inspections++;
        long arg;
        long thrown;
        if (this.arg2.equals("old")){
            arg = (long) item;
        } else {
            arg = Integer.parseInt(this.arg2);
        }
        
        if (operator.equals("*")) {
            thrown = (long) ((long) item * (long) arg);
        } else {
            thrown = item + arg;
        }
        //thrown = Math.floorDiv(thrown, 3);
        thrown %= MonkeyInTheMiddle.divisor;
        if (thrown % testDivisor == 0){
            MonkeyInTheMiddle.monkeys[trueMonkey].items.add((int) thrown);
        } else {
            MonkeyInTheMiddle.monkeys[falseMonkey].items.add((int) thrown);
        }
    }
}
public class MonkeyInTheMiddle {
    static monkey[] monkeys;
    static int divisor = 1;
    public static void main(String[] args) {

        String inputString = "";
        String[] monkeyStrings;

        try {
            File inputFile = new File("input.txt");
            //File inputFile = new File("test.txt");
            Scanner myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                inputString += (myReader.nextLine()) + '\n';
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        inputString = inputString.trim();
        monkeyStrings = inputString.split("\n\n");
        monkeys = new monkey[monkeyStrings.length];
        for (int i = 0; i < monkeys.length; i++) {
            monkeys[i] = new monkey();
            String[] monkeyStringArray = monkeyStrings[i].split("\n");
            monkeys[i].items = getStartingItems(monkeyStringArray[1]);
            monkeys[i].setOperation(monkeyStringArray[2]);
            monkeys[i].testDivisor = Integer.parseInt(monkeyStringArray[3].replaceAll("[^0-9]", ""));
            monkeys[i].trueMonkey = Integer.parseInt(monkeyStringArray[4].replaceAll("[^0-9]", ""));
            monkeys[i].falseMonkey = Integer.parseInt(monkeyStringArray[5].replaceAll("[^0-9]", ""));
            divisor *= monkeys[i].testDivisor;
        }

        for (int i = 0; i < 10000; i++) {
            runRound();
        }
        int first = 0;
        int second = 0;
        for (int i = 0; i < monkeys.length; i++) {
            if (first < monkeys[i].inspections){
                second = first;
                first = monkeys[i].inspections;
            } else if(second < monkeys[i].inspections){
                second = monkeys[i].inspections;
            }
        }
        System.out.println(first + " * " + second);
    }
    public static List<Integer> getStartingItems(String str){
        // Replacing every non-digit number
        // with a space(" ")
        str = str.replaceAll("[^0-9]", " "); // regular expression

        // Replace all the consecutive white
        // spaces with a single space
        str = str.replaceAll(" +", " ").trim();
        String[] strArr = str.split(" ");
        List<Integer> toReturn = new ArrayList<>();
        for (int i = 0; i <  strArr.length; i++) {
            toReturn.add(Integer.parseInt(strArr[i]));
        }
        return toReturn;
    }

    public static void runRound(){
        for (int i = 0; i < monkeys.length; i++) {
            monkeys[i].takeTurn();
        }
    }

}
