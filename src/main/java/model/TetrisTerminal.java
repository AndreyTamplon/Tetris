package model;

import exceptions.ConfigurationException;
import exceptions.HighScoresCreationException;
import exceptions.TetrisException;
import exceptions.TetrominoCreationException;

import java.util.*;

public class TetrisTerminal extends Model implements Subscriber
{
    private final Map<String, ArrayList<Subscriber>> communicator;
    private GameState gameState;
    Board board;
    Score score;
    HighScores highScores;
    Timer timer;
    TimerTask timerTask;

    public boolean isRunning()
    {
        return gameState.isRunning();
    }

    public boolean isEnded()
    {
        return gameState.isEnded();
    }

    public boolean isStopped()
    {
        return gameState.isStopped();
    }

    private TetrisEngine tetrisEngine;

    public TetrisTerminal()
    {
        communicator = new HashMap<>();
        for (String modelName : Models.getModelsNames())
        {
            communicator.put(modelName, new ArrayList<>());
        }
    }

    public List<ArrayList<Integer>> getBoard()
    {
        return board.getBoard().subList(tetrisEngine.getMaximumTetrominoSize(), board.getBoardHeight());
    }

    public List<AbstractMap.SimpleEntry<String, Integer>> getHighScoresTable()
    {
        return highScores.getHighScoresTable();
    }

    public int getScore()
    {
        return score.getScore();
    }

    public void registerHighScore(String name) throws HighScoresCreationException
    {
        highScores.addNewScore(name, score);
        highScores.notifySubscribers();
    }

    public void initiateGame(String configurationFileName) throws TetrisException
    {
        TetrisConfiguration configuration;
        try
        {
            configuration = new TetrisConfiguration(configurationFileName);
        }
        catch (ConfigurationException e)
        {
            throw new TetrisException("Unable to start engine");
        }
        board = new Board(configuration.getBoardWidth(), configuration.getBoardHeight());
        board.subscribe(this);
        score = new Score();
        score.subscribe(this);
        try
        {
            highScores = new HighScores(configuration.getHighScoresPathName());
        }
        catch (HighScoresCreationException e)
        {
            throw new TetrisException("Unable to open high score table", e);
        }
        highScores.subscribe(this);
        gameState = new GameState();
        gameState.subscribe(this);
        try
        {
            tetrisEngine = new TetrisEngine(board, score, gameState, configuration);
        }
        catch (ConfigurationException e)
        {
            throw new TetrisException("Unable to start engine due to the inability to configure tetromino creator", e);
        }
        timer = new Timer();
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                if (isRunning())
                {
                    try
                    {
                        oneBlockDown();
                    }
                    catch (TetrisException e)
                    {
                        endTheGame();
                        e.printStackTrace();
                    }
                }
            }
        };
        startTimer();
    }

    public void endTheGame()
    {
        tetrisEngine.gameOver();
    }

    public void restartGame() throws TetrisException
    {
        tetrisEngine.restartGame();
    }

    public void moveLeft()
    {
        tetrisEngine.shiftTetrominoLeft();
    }

    public void moveRight()
    {
        tetrisEngine.shiftTetrominoRight();
    }

    public void rotateClockwise()
    {
        tetrisEngine.rotateTetrominoClockwise();
    }

    public void rotateCounterClockwise()
    {
        tetrisEngine.rotateTetrominoCounterClockwise();
    }

    public void oneBlockDown() throws TetrisException
    {
        try
        {
            tetrisEngine.lowerTheTetrominoSafe();
        }
        catch (TetrominoCreationException e)
        {
            throw new TetrisException("Failed to move tetromino");
        }
    }

    public void fullDown() throws TetrisException
    {
        try
        {
            tetrisEngine.lowerTheTetrominoAllTheWayDown();
        }
        catch (TetrominoCreationException e)
        {
            throw new TetrisException("Failed to move tetromino");
        }
    }

    public void changeState()
    {
        tetrisEngine.changeState();
    }


    public void startTimer()
    {
        timer.schedule(timerTask, 0L, 400);
    }

    @Override
    public void update(Model model)
    {
        String modelName = model.getClass().getSimpleName();
        for (Subscriber subscriber : communicator.get(modelName))
        {
            subscriber.update(this);
        }
    }

    public void subscribe(String modelName, Subscriber subscriber)
    {
        communicator.get(modelName).add(subscriber);
    }
}
