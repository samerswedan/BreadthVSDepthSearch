
/**
 * Exception is thrown when a structure is empty
 */
public class UnderflowException extends RuntimeException
{

    /**
     * Default constructor for objects of class UnderflowException
     */
    public UnderflowException()
    {
        super();
    }

    /**
     * Constructor for objects of class UnderflowException
     */
    public UnderflowException(String message)
    {
        super(message);
    }
}
