package exceptions;

public class TetrominoCreationException extends TetrisException
{
    public TetrominoCreationException(String message)
    {
        super(message);
    }

    public TetrominoCreationException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
