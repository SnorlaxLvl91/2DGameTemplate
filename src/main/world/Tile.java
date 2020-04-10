package main.world;

import main.Graphic;
import main.ScreenAdapter;

import java.awt.*;

import static main.Constants.TILE_WIDTH;
import static main.Constants.TILE_HEIGHT;

public class Tile {

    public int x;
    public int y;
    public int id;

    public Tile(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void update(float dt){

    }

    public void render(Graphics2D g2d) {
        ScreenAdapter.drawImage(g2d, Graphic.tiles[id], x * TILE_WIDTH, y * TILE_HEIGHT);
    }
}
