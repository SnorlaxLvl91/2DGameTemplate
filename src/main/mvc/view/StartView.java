package main.mvc.view;

import main.TypeFace;
import main.mario.mvc.model.StartModel;

import java.awt.*;

import static main.Constants.VIRTUAL_HEIGHT;

public class StartView implements View {

    private StartModel model;

    public StartView(StartModel model){
        this.model = model;
    }

    public void render(Graphics2D g2d) {
        ScreenAdapter.drawImage(
                g2d,
                Graphic.backgrounds[model.background],
                0,
                0);

        model.level.render(g2d);

        g2d.setFont(TypeFace.title);
        g2d.setColor(new Color(0, 0, 0, 255));
        ScreenAdapter.drawString(g2d, "Super 50 Bros.", 1, VIRTUAL_HEIGHT / 2 - 40 + 1, ScreenAlignment.CENTER);
        g2d.setColor(new Color(255, 255, 255, 255));
        ScreenAdapter.drawString(g2d, "Super 50 Bros.", 0, VIRTUAL_HEIGHT / 2 - 40, ScreenAlignment.CENTER);
        g2d.setFont(TypeFace.medium);
        g2d.setColor(new Color(0, 0, 0, 255));
        ScreenAdapter.drawString(g2d, "Press Enter", 1, VIRTUAL_HEIGHT / 2 + 17, ScreenAlignment.CENTER);
        g2d.setColor(new Color(255, 255, 255, 255));
        ScreenAdapter.drawString(g2d,"Press Enter", 0, VIRTUAL_HEIGHT / 2 + 16, ScreenAlignment.CENTER);
    }
}