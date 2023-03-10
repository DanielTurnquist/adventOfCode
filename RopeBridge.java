import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RopeBridge {
    static int[] head = new int[]{0, 0}; //row, col
    static int[][] tail = new int[][]{
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0}
    };
    static Set<String> locs = new HashSet<>();

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
        for (String line : inputByLine) {
            executeCommand(line);
        }
        System.out.println(locs.size());
    }

    public static void executeCommand(String line) {
        int dist = -1;

        Matcher m = Pattern.compile("(\\d+)")
                .matcher(line);
        if (m.find()) {
            dist = Integer.parseInt(m.group());
        }
        for (int i = 0; i < dist; i++) {
            int[] oldHeadLoc = head.clone();
            switch (line.charAt(0)) {
                case 'U':
                    head[0]--;
                    break;
                case 'R':
                    head[1]++;
                    break;
                case 'D':
                    head[0]++;
                    break;
                case 'L':
                    head[1]--;
            }
            //System.out.println(Arrays.toString(head));
            if(tailOutOfRange()){
                tail[0][0] = oldHeadLoc[0];
                tail[0][1] = oldHeadLoc[1];
            }
            //System.out.println("t:" + Arrays.toString(transform));

            upDateTail();
            locs.add("" + tail[8][0] + "," + tail[8][1]);
            //System.out.println("locs: " + locs);
        }
    }

    public static boolean tailOutOfRange(){
        return abs(head[0] - tail[0][0]) > 1 || abs(head[1] - tail[0][1]) > 1;
    }

    public static boolean outOfRange(int[] t1, int[]t2){
        return abs(t1[0] - t2[0]) > 1 || abs(t1[1] - t2[1]) > 1;
    }

    public static void upDateTail(){
        for (int j = 0; j < tail.length-1; j++) {
            if (outOfRange(tail[j], tail[j+1])){
                catchUp(tail[j], tail[j+1]);
            }
        }
    }

    public static void catchUp(int[] first, int[] second){
        if (first[0] > second[0]){
            second[0]++;
        } else if (first[0] < second[0]){
            second[0]--;
        }
        if (first[1] > second[1]){
            second[1]++;
        } else if (first[1] < second[1]){
            second[1]--;
        }
    }

    public static int abs(int a){
        if (a < 0){
            a *= -1;
        }
        return a;
    }
}