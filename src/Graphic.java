package main.mario;

import main.Util;

import java.awt.image.BufferedImage;
import java.util.Map;

import static main.mario.Constants.*;

public class Graphic {

    public static BufferedImage[] quads = Util.GenerateQuads("res/graphics/big_tiles.png", TILE_WIDTH, TILE_HEIGHT);
    public static BufferedImage[] characterQuads = Util.GenerateQuads("res/graphics/character.png", CHARACTER_WIDTH, CHARACTER_HEIGHT);
    public static BufferedImage[] topperQuads = Util.GenerateQuads("res/graphics/tile_tops.png", TILE_WIDTH, TILE_HEIGHT);
    public static BufferedImage[] backgrounds = Util.GenerateQuads("res/graphics/backgrounds.png", 256, 128);
    public static BufferedImage[][] tilesets = Util.GenerateTileSets(Graphic.quads, TILE_SETS_WIDE, TILE_SETS_TALL, TILE_SET_WIDTH, TILE_SET_HEIGHT);
    public static BufferedImage[][] toppersets = Util.GenerateTileSets(Graphic.topperQuads, TOPPER_SETS_WIDE, TOPPER_SETS_TALL, TILE_SET_WIDTH, TILE_SET_HEIGHT);

    public static Map<String, BufferedImage[]> objects = Map.of(
            "gems", Util.GenerateQuads("res/graphics/gems.png", 16, 16),
            "jump-blocks", Util.GenerateQuads("res/graphics/jump_blocks.png", 16, 16),
            "bushes", Util.GenerateQuads("res/graphics/bushes_and_cacti.png", 16, 16));

    public static Map<String, BufferedImage[]> entities = Map.of(
            "green-alien", Util.GenerateQuads("res/graphics/green_alien.png", 16, 20),
            "creatures", Util.GenerateQuads("res/graphics/creatures.png", 16, 16));

}
