package controller;

import exceptions.CommandException;
import model.TetrisTerminal;

public interface Command
{
    void execute(TetrisTerminal tetrisTerminal) throws CommandException;
}
