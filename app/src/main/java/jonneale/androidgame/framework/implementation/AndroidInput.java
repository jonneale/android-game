package jonneale.androidgame.framework.implementation;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.os.Build.VERSION;

import jonneale.androidgame.framework.IInput;

/**
 * Created by jonneale on 11/04/2015.
 */
public class AndroidInput implements IInput {

    SingleTouchHandler touchHandler;

    public AndroidInput(Context context, View view, float scaleX, float scaleY){
        if (Integer.parseInt(VERSION.SDK) < 5){
            touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
        }
        else {
            touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
        }
    }

    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    @Override
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }
}
