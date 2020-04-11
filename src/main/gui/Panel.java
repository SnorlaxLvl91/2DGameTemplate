package main.gui;

import main.ScreenAdapter;

import java.awt.*;

public class Panel {

    public int x;
    public int y;

    public int width;
    public int height;

    public boolean visible;

    public Panel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.visible = true;
    }

    public void update(float dt) {

    }

    public void render(Graphics2D g2d) {
        if(visible) {
            g2d.setColor(new Color(255, 255, 255, 255));
            ScreenAdapter.shape(g2d, "rectangle", "fill", x, y, width, height);
            g2d.setColor(new Color(56, 56, 56, 255));
            ScreenAdapter.shape(g2d, "rectangle", "fill", x + 2, y + 2, width - 4, height - 4);
            g2d.setColor(new Color(255, 255, 255, 255));
        }
    }

    public void toggle() {
        visible = !visible;
    }
}
