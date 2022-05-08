package model;

import exceptions.ConfigurationException;
import common.Configuration;

import java.io.IOException;

public class TetrisConfiguration extends Configuration
{

    public TetrisConfiguration(String configurationFileName) throws ConfigurationException
    {
        super(configurationFileName);
    }

    public String getTetrominoClassPath(String tetrominoName)
    {
        return getProperties().getProperty(tetrominoName);
    }

    public Integer getBoardWidth()
    {
        return Integer.valueOf(getProperties().getProperty("Width"));
    }

    public Integer getBoardHeight()
    {
        return Integer.valueOf(getProperties().getProperty("Height"));
    }

    public String getHighScoresPathName()
    {
        return getProperties().getProperty("HighScores");
    }

    public String getTetrominoConfigurationPathName()
    {
        return getProperties().getProperty("TetrominoConfigurationPath");
    }

    public void setConfiguration(String configurationFileName) throws IOException
    {
        getProperties().load(TetrisConfiguration.class.getResourceAsStream(configurationFileName));
    }
}