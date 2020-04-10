package main;

import java.awt.Graphics2D;
import java.util.Map;

/**
 *
 */
public class GameObject implements Observer{

    public int x;
    public int y;
    public int width;
    public int height;
    public int frame;
    public boolean solid;
    public boolean collidable;
    public boolean consumable;
    public boolean hit;
    public String texture;

    /**
     *
     * @param def
     */
    public GameObject(Map<String, Object> def) {
        this.x = (int) def.get("x");
        this.y = (int) def.get("y");
        this.texture = (String) def.get("texture");
        this.width = (int) def.get("width");
        this.height = (int) def.get("height");
        this.frame = (int) def.get("frame");
        this.solid = def.containsKey("solid") ? (boolean) def.get("solid") : false;
    }

    /**
     *
     * @param target
     * @return
     */
    /**public boolean collides(Entity target) {
        return !(target.x > this.x + this.width - 2 || this.x > target.x + target.width - 2
                || target.y > this.y + this.height - 2 || this.y > target.y + target.height);
    }*/

    /**
     *
     * @param dt
     */
    public void update(float dt) {
    }

    /**
     *
     * @param g2d
     */
    public void render(Graphics2D g2d) {
        ScreenAdapter.drawImage(g2d, Graphic.objects.get(texture)[frame], this.x, this.y, this.width, this.height);
    }
}
