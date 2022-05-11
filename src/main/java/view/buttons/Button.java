package view.buttons;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton
{
    private final static Color purple = new Color(203, 0, 203);

    public Button(String buttonText)
    {
        super(buttonText);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
    }
}
