package main.state.game;

import main.State;
import main.StateStack;
import main.controller.StandardController;
import main.world.Level;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Map;

import static main.SoundPlayer.*;

public class PlayState extends State {

    public Level level;

    public PlayState(){
        keyListener = new StandardController(this);
        play("field-music", true);

        level = new Level();
    }

    @Override
    public void enter(Map<String, Object> params) {

    }

    @Override
    public void exit() {

    }

    @Override
    public void update(float dt) {
        if(pressed[KeyEvent.VK_SPACE]) {
            play("heal", false);
//            self.level.player.party.pokemon[1].currentHP = self.level.player.party.pokemon[1].HP

            StateStack.getInstance().append(new DialogState("Your Pokemon has been healed!"));
            pressed[KeyEvent.VK_SPACE] = false;
        }
        this.level.update(dt);
    }

    @Override
    public void render(Graphics2D g2d) {
//        g2d.setColor(new Color(188, 0, 188, 255));
//        g2d.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.level.render(g2d);
    }
}
