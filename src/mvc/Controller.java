package main.mario.mvc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Controller-Interface of MVC
 * Wraps around KeyListener-Class to complete functionalities
 */
public interface Controller extends KeyListener {

    /**
     * tbd...
     * @param e
     */
    public default void keyPressed(KeyEvent e){}

    /**
     * tbd...
     * @param e
     */
    public default void keyReleased(KeyEvent e){}

    /**
     * tbd...
     * @param e
     */
    public default void keyTyped(KeyEvent e){}
}