package model.tetromino_factory.tetrominos;

import model.Point;
import model.tetromino_factory.Tetromino;

public class OTetromino extends Tetromino
{
    public OTetromino()
    {
        super(new Point[]
                {
                        new Point(0, 2),
                        new Point(0, 3),
                        new Point(1, 2),
                        new Point(1, 3),
                });
    }

    @Override
    public boolean isRotatable()
    {
        return false;
    }
}
