package main;

import javax.sound.sampled.Clip;
import java.util.HashMap;
import java.util.Map;

import static main.Util.getClip;

/**
 * Holds required sound clips
 */
public class Sound {

    public static Map<String, Clip> clips = new HashMap<>();
    static{
        clips.put("intro-music", getClip("res\\sounds\\intro.wav"));
        clips.put("field-music", getClip("res\\sounds\\field_music.wav"));
        clips.put("heal", getClip("res\\sounds\\heal.wav"));
        clips.put("battle-music", getClip("res\\sounds\\battle_music.wav"));
    }
}
