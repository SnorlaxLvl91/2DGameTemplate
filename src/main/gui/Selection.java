package main.gui;

import main.Graphic;
import main.ScreenAdapter;
import main.ScreenAlignment;
import main.TypeFace;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Map;

import static main.SoundPlayer.*;
import static main.State.pressed;

public class Selection {

    public Item[] items;
    public int x;
    public int y;

    public int width;
    public int height;

    public Font font;

    public int gapHeight;
    public int currentSelection;

    public Selection(Map<String, Object> def) {
        this.items = (Item[]) def.get("items");
        this.x = (int) def.get("x");
        this.y = (int) def.get("y");

        this.height = (int) def.get("height");
        this.width = (int) def.get("width");
        this.font = def.containsKey("font") ? (Font) def.get("font") : TypeFace.small;

        this.gapHeight = height / items.length;

        this.currentSelection = 0;
    }

    public void update(float dt) {
        if(pressed[KeyEvent.VK_UP]) {
            if (currentSelection == 0) {
                currentSelection = items.length - 1;
            } else {
                currentSelection--;
            }
            stop("blip");
            play("blip", false);
        }else if(pressed[KeyEvent.VK_UP]) {
            if (currentSelection == items.length - 1) {
                currentSelection = 1;
            } else {
                currentSelection++;
            }
            stop("blip");
            play("blip", false);
        }else if(pressed[KeyEvent.VK_SPACE] || pressed[KeyEvent.VK_ENTER]) {
            //items[currentSelection].onSelect();

            stop("blip");
            play("blip", false);
        }
    }

    public void render(Graphics2D g2d){
        int currentY = y;

        for(int i = 0; i < items.length; i++) {
            int paddedY = currentY + (gapHeight / 2) - font.getSize() / 2;

            // draw selection marker if we're at the right index
            if (i == currentSelection) {
                ScreenAdapter.drawImage(g2d, Graphic.textures.get("cursor"), x - 8, paddedY);
            }

            //ScreenAdapter.drawString(g2d, items[i].text, x, paddedY, width, ScreenAlignment.CENTER);

            currentY = currentY + gapHeight;
        }
    }
}
