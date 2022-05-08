package model.tetromino_factory.tetrominos;

import model.Point;
import model.tetromino_factory.Tetromino;

public class TTetromino extends Tetromino
{
    public TTetromino()
    {
        super(new Point[]
                {
                        new Point(0, 2),
                        new Point(1, 2),
                        new Point(1, 3),
                        new Point(2, 2),
                });
    }

    @Override
    public boolean isRotatable()
    {
        return true;
    }
}
