package exceptions;

public class ParserException extends TetrisException
{
    public ParserException(String message)
    {
        super(message);
    }

    public ParserException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
