package main.entity;

import java.awt.Graphics2D;
import java.util.Map;

public interface BaseState {

    public void update(float dt);
    public void render(Graphics2D g2d);
    public default void exit(){};
    public default void enter(Map<String, Object> params){};
}
