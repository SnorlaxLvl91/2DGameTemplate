package main.state.game;

import main.State;
import main.StateMachine;
import main.StateStack;
import main.controller.StandardController;
import main.state.entity.PlayerIdleState;
import main.state.entity.PlayerWalkState;
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

        level.player.stateMachine = new StateMachine(Map.of(
                "walk", () -> {return new PlayerWalkState(level.player, level);},
                "idle", () -> {return new PlayerIdleState(level.player);}
        ));

        level.player.stateMachine.change("idle", null);
    }

    @Override
    public void update(float dt) {
        if(pressed[KeyEvent.VK_SPACE]) {
            play("heal", false);
//            self.level.player.party.pokemon[1].currentHP = self.level.player.party.pokemon[1].HP

            StateStack.getInstance().append(new DialogState("Your Pokemon has been healed!", () -> {}));
            pressed[KeyEvent.VK_SPACE] = false;
        }
        this.level.update(dt);
    }

    @Override
    public void render(Graphics2D g2d) {
        this.level.render(g2d);
    }

}
