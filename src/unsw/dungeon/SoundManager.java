package unsw.dungeon;
import java.util.HashMap;

import javafx.scene.media.*;

public class SoundManager {

    HashMap<String, AudioClip> soundEffects;

    public SoundManager(){
        soundEffects = new HashMap<String, AudioClip>();
    }

    public void addSoundEffect(String key, AudioClip sound){
        soundEffects.put(key, sound);
    }

    public void playSoundEffect(String key){
        soundEffects.get(key).play();
    }

    public void changeVolume(double newVolume){
        for(String key: soundEffects.keySet()){
            soundEffects.get(key).setVolume(newVolume);
        }
    }

}