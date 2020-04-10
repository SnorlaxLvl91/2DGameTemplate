package main.world;

import java.awt.*;

public class TileMap {

    public int width;
    public int height;
    public Tile[][] tiles;

    public TileMap(int width, int height){
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    public void render(Graphics2D g2d) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[y][x].render(g2d);
            }
        }
    }
}
