package model;

import exceptions.ConfigurationException;
import exceptions.TetrisException;
import exceptions.TetrominoCreationException;
import model.tetromino_factory.BasicTetrominoCreator;
import model.tetromino_factory.Tetromino;
import model.tetromino_factory.TetrominoCreator;

import java.util.ArrayList;
import java.util.Collections;

public class TetrisEngine extends Model
{
    private final Board board;
    private final TetrominoCreator tetrominoCreator;
    private final Score score;
    private final GameState gameState;
    private Tetromino currentTetromino;

    TetrisEngine(Board board, Score score, GameState gameState, TetrisConfiguration configuration) throws TetrisException, ConfigurationException
    {
        this.board = board;
        this.score = score;
        this.gameState = gameState;
        tetrominoCreator = new BasicTetrominoCreator(configuration.getTetrominoConfigurationPathName());
        uploadNewTetromino();
    }

    public enum Descent
    {
        DROPPED, NOT_DROPPED
    }

    public int getMaximumTetrominoSize()
    {
        return tetrominoCreator.getMaximumTetrominoSize();
    }

    public void shiftTetrominoRight()
    {
        Point[] coordinates = currentTetromino.getCoordinates();
        Point[] newCoordinates = new Point[currentTetromino.getTetrominoSize()];
        for (int i = 0; i < currentTetromino.getTetrominoSize(); ++i)
        {
            newCoordinates[i] = new Point(coordinates[i].getX() + 1, coordinates[i].getY());
            if (!isCellFreeToMove(newCoordinates[i]))
            {
                return;
            }
        }
        moveTetromino(newCoordinates);
        board.notifySubscribers();
    }

    public void shiftTetrominoLeft()
    {
        Point[] coordinates = currentTetromino.getCoordinates();
        Point[] newCoordinates = new Point[currentTetromino.getTetrominoSize()];
        for (int i = 0; i < currentTetromino.getTetrominoSize(); ++i)
        {
            newCoordinates[i] = new Point(coordinates[i].getX() - 1, coordinates[i].getY());
            if (!isCellFreeToMove(newCoordinates[i]))
            {
                return;
            }
        }
        moveTetromino(newCoordinates);
        board.notifySubscribers();
    }

    private void moveTetromino(Point[] newCoordinates)
    {
        Point[] coordinates = currentTetromino.getCoordinates();
        for (int i = 0; i < currentTetromino.getTetrominoSize(); ++i)
        {
            board.setValueAtThePoint(coordinates[i], Board.getEmptyCellValue());
        }
        currentTetromino.setCoordinate(newCoordinates);
        placeTetrominoOnBoard();
    }

    private void placeTetrominoOnBoard()
    {
        Point[] coordinates = currentTetromino.getCoordinates();
        for (int i = 0; i < currentTetromino.getTetrominoSize(); ++i)
        {
            board.setValueAtThePoint(coordinates[i], currentTetromino.getColorSeed());
        }
    }

    public Descent lowerTheTetromino() throws TetrominoCreationException
    {
        Point[] coordinates = currentTetromino.getCoordinates();
        Point[] newCoordinates = new Point[currentTetromino.getTetrominoSize()];
        for (int i = 0; i < currentTetromino.getTetrominoSize(); ++i)
        {
            newCoordinates[i] = new Point(coordinates[i].getX(), coordinates[i].getY() + 1);
            if (!isCellFreeToMove(newCoordinates[i]))
            {
                if (isInInvisibleArea())
                {
                    gameOver();
                }
                clearFullRows();
                uploadNewTetromino();
                board.notifySubscribers();
                return Descent.DROPPED;
            }
        }

        moveTetromino(newCoordinates);
        board.notifySubscribers();
        return Descent.NOT_DROPPED;
    }

    public void lowerTheTetrominoAllTheWayDown() throws TetrominoCreationException
    {
        changeState();
        while (lowerTheTetromino() != Descent.DROPPED) ;
        changeState();
    }

    public void gameOver()
    {
        gameState.endTheGame();
        gameState.notifySubscribers();
    }

    private boolean isInInvisibleArea()
    {
        //Board has an area where new tetrominos are generated. This area is invisible to the player.
        Point[] coordinates = currentTetromino.getCoordinates();
        for (int i = 0; i < currentTetromino.getTetrominoSize(); ++i)
        {
            if (coordinates[i].getY() < currentTetromino.getTetrominoSize())
            {
                return true;
            }
        }
        return false;
    }


    public void rotateTetrominoClockwise()
    {
        if (!currentTetromino.isRotatable())
        {
            return;
        }
        Point[] coordinates = currentTetromino.getCoordinates();
        Point[] newCoordinates = new Point[currentTetromino.getTetrominoSize()];
        Point rotationCentre = currentTetromino.getRotationCentre();
        for (int i = 0; i < currentTetromino.getTetrominoSize(); ++i)
        {
            newCoordinates[i] = new Point(rotationCentre.getX() - (coordinates[i].getY() - rotationCentre.getY()), rotationCentre.getY() + (coordinates[i].getX() - rotationCentre.getX()));
            if (!isCellFreeToMove(newCoordinates[i]))
            {
                return;
            }
        }
        moveTetromino(newCoordinates);
        board.notifySubscribers();
    }

    public void rotateTetrominoCounterClockwise()
    {
        if (!currentTetromino.isRotatable())
        {
            return;
        }
        Point[] coordinates = currentTetromino.getCoordinates();
        Point[] newCoordinates = new Point[currentTetromino.getTetrominoSize()];
        Point rotationCentre = currentTetromino.getRotationCentre();
        for (int i = 0; i < currentTetromino.getTetrominoSize(); ++i)
        {
            newCoordinates[i] = new Point(rotationCentre.getX() + (coordinates[i].getY() - rotationCentre.getY()), rotationCentre.getY() - (coordinates[i].getX() - rotationCentre.getX()));
            if (!isCellFreeToMove(newCoordinates[i]))
            {
                return;
            }
        }
        moveTetromino(newCoordinates);
        board.notifySubscribers();
    }

    private boolean isCellFreeToMove(Point point)
    {
        return (board.isWithinBorders(point) && (board.isEmptyCell(point) || currentTetromino.isInTetromino(point)));
    }

    private void clearFullRows()
    {
        boolean rowIsFull;
        Point[] coordinates = currentTetromino.getCoordinates();
        ArrayList<Integer> rowsToDelete = new ArrayList<>();
        for (int i = 0; i < currentTetromino.getTetrominoSize(); ++i)
        {
            rowIsFull = true;
            for (int j = 0; j < board.getBoardWidth(); ++j)
            {
                if (board.getValueByPoint(j, coordinates[i].getY()) == Board.getEmptyCellValue())
                {
                    rowIsFull = false;
                    break;
                }
            }
            if (Boolean.TRUE.equals(rowIsFull) && !rowsToDelete.contains(coordinates[i].getY()))
            {
                rowsToDelete.add(coordinates[i].getY());
            }
        }
        int numberOfDeletedRows = rowsToDelete.size();
        Collections.sort(rowsToDelete);
        for (Integer integer : rowsToDelete)
        {
            board.clearRow(integer);
        }
        if (numberOfDeletedRows > 0)
        {
            score.increaseScore(numberOfDeletedRows);
            board.notifySubscribers();
            score.notifySubscribers();
        }
    }

    public void restartGame() throws TetrisException
    {
        board.clearBoard();
        score.resetScore();
        try
        {
            uploadNewTetromino();
        }
        catch (TetrominoCreationException e)
        {
            throw new TetrisException("Failed to restart game", e);
        }
        gameState.resumeGame();
        board.notifySubscribers();
        score.notifySubscribers();
        gameState.notifySubscribers();
    }

    public void lowerTheTetrominoSafe() throws TetrisException
    {
        changeState();
        lowerTheTetromino();
        changeState();
    }

    public void changeState()
    {
        if (gameState.isRunning())
        {
            gameState.stopGame();
        }
        else if (!gameState.isEnded())
        {
            gameState.resumeGame();
        }
    }

    private Tetromino getNewTetromino() throws TetrominoCreationException
    {
        return tetrominoCreator.createRandomTetromino();
    }

    public void uploadNewTetromino() throws TetrominoCreationException
    {
        currentTetromino = getNewTetromino();
        placeTetrominoOnBoard();
    }
}
