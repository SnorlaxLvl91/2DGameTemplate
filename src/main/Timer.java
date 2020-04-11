package main;

import java.util.Map;
import java.util.TimerTask;
import java.util.function.Consumer;

import static main.Constants.FPS;
import static main.Constants.MAXLOOPTIME;


/**
 * Provides Methods to tween Objects
 */
public class Timer extends Observable {

    private java.util.Timer timer;

    public Timer(){
        timer = new java.util.Timer();
    }

    /**
     * Transitions a fields value to its target value in a given amount of seconds,
     * while notifying the classes Observer
     *
     * @param seconds       time to tween between start value and end value
     * @param fields        names of the given fields
     * @param startValues   values at the beginning of tweening
     * @param endValues     values at the end of tweening
     */
    public void tween(float delay, float seconds, String[] fields, int[][] startValues, int[][] endValues){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for(int x = 0; x < startValues.length; x++){
                    int tweenCounter = (int) (seconds * FPS);
                    float sum[] = new float[fields.length];
                    int[] values = new int[fields.length];
                    float[] increment = new float[fields.length];

                    for(int y = 0; y < fields.length; y++) {
                        values[y] = startValues[x][y];
                        increment[y] = (float) (endValues[x][y] - startValues[x][y]) / tweenCounter;
                    }

                    while (tweenCounter-- > 0) {
                        long timestamp;
                        long oldTimestamp;

                        // Startzeit merken
                        oldTimestamp = System.currentTimeMillis();

                        // Inkrementieren
                        for(int y = 0; y < fields.length; y++) {
                            sum[y] += increment[y];
                            if (Math.abs(sum[y]) >= 1) {
                                values[y] += (int) sum[y];
                                sum[y] = sum[y] - (int) sum[y];
                            }
                        }
                        // Zwischenzeit merken und prüfen,
                        // ob Update gemäß FPS zu lange gedauert hat
                        timestamp = System.currentTimeMillis();
                        if (timestamp - oldTimestamp > MAXLOOPTIME) {
                            System.out.println("Tween dauert zu lange!");
                        }

                        // Aktuellsten State laden
                        // Hat sich durch Update ggf geändert
                        notifyObservers(Map.of(fields, values));

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
                    notifyObservers(Map.of(new String[]{"finished"}, new boolean[]{false}));
                }
            }
        };
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(task, (long) delay * 1000);
    }

    /**
     *
     * @param interval
     * @param delay
     * @param seconds
     * @param fields
     * @param startValues
     * @param endValues
     */
    public void tweenEvery(float interval, float delay, float seconds, String[] fields, int[][] startValues, int[][] endValues){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                tween(delay, seconds, fields, startValues, endValues);
            }
        };
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(task, (long) delay * 1000, (long) interval * 1000);
    }
}