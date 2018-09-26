package Main.Entity.Projectile;

import Main.Entity.Entity;
import Main.Graphics.Sprite;

public abstract class Projectile extends Entity
{
    protected final double xOrigin, yOrigin;
    double angle;
    protected Sprite sprite;
    protected double x, y;
    protected double nx, ny;
    protected int speed, range, damage;
    public Projectile (double x, double y, double dir )
    {
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        this.x = x;
        this.y = y;
    }
    public Sprite getSprite ( )
    {
        return sprite;
    }
    public int getSpriteSize ( )
    {
        return sprite.SIZE;
    }
    protected void move ( )
    {

    }
}
