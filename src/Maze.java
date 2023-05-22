import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Objects;

public class Maze {

    // read the content of text file into a 2-dimensional array of strings
// each row of the array is a line of the file
// each column of the array is a word of the file
    Cell[][] maze;
    public int rows;
    public int cols;
    boolean solvable;
    static Cell startingPoint;

    boolean solved;

    public Maze(String filename){
        maze = readMaze(filename);
        rows = maze.length;
        cols = maze[0].length;
        solvable = true;
        solved = false;
        startingPoint = findStartingPoint(maze);
    }

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
    public  void printMaze(String[][] mazeArray) {
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
    // method that prints a cell at a given position
    public  void printMazeCellAtPosition(Cell[][] maze, int row, int col) {
        System.out.println(maze[row][col].type);
    }

public static int getRows(Cell[][] maze) {
    return maze.length;

}
    public static int getCols(Cell[][] maze) {

        return maze[0].length;
    }

    public static Cell findStartingPoint(Cell[][] maze) {
       // Cell[][] startingPoint = new Cell[1][1];
        for (int i = 0; i < getRows(maze); i++) {
            for (int j = 0; j < getCols(maze) ; j++) {
                if (maze[i][j].type.equals("m")) {

                    startingPoint = maze[i][j];
                    startingPoint.isStartingPoint = true;
                    return startingPoint;


                }
            }
        }
        return null;
    }

    public static void trailQueue(Cell[][] maze, Cell currentCell, String direction) {
       // replace the cell value with a ">"
        switch (direction){
            case "right":
                maze[currentCell.row][currentCell.col].type = ">";
                //maze

                break;
            case "left":
                maze[currentCell.row][currentCell.col].type = "<";
                break;
            case "up":
                maze[currentCell.row][currentCell.col].type = "^";
                break;
            case "down":
                maze[currentCell.row][currentCell.col].type = "˅";
                break;
        }



    }

public  void setCurrentPosition(Cell[][] maze,  int row, int col) {

}

    public  int[] getCellPosition(Cell[][] maze, Cell cell) {
        int[] position = new int[2];
        for (int i = 0; i < Maze.getRows(maze); i++) {
            for (int j = 0; j < Maze.getCols(maze); j++) {
                if (maze[i][j].equals(cell)) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        return null;
    }


   public static void search(Maze maze){
       DoubleLinkedStack<Cell> stack = new DoubleLinkedStack<>();
        Cell currentCell = startingPoint;
        stack.push(currentCell);

        boolean moreToSearch = true;
        while (!stack.isEmpty() && moreToSearch == true){

            Cell j = stack.pop();
            j.visited = true;
            trailQueue.enqueue(j);
            printMaze(maze.maze);
            System.out.println("__________________________");
            if (j.type.equals("c")){
                moreToSearch = false;
                maze.solved = true;
                System.out.println("Maze solved");

        }
        else {
            // every non-wall cell k that is a neighbour of j and is not visited is pushed onto the stack
            // and marked as visited
            Cell[] neighbours = getNeighbours(maze, j);
            for (Cell neighbour : neighbours) {
                if (!neighbour.visited && !neighbour.type.equals("1")) {
                    stack.push(neighbour);
                    neighbour.visited = true;
                }


            }
        }
        }

            }

    private static Cell[] getNeighbours(Maze maze, Cell j) {
        Cell[] neighbours = new Cell[4];
        int row = j.row;
        int col = j.col;
        if (row > 0) {
            neighbours[0] = maze.maze[row - 1][col];
        }
        if (row < maze.maze.length - 1) {
            neighbours[1] = maze.maze[row + 1][col];
        }
        if (col > 0) {
            neighbours[2] = maze.maze[row][col - 1];
        }
        if (col < maze.maze[0].length - 1) {
            neighbours[3] = maze.maze[row][col + 1];
        }
        return neighbours;
    }


    public static void main(String[] args) {
        //String[][] mazeArray = readMazeTextFile("src/maze.txt");
       // printMaze(mazeArray);
        //printMazeCharAtPosition(mazeArray, 4, 3);
       // printMaze(readMaze("src/maze.txt"));
        //printMazeCellAtPosition(readMaze("src/maze.txt"), 4, 3);

       // Cell[][] maze = readMaze("src/maze.txt");
        //System.out.println(getCols(maze));
       // System.out.println(getRows(maze));

      //     System.out.println(Arrays.toString(getCellPosition(maze, findStartingPoint(maze))));
       // System.out.println(findStartingPoint(maze));
       // System.out.println(maze[4][7].type);
       // trailQueue(maze, maze[4][7], "right");
        //printMaze(maze);
        search(new Maze("src/maze.txt"));
    }
}
