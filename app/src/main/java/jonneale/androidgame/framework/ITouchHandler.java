package jonneale.androidgame.framework;

import android.view.MotionEvent;
import android.view.View;

import java.util.List;

/**
 * Created by jonneale on 11/04/2015.
 */
public interface ITouchHandler extends View.OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<IInput.TouchEvent> getTouchEvents();
}