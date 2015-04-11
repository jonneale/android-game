package jonneale.androidgame.framework;

/**
 * Created by jonneale on 02/04/2015.
 */
public interface IAudio {
    public IMusic createMusic(String file);

    public ISound createSound(String file);
}
