package view.buttons;

import view.ScreenSwitcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AboutButton extends Button
{
    public AboutButton(ScreenSwitcher screenSwitcher, JPanel aboutPanel, Rectangle bounds)
    {
        super("ABOUT");
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                screenSwitcher.switchScreen(aboutPanel);
            }
        });
        setBounds(bounds);
    }

}
