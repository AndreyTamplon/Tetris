package view;

import model.TetrisTerminal;

import javax.swing.*;

public class ScreenSwitcher
{
    private final KeysListener keysListener;
    private final TetrisTerminal tetrisTerminal;
    private final JLayeredPane layeredPane;
    private final JPanel gamePanel;

    public ScreenSwitcher(KeysListener keysListener, TetrisTerminal tetrisTerminal, JLayeredPane layeredPane, JPanel gamePanel)
    {
        this.keysListener = keysListener;
        this.tetrisTerminal = tetrisTerminal;
        this.layeredPane = layeredPane;
        this.gamePanel = gamePanel;
    }


    public void switchScreen(JPanel panel)
    {
        if ((panel == gamePanel) != (tetrisTerminal.isRunning()))
        {
            tetrisTerminal.changeState();
        }
        layeredPane.removeAll();
        layeredPane.add(panel);
        if (panel == gamePanel)
        {
            giveFocusToGame();
        }
        layeredPane.repaint();
        layeredPane.revalidate();
    }

    public void giveFocusToGame()
    {
        if (layeredPane.getKeyListeners().length > 0)
        {
            layeredPane.removeKeyListener(keysListener);
        }
        layeredPane.addKeyListener(keysListener);
        layeredPane.grabFocus();
    }
}
