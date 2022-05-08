package model;

import exceptions.HighScoresCreationException;
import exceptions.ParserException;

import java.util.*;

public class HighScores extends Model
{
    private final ArrayList<AbstractMap.SimpleEntry<String, Integer>> highScoresTable;
    private final HighScoresFileProcessor highScoresFileProcessor;
    private final Comparator<AbstractMap.SimpleEntry<String, Integer>> comparator;
    public HighScores(String highScoresTablePathName) throws HighScoresCreationException
    {
        try
        {
            highScoresFileProcessor = new HighScoresFileProcessor(highScoresTablePathName);
            highScoresTable = highScoresFileProcessor.getTableFromFile();
        }
        catch (ParserException e)
        {
            throw new HighScoresCreationException("Failed to create high score table", e);
        }
        comparator = Comparator.comparing(AbstractMap.SimpleEntry::getValue, Comparator.reverseOrder());
    }

    public void addNewScore(String playerName, Score score) throws HighScoresCreationException
    {
        boolean playerAlreadyInTable = false;
        for(AbstractMap.SimpleEntry<String, Integer> record : highScoresTable)
        {
            if(record.getKey().equals(playerName))
            {
                if(record.getValue() < score.getScore())
                {
                    record.setValue(score.getScore());
                }
                playerAlreadyInTable = true;
                break;
            }
        }
        if(!playerAlreadyInTable)
        {
            highScoresTable.add(new AbstractMap.SimpleEntry<>(playerName, score.getScore()));
        }
        highScoresTable.sort(comparator);
        try
        {
            highScoresFileProcessor.writeTableIntoFile(highScoresTable);
        }
        catch (ParserException e)
        {
            throw new HighScoresCreationException("Failed to write to high score table");
        }
    }

    public ArrayList<AbstractMap.SimpleEntry<String, Integer>> getHighScoresTable()
    {
        return highScoresTable;
    }
}




























