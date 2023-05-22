public class Cell implements Comparable {
    int col;
    int row;
    String type;
    boolean visited;
    boolean isStartingPoint;

    public Cell(int col, int row, String type, boolean visited, boolean isStartingPoint) {
        this.col = col;
        this.row = row;
        this.type = type;
        this.visited = visited;
        this.isStartingPoint = isStartingPoint;
    }

    public Cell() {

    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}



