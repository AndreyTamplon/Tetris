package controller;

import exceptions.CommandException;
import exceptions.InvalidCommand;
import model.TetrisTerminal;

public abstract class Controller
{
    TetrisTerminal tetrisTerminal;

    protected Controller(TetrisTerminal tetrisTerminal)
    {
        this.tetrisTerminal = tetrisTerminal;
    }

    protected void executeCommand(String commandName) throws CommandException
    {
        try
        {
            Command command = createCommand(commandName);
            command.execute(tetrisTerminal);
        }
        catch (InvalidCommand e)
        {
            throw new CommandException("Couldn't create command");
        }
    }

    public abstract void processCommand(int keycode);

    public abstract void processCommand(String commandName);

    protected abstract Command createCommand(String commandName) throws InvalidCommand;

}