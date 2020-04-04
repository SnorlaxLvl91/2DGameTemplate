package main;

import java.awt.*;
import java.util.Map;

//import static main.mario.mvc.model.PlayModel.*;

public class Entity {

    public float x;
    public float y;

    public int dx;
    public int dy;

    public int width;
    public int height;

    public String texture;

    public String direction;
    public StateMachine stateMachine;
    public Animation currentAnimation;
    protected boolean alive;

    public TileMap map;
    public GameLevel level;

    public Entity(Map<String, Object> def) {
        this.x = (int) def.get("x");
        this.y = (int) def.get("y");

        this.dx = 0;
        this.dy = 0;

        this.width = (int) def.get("width");
        this.height = (int) def.get("height");

        this.texture = (String) def.get("texture");
        //this.stateMachine = (StateMachine) def.get("stateMachine");

        this.direction = "left";

        this.map = (TileMap) def.get("map");
        this.level = (GameLevel) def.get("level");
    }

    public void setStateMachine(StateMachine stateMachine){
        this.stateMachine = stateMachine;
    }

    public void changeState(String state, Map<String, Object> params) {
        this.stateMachine.change(state, params);
    }

    public void update(float dt) {
        stateMachine.update(dt);
    }

    public boolean collides(Entity entity) {
        return !(this.x > entity.x + entity.width || entity.x > this.x + this.width ||
                this.y > entity.y + entity.height || entity.y > this.y + this.height);
    }

    public void render(Graphics2D g2d) {
        ScreenAdapter.drawImage(g2d, Graphic.entities.get(texture)[currentAnimation.getCurrentFrame()],
                (x + (direction == "left" ? width : 0)), y, direction == "left" ? -width : width, height);
    }

    public boolean isAlive(){
        return alive;
    }
}