package jonneale.androidgame.framework.implementation;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

import jonneale.androidgame.framework.IMusic;

/**
 * Created by jonneale on 11/04/2015.
 */
public class AndroidMusic implements IMusic,MediaPlayer.OnCompletionListener, MediaPlayer.OnSeekCompleteListener,MediaPlayer.OnPreparedListener,MediaPlayer.OnVideoSizeChangedListener {

    MediaPlayer mediaPlayer;
    boolean isPrepared = false;

    public AndroidMusic(AssetFileDescriptor assetDescriptor){
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
                                      assetDescriptor.getStartOffset(),
                                      assetDescriptor.getLength());
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
        }
        catch (Exception e) {
            throw new RuntimeException("Couldn't load music");
        }
    }

    @Override
    public void dispose() {
        if (isPlaying()){
            this.mediaPlayer.stop();
        }
        this.mediaPlayer.release();
    }

    @Override
    public void play() {
        if (isPlaying()) return;

        try{
            synchronized (this) {
                if (!isPrepared) mediaPlayer.prepare();
                mediaPlayer.start();

            }
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        if (isPlaying()) mediaPlayer.stop();

        synchronized (this){
            isPrepared = false;
        }
    }

    @Override
    public void pause() {
        if (isPlaying()){
            this.mediaPlayer.pause();
        }
    }

    @Override
    public void setLooping(boolean looping) {
        mediaPlayer.setLooping(looping);
    }

    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume, volume);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean isStopped() {
        return !isPrepared;
    }

    @Override
    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }


    @Override
    public void seekBegin() {
        mediaPlayer.seekTo(0);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        synchronized (this) {
            isPrepared = false;
        }

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        synchronized (this) {
            isPrepared = true;
        }
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

    }
}
