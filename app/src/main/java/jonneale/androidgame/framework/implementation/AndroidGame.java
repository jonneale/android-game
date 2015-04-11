package jonneale.androidgame.framework.implementation;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import jonneale.androidgame.framework.IAudio;
import jonneale.androidgame.framework.IFileIO;
import jonneale.androidgame.framework.IGame;
import jonneale.androidgame.framework.IGraphics;
import jonneale.androidgame.framework.IInput;
import jonneale.androidgame.framework.IScreen;

/**
 * Created by jonneale on 02/04/2015.
 */
public class AndroidGame extends Activity implements IGame {
    AndroidFastRenderView renderView;
    IGraphics graphics;
    IAudio audio;
    IInput input;
    IFileIO fileIO;
    IScreen screen;
    PowerManager.WakeLock wakeLock;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        int frameBufferWidth  = isPortrait ? 800 : 1280;
        int frameBufferHeight = isPortrait ? 1280: 800;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,frameBufferHeight, Bitmap.Config.RGB_565);

        float scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();

        float scaleY = (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();

        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics   = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO     = new AndroidFileIO(this);
        audio      = new AndroidAudioFile(this);
        input      = new AndroidInput(this, renderView, scaleX, scaleY);
        screen     = getInitScreen();
        setContentView(renderView);

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);

        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "MyGame");
    }

    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        screen.resume();
        renderView.resume();
    }

    @Override
    public void onPause(){
        super onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();

        if (isFinishing())
            screen.dispose();
    }

    @Override
    public IInput getInput(){
        return input;
    }

    @Override
    public IFileIO getFileIO(){
        return fileIO;
    }

    @Override
    public IGraphics getGraphics(){
        return graphics;
    }

    @Override
    public IAudio getAudio(){
        return audio;
    }

    @Override
    public void setScreen(IScreen screen){
        if (screen == null)
            throw new IllegalArgumentException("Screen is null? WTF?");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    public IScreen getCurrentScreen(){
        return screen;
    }
}
