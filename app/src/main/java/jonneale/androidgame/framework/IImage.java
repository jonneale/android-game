package jonneale.androidgame.framework;

/**
 * Created by jonneale on 02/04/2015.
 */
public interface IImage {
    public int getWidth();
    public int getHeight();
    public IGraphics.ImageFormat getFormat();
    public void dispose();
}
