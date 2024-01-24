/*
Samer Swedan 
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;


// This class will handle reading the maze file and creating
// the maze and handle the maze searching algorithms
public class Maze {

    Cell[][] maze; // a 2-dimensional array of cell objects
    public int rows; // the amount of rows in the maze
    public int cols; // the amount of columns in the maze
    boolean solvable; // boolean to determine if the maze is solvable or not
    static Cell startingPoint; // the starting point of the maze (the mouse's starting point)
    boolean solved; // boolean to determine if the maze has been solved or not

    // constructor for the maze class
    public Maze(String filename) throws FileNotFoundException {
        maze = readMaze(filename);
        rows = maze.length;
        cols = maze[0].length;
        solvable = true;
        solved = false;
        startingPoint = findStartingPoint(maze);
    }


    /**
     * reads the maze string array from readMazeTextFile and creates a 2-dimensional array of cell objects from it
     * @param filename the name of the maze file
     * @return maze a 2-dimensional array of cell objects
     */
    public static Cell[][] readMaze(String filename) {
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


    /**
     * A helper method to read the maze text file into a 2-dimensional array of strings
     * @param fileName the name of the maze file
     * @return mazeArray a 2-dimensional array of strings
     */
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

    /**
     * prints the maze to the console
     * @param maze the maze to be printed
     */
    public static void printMaze(Cell[][] maze) {
        for (Cell[] cells : maze) {
            for (Cell cell : cells) {
                System.out.print(cell.type + " ");
            }
            System.out.println();
        }
    }

    /**
     * gets the number of rows in the maze
     * @param maze the maze
     * @return the number of rows in the maze
     */
    public static int getRows(Cell[][] maze) {
        return maze.length;

    }

    /**
     * gets the number of columns in the maze
     * @param maze the maze
     * @return the number of columns in the maze
     */
    public static int getCols(Cell[][] maze) {

        return maze[0].length;
    }

    /**
     * finds the cell that is the starting point of the maze
     * @param maze the maze that contains the starting point
     * @return null if the starting cannot be found
     */
    public static Cell findStartingPoint(Cell[][] maze) {

        for (int i = 0; i < getRows(maze); i++) {
            for (int j = 0; j < getCols(maze); j++) {
                if (maze[i][j].type.equals("m")) {

                    startingPoint = maze[i][j];
                    startingPoint.isStartingPoint = true;
                    return startingPoint;


                }
            }
        }
        return null;
    }

    /**
     * Uses a Depth-first search to solve the maze
     * @param maze the maze to be searched
     *
     */
    public static void DepthFirstSearch(Maze maze) {

        DoubleLinkedStack<Cell> stack = new DoubleLinkedStack<>();
        Cell currentCell = startingPoint;
        stack.push(currentCell);

        int moves = 0;
        boolean moreToSearch = true;
        while (!stack.isEmpty() && moreToSearch) {

            Cell j = stack.pop();
            j.visited = true;
            trailQueue.enqueue(j);

            if (j.type.equals("c")) {
                moreToSearch = false;
                maze.solved = true;


            } else {

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
            moves++;
            printMaze(maze.maze);
            System.out.println("_____________________________");
            if (maze.solved) {
                System.out.println("Solved in " + moves + " moves");

            } else if (stack.isEmpty()) {
                System.out.println("Not Solvable");
            }
        }

    }

    /**
     * Uses a Breadth-first search to solve the maze
     * @param maze the maze to be searched
     */
    public static void BreadthFirstSearch(Maze maze) {

        CircularDoubleLinkedQueue<Cell> Cqueue = new CircularDoubleLinkedQueue<>();
        Cell currentCell = startingPoint;
        Cqueue.enqueue(currentCell);

        int moves = 0;
        boolean moreToSearch = true;
        while (!Cqueue.isEmpty() && moreToSearch) {

          Cell j = Cqueue.dequeue();
            j.visited = true;
            trailQueue.enqueue(j);

            if (j.type.equals("c")) {
                moreToSearch = false;
                maze.solved = true;

            } else {

                Cell[] neighbours = getNeighbours(maze, j);
                for (Cell neighbour : neighbours) {
                    if (!neighbour.visited && !neighbour.type.equals("1")) {
                        Cqueue.enqueue(neighbour);
                        neighbour.visited = true;
                    }

                }
            }
            moves++;
            printMaze(maze.maze);
            System.out.println("_____________________________");

            if (maze.solved) {
                System.out.println("Solved in " + moves + " moves");

            } else if (Cqueue.isEmpty()) {
                System.out.println("Not Solvable");
            }
        }

    }

    /**
     * gets the neighbours of a cell
     * @param maze the maze that contains the cell
     * @param j the cell whose neighbours we are looking for
     * @return neighbours[] an array of cells that are neighbours of j
     */
    public static Cell[] getNeighbours(Maze maze, Cell j) {
        Cell[] neighbours = new Cell[4];
        int row = j.row;
        int col = j.col;
        if (row > 0) {
            neighbours[0] = maze.maze[row - 1][col];
            neighbours[0].direction = "^";
        }
        if (row < maze.maze.length - 1) {
            neighbours[1] = maze.maze[row + 1][col];
            neighbours[1].direction = "v";
        }
        if (col > 0) {

            neighbours[2] = maze.maze[row][col - 1];
            neighbours[2].direction = "<";
        }
        if (col < maze.maze[0].length - 1) {
            neighbours[3] = maze.maze[row][col + 1];
            neighbours[3].direction = ">";
        }
        return neighbours;
    }


    // main method
    // The user enters the name of the text file containing the maze
    // The program then solves the maze using both a depth-first search and a breadth-first search
    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);


        System.out.println("Enter the file name");
        String fileName = sc.next();

        System.out.println("Depth First Search");
        DepthFirstSearch(new Maze("mazes/" + fileName));

        System.out.println("--------------------------------------------------------------------");

        System.out.println("Breadth First Search");
        BreadthFirstSearch(new Maze("mazes/" + fileName));


    }
}
