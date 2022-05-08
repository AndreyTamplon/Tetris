package exceptions;

public class TetrisException extends Exception
{
    public TetrisException(String message)
    {
        super(message);
    }

    public TetrisException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
