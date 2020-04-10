package main.state.entity;

import main.State;
import main.entity.Player;

import java.awt.*;
import java.util.Map;

public class PlayerIdleState extends State {

    public Player player;

    public PlayerIdleState(Player player){
        this.player = player;
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

    }
}
