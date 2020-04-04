package main.mario;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import static main.tiles1.Constants.*;


public class ScreenAdapter {

    private static int camX;
    private static int camY;

    public static void translate(int x, int y){
        double factorX = ((double) WINDOW_WIDTH) / VIRTUAL_WIDTH;
        double factorY = ((double) WINDOW_HEIGHT) / VIRTUAL_HEIGHT;

        camX = (int)(factorX * x);
        camY = (int)(factorY * y);
    }

    public static void drawImage(Graphics2D g2d, BufferedImage image, float x, float y, int width, int height){
        double factorX = ((double) WINDOW_WIDTH) / VIRTUAL_WIDTH;
        double factorY = ((double) WINDOW_HEIGHT) / VIRTUAL_HEIGHT;

        // Größe der ursprünglichen Bilddatei
        int sx1 = 0;
        int sy1 = 0;
        int sx2 = image.getWidth();
        int sy2 = image.getHeight();

        // Größe der Datei auf dem Screen
        int dx1 = (int)(factorX * x);
        int dy1 = (int)(factorY * y);
        int dx2 = (int)(factorX * (x + width));
        int dy2 = (int)(factorY * (y + height));

        g2d.drawImage(image, dx1 - camX, dy1 - camY, dx2 - camX, dy2 - camY, sx1, sy1, sx2, sy2, null);
    }

    public static void drawImage(Graphics2D g2d, BufferedImage image, float x, float y){
        double factorX = ((double) WINDOW_WIDTH) / image.getWidth();
        double factorY = ((double) WINDOW_HEIGHT) / image.getHeight();

        // Größe der ursprünglichen Bilddatei
        int sx1 = 0;
        int sy1 = 0;
        int sx2 = image.getWidth();
        int sy2 = image.getHeight();

        // Größe der Datei auf dem Screen
        int dx1 = (int)(factorX * x);
        int dy1 = (int)(factorY * y);
        int dx2 = (int)(factorX * sx2);
        int dy2 = (int)(factorY * sy2);

        g2d.drawImage(image, dx1 - 0*camX, dy1 - 0*camY, dx2 + dx1 - 0*camX, dy2 + dy1 - 0*camY, sx1, sy1, sx2, sy2, null);
    }

    public static int adaptNumber(String type, int number){
        double factorX = ((double) WINDOW_WIDTH) / VIRTUAL_WIDTH;
        double factorY = ((double) WINDOW_HEIGHT) / VIRTUAL_HEIGHT;

        switch (type){
            case "x":
                return (int) (factorX * number);
            default:
                return (int) (factorY * number);
        }
    }

    public static void drawString(Graphics2D g2d, String text, int offsetX, int offsetY, ScreenAlignment alignment){
        int x = 0;
        int y = 0;
        int textWidth;
        int textHeight;

        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        textWidth = metrics.stringWidth(text);
        textHeight = metrics.getHeight();
        switch(alignment){
            case CENTER:
            case SOUTH:
            case NORTH:
                x = Game.WINDOW_WIDTH / 2 - textWidth / 2;
                break;
            case NORTHEAST:
            case EAST:
            case SOUTHEAST:
                x = Game.WINDOW_WIDTH - textWidth;
                break;
            default:
                break;
        }

        switch(alignment){
            case WEST:
            case EAST:
            case CENTER:
                y = Game.WINDOW_HEIGHT / 2 - textHeight / 2;
                break;
            case SOUTHWEST:
            case SOUTHEAST:
            case SOUTH:
                y = Game.WINDOW_HEIGHT - textHeight;
                break;
            case NORTHEAST:
            case NORTH:
            case NORTHWEST:
                y = textHeight / 2;
                break;
            default:
                break;
        }
        g2d.drawString(text, x + offsetX, y + offsetY);
    }
}
