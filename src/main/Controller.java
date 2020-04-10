package main;

import java.awt.event.KeyAdapter;

/**
 * Controller-Interface of MVC
 * Wraps around KeyAdapter-Class to complete functionalities
 */
public abstract class Controller extends KeyAdapter {

    public State state;

    public Controller(State state){
        this.state = state;
        state.pressed = new boolean[256];
    }
}