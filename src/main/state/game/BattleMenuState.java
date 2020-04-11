package main.state.game;

import main.State;
import main.StateStack;
import main.gui.Item;
import main.gui.Menu;

import java.awt.*;
import java.util.Map;

import static main.Constants.*;
import static main.SoundPlayer.*;

public class BattleMenuState extends State {

    BattleState battleState;
    Menu battleMenu;

    public BattleMenuState(BattleState battleState){
        this.battleState = battleState;

        this.battleMenu = new Menu(Map.of(
                "x", VIRTUAL_WIDTH - 64,
                "y", VIRTUAL_HEIGHT - 64,
                "width", 64,
                "height", 64,
                "items", new Item[]{
                        new Item(
                                "Fight",
                                () -> {
                                    StateStack.getInstance().remove();
                                    StateStack.getInstance().append(
                                            new DialogState(
                                                    "Hier soll eine Angriffslogik implementiert werden!",
                                                    () -> {
                                                    }
                                            )
                                    );
                                }
                        ),
                        new Item(
                                "Run",
                                () -> {
                                    play("run", false);
                                    StateStack.getInstance().remove();
                                    // show a message saying they successfully ran, then fade in
                                    // and out back to the field automatically
                                    StateStack.getInstance().append(
                                            new DialogState(
                                                    "You fled successfully!",
                                                    () -> {
                                                    }
                                            )
                                    );
                                    StateStack.getInstance().append(
                                            new FadeInState(
                                                    new Color(255, 255, 255),
                                                    1,
                                                    () -> {
                                                        // resume field music
                                                        play("field-music", true);

                                                        // pop message state
                                                        StateStack.getInstance().remove();

                                                        // pop battle state
                                                        StateStack.getInstance().remove();

                                                        StateStack.getInstance().append(
                                                                new FadeOutState(
                                                                        new Color(255, 255, 255),
                                                                        1,
                                                                        () -> {
                                                                        }
                                                                )
                                                        );
                                                    }
                                            )
                                    );
                                }
                        )
                }
        ));
    }

    public void update(float dt) {
        battleMenu.update(dt);
    }

    public void render(Graphics2D g2d) {
        battleMenu.render(g2d);
    }
}
