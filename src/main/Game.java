package main;

import main.state.game.StartState;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;

import static main.Constants.DELTA_TIME;
import static main.Constants.MAXLOOPTIME;
import static main.Constants.WINDOW_WIDTH;
import static main.Constants.WINDOW_HEIGHT;

/**
 *
 */
public class Game extends Frame implements Runnable {
    private static Game game;
    private static StateStack stateStack;
    private Canvas canvas;

    /**
     *
     */
    private Game() {
        setUndecorated(true);                       // Titelleiste entfernen

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        canvas.setMaximumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        canvas.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        canvas.setFocusable(false);

        // Screen bzw. Panel hinzufügen
        setLayout(new BorderLayout());
        //add(Screen.getInstance(), BorderLayout.CENTER);
        add(canvas, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setFocusable(true);
        setVisible(true);                           // Fenster sichtbar machen

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                if(e.getComponent().getWidth() != WINDOW_WIDTH) {   //If window width has changed
                    WINDOW_HEIGHT = (int)((float) Game.getInstance().getWidth() / WINDOW_WIDTH * WINDOW_HEIGHT);
                    WINDOW_WIDTH = Game.getInstance().getWidth();
                }else{                                              //If window height has changed
                    WINDOW_WIDTH = (int)((float) Game.getInstance().getHeight() / WINDOW_HEIGHT * WINDOW_WIDTH);
                    WINDOW_HEIGHT = Game.getInstance().getHeight();
                }
            }
        });
    }

    /**
     *
     * @return
     */
    public static Game getInstance() {
        if(game == null)
            game = new Game();
        return game;
    }

    /**
     *
     * @param dt
     */
    public void update(float dt){
        stateStack.update(dt);
    }

    /**
     *
     */
    public void render(){
        BufferStrategy bs = canvas.getBufferStrategy();
        if(bs == null){
            canvas.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        stateStack.render((Graphics2D) g);
        bs.show();
        g.dispose();
    }

    /**
     *
     */
    public void run() {
        // Unendlich-Schleife
        // Spiel soll durch Tasteneingabe beendet werden
        while(true) {
            long timestamp;
            long oldTimestamp;
            //State state;

            // Startzeit merken
            oldTimestamp = System.currentTimeMillis();

            // Aktuellsten State laden
            update(DELTA_TIME);

            // Zwischenzeit merken und prüfen,
            // ob Update gemäß FPS zu lange gedauert hat
            timestamp = System.currentTimeMillis();
            if (timestamp - oldTimestamp > MAXLOOPTIME) {
                System.out.println("Update dauert zu lange!");
            }

            // Aktuellsten State laden
            // Hat sich durch Update ggf geändert
            render();
            //Screen.getInstance().repaint();

            // Endzeit merken und prüfen,
            // ob noch zu viel Zeit über ist.
            timestamp = System.currentTimeMillis();
            if (timestamp - oldTimestamp <= MAXLOOPTIME) {
                try {
                    Thread.sleep(MAXLOOPTIME - (timestamp - oldTimestamp));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param arg
     */
    public static void main(String[] arg) {
        stateStack = StateStack.getInstance();
        stateStack.append(new StartState());
        new Thread(getInstance()).start();
    }
}