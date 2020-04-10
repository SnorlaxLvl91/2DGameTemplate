package main;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import static main.Constants.TILE_WIDTH;
import static main.Constants.TILE_HEIGHT;
import static main.Constants.TILE_SETS_WIDE;
import static main.Constants.TILE_SETS_TALL;
import static main.Constants.TILE_SET_WIDTH;
import static main.Constants.TILE_SET_HEIGHT;
import static main.Util.*;

public class Graphic {

    // example for simple texture
    public static BufferedImage[] quads = GenerateQuads("res/graphics/sheet.png", TILE_WIDTH, TILE_HEIGHT);

    // example for multidimensional textures
    public static BufferedImage[][] tilesets; // = GenerateTileSets(Graphic.quads, TILE_SETS_WIDE, TILE_SETS_TALL, TILE_SET_WIDTH, TILE_SET_HEIGHT);

    // textboxes
    public static BufferedImage[][] textboxes = GenerateTileSets(
            GenerateQuads("res/graphics/textboxes.png", 8, 8),
            10,
            2,
            3,
            3
    );

    // map containing different types of object textures
    public static Map<String, BufferedImage[]> objects = Map.of(
            /** accessKey, Util.GenerateQuads("res/graphics/name.png", 16, 16) */);

    // map containing different types of entity textures
    public static Map<String, BufferedImage[]> entities = Map.of();

    public static Map<String, BufferedImage> textures = new HashMap<>();
    static {
        textures.put("tiles", loadImage("res/graphics/sheet.png"));
        textures.put("entities", loadImage("res/graphics/entities.png"));
        textures.put("cursor", loadImage("res/graphics/cursor.png"));

        textures.put("aardart-back", loadImage("res/graphics/pokemon/aardart-back.png"));
        textures.put("aardart-front", loadImage("res/graphics/pokemon/aardart-front.png"));
        textures.put("agnite-back", loadImage("res/graphics/pokemon/agnite-back.png"));
        textures.put("agnite-front", loadImage("res/graphics/pokemon/agnite-front.png"));
        textures.put("anoleaf-back", loadImage("res/graphics/pokemon/anoleaf-back.png"));
        textures.put("anoleaf-front", loadImage("res/graphics/pokemon/anoleaf-front.png"));
        textures.put("bamboon-back", loadImage("res/graphics/pokemon/bamboon-back.png"));
        textures.put("bamboon-front", loadImage("res/graphics/pokemon/bamboon-front.png"));
        textures.put("cardiwing-back", loadImage("res/graphics/pokemon/cardiwing-back.png"));
        textures.put("cardiwing-front", loadImage("res/graphics/pokemon/cardiwing-front.png"));
    }
}
