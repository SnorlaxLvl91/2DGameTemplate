package main.state.entity;

import main.Animation;
import main.Graphic;
import main.ScreenAdapter;
import main.entity.Entity;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class EntityState {

    public Entity entity;

    public EntityState(Entity entity) {
        this.entity = entity;
    }

    public abstract void update(float dt);
    public abstract void enter(Map<String, Object> params);
    public abstract void exit();
    public abstract void processAI(HashMap<String, Object> params, float dt);

    public void render(Graphics2D g2d) {
        Animation anim = entity.currentAnimation;
        ScreenAdapter.drawImage(
                g2d,
                Graphic.entities[anim.getCurrentFrame()],
                entity.x,
                entity.y);
    }
}
