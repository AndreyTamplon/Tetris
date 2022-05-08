package view;

import model.Models;
import model.TetrisTerminal;
import model.Model;
import model.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class BoardView extends JPanel implements Subscriber
{
    private List<ArrayList<Integer>> board;
    private final int boardWidth;
    private final int boardHeight;
    private final int squareWidth;
    private final int squareHeight;

    TetrisTerminal tetrisTerminal;

    BoardView(TetrisTerminal tetrisTerminal, Rectangle bounds)
    {
        this.tetrisTerminal = tetrisTerminal;
        board = tetrisTerminal.getBoard();
        tetrisTerminal.subscribe(Models.getBoardModelName(), this);
        boardWidth = board.get(0).size();
        boardHeight = board.size();
        setBounds(bounds);
        setBackground(Color.WHITE);
        squareWidth = (int) (getSize().getWidth() / boardWidth);
        squareHeight = (int) (getSize().getHeight() / boardHeight);
        repaint();
    }

    @Override
    public void update(Model model)
    {
        board = tetrisTerminal.getBoard();
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        drawBoard(graphics);
        drawBorder(graphics);
    }

    private void drawSquare(Graphics graphics, int x, int y, int colorSeed)
    {
        Color innerColor = new Color((colorSeed + 70) % 255, (colorSeed + 130) % 255, colorSeed);
        graphics.setColor(innerColor);
        graphics.fillRect(x + 1, y + 1, squareWidth - 2, squareHeight - 2);
    }

    private void drawBorder(Graphics graphics)
    {
        graphics.setColor(Color.BLACK);
        graphics.drawLine(0, 0, getWidth(), 0);
    }

    private void drawBoard(Graphics graphics)
    {
        for (int i = 0; i < boardHeight; ++i)
        {
            for (int j = 0; j < boardWidth; ++j)
            {
                if (board.get(i).get(j) > 0)
                {
                    drawSquare(graphics, j * squareWidth, i * squareHeight, board.get(i).get(j));
                }
            }
        }
    }
}
