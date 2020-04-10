package main.state.game;

import main.Graphic;
import main.ScreenAdapter;
import main.State;
import main.StateStack;
import main.controller.StandardController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Random;

import static main.Constants.*;

public class DialogState extends State {

    private final int TOP_LEFT = 0;
    private final int TOP = 1;
    private final int TOP_RIGHT = 2;
    private final int CENTER_LEFT = 3;
    private final int CENTER = 4;
    private final int CENTER_RIGHT = 5;
    private final int BOTTOM_LEFT = 6;
    private final int BOTTOM = 7;
    private final int BOTTOM_RIGHT = 8;

    private int textbox;
    private String text;

    public DialogState(String text){
        this(text, new Random().nextInt(20));
    }

    public DialogState(String text, int textbox){
        keyListener = new StandardController(this);
        this.text = text;
        this.textbox = textbox;
    }

    @Override
    public void enter(Map<String, Object> params) {

    }

    @Override
    public void exit() {

    }

    @Override
    public void update(float dt) {
        if(pressed[KeyEvent.VK_ENTER] || pressed[KeyEvent.VK_SPACE]){
            StateStack.getInstance().remove();
        }
    }

    @Override
    public void render(Graphics2D g2d) {

        for(int i = 0; i < 9; i++){
            int x = 0;
            int y = 3 * VIRTUAL_HEIGHT / 4;
            int width = 10;
            int height = 10;

            switch (i){
                case TOP_LEFT:
                    break;
                case TOP:
                    x = x + width;
                    width = VIRTUAL_WIDTH - 2 * width;
                    break;
                case TOP_RIGHT:
                    x = VIRTUAL_WIDTH - width;
                    break;
                case CENTER_LEFT:
                    y = y + height;
                    height = VIRTUAL_HEIGHT / 4 - 2 * height;
                    break;
                case CENTER:
                    x = x + width;
                    width = VIRTUAL_WIDTH - 2 * width;
                    y = y + height;
                    height = VIRTUAL_HEIGHT / 4 - 2 * height;
                    break;
                case CENTER_RIGHT:
                    x = VIRTUAL_WIDTH - width;
                    y = y + height;
                    height = VIRTUAL_HEIGHT / 4 - 2 * height;
                    break;
                case BOTTOM_LEFT:
                    y = VIRTUAL_HEIGHT - height;
                    break;
                case BOTTOM:
                    x = x + width;
                    width = VIRTUAL_WIDTH - 2 * width;
                    y = VIRTUAL_HEIGHT - height;
                    break;
                case BOTTOM_RIGHT:
                    x = VIRTUAL_WIDTH - width;
                    y = VIRTUAL_HEIGHT - height;
                    break;
                default:
                    continue;
            }
            ScreenAdapter.drawImage(g2d, Graphic.textboxes[textbox][i], x, y, width, height);
        }

        /**
        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRect(0, (int)(3 * WINDOW_HEIGHT / 4), WINDOW_WIDTH, (int)(WINDOW_HEIGHT / 4));*/
    }
}
