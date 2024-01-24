/*

Samer Swedan 
 */
public class Cell implements Comparable {
    int col; // the column of the cell
    int row; // the row of the cell
    String type; // the type of the cell "m" for mouse, "c" for cheese, "1" for wall, "0" for pathway
    boolean visited; // a boolean to check if the cell has been visited by the search algorithm
    boolean isStartingPoint; // a boolean to check if the cell is the starting point (mouse starting point)
    String direction =""; // the direction the mouse is facing and moving "˄" for up, "˅" for down, "<" for left, ">" for right

// constructor for the cell class
    public Cell(int col, int row, String type, boolean visited, boolean isStartingPoint, String direction) {
        this.col = col;
        this.row = row;
        this.type = type;
        this.visited = visited;
        this.isStartingPoint = isStartingPoint;
        this.direction = direction;
    }

    // default constructor for the cell class
    public Cell() {

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}



