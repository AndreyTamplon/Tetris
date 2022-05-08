package exceptions;


public class InvalidCommand extends TetrisException
{
    public InvalidCommand(String message)
    {
        super(message);
    }

    public InvalidCommand(String message, Throwable cause)
    {
        super(message, cause);
    }
}
