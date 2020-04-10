package main.state.game;

import main.*;
import main.controller.StandardController;
import main.entity.EntityDefs;

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

    private final int TOP_OFFSET = 20;
    private final int LEFT_OFFSET = 20;
    private final int RIGHT_OFFSET = 20;
    private final int BOTTOM_OFFSET = 20;

    private final int TAB_SPEED = 50;
    private int tab = 0;
    private final int TEXT_SPEED = 60;
    private int textSpeedUp = 1;

    private int textbox;
    private String text;
    private String[] drawableText;
    private String[] textOnScreen;
    private int numOfLettersToDraw;
    private int wrap = VIRTUAL_WIDTH - LEFT_OFFSET - RIGHT_OFFSET;

    public DialogState(String text){
        this(text, new Random().nextInt(20));
    }

    public DialogState(String text, int textbox){
        keyListener = new StandardController(this);
        this.text = text;
        this.textbox = textbox;
        numOfLettersToDraw = 0;
        drawableText = wrappedText();
        System.out.println(EntityDefs.ENTITY_DEFS);
    }

    private String[] wrappedText(){
        String[] wrappedText = {"", ""};

        Canvas c = new Canvas();
        FontMetrics fm = c.getFontMetrics(TypeFace.small);
        int zeile = 0;

        textSpeedUp = 1;
        do{
            String nextWord;

            if(text.split(" ").length > 1) {
                nextWord = text.split(" ")[0];
            }else{
                nextWord = text;
                if(text.equals("")){
                    break;
                }
            }

            if(fm.stringWidth(wrappedText[zeile] + nextWord) > wrap){
                if(zeile == 0){
                    zeile = zeile + 1;
                }else {
                    break;
                }
            }
            text = text.substring(nextWord.length());
            text = text.trim();
            if(wrappedText[zeile] == null)
                wrappedText[zeile] = nextWord + " ";
            else
                wrappedText[zeile] += nextWord + " ";
        }while(true);

        return wrappedText;
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
            if(numOfLettersToDraw >= drawableText[0].length() + drawableText[1].length()) {
                if(text.equals("")){
                    StateStack.getInstance().remove();
                }else {
                    drawableText = wrappedText();
                    numOfLettersToDraw = 0;
                }
            }else{
                textSpeedUp = 4;
            }
            pressed[KeyEvent.VK_ENTER] = pressed[KeyEvent.VK_SPACE] = false;
        }
        numOfLettersToDraw = Math.min(
                (int)(numOfLettersToDraw + textSpeedUp * TEXT_SPEED * dt),
                drawableText[0].length() + drawableText[1].length()
        );
        tab = (tab + 1) % TAB_SPEED;
    }

    @Override
    public void render(Graphics2D g2d) {

        for(int i = 0; i < 9; i++){
            int x = 0;
            int y = 2 * VIRTUAL_HEIGHT / 3;
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
                    height = VIRTUAL_HEIGHT / 3 - 2 * height;
                    break;
                case CENTER:
                    x = x + width;
                    width = VIRTUAL_WIDTH - 2 * width;
                    y = y + height;
                    height = VIRTUAL_HEIGHT / 3 - 2 * height;
                    break;
                case CENTER_RIGHT:
                    x = VIRTUAL_WIDTH - width;
                    y = y + height;
                    height = VIRTUAL_HEIGHT / 3 - 2 * height;
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
        g2d.setColor(Color.BLACK);
        g2d.setFont(TypeFace.small);
        textOnScreen = new String[]{"", ""};
        for(int i = 0; i < numOfLettersToDraw; i++){
            if(i < drawableText[0].length()) {
                textOnScreen[0] += drawableText[0].substring(i, i + 1);
            }else{
                textOnScreen[1] += drawableText[1].substring(i - drawableText[0].length(), i + 1 - drawableText[0].length());
            }
        }
        ScreenAdapter.drawString(g2d, textOnScreen[0], LEFT_OFFSET, 2 * VIRTUAL_HEIGHT / 3 + TOP_OFFSET, ScreenAlignment.NORTHWEST);
        ScreenAdapter.drawString(g2d, textOnScreen[1], LEFT_OFFSET, 2 * VIRTUAL_HEIGHT / 3 + TOP_OFFSET * 2, ScreenAlignment.NORTHWEST);

        if(numOfLettersToDraw == drawableText[0].length() + drawableText[1].length() && tab < TAB_SPEED / 2){
            ScreenAdapter.fillOval(g2d, VIRTUAL_WIDTH - RIGHT_OFFSET, VIRTUAL_HEIGHT - BOTTOM_OFFSET, 5, 5);
        }
    }
}
