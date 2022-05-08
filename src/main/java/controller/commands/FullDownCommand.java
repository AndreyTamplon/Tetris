package controller.commands;

import exceptions.CommandException;
import exceptions.TetrisException;
import controller.Command;
import model.TetrisTerminal;

public class FullDownCommand implements Command
{
    @Override
    public void execute(TetrisTerminal tetrisTerminal) throws CommandException
    {
        if(tetrisTerminal.isRunning())
        {
            try
            {
                tetrisTerminal.fullDown();
            }
            catch (TetrisException e)
            {
                throw new CommandException("Failed to execute full down command");
            }

        }
    }
}
