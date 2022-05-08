package exceptions;

public class InvalidTetromino extends TetrominoCreationException
{
    public InvalidTetromino(String message)
    {
        super(message);
    }

    public InvalidTetromino(String message, Throwable cause)
    {
        super(message, cause);
    }
}
