package controller;

import exceptions.*;
import model.TetrisTerminal;

import java.lang.reflect.InvocationTargetException;

public class BasicController extends Controller
{
    private final ControllerConfiguration controllerConfiguration;
    public BasicController(String configurationFileName, TetrisTerminal tetrisTerminal)
            throws ConfigurationException
    {
        super(tetrisTerminal);
        controllerConfiguration = new ControllerConfiguration(configurationFileName);
    }
    public void processCommand(int keyCode)
    {
        if(controllerConfiguration.getCommandNameByKeyCode(keyCode) != null)
        {
            processCommand(controllerConfiguration.getCommandNameByKeyCode(keyCode));
        }

    }

    public void processCommand(String commandName)
    {
        try
        {
            executeCommand(commandName);
        }
        catch (CommandException e)
        {
            try
            {
                executeCommand("EndCommand");
            }
            catch (CommandException ex)
            {
                ex.printStackTrace();
                System.exit(1);
            }
        }
    }
    @Override
    protected Command createCommand(String commandName) throws InvalidCommand
    {
        try
        {
            return (Command) Class.forName(controllerConfiguration.getCommandClassPath(commandName)).getDeclaredConstructor().newInstance();
        }
        catch (ClassNotFoundException | NullPointerException e)
        {
            throw new InvalidCommand("Could not find an command with the name " + commandName);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
        {
            throw new InvalidCommand("Could not create an command with the name " + commandName, e);
        }
    }
}
