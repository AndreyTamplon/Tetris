package view;

import controller.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeysListener implements KeyListener
{
    private final Controller controller;

    public KeysListener(Controller controller)
    {
        super();
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        controller.processCommand(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }
}