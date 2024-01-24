/*
Samer Swedan 
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// A queue to hold the trail of Cells traversed by the search algorithms
public class trailQueue {
    static Queue<Cell> trailQueue = new LinkedList<>();

    /**
     * adds a cell to the queue and assigns a direction to it
     * @param cell the cell to be added to the queue
     */
    public static void enqueue(Cell cell) {

          trailQueue.add(cell);
          System.out.println("trailQueue: [" + cell.row + ", " + cell.col + "]");
          if (cell.direction != null)
            assignDirection(cell);

        if (!cell.type.equals("c") && !cell.type.equals("m")){
            cell.type = cell.direction;

        }
    }

    /**
     * compares the positions of the cells in the queue to the position of the cell passed in and assign a direction
     * @param cell the cell to be assigned a direction
     */
    public static void assignDirection(Cell cell) {
        int[] jPosition = new int[2];
        jPosition[0] = cell.row;
        jPosition[1] = cell.col;
        int[] cellPosition = new int[2];
        Cell[] trailArray = new Cell[trailQueue.size()];
        trailArray = trailQueue.toArray(trailArray);
        for (Cell value : trailArray) {
            cellPosition[0] = value.row;
            cellPosition[1] = value.col;
            if (Arrays.equals(jPosition, cellPosition)) {
                if (value.direction.equals("^")) {
                    cell.direction = "^";
                }
                if (value.direction.equals("v")) {
                    cell.direction = "˅";
                }
                if (value.direction.equals("<")) {

                    cell.direction = "<";
                }
                if (value.direction.equals(">")) {
                    cell.direction = ">";
                }
            }
        }
    }



}