package exceptions;

public class HighScoresCreationException extends Exception
{
    public HighScoresCreationException(String message)
    {
        super(message);
    }

    public HighScoresCreationException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
