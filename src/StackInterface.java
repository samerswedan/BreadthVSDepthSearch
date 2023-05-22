
/**
 * Stack ADT operations
 *
 * @author Jalal Kawash
 */

public interface StackInterface <Cell extends Comparable>
{
    public boolean isEmpty();
    public boolean isFull();
    public void push (Cell element);
    public Cell pop();
    public Cell peek();
}
