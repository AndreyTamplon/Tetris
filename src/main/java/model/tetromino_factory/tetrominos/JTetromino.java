package model.tetromino_factory.tetrominos;

import model.Point;
import model.tetromino_factory.Tetromino;

public class JTetromino extends Tetromino
{
    public JTetromino()
    {
        super(new Point[]
                {
                        new Point(1, 1),
                        new Point(1, 2),
                        new Point(1, 3),
                        new Point(0, 3)
                });
    }

    @Override
    public boolean isRotatable()
    {
        return true;
    }
}
