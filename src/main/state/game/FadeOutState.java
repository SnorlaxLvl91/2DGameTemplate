package main.state.game;

import main.*;

import java.awt.*;
import java.util.Map;

import static main.Constants.WINDOW_WIDTH;
import static main.Constants.WINDOW_HEIGHT;

public class FadeOutState extends State implements Observer {

    private Action onFadeComplete;
    private int r;
    private int g;
    private int b;
    public int opacity;
    private float seconds;

    public FadeOutState(Color color, float seconds, Action onFadeComplete) {
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
        this.opacity = 255;
        this.seconds = seconds;
        this.onFadeComplete = onFadeComplete;

        Timer timer = new Timer();
        timer.register(this);
        timer.tween(0, seconds, new String[]{"opacity"}, new int[][]{{255}}, new int[][]{{0}});
    }

    @Override
    public void enter(Map<String, Object> params) {

    }

    @Override
    public void exit() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(r, g, b, opacity));
        g2d.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        g2d.setColor(new Color(255, 255, 255, 255));
    }

    @Override
    public void doSomething() {
        if(opacity <= 0) {
            StateStack.getInstance().remove();
            onFadeComplete.execute();
        }
    }
}
