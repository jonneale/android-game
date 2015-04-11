package jonneale.androidgame.framework;

/**
 * Created by jonneale on 02/04/2015.
 */
public interface IGame {

    public IAudio getAudio();

    public IInput getInput();

    public IFileIO getFileIO();

    public IGraphics getGraphics();

    public void setScreen(IScreen screen);

    public IScreen getCurrentScreen();

    public IScreen getInitScreen();

}
