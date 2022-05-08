package controller.commands;

import controller.Command;
import model.TetrisTerminal;

public class PauseCommand implements Command
{
    @Override
    public void execute(TetrisTerminal tetrisTerminal)
    {
        if(!tetrisTerminal.isEnded())
        {
            tetrisTerminal.changeState();
        }
    }
}
