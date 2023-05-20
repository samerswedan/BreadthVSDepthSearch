import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Part1 {

    // read the content of text file into a 2-dimensional array of strings
// each row of the array is a line of the file
// each column of the array is a word of the file
    public static String[][] readTextFile(String fileName) {
        String[][] textArray = new String[0][];
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(" ");
                textArray = Arrays.copyOf(textArray, textArray.length + 1);
                textArray[textArray.length - 1] = words;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textArray;
    }

    // print the content of the text file
    public static void printTextFile(String[][] textArray) {
        for (int i = 0; i < textArray.length; i++) {
            for (int j = 0; j < textArray[i].length; j++) {
                System.out.print(textArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    // make a method that prints the symbol of the maze at a given position
    public static void printPosition(String[][] textArray, int row, int col) {
        System.out.println(textArray[row][col]);
    }


    public static void main(String[] args) {
        String[][] textArray = readTextFile("src/maze.txt");
        printTextFile(textArray);
        printPosition(textArray, 1, 8);

    }
}
