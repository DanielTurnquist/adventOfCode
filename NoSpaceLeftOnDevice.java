import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Dir {
    public Dir(String name) {
        this.name = name;
    }

    String name;
    int size = 0;
    int recursiveSize = 0;
    List<Dir> subDirs = new ArrayList<>();
    Dir parent;

    @Override
    public String toString() {
        return name + " " + size;
    }
}

public class NoSpaceLeftOnDevice {
    static Dir home;
    static Dir currentDir;
    static int totalP1 = 0;
    static int minSpaceNeeded = 0;
    static int deletionCandidate = 70000000;

    public static void main(String[] args) {
        home = new Dir("/");
        currentDir = home;
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
            handleLine(line);
        }
        setRecursiveSizes(home);
        minSpaceNeeded = 30000000 - (70000000 - home.recursiveSize);
        setDeletionCandidate(home);
        System.out.println(minSpaceNeeded);
        System.out.println(deletionCandidate);
    }

    public static void handleLine(String line) {
        String[] lineItemized = line.split(" ");
        if (lineItemized[0].equals("$")) {
            executeCommand(lineItemized);
            return;
        }
        addDirOrFile(lineItemized);
    }

    public static void executeCommand(String[] cmd) {
        if (cmd[1].equals("cd")) {
            executeCD(cmd[2]);
        }
    }

    public static void addDirOrFile(String[] item) {
        if (item[0].equals("dir")) {
            Dir newDir = new Dir(item[1]);
            newDir.parent = currentDir;
            currentDir.subDirs.add(newDir);
            return;
        }
        currentDir.size += Integer.parseInt(item[0]);
    }

    public static void executeCD(String loc) {
        if (loc.equals("..")) {
            currentDir = currentDir.parent;
            return;
        }
        for (int i = 0; i < currentDir.subDirs.size(); i++) {
            if (currentDir.subDirs.get(i).name.equals(loc)) {
                currentDir = currentDir.subDirs.get(i);
            }
        }
    }

    public static void setRecursiveSizes(Dir dir) {
        if (dir.subDirs.size() == 0) {
            //base case
            dir.recursiveSize = dir.size;
            if (dir.recursiveSize <= 100000) {
                totalP1 += dir.recursiveSize;
            }
            return;
        }
        for (int i = 0; i < dir.subDirs.size(); i++) {
            setRecursiveSizes(dir.subDirs.get(i));
            dir.recursiveSize += dir.subDirs.get(i).recursiveSize;
        }
        dir.recursiveSize += dir.size;
        if (dir.recursiveSize <= 100000) {
            totalP1 += dir.recursiveSize;
        }
    }

    public static void setDeletionCandidate(Dir dir) {
        if (dir.recursiveSize > minSpaceNeeded && dir.recursiveSize < deletionCandidate){
            deletionCandidate = dir.recursiveSize;
        }
        for (int i = 0; i < dir.subDirs.size(); i++) {
            setDeletionCandidate(dir.subDirs.get(i));
        }
    }
}
