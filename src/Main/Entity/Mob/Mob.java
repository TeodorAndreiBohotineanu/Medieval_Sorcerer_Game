package Main.Entity.Mob;

import Main.Entity.Entity;
import Main.Entity.Particle.Particle;
import Main.Entity.Projectile.Projectile;
import Main.Entity.Projectile.WizardProjectile;
import Main.Graphics.Sprite;
import Main.level.Level;

import java.util.ArrayList;
import java.util.List;

public abstract class Mob extends Entity
{
     protected Sprite sprite;
     protected boolean moving = false;
    protected enum Direction
    {
        UP, DOWN, LEFT, RIGHT
    }
    protected Direction dir;
     protected List<Projectile> projectiles = new ArrayList<Projectile>( );

     public void move (double xa, double ya )
     {
         if (xa !=0 && ya !=0)
         {
             move (xa, 0);
             move (0,ya);
             return;
         }
         if (xa > 0) dir = Direction.RIGHT; /// right
         if (xa < 0) dir = Direction.LEFT; /// left
         if (ya > 0) dir = Direction.DOWN; /// down
         if (ya < 0) dir = Direction.UP; /// up
         while (xa != 0)
         {
             if (Math.abs(xa) > 1 )
             {
                 if (!collision(abs (xa), ya ))
                     this.x += abs(xa);
                 xa -= abs(xa);
             }
             else
             {
                 if (!collision(abs (xa), ya ))
                     this.x += xa;
                 xa = 0;
             }
         }
         while (ya != 0)
         {
             if (Math.abs(ya) > 1 )
             {
                 if (!collision(xa, abs(ya) ))
                     this.y += abs(ya);
                 ya -= abs(ya);
             }
             else
             {
                 if (!collision(xa, abs(ya)))
                     this.y += ya;
                 ya = 0;
             }
         }
     }

    private int abs(double value)
    {
        if (value < 0 ) return -1;
        else return 1;
    }

    public void update ( )
     {

     }
     protected void shoot ( double x, double y, double dir)
     {
         Projectile p = new WizardProjectile( x ,y , dir);
         level.add(p);
     }
    public Sprite getSprite( )
    {
        return sprite;
    }
     private boolean collision (double xa, double ya)
     {
         boolean solid = false;
         for (int c = 0 ; c < 4 ; c ++)
         {
             double xt = ((x + xa) - c % 2 * 16 - 5)/16;
             double yt = ((y + ya) - c / 2 * 16 + 16)/16;
             int ix = (int) Math.ceil(xt);
             int iy = (int) Math.ceil(yt);
             if (c % 2 == 0) ix = (int) Math.floor(xt);
             if (c / 2 == 0) iy = (int) Math.floor(yt);
             if (level.getTile(ix, iy).solid( ))
                 solid = true;
         }
         return  solid;
     }
     public void render ( )
     {

     }
}
