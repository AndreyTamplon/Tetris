package view.buttons;

import view.ScreenSwitcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LeadersButton extends Button
{
    public LeadersButton(ScreenSwitcher screenSwitcher, JPanel highScorePanel, Rectangle bounds)
    {
        super("LEADERS");
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                screenSwitcher.switchScreen(highScorePanel);
            }
        });
        setBounds(bounds);
    }
}
