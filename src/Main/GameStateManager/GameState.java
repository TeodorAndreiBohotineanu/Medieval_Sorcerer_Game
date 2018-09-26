package Main.GameStateManager;

import java.awt.*;

public abstract class GameState
{
    protected GameStateManager gsm;

    public abstract void render( );


    public abstract void update( );

    public abstract void draw(Graphics2D g);

    public abstract void keyPressed(int k);

    public abstract void keyReleased(int k);

}
