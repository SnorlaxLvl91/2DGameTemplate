package main.state.entity;

import main.Observer;
import main.Timer;
import main.entity.Entity;
import main.world.Level;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import static main.Constants.TILE_WIDTH;
import static main.Constants.TILE_HEIGHT;
import static main.state.game.PlayState.*;

public class EntityWalkState extends EntityState implements Observer {

    Level level;
    boolean canWalk;

    public EntityWalkState(Entity entity, Level level) {
        super(entity);
        this.level = level;

        canWalk = false;
    }

    @Override
    public void update(float dt) {

    }

    public void enter(Map<String, Object> params) {
        attemptMove();
    }

    @Override
    public void exit() {

    }

    @Override
    public void processAI(HashMap<String, Object> params, float dt) {

    }

    public void attemptMove(){
        entity.changeAnimation("walk-" + entity.direction);

        int toX = entity.mapX;
        int toY = entity.mapY;

        if(entity.direction == "left") {
            toX = toX - 1;
        }else if(entity.direction == "right") {
            toX = toX + 1;
        }else if(entity.direction == "up") {
            toY = toY - 1;
        }else {
            toY = toY + 1;
        }

        // break out if we try to move out of the map boundaries
        if(toX < 0 || toX > 23 || toY < 0 || toY > 12) {
            entity.changeState("idle");
            entity.changeAnimation("idle-" + entity.direction);
            pressed = new boolean[256];
            return;
        }

        entity.mapY = toY;
        entity.mapX = toX;

        Timer timer = new Timer();
        timer.register(this);
        timer.tween(
                0,
                0.45f,
                new String[]{"x", "y"},
                new int[][]{{entity.x, entity.y}},
                new int[][]{{toX * TILE_WIDTH, toY * TILE_HEIGHT - entity.height / 2}}
        );
    }

    public void update(Object obj){
        Map<String[], Object> map = (Map<String[], Object>) obj;

        for (String[] keys : map.keySet()) {
            if(keys[0].equals("finished")){
                doSomething();
                break;
            }
            int[] values = (int[]) map.get(keys);

            for(int i = 0; i < keys.length; i++) {
                if(keys[i].equals("x")){
                    entity.x = values[i];
                }else if(keys[i].equals("y")){
                    entity.y = values[i];
                }
            }
        }
    }

    public void doSomething(){
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
        }else{
            entity.changeState("idle");
        }
        pressed = new boolean[256];
    }
}
