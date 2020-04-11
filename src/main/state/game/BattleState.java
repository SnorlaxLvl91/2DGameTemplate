package main.state.game;

import main.*;
import main.battle.BattleSprite;
import main.battle.Opponent;
import main.controller.StandardController;
import main.entity.Player;
import main.gui.ProgressBar;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Random;

import static main.Constants.VIRTUAL_WIDTH;
import static main.Constants.VIRTUAL_HEIGHT;
import static main.SoundPlayer.*;

public class BattleState extends State implements Observer{

    public Player player;
    public boolean battleStarted;

    public Opponent opponent;

    public BattleSprite playerSprite;
    public BattleSprite opponentSprite;

    public ProgressBar playerHealthBar;
    public ProgressBar opponentHealthBar;

    public ProgressBar playerExpBar;

    public boolean renderHealthBars;

    public int playerCircleX;
    public int opponentCircleX;

    public Pokemon playerPokemon;
    public Pokemon opponentPokemon;

    public BattleState(Player player) {
        keyListener = new StandardController(this);
        this.player = player;
        //self.bottomPanel = Panel(0, VIRTUAL_HEIGHT - 64, VIRTUAL_WIDTH, 64)

        // flag for when the battle can take input, set in the first update call
        battleStarted = false;

        opponent = new Opponent(Map.of(
                "party", new Party(Map.of(
                        "pokemon", new Pokemon[]{new Pokemon(
                                Pokemon.getRandomDef(), (2 + new Random().nextInt(5)))
                }
            ))
        ));

        playerSprite = new BattleSprite(player.party.pokemon[0].battleSpriteBack, -64, VIRTUAL_HEIGHT - 128);

        opponentSprite = new BattleSprite(opponent.party.pokemon[0].battleSpriteFront, VIRTUAL_WIDTH, 8);

        // health bars for pokemon
        playerHealthBar = new ProgressBar(Map.of(
                "x", VIRTUAL_WIDTH - 160,
                "y", VIRTUAL_HEIGHT - 80,
                "width",  152,
                "height", 6,
                "color", new Color(189, 32, 32),
                "value", player.party.pokemon[0].currentHP,
                "max", player.party.pokemon[0].HP
        ));

        opponentHealthBar = new ProgressBar(Map.of(
                "x", 8,
                "y", 8,
                "width", 152,
                "height", 6,
                "color", new Color(189, 32, 32),
                "value", opponent.party.pokemon[0].currentHP,
                "max", opponent.party.pokemon[0].HP
        ));

        // exp bar for player
        playerExpBar = new ProgressBar(Map.of(
                "x", VIRTUAL_WIDTH - 160,
                "y", VIRTUAL_HEIGHT - 73,
                "width", 152,
                "height", 6,
                "color", new Color(32, 32, 189),
                "value", player.party.pokemon[0].currentExp,
                "max", player.party.pokemon[0].expToLevel
        ));

        // flag for rendering health (and exp)bars, shown after pokemon slide in
        this.renderHealthBars = false;

        // circles underneath pokemon that will slide from sides at start
        this.playerCircleX = -68;
        this.opponentCircleX = VIRTUAL_WIDTH + 32;

        // references to active pokemon
        this.playerPokemon = player.party.pokemon[0];
        this.opponentPokemon = opponent.party.pokemon[0];
    }

    public void update(float dt) {
        // this will trigger the first time this state is actively updating on the stack
        if(pressed[KeyEvent.VK_SPACE]){
            stop("battle-music");
            StateStack.getInstance().append(
                    new FadeInState(
                            new Color(255, 255, 255),
                            1,
                            () -> {
                                StateStack.getInstance().remove();
                                StateStack.getInstance().remove();
                                new DialogState("Das war knapp!", () -> {});
                                new PlayState();
                                new FadeOutState(
                                        new Color(255, 255, 255),
                                        1,
                                        () -> {
                                           StateStack.getInstance().remove();
                                        });
                            })
            );
        }

        if(!battleStarted) {
            triggerSlideIn();
        }
    }

    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(214, 214, 214, 255));
        ScreenAdapter.shape(g2d, "rectangle", "fill", 0, 0, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        g2d.setColor(new Color(45, 184, 45, 124));
        ScreenAdapter.shape(g2d, "ellipse", "fill", opponentCircleX, 60, 72, 24);
        ScreenAdapter.shape(g2d, "ellipse", "fill", playerCircleX, VIRTUAL_HEIGHT - 64, 72, 24);

        g2d.setColor(new Color(255, 255, 255, 255));
        opponentSprite.render(g2d);
        playerSprite.render(g2d);

        if(renderHealthBars) {
            playerHealthBar.render(g2d);
            opponentHealthBar.render(g2d);
            playerExpBar.render(g2d);

            // render level text
            g2d.setColor(new Color(0, 0, 0, 255));
            g2d.setFont(TypeFace.small);
            ScreenAdapter.drawString(g2d, "LV " + playerPokemon.level, playerHealthBar.x, playerHealthBar.y - 10, ScreenAlignment.NORTHWEST);
            ScreenAdapter.drawString(g2d, "LV " + opponentPokemon.level, opponentHealthBar.x, opponentHealthBar.y + 8, ScreenAlignment.NORTHWEST);
            g2d.setFont(TypeFace.medium);
            g2d.setColor(new Color(255, 255, 255, 255));
        }

//        bottomPanel.render(g2d);

    }

    public void triggerSlideIn() {
        battleStarted = true;

        // slide the sprites and circles in from the edges, then trigger first dialogue boxes
        Timer timer = new Timer();
        timer.register(this);
        timer.tween(
                0,
                1,
                new String[]{"playerSprite", "opponentSprite", "playerCircleX", "opponentCircleX"},
                new int[][]{{playerSprite.x, opponentSprite.x, playerCircleX, opponentCircleX}},
                new int[][]{{66, VIRTUAL_WIDTH - 96, 66, VIRTUAL_WIDTH - 96}}
        );
    }

    public void triggerStartingDialogue() {
         // display a dialogue first for the pokemon that appeared, then the one being sent out
         StateStack.getInstance().append(
                 new DialogState("A wild " + opponentPokemon.name + " appeared!",
                         () -> {
                             StateStack.getInstance().append(new DialogState(
                                     "Go, " + playerPokemon.name + "!",
                                     () -> {
                                         StateStack.getInstance().append(
                                                 new BattleMenuState(this)
                                         );
                                     }
                             ));
                         }
                 )
         );
    }

    public void update(Object obj){
        Map<String[], Object> map = (Map<String[], Object>) obj;

        for (String[] keys : map.keySet()) {
            if(keys[0].equals("finished")){
                doSomething();
                break;
            }
            int[] values = (int[]) map.get(keys);

            for(int i = 0; i < keys.length; i++) {
                if(keys[i].equals("playerSprite")){
                    playerSprite.x = values[i];
                }else if(keys[i].equals("opponentSprite")){
                    opponentSprite.x = values[i];
                }else if(keys[i].equals("playerCircleX")){
                    playerCircleX = values[i];
                }else if(keys[i].equals("opponentCircleX")){
                    opponentCircleX = values[i];
                }
            }
        }
    }

    public void doSomething(){
        triggerStartingDialogue();
        renderHealthBars = true;
    }
}
