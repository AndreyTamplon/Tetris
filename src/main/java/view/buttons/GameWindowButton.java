package view.buttons;

import view.ScreenSwitcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWindowButton extends Button
{
    public GameWindowButton(ScreenSwitcher screenSwitcher, JPanel gamePanel, Rectangle bounds)
    {
        super("GAME WINDOW");
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                screenSwitcher.switchScreen(gamePanel);
            }
        });
        setBounds(bounds);
    }
}
