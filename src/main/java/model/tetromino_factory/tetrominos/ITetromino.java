package model.tetromino_factory.tetrominos;

import model.Point;
import model.tetromino_factory.Tetromino;

public class ITetromino extends Tetromino
{
    public ITetromino()
    {
        super(new Point[]
                {
                        new Point(0, 3),
                        new Point(1, 3),
                        new Point(2, 3),
                        new Point(3, 3)
                });
    }

    @Override
    public boolean isRotatable()
    {
        return true;
    }
}
