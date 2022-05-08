package model.tetromino_factory;


import model.Point;

import java.util.Random;

public abstract class Tetromino
{
    private Point[] coordinates;
    private final int colorSeed;

    protected Tetromino(Point[] coordinates)
    {
        Random random = new Random();
        this.coordinates = coordinates;
        colorSeed = random.nextInt(254) + 1;
    }

    public int getTetrominoSize()
    {
        return coordinates.length;
    }

    public int getColorSeed()
    {
        return colorSeed;
    }

    public Point getRotationCentre()
    {
        return coordinates[1];
    }

    public void setCoordinate(int index, Point p)
    {
        coordinates[index].setX(p.getX());
        coordinates[index].setY(p.getY());
    }

    public void setCoordinate(Point[] coordinates)
    {
        this.coordinates = coordinates;
    }

    public abstract boolean isRotatable();

    public Point[] getCoordinates()
    {
        return coordinates;
    }

    public Boolean isInTetromino(Point p)
    {
        for (Point t : coordinates)
        {
            if (t.equals(p))
            {
                return true;
            }
        }
        return false;
    }
}
