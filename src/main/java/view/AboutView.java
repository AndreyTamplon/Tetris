package view;

import javax.swing.*;
import java.awt.*;


public class AboutView extends JPanel
{
    private final String[] rules = {
            "1st rule: You do not talk about Tetris.\n",
            "2nd rule: You do not talk about Tetris.\n",
            "3rd rule: If someone yells \"Stop!\", goes limp,\n",
            "taps out, the Tetris is over.\n",
            "4th rule: Only one guy to a play.\n",
            "5th rule: One play at a time.\n",
            "6th rule: No korobeiniki, no gameboy.\n",
            "7th rule: Plays will go on as long as they have to.\n",
            "8th rule: If this is your first time, you have to play.",
            "9th rule: The control is carried out by arrows,",
            "Space - for dropping, p - for pause"};
    private static final Font FONT = new Font(Font.MONOSPACED, Font.BOLD, 16);

    public AboutView(Rectangle bounds)
    {
        setBounds(bounds);
        setBackground(Color.BLACK);
        repaint();
    }

    private void drawAbout(Graphics graphics)
    {
        graphics.setFont(FONT);
        graphics.setColor(new Color(203, 0, 203));
        for (int i = 0; i < rules.length - 2; ++i)
        {
            graphics.drawString(rules[i], 0, 50 + i * 20);
        }
        graphics.setColor(new Color(255, 255, 255));
        graphics.drawString(rules[rules.length - 2], 0, 50 + (rules.length - 2) * 20);
        graphics.drawString(rules[rules.length - 1], 0, 50 + (rules.length - 1) * 20);
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        drawAbout(graphics);
    }

}
