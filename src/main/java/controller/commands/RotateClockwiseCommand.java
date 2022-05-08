package controller.commands;

import controller.Command;
import model.TetrisTerminal;

public class RotateClockwiseCommand implements Command
{
    @Override
    public void execute(TetrisTerminal tetrisTerminal)
    {
        if(tetrisTerminal.isRunning())
        {
            tetrisTerminal.rotateClockwise();
        }
    }
}
