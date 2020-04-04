package main.mario;

import java.awt.*;
import java.util.Stack;

public class StateStack extends Stack<State> {

    private Game game;
    private static StateStack stateStack;

    private StateStack(){
        game = Game.getInstance();
    }

    /**
     *
     * @return
     */
    public static StateStack getInstance(){
        if(stateStack == null)
            stateStack = new StateStack();
        return stateStack;
    }

    /**
     *
     * @param newState
     */
    public void append(State newState){
        if(!isEmpty()) {
            State oldState = peek();
            game.removeKeyListener(oldState.getController());
        }
        push(newState);
        game.addKeyListener(newState.getController());
    }

    /**
     *
     * @param newState
     */
    public void change(State newState){
        if(!isEmpty()) {
            State oldState = peek();
            game.removeKeyListener(oldState.getController());
            pop();
        }
        push(newState);
        game.addKeyListener(newState.getController());
    }

    /**
     *
     */
    public void remove(){
        if(!isEmpty()) {
            State oldState = peek();
            game.removeKeyListener(oldState.getController());
            pop();
            if(!isEmpty()) {
                State newState = peek();
                game.addKeyListener(newState.getController());
            }
        }
    }

    /**
     *
     * @param dt
     */
    public void update(float dt){
        stateStack.peek().update(dt);
    }

    /**
     *
     * @param g2d
     */
    public void render(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        Stack<State> stack = new Stack<State>();
        stack.addAll(stateStack);
        for(State state:stack){
            state.render(g2d);
        }
        //stack.stream().forEach(e -> e.getView().render(g2d));
        stack.empty();
    }
}
