import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class MazeUtils {

    // read the content of text file into a 2-dimensional array of strings
// each row of the array is a line of the file
// each column of the array is a word of the file

    public int rows;
    public int cols;

    public static Cell[][] readMaze(String filename){
        String[][] mazeArray = readMazeTextFile(filename);
        Cell[][] maze = new Cell[mazeArray.length][mazeArray[0].length];
        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[0].length; j++) {
                maze[i][j] = new Cell();
                maze[i][j].row = i;
                maze[i][j].col = j;
                maze[i][j].type = mazeArray[i][j];
                maze[i][j].visited = false;
            }
        }
        return maze;
    }
    public static String[][] readMazeTextFile(String fileName) {
        String[][] mazeArray = new String[0][];
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(" ");
                mazeArray = Arrays.copyOf(mazeArray, mazeArray.length + 1);
                mazeArray[mazeArray.length - 1] = words;
            }
        } catch (Exception e) {

            System.out.println("Error reading maze file");
        }
        return mazeArray;
    }

    // print the content of the text file
    public static void printMaze(String[][] mazeArray) {
        for (String[] strings : mazeArray) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }

    public static void printMaze(Cell[][] maze) {
        for (Cell[] cells : maze) {
            for (Cell cell : cells) {
                System.out.print(cell.type + " ");
            }
            System.out.println();
        }
    }
    // method that prints the symbol of the maze at a given position
    public static void printMazeCharAtPosition(String[][] mazeArray, int row, int col) {
        System.out.println(mazeArray[row][col]);
    }


    public static void main(String[] args) {
        //String[][] mazeArray = readMazeTextFile("src/maze.txt");
       // printMaze(mazeArray);
        //printMazeCharAtPosition(mazeArray, 4, 3);
        printMaze(readMaze("src/maze.txt"));

    }
}
