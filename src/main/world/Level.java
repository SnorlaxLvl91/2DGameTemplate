package main.world;

import main.ScreenAdapter;
import main.entity.EntityDefs;
import main.entity.Player;

import java.awt.*;
import java.util.Map;

import static main.Constants.*;

public class Level {

    public int tileWidth;
    public int tileHeight;

    public TileMap baseLayer;
    public TileMap grassLayer;
    public TileMap halfGrassLayer;

    public Player player;

    public int camX;
    public int camY;

    public Level() {

        this.tileWidth = 50;
        this.tileHeight = 50;

        this.createMaps();

        this.player = new Player(Map.of(
                "animations", EntityDefs.ENTITY_DEFS.get("player").get("animations"),
                "mapX", 12,
                "mapY", 7,
                "width", 16,
                "height", 16
        ));

    }

    public void createMaps() {
        baseLayer = new TileMap(tileWidth, tileHeight);
        grassLayer = new TileMap(tileWidth, tileHeight);
        halfGrassLayer = new TileMap(tileWidth, tileHeight);

        for (int y = 0; y < tileHeight; y++) {
            for (int x = 0; x < tileWidth; x++) {
                int id = ((int[])(TileDefs.TILE_IDS.get("grass")))[0];

                baseLayer.tiles[x][y] = new Tile(x, y, id);
            }
        }

        // place tall grass in the tall grass layer
        for (int y = 0; y < tileHeight; y++) {
            for (int x = 0; x < tileWidth; x++) {
                int id = y > 10 ? ((int[])(TileDefs.TILE_IDS.get("tall-grass")))[0] : ((int[])(TileDefs.TILE_IDS.get("empty")))[0];

                grassLayer.tiles[x][y] = new Tile(x, y, id);
            }
        }
    }

    public void update(float dt) {
        player.update(dt);
        updateCamera();
    }

    public void updateCamera() {

        camX = Math.max(
                0,
                Math.min(
                        TILE_WIDTH * baseLayer.width - VIRTUAL_WIDTH,
                        (int) player.x - (VIRTUAL_WIDTH / 2)
                )
        );
        camY = Math.max(
                0,
                Math.min(
                        TILE_HEIGHT* baseLayer.height - VIRTUAL_HEIGHT,
                        (int) player.y - (VIRTUAL_HEIGHT / 2)
                )
        );
    }

    public void render(Graphics2D g2d) {
        ScreenAdapter.translate(camX, camY);

        baseLayer.render(g2d);
        grassLayer.render(g2d);
        player.render(g2d);
    }
}
