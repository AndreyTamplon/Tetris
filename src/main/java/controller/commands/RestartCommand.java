package controller.commands;

import exceptions.CommandException;
import exceptions.TetrisException;
import controller.Command;
import model.TetrisTerminal;

public class RestartCommand implements Command
{
    @Override
    public void execute(TetrisTerminal tetrisTerminal) throws CommandException
    {
        try
        {
            tetrisTerminal.restartGame();
        }
        catch (TetrisException e)
        {
            throw new CommandException("Failed to execute restart command", e);
        }

    }
}