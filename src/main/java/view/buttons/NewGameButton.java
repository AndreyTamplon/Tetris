package view.buttons;

import controller.Controller;
import view.ScreenSwitcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewGameButton extends Button
{
    public NewGameButton(ScreenSwitcher screenSwitcher, Controller controller, JPanel gamePanel, Rectangle bounds)
    {
        super("NEW GAME");
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                controller.processCommand("RestartCommand");
                screenSwitcher.switchScreen(gamePanel);
                screenSwitcher.giveFocusToGame();
            }
        });
        setBounds(bounds);
    }
}
