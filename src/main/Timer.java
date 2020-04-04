package main;

import java.util.Map;
import java.util.TimerTask;

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
     * @param field         name of the given field
     * @param startValue    value at the beginning of tweening
     * @param endValue      value at the end of tweening
     */
    public void tween(float seconds, String field, int startValue, int endValue){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                float sum = 0;
                int value = startValue;
                int tweenCounter = (int) (seconds * FPS);
                final float increment = (float)(endValue - startValue) / tweenCounter;

                while(tweenCounter-- > 0) {
                    long timestamp;
                    long oldTimestamp;

                    // Startzeit merken
                    oldTimestamp = System.currentTimeMillis();

                    // Inkrementieren
                    sum += increment;
                    if (Math.abs(sum) >= 1) {
                        value += (int) sum;
                        sum = sum - (int) sum;
                    }
                    // Zwischenzeit merken und prüfen,
                    // ob Update gemäß FPS zu lange gedauert hat
                    timestamp = System.currentTimeMillis();
                    if (timestamp - oldTimestamp > MAXLOOPTIME) {
                        System.out.println("Tween dauert zu lange!");
                    }

                    // Aktuellsten State laden
                    // Hat sich durch Update ggf geändert
                    notifyObservers(Map.of(field, value));

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
        };
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(task, 0);
    }
}