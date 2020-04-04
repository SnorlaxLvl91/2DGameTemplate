package main;

import java.awt.*;

import static main.Constants.TILE_WIDTH;
import static main.Constants.TILE_HEIGHT;

/**
 *
 */
public class TileMap {

    public int width;
    public int height;
    public Tile[][] tiles;

    /**
     *
     * @param width
     * @param height
     */
    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    /**
     *
     * @param dt
     */
    public void update(float dt) {
    }

    /**
     * Returns the Tile object to a given Position (x, y) on the screen.
     * @param x             x position on the screen
     * @param y             y position on the screen
     * @return
     */
    public Tile pointToTile(float x, float y) {
        // check if (x, y) is "on" the map, if not return null
        if (x < 0 || x > this.width * TILE_WIDTH || y < 0 || y > this.height * TILE_HEIGHT)
            return null;
        return tiles[(int) x / TILE_WIDTH][(int) y / TILE_HEIGHT];
    }

    /**
     *
     * @param g2d
     */
    public void render(Graphics2D g2d) {
        for(int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.tiles[x][y].render(g2d);
            }
        }
    }
}
