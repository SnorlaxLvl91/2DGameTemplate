package main.mario.mvc;

import main.mario.StateStack;

/**
 *
 */
public interface Model {

    public static boolean[] pressed = new boolean[256];

    public abstract void update(float dt);

}
