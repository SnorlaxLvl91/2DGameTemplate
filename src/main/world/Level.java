package main.world;

import main.StateMachine;
import main.entity.EntityDefs;
import main.entity.Player;
import main.state.entity.PlayerIdleState;
import main.state.entity.PlayerWalkState;

import java.awt.*;
import java.util.Map;

public class Level {

    public int tileWidth;
    public int tileHeight;

    public TileMap baseLayer;
    public TileMap grassLayer;
    public TileMap halfGrassLayer;

    public Player player;
    public StateMachine stateMachine;

    public Level() {

        this.tileWidth = 50;
        this.tileHeight = 50;

        /**this.baseLayer = TileMap(tileWidth, tileHeight);
        this.grassLayer = TileMap(tileWidth, tileHeight);
        this.halfGrassLayer = TileMap(tileWidth, tileHeight);*/

        this.createMaps();

        this.player = new Player(Map.of(
                "animations", EntityDefs.ENTITY_DEFS.get("player").get("animations"),
                "mapX", 10,
                "mapY", 10,
                "width", 16,
                "height", 16
        ));

        player.stateMachine = new StateMachine(Map.of(
            "walk", () -> {return new PlayerWalkState(player, this);},
            "idle", () -> {return new PlayerIdleState(player);}
        ));

        player.stateMachine.change("idle", null);
    }

    public void createMaps() {
//    -- fill the base tiles table with random grass IDs

        baseLayer = new TileMap(tileWidth, tileHeight);
        grassLayer = new TileMap(tileWidth, tileHeight);
        halfGrassLayer = new TileMap(tileWidth, tileHeight);

        for (int y = 0; y < tileHeight; y++) {
//            table.insert(self.baseLayer.tiles, {})
            for (int x = 0; x < tileWidth; x++) {
                int id = ((int[])(TileDefs.TILE_IDS.get("grass")))[0];  //TILE_IDS['grass'][math.random(#TILE_IDS['grass'])]
//                table.insert(self.baseLayer.tiles[y], Tile(x, y, id))
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
    }

    public void render(Graphics2D g2d) {
        baseLayer.render(g2d);
        grassLayer.render(g2d);
        player.render(g2d);
    }
}
