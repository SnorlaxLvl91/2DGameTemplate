package main;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Constants.WINDOW_WIDTH;
import static main.Constants.WINDOW_HEIGHT;
import static main.Constants.VIRTUAL_WIDTH;
import static main.Constants.VIRTUAL_HEIGHT;

public class ScreenAdapter {

    private static int camX = 0;
    private static int camY = 0;

    /**
     * Sets "camera" parameters to illustrate camera movement on the screen
     *
     * @param x
     * @param y
     */
    public static void translate(int x, int y) {
        double factorX = ((double) WINDOW_WIDTH) / VIRTUAL_WIDTH;
        double factorY = ((double) WINDOW_HEIGHT) / VIRTUAL_HEIGHT;

        camX = (int) (factorX * x);
        camY = (int) (factorY * y);
    }

    /**
     * @param g2d
     * @param image
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public static void drawImage(Graphics2D g2d,
                                 BufferedImage image,
                                 float x,
                                 float y,
                                 int width,
                                 int height,
                                 boolean onCamera) {
        double factorX = ((double) WINDOW_WIDTH) / VIRTUAL_WIDTH;
        double factorY = ((double) WINDOW_HEIGHT) / VIRTUAL_HEIGHT;

        // Größe der ursprünglichen Bilddatei
        int sx1 = 0;
        int sy1 = 0;
        int sx2 = image.getWidth();
        int sy2 = image.getHeight();

        // Größe der Datei auf dem Screen
        int dx1 = (int) (factorX * x);
        int dy1 = (int) (factorY * y);
        int dx2 = (int) (factorX * (x + width));
        int dy2 = (int) (factorY * (y + height));

        if(onCamera)
            g2d.drawImage(image, dx1 - camX, dy1 - camY, dx2 - camX, dy2 - camY, sx1, sy1, sx2, sy2, null);
        else
            g2d.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);

    }

    /**
     * @param g2d
     * @param image
     * @param x
     * @param y
     */
    public static void drawImage(Graphics2D g2d,
                                 BufferedImage image,
                                 float x,
                                 float y,
                                 boolean onCamera) {
        // Größe der ursprünglichen Bilddatei
        int width = image.getWidth();
        int height = image.getHeight();

        drawImage(g2d, image, x, y, width, height, onCamera);
    }

    /**
     * @param g2d
     * @param image
     * @param x
     * @param y
     */
    public static void drawImage(Graphics2D g2d,
                                 BufferedImage image,
                                 float x,
                                 float y,
                                 int width,
                                 int height) {

        drawImage(g2d, image, x, y, width, height, true);
    }

    /**
     * @param g2d
     * @param image
     * @param x
     * @param y
     */
    public static void drawImage(Graphics2D g2d,
                                 BufferedImage image,
                                 float x,
                                 float y) {

        drawImage(g2d, image, x, y, true);
    }

    /**
     * @param g2d
     * @param text
     * @param offsetX
     * @param offsetY
     * @param alignment
     */
    public static void drawString(Graphics2D g2d, String text, int offsetX, int offsetY, ScreenAlignment alignment) {
        int x = 0;
        int y = 0;
        int textWidth;
        int textHeight;
        Font oldFont;

        double factorX = ((double) WINDOW_WIDTH) / VIRTUAL_WIDTH;
        double factorY = ((double) WINDOW_HEIGHT) / VIRTUAL_HEIGHT;

        // consider font size to adjust correct screen position
//        g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, (int)(factorX * g2d.getFont().getSize())));
        oldFont = g2d.getFont();
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        textWidth = metrics.stringWidth(text);
        textHeight = metrics.getHeight();

        // set x postion for text on screen
        switch (alignment) {
            case CENTER:
            case SOUTH:
            case NORTH:
                x = VIRTUAL_WIDTH / 2 - textWidth / 2;
                break;
            case NORTHEAST:
            case EAST:
            case SOUTHEAST:
                x = VIRTUAL_WIDTH - textWidth;
                break;
            default:
                break;
        }

        // set y postion for text on screen
        switch (alignment) {
            case WEST:
            case EAST:
            case CENTER:
                y = VIRTUAL_HEIGHT / 2 - textHeight / 2;
                break;
            case SOUTHWEST:
            case SOUTHEAST:
            case SOUTH:
                y = VIRTUAL_HEIGHT - textHeight;
                break;
            case NORTHEAST:
            case NORTH:
            case NORTHWEST:
                y = textHeight / 2;
                break;
            default:
                break;
        }
        g2d.setFont(new Font(g2d.getFont().getFontName(), Font.PLAIN, (int) (factorX * g2d.getFont().getSize())));
        g2d.drawString(text, (int) (factorX * (x + offsetX)), (int) (factorY * (y + offsetY)));
        g2d.setFont(oldFont);
    }

    /**
     * @param g2d
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public static void shape(Graphics2D g2d, String shape, String type, int x, int y, int width, int height) {
        double factorX = ((double) WINDOW_WIDTH) / VIRTUAL_WIDTH;
        double factorY = ((double) WINDOW_HEIGHT) / VIRTUAL_HEIGHT;

        x = (int) (factorX * x);
        y = (int) (factorY * y);
        width = (int) (factorX * width);
        height = (int) (factorY * height);

        if (shape.equals("rectangle")) {
            if (type.equals("fill"))
                g2d.fillRect(x, y, width, height);
            else
                g2d.drawRect(x, y, width, height);
        } else {
            if (type.equals("fill"))
                g2d.fillOval(x, y, width, height);
            else
                g2d.fillOval(x, y, width, height);
        }
    }
}