package main;

import java.util.Arrays;
import java.util.List;

public class Constants {
    // Game-Loop
    public static final int FPS = 60;
    public static final float DELTA_TIME = (float) 1 / FPS;
    public static final long MAXLOOPTIME = 1000 / FPS;

    // Window Size
    public static int WINDOW_WIDTH = 960; //1280;
    public static int WINDOW_HEIGHT = 540; //720;

    // Auflösung
    public static final int VIRTUAL_WIDTH = 256;
    public static final int VIRTUAL_HEIGHT = 144;

    // Map
    public static final int MAP_WIDTH = 100;
    public static final int MAP_HEIGHT = 10;

    public static final int TILE_WIDTH = 16;
    public static final int TILE_HEIGHT = 16;

    public static final int TILE_SET_WIDTH = 5;
    public static final int TILE_SET_HEIGHT = 4;

    public static final int TILE_SETS_WIDE = 6;
    public static final int TILE_SETS_TALL = 10;
    public static final int TILE_SETS = TILE_SETS_WIDE * TILE_SETS_TALL;

    public static final int TOPPER_SETS_WIDE = 6;
    public static final int TOPPER_SETS_TALL = 18;
    public static final int TOPPER_SETS = TOPPER_SETS_WIDE * TOPPER_SETS_TALL;

    public static final int CHARACTER_WIDTH = 16;
    public static final int CHARACTER_HEIGHT = 20;

    public static final int CHARACTER_MOVE_SPEED = 40;
    public static final int JUMP_VELOCITY = -200;
    public static final int GRAVITY = 7;

    public static final int SCREEN_TILE_WIDTH = VIRTUAL_WIDTH / TILE_WIDTH;
    public static final int SCREEN_TILE_HEIGHT = VIRTUAL_HEIGHT / TILE_HEIGHT;

    public static final int CAMERA_SPEED = 100;

    public static final int BACKGROUND_SCROLL_SPEED = 10;

    public static final int PLAYER_WALK_SPEED = 60;
    public static final int PLAYER_JUMP_VELOCITY = -150;

    public static final int SNAIL_MOVE_SPEED = 10;

    public static final int TILE_ID_EMPTY = 5; //5;
    public static final int TILE_ID_GROUND = 10; //3;

    public static final List<Integer> COLLIDABLE_TILES = Arrays.asList(TILE_ID_GROUND);
    public static final List<Integer> BUSH_IDS = Arrays.asList(0, 1, 4, 5);
    public static final List<Integer> COIN_IDS = Arrays.asList(0, 1, 2);
    public static final List<Integer> CRATES = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
    public static final List<Integer> GEMS = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
    public static final List<Integer> JUMP_BLOCKS = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                                                                  10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                                                                  20, 21, 22, 23, 24, 25, 26, 27, 28, 29);
}
