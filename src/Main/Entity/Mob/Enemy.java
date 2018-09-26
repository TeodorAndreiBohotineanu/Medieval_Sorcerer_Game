package Main.Entity.Mob;

import Main.Entity.Entity;
import Main.Entity.Projectile.WizardProjectile;
import Main.Graphics.AnimatedSprite;
import Main.Graphics.Screen;
import Main.Graphics.Sprite;
import Main.Graphics.Spritesheet;
import Main.Util.Debug;

import java.util.List;

public class Enemy extends  Mob
{
    private boolean walking = false;

    private AnimatedSprite down = new AnimatedSprite(Spritesheet.enemy_down, 32,32,3);
    private AnimatedSprite up = new AnimatedSprite(Spritesheet.enemy_up, 32,32,3);
    private AnimatedSprite left = new AnimatedSprite(Spritesheet.enemy_left, 32,32,3);
    private AnimatedSprite right = new AnimatedSprite(Spritesheet.enemy_right, 32,32,3);
    private AnimatedSprite animSprite = down;
    private int FireRate;
    private double speed = 0.8;
    private double xa = 0, ya = 0;
    public Enemy (int x, int y )
    {
        this.x = x * 16;
        this.y = y * 16;
        sprite = animSprite.getSprite( );
        FireRate = WizardProjectile.FIRE_RATE + 15;
    }
    public void update ( )
    {
            if (FireRate > 0)
                FireRate--;
            move( );
            if (walking) {
                animSprite.update();
            }
            if (ya < 0) {
                animSprite = up;
                dir = Direction.UP;
            }
            if (ya > 0) {
                animSprite = down;
                dir = Direction.DOWN;
            }
            if (xa < 0) {
                animSprite = left;
                dir = Direction.LEFT;
            }
            if (xa > 0) {
                animSprite = right;
                dir = Direction.RIGHT;
            }
        }

    private void move( )
    {
        List<Player> players = level.getPlayers(this, 90);
        if (players.size( ) > 0)
        {
            Player player = players.get(0);
            xa = 0;
            ya = 0;
            if (x < player.getX()) xa+= speed;
            if (x > player.getX()) xa-= speed;
            if (y < player.getY()) ya+= speed;
            if (y > player.getY()) ya-= speed;
        }
        if (xa != 0 || ya != 0)
        {
            walking = true;
            move(xa, ya);
            Player p = level.getClientPlayer( );
            double dx = p.getX( ) - x;
            double dy = p.getY( ) - y;
            double dir2 = Math.atan2(dy,dx);
            if ( FireRate <= 0 )
            {
                shoot(x + 2 , y -16, dir2);
                FireRate = WizardProjectile.FIRE_RATE + 15;
            }
        }
        else
        {
            walking = false;
        }
    }

    public void render (Screen screen )
    {
        sprite = animSprite.getSprite();
        screen.renderMob((int) (x - 16) , (int) (y - 30) ,this);
       // Debug.drawRect(screen,40,40,100,40,0xffFF00FF,false);

    }
}
