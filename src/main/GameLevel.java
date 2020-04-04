package main;

import java.awt.Graphics2D;
import java.util.List;

/**
 *
 */
public class GameLevel {

    public List<Entity> entities;
    public List<GameObject> objects;
    public TileMap tileMap;

    /**
     *
     * @param entities
     * @param objects
     * @param tilemap
     */
    public GameLevel(List<Entity> entities,
                     List<GameObject> objects,
                     TileMap tilemap) {
        this.entities = entities;
        this.objects = objects;
        this.tileMap = tilemap;
    }

    /**
     *
     */
    public void clear() {

        objects.removeIf(e -> e == null);

        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i) == null) {
                entities.remove(i);
            }
        }
    }

    /**
     *
     * @param dt
     */
    public void update(float dt) {
        tileMap.update(dt);
        objects.stream().forEach(e -> e.update(dt));
        entities.stream().forEach(e -> e.update(dt));
    }

    /**
     *
     * @param g2d
     */
    public void render(Graphics2D g2d) {
        tileMap.render(g2d);
        for(GameObject object:objects)
            object.render(g2d);
        for(Entity entity:entities)
            entity.render(g2d);
    }
}
