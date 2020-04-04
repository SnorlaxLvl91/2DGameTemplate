package main;

import java.util.Map;

/**
 *
 */
public class Animation {

    private int[] frames;
    private float interval;
    private float timer;
    private int currentFrame;

    /**
     *
     * @param params
     */
    public Animation(Map<String, Object> params) {
        frames = (int[]) params.get("frames");
        interval = (float) params.get("interval");
        timer = 0;
        currentFrame = 0;
    }

    /**
     *
     * @param dt
     */
    public void update(float dt) {
        // no need to update if animation is only one frame
        if (frames.length > 1) {
            timer = timer + dt;
            if (timer > interval) {
                timer = timer % interval;
                currentFrame = Math.max(0, (currentFrame + 1) % frames.length);
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