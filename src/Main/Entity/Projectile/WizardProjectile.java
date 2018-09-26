package Main.Entity.Projectile;

import Main.Entity.Spawner.ParticleSpawner;
import Main.Entity.Spawner.Spawner;
import Main.Graphics.Screen;
import Main.Graphics.Sprite;

public class WizardProjectile extends Projectile {
    public static final int FIRE_RATE = 20;
    public WizardProjectile(double x, double y, double dir) {
        super(x, y, dir);
        range = 100;
        speed = 3;
        damage = 20;
        sprite = Sprite.projectile_wizard;
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }
    public void update ( )
    {
        if (level.tileCollision((int)(x + nx),(int)(y + ny), 11, 5,5))
        {
            level.add(new ParticleSpawner( (int) x , (int) y, 44,50, level));
            remove ( );
        }
            move( );
    }
    protected void move ( )
    { {
            x += nx;
            y += ny;
        }
        if (calculateDistance ( ) > range)
            remove( );
    }
    private double calculateDistance ( )
    {
        double dist;
        dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
        return dist;
    }
    public void render (Screen screen)
    {
        screen.renderProjectile((int) x - 10,(int) y - 5, this);
    }
}
