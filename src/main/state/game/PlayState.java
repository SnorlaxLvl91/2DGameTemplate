package main.state.game;

import main.State;
import main.controller.StandardController;

import java.awt.*;
import java.util.Map;

import static main.Constants.WINDOW_HEIGHT;
import static main.Constants.WINDOW_WIDTH;

public class PlayState extends State {

    public PlayState(){
        keyListener = new StandardController(this);
    }

    @Override
    public void enter(Map<String, Object> params) {

    }

    @Override
    public void exit() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(188, 0, 188, 255));
        g2d.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
}
