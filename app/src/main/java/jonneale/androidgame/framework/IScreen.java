package jonneale.androidgame.framework;

/**
 * Created by jonneale on 02/04/2015.
 */
public interface IScreen {

    public void update(float deltaTime);

    public void paint(float deltaTime);

    public void pause();

    public void resume();

    public void dispose();

    public void backButton();

}
