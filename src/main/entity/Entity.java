package main.entity;

import main.Animation;
import main.StateMachine;

import java.awt.*;
import java.util.Map;

import static main.Constants.TILE_WIDTH;
import static main.Constants.TILE_HEIGHT;

public abstract class Entity {

    public String direction;
    public Map<String, Object> animations;
    public Animation currentAnimation;
    public StateMachine stateMachine;


    public int mapX;
    public int mapY;

    public int width;
    public int height;

    public int x;
    public int y;

    public Entity(Map<String, Object> def) {
        direction = "down";
        //animations = createAnimations(def.get("animations"))

        mapX = (int) def.get("mapX");
        mapY = (int) def.get("mapY");

        width = (int) def.get("width");
        height = (int) def.get("height");

        x = mapX * TILE_WIDTH;
        y = mapY * TILE_HEIGHT - height / 2;
    }

    public void changeState(String name) {
        //stateMachine.change(name);
    }

    public void changeAnimation(String name) {
        //currentAnimation = animations[name];
    }

    public void createAnimations(/**animations*/) {
        /**local animationsReturned = {}

        for k, animationDef in pairs (animations) do
            animationsReturned[k] = Animation {
            texture = animationDef.texture or 'entities',
                    frames = animationDef.frames,
                    interval = animationDef.interval
        }
        end

        return animationsReturned
        end*/
    }

    public abstract boolean onInteract();

    public void processAI(Map<String, Object> params, float dt) {
//        stateMachine.processAI(params, dt);
    }

    public void update(float dt) {
//        currentAnimation.update(dt);
//        stateMachine.update(dt);
    }

    public void render(Graphics2D g2d){
//        stateMachine.render(g2d);
    }
}
