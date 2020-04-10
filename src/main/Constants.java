package main;

import java.util.Arrays;
import java.util.List;

public class Constants {
    // Game-Loop
    public static final int FPS = 60;
    public static final float DELTA_TIME = (float) 1 / FPS;
    public static final long MAXLOOPTIME = 1000 / FPS;

    // Window Size
    public static int WINDOW_WIDTH = 1120;
    public static int WINDOW_HEIGHT = 630;

    // Auflösung
    public static final int VIRTUAL_WIDTH = 384;
    public static final int VIRTUAL_HEIGHT = 216;

    // Map
    public static final int MAP_WIDTH = 100;
    public static final int MAP_HEIGHT = 10;

    // Tile(set)
    public static final int TILE_WIDTH = 16;
    public static final int TILE_HEIGHT = 16;

    public static final int TILE_SET_WIDTH = 5;
    public static final int TILE_SET_HEIGHT = 4;

    public static final int TILE_SETS_WIDE = 6;
    public static final int TILE_SETS_TALL = 10;

    public static final List<Integer> COLLIDABLE_TILES = Arrays.asList();
}
