package main.mario.entity;

import java.awt.*;
import java.util.Map;

public interface State {

    public void update(float dt);
    public void render(Graphics2D g2d);
    public default void exit(){};
    public default void enter(Map<String, Object> params){};
}
