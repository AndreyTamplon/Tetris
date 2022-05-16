package view;

import exceptions.HighScoresCreationException;
import controller.Controller;
import model.*;
import view.buttons.*;
import view.buttons.Button;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JFrame implements Subscriber
{
    private final Controller controller;
    private final TetrisTerminal tetrisTerminal;
    private final BoardView boardView;
    private final ScoreView scoreView;
    private final HighScoreView highScoreView;
    private final AboutView aboutView;
    private JLayeredPane layeredPane;

    public View(TetrisTerminal tetrisTerminal, Controller controller)
    {
        setSize(600, 470);
        this.tetrisTerminal = tetrisTerminal;
        this.controller = controller;
        boardView = new BoardView(tetrisTerminal, new Rectangle(0, 0, getWidth() / 2, getHeight() - 80));
        scoreView = new ScoreView(tetrisTerminal, new Rectangle(0, 0, getWidth() / 2, getHeight() - 80));
        highScoreView = new HighScoreView(tetrisTerminal, new Rectangle(0, 0, getWidth(), getHeight() - 70));
        aboutView = new AboutView(new Rectangle(0, 0, getWidth(), getHeight() - 70));
        initialiseUI();
    }


    private void initialiseUI()
    {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 50, getWidth(), getHeight() - 50);
        layeredPane.setBackground(Color.WHITE);
        add(layeredPane);
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.X_AXIS));
        gamePanel.add(boardView);
        gamePanel.add(scoreView);
        gamePanel.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(gamePanel);
        JPanel highScorePanel = new JPanel();
        highScorePanel.add(highScoreView);
        highScorePanel.setBounds(0, 0, getWidth(), getHeight());
        KeysListener keysListener = new KeysListener(controller);
        addKeyListener(keysListener);
        tetrisTerminal.subscribe(Models.getGameStateModelName(), this);
        ArrayList<Button> buttons = new ArrayList<>();
        ScreenSwitcher screenSwitcher = new ScreenSwitcher(keysListener, tetrisTerminal, layeredPane, gamePanel);
        buttons.add(new GameWindowButton(screenSwitcher, gamePanel, new Rectangle(0, 10, 125, 20)));
        buttons.add(new NewGameButton(screenSwitcher, controller, gamePanel, new Rectangle(130, 10, 100, 20)));
        buttons.add(new AboutButton(screenSwitcher, aboutView, new Rectangle(235, 10, 80, 20)));
        buttons.add(new LeadersButton(screenSwitcher, highScorePanel, new Rectangle(320, 10, 90, 20)));
        buttons.add(new EndButton(controller, new Rectangle(415, 10, 60, 20)));
        Header header = new Header(new Rectangle(0, 0, getWidth(), 50), buttons);
        getContentPane().add(header);
        setTitle("Tetris");
        setFocusable(true);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void update(Model model)
    {
        if (tetrisTerminal.isEnded())
        {
            String userName = JOptionPane.showInputDialog("Write your name");
            try
            {
                if (userName != null && userName.length() > 0)
                {
                    tetrisTerminal.registerHighScore(userName);
                }
            }
            catch (HighScoresCreationException e)
            {
                JOptionPane.showMessageDialog(layeredPane, "Failed to put your name into leaders list. Perhaps you are not");
            }
        }
    }
}
