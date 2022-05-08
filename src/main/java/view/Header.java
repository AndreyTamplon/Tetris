package view;

import view.buttons.Button;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Header extends JPanel
{
    public Header(Rectangle bounds, List<Button> buttons)
    {
        setBackground(Color.YELLOW);
        setBounds(bounds);
        setLayout(null);
        placeButtons(buttons);
    }

    private void placeButtons(List<Button> buttons)
    {
        for (JButton button : buttons)
        {
            add(button);
        }
    }

}
