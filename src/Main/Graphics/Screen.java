package Main.Graphics;

import Main.Entity.Mob.Mob;
import Main.Entity.Projectile.Projectile;
import Main.level.tile.Tile;

import java.util.Random;

public class Screen
{
    public int width;
    public int height;
    public int[] pixels;
    public final int MAP_SIZE = 64;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
    public int xOffset, yOffset;
    private Random random = new Random ( );
    public Screen ( int width, int height )
    {
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
        for ( int i = 0 ; i < MAP_SIZE * MAP_SIZE ; i ++)
        {
            tiles[i]=random.nextInt(0xffffff);
        }
    }
    public void clear ( )
    {
        for (int i = 0 ; i < pixels.length ; i ++)
        {
            pixels[i] = 0;
        }
    }
    public void renderSprite ( int xp, int yp, Sprite sprite, boolean fixed) {
        if (fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        for (int y = 0; y < sprite.getHeight( ); y++)
        {
            int ya = y + yp;
            for (int x = 0; x < sprite.getWidth( ); x++)
            {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue; /// daca sare din bounds...
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];

            }
        }
    }
    public void renderTile (int xp, int yp, Tile tile )
    {
        xp -=xOffset;
        yp -=yOffset;
        for (int y = 0 ;y <tile.sprite.SIZE ; y ++)
        {
            int ya = yp + y;
            for (int x = 0 ;x <tile.sprite.SIZE ; x ++)
            {
                int xa = xp + x;
                if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >=height) break;
                if (xa <0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y *tile.sprite.SIZE];
            }
        }
    }
    public void renderProjectile (int xp, int yp, Projectile p )
    {
        xp -=xOffset;
        yp -=yOffset;
        for (int y = 0 ;y <p.getSpriteSize( ) ; y ++)
        {
            int ya = y + yp;
            for (int x = 0 ;x <p.getSpriteSize( ) ; x ++)
            {
                int xa = x + xp;
                if (xa < -p.getSpriteSize( ) || xa >= width || ya < 0 || ya >=height) break;
                if (xa < 0) xa = 0;
                int col = p.getSprite( ).pixels[x + y *p.getSprite( ).SIZE];
                if (col != 0xffff00ff)
                pixels[xa + ya * width] = col;
            }
        }
    }
    public void renderMob(int xp, int yp, Sprite sprite)
    {
        xp -=xOffset;
        yp -=yOffset;
        for (int y = 0 ;y < 32 ; y ++)
        {
            int ya = yp + y;
            for (int x = 0 ;x < 32 ; x ++)
            {
                int xa = xp + x;
                if (xa < -32 || xa >= width || ya < 0 || ya >=height) break;
                if (xa <0) xa = 0;
                int col = sprite.pixels [ x + y * 32];
                if (col != 0xffFF00FF)
                pixels[xa + ya * width] = col;
            }
        }
    }  public void renderMob(double xp, double yp, Mob mob)
    {
        xp -=xOffset;
        yp -=yOffset;
        for (int y = 0 ;y < 32 ; y ++)
        {
            double ya = yp + y;
            for (int x = 0 ;x < 32 ; x ++)
            {
                double xa = xp + x;
                if (xa < -32 || xa >= width || ya < 0 || ya >=height) break;
                if (xa <0) xa = 0;
                int col = mob.getSprite( ).pixels [ x + y * 32];
                if (col != 0xffFF00FF)
                pixels[(int)xa + (int)ya * width] = col;
            }
        }
    }
    public void setOffset ( int xOffset, int yOffset)
    {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void drawRect(int xp, int yp, int width, int height, int color, boolean fixed)
    {
        if (fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        for (int x = xp ; x < xp + width ; x ++)
        {
            if ( x >= this.width || x < 0 || yp >= this.height) continue;
            pixels[x + yp * this.width] = color;
            if (yp + height >= this.height) continue;
            if  (yp + height > 0)
            pixels[x + (yp + height) * this.width] = color;
        }
        for (int y = yp ; y <= yp + height ; y ++)
        {
            if ( xp >= this.width || y < 0 || y >= this.height) continue;
            if ( xp + width >= this.width) continue;
                pixels[xp + y * this.width] = color;
            if ( xp + width > 0)
            pixels[xp + width + y * this.width] = color;
        }

    }
}
