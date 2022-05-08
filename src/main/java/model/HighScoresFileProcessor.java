package model;

import exceptions.ParserException;

import java.io.*;
import java.util.*;

public class HighScoresFileProcessor
{
    private final File highScoresTableFile;

    public HighScoresFileProcessor(String highScoresTablePathName) throws ParserException
    {
        try
        {
            highScoresTableFile = new File(highScoresTablePathName);
            if (!highScoresTableFile.exists())
            {
                highScoresTableFile.createNewFile();
            }
        }
        catch (IOException e)
        {
            throw new ParserException("Failed to create high scores file", e);
        }
    }

    public ArrayList<AbstractMap.SimpleEntry<String, Integer>> getTableFromFile() throws ParserException
    {
        ArrayList<AbstractMap.SimpleEntry<String, Integer>> highScoresTable = new ArrayList<>();
        try (Scanner scanner = new Scanner(highScoresTableFile).useDelimiter("\n"))
        {
            String[] line;
            while (scanner.hasNextLine())
            {
                line = scanner.nextLine().split("=");
                highScoresTable.add(new AbstractMap.SimpleEntry<>(line[0], Integer.parseInt(line[1])));
            }
        }
        catch (FileNotFoundException e)
        {
            throw new ParserException("Couldn't get table");
        }
        return highScoresTable;
    }

    public void writeTableIntoFile(ArrayList<AbstractMap.SimpleEntry<String, Integer>> highScoresTable) throws ParserException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(highScoresTableFile, false)))
        {
            for (AbstractMap.SimpleEntry<String, Integer> result : highScoresTable)
            {
                writer.write(result.getKey() + "=" + result.getValue() + "\n");
            }
        }
        catch (IOException e)
        {
            throw new ParserException("Failed to write high scores into file");
        }

    }
}
