package jonneale.androidgame.framework.implementation;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

import jonneale.androidgame.framework.IAudio;
import jonneale.androidgame.framework.IMusic;
import jonneale.androidgame.framework.ISound;

/**
 * Created by jonneale on 11/04/2015.
 */
public class AndroidAudio implements IAudio {
    AssetManager assets;
    SoundPool soundPool;

    public AndroidAudio(Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);

    }
    @Override
    public IMusic createMusic(String filename) {
        try{
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            return new AndroidMusic(assetDescriptor);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Music load error - filename " + filename);
        }
    }

    @Override
    public ISound createSound(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            int soundId = soundPool.load(assetDescriptor, 0);
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '" + filename + "'");
        }
    }
}
