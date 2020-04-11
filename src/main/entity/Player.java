package main.entity;

import main.Party;
import main.Pokemon;
import main.battle.Opponent;

import java.util.Map;
import java.util.Random;

public class Player extends Entity{

    public Party party;

    public Player(Map<String, Object> def) {
        super(def);
        party = new Party(Map.of(
                        "pokemon", new Pokemon[]{new Pokemon(
                                Pokemon.getRandomDef(), (2 + new Random().nextInt(5))
                    )}
        ));
    }

    @Override
    public boolean onInteract() {
        return false;
    }
}
