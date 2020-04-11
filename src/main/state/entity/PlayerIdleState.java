package main.state.entity;

import main.entity.Entity;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import static main.state.game.PlayState.*;

public class PlayerIdleState extends EntityState {

    public PlayerIdleState(Entity entity) {
        super(entity);
        entity.changeAnimation("idle-" + entity.direction);
    }

    @Override
    public void exit() {

    }

    @Override
    public void processAI(HashMap<String, Object> params, float dt) {

    }

    @Override
    public void update(float dt) {
        if(pressed[KeyEvent.VK_LEFT]) {
            entity.direction = "left";
            entity.changeState("walk");
        }else if(pressed[KeyEvent.VK_RIGHT]) {
            entity.direction = "right";
            entity.changeState("walk");
        }else if(pressed[KeyEvent.VK_UP]) {
            entity.direction = "up";
            entity.changeState("walk");
        }else if(pressed[KeyEvent.VK_DOWN]) {
            entity.direction = "down";
            entity.changeState("walk");
        }
    }

    @Override
    public void enter(Map<String, Object> params) {

    }
}
