import exceptions.ConfigurationException;
import exceptions.TetrisException;
import controller.Controller;
import controller.BasicController;
import model.TetrisTerminal;
import view.View;

import java.awt.*;

public class Game
{
    public static void main(String[] args)
    {
        TetrisTerminal tetrisTerminal = new TetrisTerminal();

        Controller controller;
        try
        {
            tetrisTerminal.initiateGame("/TetrisConfiguration.properties");
            controller = new BasicController("/ControllerConfiguration.properties", tetrisTerminal);
        }
        catch (ConfigurationException | TetrisException e)
        {
            e.printStackTrace();
            return;
        }

        Controller finalController = controller;
        EventQueue.invokeLater(() ->
        {
            View view = new View(tetrisTerminal, finalController);
            view.setVisible(true);
        });
    }
}
