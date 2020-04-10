package main;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.util.Map;

public abstract class State {

    public KeyListener keyListener;
    public boolean[] pressed;

    public abstract void enter(Map<String, Object> params);
    public abstract void exit();
    public abstract void update(float dt);
    public abstract void render(Graphics2D g2d);
}
