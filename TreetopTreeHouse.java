import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TreetopTreeHouse {
    static List<List<Integer>> rows = new ArrayList<>();
    static int p1total = 0;
    static int p2ans = 0;
    public static void main(String[] args) {
        List<String> inputByLine = new ArrayList<String>();
        try {
            File inputFile = new File("input.txt");
            //File inputFile = new File("test.txt");
            Scanner myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                inputByLine.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (String line: inputByLine) {
            rows.add(extractNumsFromLine(line));
        }
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(0).size(); j++) {
                int ss = getScenicScore(i, j);
                if (ss > p2ans){
                    p2ans = ss;
                }
            }
        }
        System.out.println(p2ans);
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

    public static boolean treeIsVisible(int row, int col) {
        boolean result =  visibleHorizontally(row, col) || visibleVertically(row, col);
        //printVisibilityInGrid(row, col, result);
        return result;
    }

    public static boolean visibleHorizontally(int row, int col){
        int height = rows.get(row).get(col);
        boolean fromLeft = true;
        boolean fromRight = true;
        for (int i = 0; i < rows.get(row).size(); i++) {
            if (i < col && rows.get(row).get(i) >= height){
                fromLeft = false;
            }
            if (i > col && rows.get(row).get(i) >= height)
                fromRight = false;
        }
        return fromLeft || fromRight;
    }

    public static boolean visibleVertically(int row, int col){
        int height = rows.get(row).get(col);
        boolean fromAbove = true;
        boolean fromBelow = true;
        for (int i = 0; i < rows.size(); i++) {
            if (i < row && rows.get(i).get(col) >= height){
                fromAbove = false;
            }
            if (i > row && rows.get(i).get(col) >= height)
                fromBelow = false;
        }
        return fromAbove || fromBelow;

    }
    public static void printVisibilityInGrid(int row, int col, boolean isVisible){
        String grid = "";
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(0).size(); j++) {
                if (i == row && j == col){
                    if (isVisible){
                        grid += "V";
                    } else {
                        grid += "A";
                    }
                }
                else {
                    grid += rows.get(i).get(j);
                }
            }
            grid += "\n";
        }
        System.out.println(grid);
    }

    public static int getScenicScore(int row, int col){
        return getHorizontalScore(row, col) * getVerticalScore(row, col);
    }

    public static int getHorizontalScore(int row, int col){
        int left = 0;
        int right = 0;
        int height = rows.get(row).get(col);
        for (int i = col - 1; i >= 0; i--) {
            left++;
            if (rows.get(row).get(i) >= height){
                break;
            }
        }
        for (int i = col + 1; i < rows.get(row).size(); i++) {
            right++;
            if (rows.get(row).get(i) >= height){
                break;
            }
        }
        return left * right;
    }

    public static int getVerticalScore(int row, int col){
        int up = 0;
        int down = 0;
        int height = rows.get(row).get(col);
        for (int i = row - 1; i >= 0; i--) {
            up++;
            if (rows.get(i).get(col) >= height){
                break;
            }
        }
        for (int i = row + 1; i < rows.size(); i++) {
            down++;
            if (rows.get(i).get(col) >= height){
                break;
            }
        }
        return up * down;
    }

}
