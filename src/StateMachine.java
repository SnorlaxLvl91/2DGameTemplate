package main.mario;

import java.awt.*;
import java.util.Map;

import main.mario.entity.State;
import main.mario.entity.StateGenerator;

/**
 *
 */
public class StateMachine {

    Map<String, Object> states;
    State current;
    public StateGenerator stateGenerator;

    public StateMachine(Map<String, Object> states) {
        this.states = states;
    }

    public void change(String stateName, Map<String, Object> enterParams) {
        if(this.current != null)
            this.current.exit();
        stateGenerator = (StateGenerator) states.get(stateName);
        this.current = stateGenerator.generate();
        this.current.enter(enterParams);
    }

    public void update(float dt) {
        this.current.update(dt);
    }

    public void render(Graphics2D g2d) {
        current.render(g2d);
    }
}
