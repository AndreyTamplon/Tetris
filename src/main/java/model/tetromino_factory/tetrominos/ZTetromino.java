package model.tetromino_factory.tetrominos;

import model.Point;
import model.tetromino_factory.Tetromino;

public class ZTetromino extends Tetromino
{
    public ZTetromino()
    {
        super(new Point[]
                {
                        new Point(0, 1),
                        new Point(0, 2),
                        new Point(1, 2),
                        new Point(1, 3),
                });
    }

    @Override
    public boolean isRotatable()
    {
        return true;
    }
}
