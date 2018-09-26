package Main.Entity;

import Main.Graphics.Screen;
import Main.Graphics.Sprite;
import Main.level.Level;

import java.util.Random;
import java.util.Scanner;

public class Entity
{
    public double x,y;
    public boolean removed = false;
    protected Level level;
    protected Sprite sprite;
    protected final Random random = new Random();
    public Entity ( )
    {

    }
    public Entity (int x, int y, Sprite sprite)
    {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }
    public void update ( )
    {

    }
    public void render (Screen screen)
    {
        if (sprite != null) screen.renderSprite((int) x, (int) y, sprite, true);
    }

    public double getX( ) {
        return x;
    }

    public double getY( ) {
        return y;
    }

    public void remove ( )
    {
        removed = true;
    }
    public boolean isRemoved ( )
    {
        return  removed;
    }
    public void init (Level level)
    {
        this.level = level;
    }
}
