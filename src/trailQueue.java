import java.util.LinkedList;
import java.util.Queue;

public class trailQueue {
    static Queue<Cell> trailQueue = new LinkedList<>();

    // make a method that adds a cell to the queue
    public static void enqueue(Cell cell) {
        trailQueue.add(cell);
        if (cell.type.equals("c") == false) {
            cell.type = String.valueOf('>');

        }
    }
}