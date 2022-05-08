package view.buttons;

import controller.Controller;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EndButton extends Button
{
    public EndButton(Controller controller, Rectangle bounds)
    {
        super("END");
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                controller.processCommand("EndCommand");
            }
        });
        setBounds(bounds);
    }
}
