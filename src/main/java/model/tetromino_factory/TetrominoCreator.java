package model.tetromino_factory;

import exceptions.InvalidTetromino;

public abstract class TetrominoCreator
{
    private final int maximumTetrominoSize;

    public int getMaximumTetrominoSize()
    {
        return maximumTetrominoSize;
    }

    public TetrominoCreator(int maximumTetrominoSize)
    {
        this.maximumTetrominoSize = maximumTetrominoSize;
    }

    protected abstract Tetromino createTetromino(String tetrominoName) throws InvalidTetromino;

    public abstract Tetromino createRandomTetromino() throws InvalidTetromino;
}
