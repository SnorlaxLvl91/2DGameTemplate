package main.gui;

import main.ScreenAdapter;
import main.ScreenAlignment;
import main.TypeFace;

import java.awt.*;
import java.awt.event.KeyEvent;

import static main.State.pressed;

public class Textbox {

    public Panel panel;

    public int x;
    public int y;

    public int width;
    public int height;

    public String text;
    public Font font;
    public String[] textChunks;
    public String[] displayingChunks;

    public int chunkCounter;
    public boolean closed;
    public boolean endOfText;

    public Textbox(int x, int y, int width, int height, String text, Font font) {
        panel = new Panel(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.text = text;
        this.font = font != null ? font : TypeFace.small;
        //self.textChunks = font.getWrap(self.text, self.width - 12)

        this.chunkCounter = 1;
        this.endOfText = false;
        this.closed = false;

        next();
    }

    //Goes to the next page of text if there is any, otherwise toggles the textbox.
    public String[] nextChunks() {
        String[] chunks = new String[2];

        for (int i = chunkCounter; i < chunkCounter + 2; i++) {
            chunks[i - chunkCounter] = textChunks[i];

            // if we've reached the number of total chunks, we can return
            if (i == textChunks.length) {
                endOfText = true;
                return chunks;
            }
        }
        chunkCounter = chunkCounter + 2;

        return chunks;
    }

    public void next() {
        if (endOfText) {
            displayingChunks = new String[2];
            panel.toggle();
            closed = true;
        } else {
            displayingChunks = nextChunks();
        }
    }

    public void update(float dt) {
        if (pressed[KeyEvent.VK_SPACE] || pressed[KeyEvent.VK_ENTER]){
            next();
        }
    }

    public boolean isClosed() {
        return closed;
    }

    public void render(Graphics2D g2d) {
        panel.render(g2d);

        g2d.setFont(font);
        for (int i = 1; i < displayingChunks.length; i++) {
            ScreenAdapter.drawString(g2d, displayingChunks[i], x + 3, y + 3 + (i - 1) * 16, ScreenAlignment.NORTHWEST);
        }
    }
}