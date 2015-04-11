package jonneale.androidgame.framework.implementation;

import android.media.SoundPool;

import jonneale.androidgame.framework.ISound;

/**
 * Created by jonneale on 11/04/2015.
 */
public class AndroidSound implements ISound {

    int soundId;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundId){
        this.soundPool = soundPool;
        this.soundId = soundId;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }
}
