package main.gui;

import main.ScreenAdapter;

import java.awt.*;
import java.util.Map;

public class ProgressBar {

    public int x;
    public int y;

    public int width;
    public int height;

    public Color color;

    public int value;
    public int max;

    public ProgressBar(Map<String, Object> def) {
        this.x = (int) def.get("x");
        this.y = (int) def.get("y");

        this.width = (int) def.get("width");
        this.height = (int) def.get("height");

        this.color = (Color) def.get("color");

        this.value = (int) def.get("value");
        this.max = (int) def.get("max");
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void update(){}

    public void render(Graphics2D g2d){
        // multiplier on width based on progress
        int renderWidth = value * width / max;

        // draw main bar, with calculated width based on value / max
        g2d.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 255));
        if(value > 0) {
            ScreenAdapter.shape(g2d, "rectangle", "fill", x, y, renderWidth, height);
        }

        // draw outline around actual bar
        g2d.setColor(new Color(0, 0, 0, 255));
        ScreenAdapter.shape(g2d, "rectangle", "line", x, y, width, height);
        g2d.setColor(new Color(255, 255, 255, 255));
    }
}
