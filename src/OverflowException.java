
/**
 * Exception is thrown when a bounded structure is full
 *
 * 
 */
public class OverflowException extends RuntimeException
{

    /**
     * Default constructor for objects of class OverflowException
     */
    public OverflowException()
    {
        super();
    }

    /**
     * Constructor for objects of class OverflowException
     */
    public OverflowException(String message)
    {
        super(message);
    }
}
