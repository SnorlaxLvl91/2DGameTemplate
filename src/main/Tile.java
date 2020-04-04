package main;

import java.awt.Graphics2D;

import static main.Constants.COLLIDABLE_TILES;
import static main.Constants.TILE_WIDTH;
import static main.Constants.TILE_HEIGHT;

public class Tile {

    public int x;
    public int y;
    public int width;
    public int height;
    public int id;
    public int tileset;

    /**
     *
     * @param x
     * @param y
     * @param id
     * @param tileset
     */
    public Tile(int x, int y, int id, int tileset) {
        this.x = x;
        this.y = y;
        this.width = TILE_WIDTH;
        this.height = TILE_HEIGHT;
        this.id = id;
        this.tileset = tileset;
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
                x * TILE_WIDTH,
                y * TILE_HEIGHT,
                TILE_WIDTH,
                TILE_HEIGHT);
    }
}