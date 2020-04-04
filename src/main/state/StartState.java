package main.state;

import main.State;
import main.mvc.Controller;
import main.mvc.Model;
import main.mvc.View;
import main.mvc.controller.StandardController;
import main.mvc.model.StartModel;
import main.mvc.view.StartView;

import java.awt.Graphics2D;

public class StartState implements State {

    public static StandardController controller;
    public static StartView view;
    public static StartModel model;

    public StartState(){
        model = new StartModel();
        controller = new StandardController(model);
        view = new StartView(model);
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
