package main;

import main.mvc.Controller;
import main.mvc.Model;
import main.mvc.View;

import java.awt.Graphics2D;

public interface State {

    public void update(float dt);

    public void render(Graphics2D g2d);

    public Controller getController();

    public View getView();

    public Model getModel();
}
