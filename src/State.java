package main.mario;

import main.mario.mvc.Controller;
import main.mario.mvc.Model;
import main.mario.mvc.View;

import java.awt.*;

public interface State {

    public void update(float dt);

    public void render(Graphics2D g2d);

    public Controller getController();

    public View getView();

    public Model getModel();
}
