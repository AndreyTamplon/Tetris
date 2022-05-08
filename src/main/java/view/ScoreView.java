package view;

import model.Models;
import model.TetrisTerminal;
import model.Model;
import model.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ScoreView extends JPanel implements Subscriber
{
    private int score;
    private static final Font FONT = new Font(Font.MONOSPACED, Font.BOLD, 90);
    private final TetrisTerminal tetrisTerminal;

    public ScoreView(TetrisTerminal tetrisTerminal, Rectangle bounds)
    {
        this.tetrisTerminal = tetrisTerminal;
        score = tetrisTerminal.getScore();
        tetrisTerminal.subscribe(Models.getScoreModelName(), this);
        setBackground(Color.WHITE);
        setBounds(bounds);
        repaint();
    }

    private void drawScore(Graphics graphics)
    {
        Random random = new Random();
        graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        graphics.setFont(FONT);
        graphics.drawString(String.valueOf(score), 130, 220);

    }

    private void drawBorder(Graphics graphics)
    {
        graphics.setColor(Color.BLACK);
        graphics.drawLine(0, 0, 0, getSize().height);
        graphics.drawLine(0, 0, getSize().width, 0);
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        drawScore(graphics);
        drawBorder(graphics);
    }

    @Override
    public void update(Model model)
    {
        score = tetrisTerminal.getScore();
        repaint();
    }
}
