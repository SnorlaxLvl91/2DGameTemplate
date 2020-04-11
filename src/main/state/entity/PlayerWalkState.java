package main.state.entity;

import main.StateStack;
import main.entity.Entity;
import main.entity.Player;
import main.state.game.BattleState;
import main.state.game.FadeInState;
import main.state.game.FadeOutState;
import main.world.Level;
import main.world.TileDefs;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static main.SoundPlayer.*;

public class PlayerWalkState extends EntityWalkState {

    boolean encounterFound = false;

    public PlayerWalkState(Entity entity, Level level){
        super(entity, level);
        this.encounterFound = false;
        entity.changeAnimation("walk-" + entity.direction);
    }

    @Override
    public void enter(Map<String, Object> params) {
        checkForEncounter();

        if(!encounterFound) {
            attemptMove();
        }
    }

    public void checkForEncounter() {
        int x = entity.mapX;
        int y = entity.mapY;

        if(entity.direction == "left") {
            x = x - 1;
        }else if(entity.direction == "right") {
            x = x + 1;
        }else if(entity.direction == "up") {
            y = y - 1;
        }else {
            y = y + 1;
        }

        // chance to go to battle if we're walking into a grass tile, else move as normal
        if (level.grassLayer.tiles[x][y].id == ((int[]) TileDefs.TILE_IDS.get("tall-grass"))[0]
                && new Random().nextInt(1) == 0) {
            entity.changeState("idle");

            // trigger music changes
            stop("field-music");
            play("battle-music", true);

            // first, push a fade in; when that's done, push a battle state and a fade
            // out, which will fall back to the battle state once it pushes itself off
            StateStack.getInstance().append(
                    new FadeInState(
                            new Color(255, 255, 255),
                            1,
                            () -> {
                                StateStack.getInstance().remove();
                                StateStack.getInstance().append(
                                        new BattleState((Player) entity)
                                );
                                StateStack.getInstance().append(
                                        new FadeOutState(
                                                new Color(255, 255, 255),
                                                1,
                                                () -> {
                                                    StateStack.getInstance().remove();
                                                }
                                        )
                                );
                            }
                    )
            );
            encounterFound = true;
        }
        encounterFound = false;
    }

    @Override
    public void exit() {

    }

    @Override
    public void processAI(HashMap<String, Object> params, float dt) {

    }

    @Override
    public void update(float dt) {

    }
}
