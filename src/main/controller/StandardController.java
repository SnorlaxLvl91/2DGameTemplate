package main.controller;

import main.SoundPlayer;
import main.State;
import main.Controller;

import java.awt.event.KeyEvent;

/**
 * Concrete Controller as part of MVC
 * Contains standard functionality which is
 * - set keys in model to true, if keys are pressed
 * - set keys in model to false, if keys are released
 */
public class StandardController extends Controller {

    public StandardController(State state){
        super(state);
    }

    /**
     * Sets keys in model to true, if keys are pressed
     * @param e     KeyEvent which is fired by keyboard input
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
        else if(e.getKeyCode() == KeyEvent.VK_P)
            SoundPlayer.pause("music");
        else if(e.getKeyCode() == KeyEvent.VK_O)
            SoundPlayer.replay("music");
        else if(e.getKeyCode() == KeyEvent.VK_I)
            SoundPlayer.stop("music");
        else if(e.getKeyCode() >= 0 && e.getKeyCode() < 256) {
            state.pressed[e.getKeyCode()] = true;
        }else {
            //...
        }
    }

    /**
     * Sets keys in model to false, if keys are released
     * @param e     KeyEvent which is fired by keyboard input
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() >= 0 && e.getKeyCode() < 256) {
            state.pressed[e.getKeyCode()] = false;
        }else {
            //...
        }
    }
}
