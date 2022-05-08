package view;

import model.Model;
import model.Models;
import model.Subscriber;
import model.TetrisTerminal;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap;

public class HighScoreView extends JTable implements Subscriber
{
    private final TetrisTerminal tetrisTerminal;

    public HighScoreView(TetrisTerminal tetrisTerminal, Rectangle bounds)
    {
        super(11, 2);
        this.tetrisTerminal = tetrisTerminal;
        tetrisTerminal.subscribe(Models.getHighScoresModelName(), this);
        setBounds(bounds);
        repaint();
    }

    private void fillTable()
    {
        var highScoreTable = tetrisTerminal.getHighScoresTable();
        setValueAt("NAME", 0, 0);
        setValueAt("Score", 0, 1);
        int i = 1;
        for (AbstractMap.SimpleEntry<String, Integer> highScore : highScoreTable)
        {
            if (i > 10)
            {
                break;
            }
            setValueAt(highScore.getKey(), i, 0);
            setValueAt(highScore.getValue(), i, 1);
            ++i;
        }
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        fillTable();
        super.paintComponent(graphics);

    }

    @Override
    public void update(Model model)
    {
        fillTable();
        repaint();
    }
}
