package main.mario;

import java.awt.*;

import static main.mario.Constants.*;

public class Tile {

    public int x;
    public int y;
    public int width;
    public int height;
    public int id;
    public boolean topper;
    public int tileset;
    public int topperset;

    /**
     *
     * @param x
     * @param y
     * @param id
     * @param topper
     * @param tileset
     * @param topperset
     */
    public Tile(int x, int y, int id, boolean topper, int tileset, int topperset) {
        this.x = x;
        this.y = y;
        this.width = TILE_WIDTH;
        this.height = TILE_HEIGHT;
        this.id = id;
        this.tileset = tileset;
        this.topper = topper;
        this.topperset = topperset;
    }

    /**
     * checks if tile belongs to a list of collidable tiles
    */
    public boolean collidable() {
        for (int v : COLLIDABLE_TILES) {
            if (v == id) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param g2d
     */
    public void render(Graphics2D g2d) {
        ScreenAdapter.drawImage(g2d,
                Graphic.tilesets[tileset][id],
                (int) x * TILE_WIDTH,
                (int) y * TILE_HEIGHT,
                TILE_WIDTH,
                TILE_HEIGHT);

        if(topper) {
            ScreenAdapter.drawImage(g2d,
                    Graphic.toppersets[topperset][id],
                    x * TILE_HEIGHT,
                    y * TILE_WIDTH,
                    TILE_WIDTH,
                    TILE_HEIGHT);
        }
    }
}