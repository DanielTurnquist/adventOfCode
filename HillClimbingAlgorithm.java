import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class StepTaken{
    int stepsSoFar;
    int[] coords;
    StepTaken(int stepsSoFar, int[] coords){
        this.stepsSoFar = stepsSoFar;
        this.coords = coords;
    }
}
public class HillClimbingAlgorithm {
    static char[][] grid;
    static HashSet<ArrayList<Integer>> placesVisited = new HashSet<>();
    static Queue<StepTaken> stepTakenQueue = new LinkedList<>();
    static ArrayList<Integer> eCoords = new ArrayList<>();
    static boolean eFound = false;

    public static void main(String[] args) throws Exception {
        List<String> inputByLine = new ArrayList<String>();
        File inputFile = new File("input.txt");
        //File inputFile = new File("test.txt");
        Scanner myReader = new Scanner(inputFile);
        while (myReader.hasNextLine()) {
            inputByLine.add(myReader.nextLine());
        }
        myReader.close();
        grid = new char[inputByLine.size()][inputByLine.get(0).length()];
        for (int i = 0; i < inputByLine.size(); i++) {
            for (int j = 0; j < inputByLine.get(i).length(); j++) {
                grid[i][j] = inputByLine.get(i).charAt(j);
            }
        }
        //int[] sCoords = getCoords('`');
        int[] eCoordsArr = getCoords('{');
        ArrayList<int[]> aList = getaCoords();
        eCoords.add(eCoordsArr[0]);
        eCoords.add(eCoordsArr[1]);
        for (int i = 0; i < aList.size(); i++) {
            stepTakenQueue.add(new StepTaken(1, aList.get(i)));
        }
        while (!eFound){
            //System.out.println(Arrays.toString(stepTakenQueue.peek().coords));
            //System.out.println(stepTakenQueue.peek().stepsSoFar);
            spreadOut(stepTakenQueue.remove());
        }
    }
    static int[] getCoords(char c) throws Exception{
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        throw new Exception("char not found");
    }
    static int getHeight(int[] coords){
        return grid[coords[0]][coords[1]];
    }
    static void spreadOut(StepTaken s){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(s.coords[0]);
        arr.add(s.coords[1]);
        if (arr.equals(eCoords)){
            eFound = true;
            System.out.println(s.stepsSoFar - 1);
        }

        considerLocation(s.coords, new int[]{s.coords[0] + 1, s.coords[1]}, s.stepsSoFar);
        considerLocation(s.coords, new int[]{s.coords[0] - 1, s.coords[1]}, s.stepsSoFar);
        considerLocation(s.coords, new int[]{s.coords[0], s.coords[1] + 1}, s.stepsSoFar);
        considerLocation(s.coords, new int[]{s.coords[0], s.coords[1] - 1}, s.stepsSoFar);
    }
    static void considerLocation(int[] coords, int[] newCoords, int s){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(newCoords[0]);
        arr.add(newCoords[1]);
        if (placesVisited.contains(arr)){
            return;
        }
        try {
            if (getHeight(coords) < getHeight(newCoords) - 1) {
                return;
            }
        } catch (IndexOutOfBoundsException e){
            return;
        }
        //System.out.println(grid[newCoords[0]][newCoords[1]] + Arrays.toString(newCoords));
        placesVisited.add(arr);
        stepTakenQueue.add(new StepTaken(s+1, newCoords));
    }

    static ArrayList<int[]> getaCoords(){
        ArrayList<int[]> toReturn = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'a') {
                    toReturn.add(new int[]{i,j});
                }
            }
        }
        return toReturn;
    }
}
