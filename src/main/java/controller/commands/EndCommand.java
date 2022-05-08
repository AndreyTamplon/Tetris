package controller.commands;

import controller.Command;
import model.TetrisTerminal;

public class EndCommand implements Command
{
    @Override
    public void execute(TetrisTerminal tetrisTerminal)
    {
        tetrisTerminal.endTheGame();
    }
}
