package main;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;

public abstract class State {

    public KeyListener keyListener;
    public static boolean[] pressed;

    public void enter(){
        pressed = new boolean[256];
    }

    public void exit(){
        pressed = new boolean[256];
    }

    public abstract void update(float dt);
    public abstract void render(Graphics2D g2d);
}
