package model;

import java.util.ArrayList;

public class Board extends Model
{
    private ArrayList<ArrayList<Integer>> board;
    private final int boardWidth;
    private final int boardHeight;
    private static final int EMPTY_CELL_VALUE = 0;

    public Board(int boardWidth, int boardHeight)
    {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        board = createZeroedBoard(boardWidth, boardHeight);
    }

    public void clearBoard()
    {
        board.clear();
        board = createZeroedBoard(boardWidth, boardHeight);
    }

    private ArrayList<ArrayList<Integer>> createZeroedBoard(int boardWidth, int boardHeight)
    {
        ArrayList<ArrayList<Integer>> zeroedBoard = new ArrayList<>();
        ArrayList<Integer> row;
        for(int i = 0; i < boardHeight; ++i)
        {
            row = new ArrayList<>();
            for(int j = 0; j < boardWidth; ++j)
            {
                row.add(EMPTY_CELL_VALUE);
            }
            zeroedBoard.add(row);
        }
        return zeroedBoard;
    }

    public ArrayList<ArrayList<Integer>> getBoard()
    {
        return board;
    }

    public static int getEmptyCellValue()
    {
        return EMPTY_CELL_VALUE;
    }

    public boolean isWithinBorders(Point point)
    {
        int x = point.getX();
        int y = point.getY();
        return !(x < 0 || x >= boardWidth || y < 0 || y >= boardHeight);
    }

    public boolean isEmptyCell(Point point)
    {
        return getValueByPoint(point) == EMPTY_CELL_VALUE;
    }

    public int getBoardWidth()
    {
        return boardWidth;
    }

    public int getBoardHeight()
    {
        return boardHeight;
    }

    private void deleteRow(int rowNumber)
    {
        board.remove(rowNumber);
        ArrayList<Integer> row = new ArrayList<>();
        for(int j = 0; j < boardWidth; ++j)
        {
            row.add(0);
        }
        board.add(0, row);
    }
    public void clearRow(int rowNumber)
    {
        deleteRow(rowNumber);
    }

    public void setValueAtThePoint(Point point, Integer element)
    {
        board.get(point.getY()).set(point.getX(), element);
    }

    public void setValueAtThePoint(int x, int y, Integer element)
    {
        board.get(y).set(x, element);
    }

    public Integer getValueByPoint(Point point)
    {
        return board.get(point.getY()).get(point.getX());
    }

    public Integer getValueByPoint(int x, int y)
    {
        return board.get(y).get(x);
    }

}
