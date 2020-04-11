package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static main.PokeDefs.POKEMON_DEFS;

public class Pokemon {

    public String name;

    public String battleSpriteFront;
    public String battleSpriteBack;

    public int baseHP;
    public int baseAttack;
    public int baseDefense;
    public int baseSpeed;

    public int HPIV;
    public int attackIV;
    public int defenseIV;
    public int speedIV;

    public int HP;
    public int attack;
    public int defense;
    public int speed;

    public int level;
    public int currentExp;
    public int expToLevel;

    public int currentHP;

    public Pokemon(Map<String, Object> def, int level) {
        this.name = (String) def.get("name");

        this.battleSpriteFront = (String) def.get("battleSpriteFront");
        this.battleSpriteBack = (String) def.get("battleSpriteBack");

        this.baseHP = (int) def.get("baseHP");
        this.baseAttack = (int) def.get("baseAttack");
        this.baseDefense = (int) def.get("baseDefense");
        this.baseSpeed = (int) def.get("baseSpeed");

        this.HPIV = (int) def.get("HPIV");
        this.attackIV = (int) def.get("attackIV");
        this.defenseIV = (int) def.get("defenseIV");
        this.speedIV = (int) def.get("speedIV");

        this.HP = baseHP;
        this.attack = baseAttack;
        this.defense = baseDefense;
        this.speed = baseSpeed;

        this.level = level;
        this.currentExp = 0;
        this.expToLevel = (int) (this.level * this.level * 5 * 0.75);

        calculateStats();

        this.currentHP = HP;
    }

    public void calculateStats() {
        for(int i = 0; i < this.level; i++) {
            statsLevelUp();
        }
    }

    public static Map<String, Object> getRandomDef() {
        List<String> keysAsArray = new ArrayList<String>(POKEMON_DEFS.keySet());
        Random r = new Random();
        return POKEMON_DEFS.get(keysAsArray.get(r.nextInt(keysAsArray.size())));
    }

    /**Takes the IV (individual value) for each stat into consideration and rolls
    the dice 3 times to see if that number is less than or equal to the IV (capped at 5).
    The dice is capped at 6 just so no stat ever increases by 3 each time, but
    higher IVs will on average give higher stat increases per level. Returns all of
    the increases so they can be displayed in the TakeTurnState on level up.*/

    public int[] statsLevelUp() {

        Random random = new Random();

        int HPIncrease = 0;

        for (int j = 0; j < 2; j++) {
            if (random.nextInt(6) <= HPIV) {
                HP++;
                HPIncrease++;
            }
        }

        int attackIncrease = 0;

        for (int j = 0; j < 2; j++) {
            if (random.nextInt(6) <= attackIV) {
                attack++;
                attackIncrease++;
            }
        }

        int defenseIncrease = 0;

        for (int j = 0; j < 2; j++) {
            if (random.nextInt(6) <= defenseIV) {
                defense++;
                defenseIncrease++;
            }
        }

        int speedIncrease = 0;

        for (int j = 0; j < 3; j++) {
            if (random.nextInt(6) <= speedIV) {
                speed++;
                speedIncrease++;
            }
        }

        return new int[]{HPIncrease, attackIncrease, defenseIncrease, speedIncrease};
    }

    public int[] levelUp() {
        level++;
        expToLevel = (int)(level * level * 5 * 0.75);

        return statsLevelUp();
    }
}
