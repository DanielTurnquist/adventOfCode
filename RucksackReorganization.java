import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;;

public class RucksackReorganization {
    public static void main(String[] args) {
        List<String> rucksacks = new ArrayList<String>();
        try {
            File inputFile = new File("RucksackReorganizationInput.txt");
            //File inputFile = new File("test.txt");
            Scanner myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                rucksacks.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        int total = 0;
        for (int i = 0; i < rucksacks.size(); i += 3) {
            HashSet<Character> firstSack = new HashSet<>();
            HashSet<Character> secondSack = new HashSet<>();
            for (int j = 0; j < rucksacks.get(i).length(); j++) {
                firstSack.add(rucksacks.get(i).charAt(j));
            }
            for (int j = 0; j < rucksacks.get(i+1).length(); j++) {
                secondSack.add(rucksacks.get(i+1).charAt(j));
            }
            firstSack.retainAll(secondSack);
            for (int j = 0; j < rucksacks.get(i+2).length(); j++) {
                if (firstSack.contains(rucksacks.get(i+2).charAt(j))){
                    total += getItemPriority(rucksacks.get(i+2).charAt(j));
                    break;
                };
            }
        }
        System.out.println(total);
    }

    public static int getItemPriority(char c){
        if ((int) c > 96){
            return (int) c % 96;
        }
        return (int) c % 64 + 26;
    }
}
