package main.state;

import main.State;
import main.mvc.controller.StandardController;
import main.mario.mvc.model.PlayModel;
import main.mario.mvc.view.PlayView;

import java.awt.*;

public class PlayState implements State {

    public static StandardController controller;
    public static PlayView view;
    public static PlayModel model;

    public PlayState(){
        model = new PlayModel();
        controller = new StandardController(model);
        view = new PlayView(model);
    }

    @Override
    public void update(float dt) {
        model.update(dt);
    }

    @Override
    public void render(Graphics2D g2d) {
        view.render(g2d);
    }

    @Override
    public Controller getController() {
        return controller;
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public Model getModel() {
        return model;
    }
}
