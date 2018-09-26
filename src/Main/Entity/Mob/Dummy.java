package Main.Entity.Mob;

import Main.Graphics.AnimatedSprite;
import Main.Graphics.Screen;
import Main.Graphics.Sprite;
import Main.Graphics.Spritesheet;

public class Dummy extends  Mob
{
    private AnimatedSprite down = new AnimatedSprite(Spritesheet.dummy_down, 32,32,3);
    private AnimatedSprite up = new AnimatedSprite(Spritesheet.dummy_up, 32,32,3);
    private AnimatedSprite left = new AnimatedSprite(Spritesheet.dummy_left, 32,32,3);
    private AnimatedSprite right = new AnimatedSprite(Spritesheet.dummy_right, 32,32,3);
    private AnimatedSprite animSprite = down;
    private int time = 0;
    private int xa = 0;
    private int ya = 0;
    private boolean walking = false;
    public Dummy (int x, int y)
    {
        this.x = x * 16;
        this.y = y * 16;
        sprite = Sprite.dummy;
    }
    public void update ( )
    {
        time ++;
        if ( time % 120 == 0)
        {
            xa = random.nextInt(3) - 1;
            ya = random.nextInt(3) - 1;
            if (random.nextInt(3) == 0)
            {
                xa = 0;
                ya = 0;
            }
        }
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
    public void render (Screen screen)
    {
        screen.renderMob((int) x , (int) y,sprite);
        sprite = animSprite.getSprite( );
    }
}
