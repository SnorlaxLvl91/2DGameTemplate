package main.battle;

import main.Party;

import java.util.Map;

public class Opponent {

    public Party party;

    public Opponent(Map<String, Object> def) {
        this.party = (Party) def.get("party");
    }

    public void takeTurn() {

    }
}