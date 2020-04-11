package main;

import java.util.Map;

/**
 *
 */
public class Animation {

    private String texture;
    private int[] frames;
    private float interval;
    private float timer;
    private int currentFrame;
    private int timesPlayed;
    private boolean looping;

    /**
     *
     * @param params
     */
    public Animation(Map<String, Object> params) {
        texture = (String) params.get("texture");
        frames = (int[]) params.get("frames");
        interval = (float) params.get("interval");

        looping = params.containsKey("looping") ? (boolean) params.get("looping") : true;
        timer = 0;
        currentFrame = 0;
        timesPlayed = 0;
    }

    public void refresh(){
        timer = 0;
        currentFrame = 0;
        timesPlayed = 0;
    }

    /**
     *
     * @param dt
     */
    public void update(float dt) {

        if(!looping && timesPlayed > 0) {
            return;
        }
        // no need to update if animation is only one frame
        if (frames.length > 1) {
            timer = timer + dt;
            if (timer > interval) {
                timer = timer % interval;
                currentFrame = Math.max(0, (currentFrame + 1) % frames.length);

                if(currentFrame == 0){
                    timesPlayed++;
                }
            }
        }
    }

    /**
     *
     * @return
     */
    public int getCurrentFrame() {
        return frames[currentFrame];
    }
}