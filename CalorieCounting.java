import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalorieCounting {
    public static void main(String[] args) {
        String inputData = "";
        try {
            File inputFile = new File("CalorieCountingInput.txt");
            Scanner myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                inputData += myReader.nextLine() + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String[] partsStings = inputData.trim().split("\n\n");
        List<Integer> parts = new ArrayList<Integer>();
        for (String part : partsStings) {
            int sum = sumUpPart(part);
            parts.add(sum);
        }
        Collections.sort(parts);
        Collections.reverse(parts);
        System.out.println(parts.get(0) + parts.get(1) + parts.get(2));
    }

    public static int sumUpPart(String part){
        int total = 0;
        String[] calCounts = part.split("\n");
        for (String calCount : calCounts) {
            total += Integer.parseInt(calCount);
        }
        return total;
    }
}