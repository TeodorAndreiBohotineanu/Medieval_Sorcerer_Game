package Main.Entity.Mob;

import Main.Entity.Entity;
import Main.Entity.Projectile.Projectile;
import Main.Entity.Projectile.WizardProjectile;
import Main.Game;
import Main.Graphics.AnimatedSprite;
import Main.Graphics.Screen;
import Main.Graphics.Sprite;
import Main.Graphics.Spritesheet;
import Main.Input.Keyboard;
import Main.Input.Mouse;

import java.util.List;

public class Player extends  Mob
{
    private AnimatedSprite down = new AnimatedSprite(Spritesheet.player_down, 32,32,3);
    private AnimatedSprite up = new AnimatedSprite(Spritesheet.player_up, 32,32,3);
    private AnimatedSprite left = new AnimatedSprite(Spritesheet.player_left, 32,32,3);
    private AnimatedSprite right = new AnimatedSprite(Spritesheet.player_right, 32,32,3);
    private AnimatedSprite animSprite = down;
    private Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    private int fireRate = 0;
    private boolean walking = false;
    public Player (Keyboard input)
    {
        this.input = input;
        sprite = Sprite.player_down;
        animSprite = down;
    }
    public Player ( int x, int y, Keyboard input)
    {
        this.x = x;
        this.y = y;
        this.input = input;
        fireRate = WizardProjectile.FIRE_RATE;
    }
    public void update ( )
    {
        double speed = 1.4;
        if (walking)
        animSprite.update( );
        if (fireRate > 0)
            fireRate--;
        double xa = 0, ya = 0;
        if (anim < 7500) anim++;
        else anim = 0;
        if (input.up)
        {
            animSprite = up;
            ya -= speed;
        }
        if (input.down)
        {
            animSprite = down;
            ya += speed;
        }
        if ( input.left )
        {
            animSprite = left;
            xa -= speed;
        }
        if ( input.right )
        {
            animSprite = right;
            xa += speed;
        }
        if (xa != 0 || ya != 0)
        {
            walking = true;
            move(xa, ya);
        }
        else
        {
            walking = false;
        }
        clear ( );
        updateShooting( );
    }

    private void clear( )
    {
        for ( int i = 0 ;i < level.getProjectiles( ).size( ) ; i ++)
        {
            Projectile p = level.getProjectiles( ).get(i);
            if (p.isRemoved( ))
                level.getProjectiles( ).remove( i );
        }
    }

    private void updateShooting( )
    {
        if (Mouse.getB() == 1 && fireRate <= 0)
        {
            double dx = Mouse.getX( ) - Game.getWindowWidth( ) /2 ;
            double dy = Mouse.getY( ) - Game.getWindowHeight( ) /2 ;
            double dir = Math.atan2(dy,dx);
            shoot( x, y, dir);
            fireRate = WizardProjectile.FIRE_RATE;
        }
    }

    public void render (Screen screen)
    {
        sprite = animSprite.getSprite( );
        screen.renderMob((int)(x - 16) ,(int) (y - 16) , sprite);
    }
}
