package model.tetromino_factory;

import exceptions.ConfigurationException;
import common.Configuration;
import java.io.IOException;

public class TetrominosConfiguration extends Configuration
{
    public TetrominosConfiguration(String configurationFileName) throws ConfigurationException
    {
        super(configurationFileName);
    }

    public String getTetrominoClassPath(String tetrominoName)
    {
        return getProperties().getProperty(tetrominoName);
    }

    public int getNumberOfTetrominos()
    {
        return getProperties().size();
    }
    public void setConfiguration(String configurationFileName) throws IOException
    {
        getProperties().load(TetrominosConfiguration.class.getResourceAsStream(configurationFileName));
    }
}
