package main;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Stack;

import static main.Constants.WINDOW_WIDTH;
import static main.Constants.WINDOW_HEIGHT;

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
            oldState.exit();
            game.removeKeyListener(oldState.keyListener);
        }
        newState.enter();
        push(newState);
        game.addKeyListener(newState.keyListener);
    }

    /**
     *
     * @param newState
     */
    public void change(State newState){
        if(!isEmpty()) {
            State oldState = peek();
            game.removeKeyListener(oldState.keyListener);
            pop();
        }
        push(newState);
        game.addKeyListener(newState.keyListener);
    }

    /**
     *
     */
    public void remove(){
        if(!isEmpty()) {
            State oldState = peek();
            game.removeKeyListener(oldState.keyListener);
            oldState.exit();
            pop();
            if(!isEmpty()) {
                State newState = peek();
                newState.enter();
                game.addKeyListener(newState.keyListener);
            }
        }
    }

    /**
     *
     * @param dt
     */
    public void update(float dt){
        if(!stateStack.isEmpty())
            stateStack.peek().update(dt);
    }

    /**
     *
     * @param g2d
     */
    public void render(Graphics2D g2d){
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        Stack<State> stack = new Stack<State>();
        stack.addAll(stateStack);
        for(State state:stack){
            state.render(g2d);
        }
        stack.empty();
    }
}
