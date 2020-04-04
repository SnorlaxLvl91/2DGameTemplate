package main;

import javax.sound.sampled.Clip;
import java.util.Map;

import static main.Util.getClip;

public class Sound {

    public static Map<String, Clip> clips = Map.of(
            "music", getClip("res\\sounds\\music.wav"),
            "jump", getClip("res\\sounds\\jump.wav"),
            "kill", getClip("res\\sounds\\kill.wav"),
            "empty-block", getClip("res\\sounds\\empty_block.wav"),
            "powerup-reveal", getClip("res\\sounds\\powerup_reveal.wav"),
            "pickup", getClip("res\\sounds\\pickup.wav")
    );
}
