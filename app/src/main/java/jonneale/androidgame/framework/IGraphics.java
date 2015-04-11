package jonneale.androidgame.framework;

import android.graphics.Paint;

/**
 * Created by jonneale on 02/04/2015.
 */
public interface IGraphics {

    public static enum ImageFormat {
        ARGB8888, ARGB4444, RGB565
    }

    public IImage newImage(String fileName, ImageFormat format);

    public void clearScreen(int color);

    public void drawLine(int x, int y, int x2, int y2, int color);

    public void drawRect(int x, int y, int width, int height, int color);

    public void drawImage(IImage image, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight);

    public void drawImage(IImage image, int x, int y);

    void drawString(String text, int x, int y, Paint paint);

    public int getWidth();

    public int getHeight();

    public void drawARGB(int i, int j, int k, int l);

}
