package main.gui;

import java.awt.*;
import java.util.Map;

public class Menu {

    Panel panel;
    Selection selection;

    public Menu(Map<String, Object> def) {
        this.panel = new Panel((int) def.get("x"), (int) def.get("y"), (int) def.get("width"), (int) def.get("height"));

        selection = new Selection(Map.of(
                "items", (Item[]) def.get("items"),
                "x", (int) def.get("x"),
                "y", (int) def.get("y"),
                "width", (int) def.get("width"),
                "height", (int) def.get("height")
        ));
    }

    public void update(float dt){
        selection.update(dt);
     }

    public void render(Graphics2D g2d){
        panel.render(g2d);
        selection.render(g2d);
    }
}
