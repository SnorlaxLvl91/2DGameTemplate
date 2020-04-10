package main.entity;

import java.util.Map;

public class Player extends Entity{

    public Player(Map<String, Object> def) {
        super(def);
    }

    @Override
    public boolean onInteract() {
        return false;
    }
}
