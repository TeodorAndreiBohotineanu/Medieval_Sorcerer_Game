package Main.Entity.Mob;

import Main.Graphics.AnimatedSprite;
import Main.Graphics.Screen;
import Main.Graphics.Spritesheet;

public class Chaser extends  Mob
{
    private boolean walking = false;

    private AnimatedSprite down = new AnimatedSprite(Spritesheet.chaser_down, 32,32,3);
    private AnimatedSprite up = new AnimatedSprite(Spritesheet.chaser_up, 32,32,3);
    private AnimatedSprite left = new AnimatedSprite(Spritesheet.chaser_left, 32,32,3);
    private AnimatedSprite right = new AnimatedSprite(Spritesheet.chaser_right, 32,32,3);
    private AnimatedSprite animSprite = down;
    private int xa = 0, ya = 0;
    public Chaser (int x, int y )
    {
        this.x = x * 16;
        this.y = y * 16;
        sprite = animSprite.getSprite( );
    }
    public void update ( )
    {
        move ( );
        if (walking) {
            animSprite.update( );
        }
        if (ya < 0)
        {
            animSprite = up;
            dir = Direction.UP;
        }
        if (ya > 0)
        {
            animSprite = down;
            dir = Direction.DOWN;
        }
        if ( xa < 0 )
        {
            animSprite = left;
            dir = Direction.LEFT;
        }
        if ( xa > 0 )
        {
            animSprite = right;
            dir = Direction.RIGHT;
        }
    }

    private void move( )
    {
        Player player = level.getClientPlayer( );
        xa = 0;
        ya = 0;
        if (x < player.getX( ) ) xa++;
        if (x > player.getX( ) ) xa--;
        if (y < player.getY( ) ) ya++;
        if (y > player.getY( ) ) ya--;
        if (xa != 0 || ya != 0)
        {
            walking = true;
            move(xa, ya);
        }
        else
        {
            walking = false;
        }
    }

    public void render (Screen screen )
    {
        sprite = animSprite.getSprite();
        screen.renderMob((int) x ,(int) y ,this);
    }
}
