package main.mvc.view;

import main.mvc.View;
import main.mvc.model.StartModel;

import java.awt.Graphics2D;

public class StartView implements View {

    private StartModel model;

    public StartView(StartModel model){
        this.model = model;
    }

    public void render(Graphics2D g2d) {}
}