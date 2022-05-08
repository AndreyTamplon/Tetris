package model.tetromino_factory;

import exceptions.ConfigurationException;
import exceptions.InvalidTetromino;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class BasicTetrominoCreator extends TetrominoCreator
{
    private final TetrominosConfiguration tetrominosConfiguration;
    private final Random random;

    public BasicTetrominoCreator(String configurationFileName) throws ConfigurationException
    {
        super(4);
        tetrominosConfiguration = new TetrominosConfiguration(configurationFileName);
        random = new Random();
    }

    @Override
    protected Tetromino createTetromino(String tetrominoName) throws InvalidTetromino
    {
        try
        {
            return (Tetromino) Class.forName(tetrominosConfiguration.getTetrominoClassPath(tetrominoName)).getDeclaredConstructor().newInstance();
        }
        catch (ClassNotFoundException | NullPointerException e)
        {
            throw new InvalidTetromino("Could not find an tetromino with the name " + tetrominoName);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
        {
            throw new InvalidTetromino("Could not create an tetromino with the name " + tetrominoName, e);
        }
    }

    public Tetromino createRandomTetromino() throws InvalidTetromino
    {
        int tetrominoNumber = random.nextInt(tetrominosConfiguration.getNumberOfTetrominos());
        int i = 0;
        for(String tetrominoName : tetrominosConfiguration.getProperties().stringPropertyNames())
        {
            if(i == tetrominoNumber)
            {
                return createTetromino(tetrominoName);
            }
            ++i;
        }
        return null;
    }
}
