package main.mario.mvc.model;

import main.SoundPlayer;
import main.mario.GameLevel;
import main.mario.LevelMaker;
import main.mario.StateStack;
import main.mario.mvc.Model;
import main.mario.state.PlayState;

import java.awt.event.KeyEvent;
import java.util.Random;

public class StartModel implements Model {

    public GameLevel level;
    public int background;

    public StartModel() {
        this.level = LevelMaker.generate(100, 10);
        this.background = new Random().nextInt(3);
        SoundPlayer.play("music", true);
    }

    public void update(float dt) {
        if (pressed[KeyEvent.VK_ENTER] || pressed[KeyEvent.VK_SPACE]) {
            StateStack.getInstance().change(new PlayState());
            pressed[KeyEvent.VK_ENTER] = pressed[KeyEvent.VK_SPACE] = false;
        }
    }
}
