package main;

import java.awt.*;
import java.util.Map;

public class Party {

    public Pokemon[] pokemon;

    public Party(Map<String, Object> def) {
        this.pokemon = (Pokemon[]) def.get("pokemon");
    }

    public void update(float dt) {}

    public void render(Graphics2D g2d){}
}
