package exceptions;

public class CouldNotConfigureException extends Exception
{
    public CouldNotConfigureException(String message)
    {
        super(message);
    }

    public CouldNotConfigureException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
