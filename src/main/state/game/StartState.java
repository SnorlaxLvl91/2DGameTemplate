package main.state.game;

import main.*;
import main.controller.StandardController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static main.SoundPlayer.*;
import static main.Constants.*;
import static main.PokeDefs.*;

public class StartState extends State implements Observer {

    private String sprite;
    public int spriteX;
    private int spriteY;
    private Timer timer;

    public StartState(){
        keyListener = new StandardController(this);
        play("intro-music", true);

        List<String> keysAsArray = new ArrayList<String>(POKEMON_DEFS.keySet());
        Random r = new Random();
        this.sprite = (String) POKEMON_DEFS
                .get(keysAsArray.get(r.nextInt(keysAsArray.size())))
                .get("battleSpriteFront");

        this.spriteX = VIRTUAL_WIDTH / 2 - 32;
        this.spriteY = VIRTUAL_HEIGHT / 2 - 16;

        timer = new Timer();
        timer.register(this);
        timer.tweenEvery(3, 2, 0.4f,
                new String[]{"spriteX"}, new int[][]{{spriteX}, {VIRTUAL_WIDTH + 50}}, new int[][]{{-64}, {spriteX}});
    }

    @Override
    public void enter(Map<String, Object> params) {

    }

    @Override
    public void exit() {

    }

    @Override
    public void update(float dt) {
        if(pressed[KeyEvent.VK_ENTER] || pressed[KeyEvent.VK_SPACE]) {
            StateStack.getInstance().append(
                    new FadeInState(
                            new Color(255, 255, 255),
                            1f,
                            () -> {
                                stop("intro-music");
                                StateStack.getInstance().remove();
                                StateStack.getInstance().append(new PlayState());
                                StateStack.getInstance().append(new DialogState(""));
                                StateStack.getInstance().append(new FadeOutState(
                                        new Color(255, 255, 255),
                                        1,
                                        () -> {}));
                            }
                            ));
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(188, 188, 188, 255));
        g2d.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        g2d.setColor(new Color(24, 24, 24, 255));
        g2d.setFont(TypeFace.large);
        ScreenAdapter.drawString(g2d, "50-Mon!", 0,  - 72, ScreenAlignment.CENTER);
        g2d.setFont(TypeFace.medium);
        ScreenAdapter.drawString(g2d, "Press Enter", 0, -55, ScreenAlignment.SOUTH);
        g2d.setFont(TypeFace.small);

        g2d.setColor(new Color(45, 184, 45, 124));
        ScreenAdapter.fillOval(g2d, VIRTUAL_WIDTH / 2 - 36, VIRTUAL_HEIGHT / 2 + 32, 72, 24);

        g2d.setColor(new Color(255, 255, 255, 255));
        ScreenAdapter.drawImage(g2d, Graphic.textures.get(this.sprite), this.spriteX, this.spriteY);
    }

    public void doSomething(){
        if(spriteX >= VIRTUAL_WIDTH) {
            List<String> keysAsArray = new ArrayList<String>(POKEMON_DEFS.keySet());
            Random r = new Random();
            this.sprite = (String) POKEMON_DEFS
                    .get(keysAsArray.get(r.nextInt(keysAsArray.size())))
                    .get("battleSpriteFront");
        }
    }
}
