package main;

import java.awt.image.BufferedImage;
import java.util.Map;

import static main.Constants.TILE_WIDTH;
import static main.Constants.TILE_HEIGHT;
import static main.Constants.TILE_SETS_WIDE;
import static main.Constants.TILE_SETS_TALL;
import static main.Constants.TILE_SET_WIDTH;
import static main.Constants.TILE_SET_HEIGHT;

public class Graphic {

    // example for simple texture
    public static BufferedImage[] quads = Util.GenerateQuads("res/graphics/name.png", TILE_WIDTH, TILE_HEIGHT);

    // example for multidimensional textures
    public static BufferedImage[][] tilesets = Util.GenerateTileSets(Graphic.quads, TILE_SETS_WIDE, TILE_SETS_TALL, TILE_SET_WIDTH, TILE_SET_HEIGHT);

    // map containing different types of object textures
    public static Map<String, BufferedImage[]> objects = Map.of(
            /** accessKey, Util.GenerateQuads("res/graphics/name.png", 16, 16) */);

    // map containing different types of entity textures
    public static Map<String, BufferedImage[]> entities = Map.of();

}
