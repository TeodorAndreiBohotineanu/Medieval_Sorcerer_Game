package Main.Graphics;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spritesheet
{
    private String path;
    public final int SIZE;
    public int[] pixels;
    private Sprite[] sprites;
    public final int SPRITE_WIDTH, SPRITE_HEIGHT;
    private int width, height;
    public static Spritesheet tiles = new Spritesheet("/textures/sheets/spritesheet.png",256);
    public static Spritesheet spawn_level = new Spritesheet("/textures/sheets/SpawnLevelTiles.png",64);
    public static Spritesheet projectile_wizard = new Spritesheet("/textures/sheets/projectiles/wizard.png",48);
    /// Player
    public static Spritesheet player = new Spritesheet("/textures/sheets/player.png",128,96);
    public static Spritesheet player_down = new Spritesheet(player, 0,0,1,3,32);
    public static Spritesheet player_up = new Spritesheet(player, 1,0,1,3,32);
    public static Spritesheet player_left = new Spritesheet(player, 3,0,1,3,32);
    public static Spritesheet player_right = new Spritesheet(player, 2,0,1,3,32);
    /// NPCs / Mobs
    public static Spritesheet dummy = new Spritesheet("/textures/sheets/dummy.png",128,96);
    public static Spritesheet dummy_down = new Spritesheet(dummy, 0,0,1,3,32);
    public static Spritesheet dummy_up = new Spritesheet(dummy, 1,0,1,3,32);
    public static Spritesheet dummy_left = new Spritesheet(dummy, 3,0,1,3,32);
    public static Spritesheet dummy_right = new Spritesheet(dummy, 2,0,1,3,32);

    public static Spritesheet chaser = new Spritesheet("/textures/sheets/chaser.png",128,96);
    public static Spritesheet chaser_down = new Spritesheet(chaser,0,0,1,3,32);
    public static Spritesheet chaser_up = new Spritesheet(chaser,1,0,1,3,32);
    public static Spritesheet chaser_left = new Spritesheet(chaser,3,0,1,3,32);
    public static Spritesheet chaser_right = new Spritesheet(chaser,2,0,1,3,32);

    public static Spritesheet enemy = new Spritesheet("/textures/sheets/enemy.png",128,96);
    public static Spritesheet enemy_down = new Spritesheet(enemy,0,0,1,3,32);
    public static Spritesheet enemy_up = new Spritesheet(enemy,1,0,1,3,32);
    public static Spritesheet enemy_left = new Spritesheet(enemy,3,0,1,3,32);
    public static Spritesheet enemy_right = new Spritesheet(enemy,2,0,1,3,32);

    public Spritesheet (String path, int size)
    {
        this.path = path;
        SIZE = size;
        SPRITE_WIDTH = size;
        SPRITE_HEIGHT = size;
        pixels = new int [SIZE * SIZE];
        load( );
    }
    public Spritesheet ( Spritesheet sheet, int x, int y, int width, int height, int spriteSize) {
        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w = width * spriteSize;
        int h = height * spriteSize;
        if (width == height)
            SIZE = width;
        else
            SIZE = -1;
        SPRITE_WIDTH = w;
        SPRITE_HEIGHT = h;
        pixels = new int[w * h];
        for (int y0 = 0; y0 < h; y0++) {
            int yp = yy + y0;
            for (int x0 = 0; x0 < w; x0++) {
                int xp = xx + x0;
                pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.SPRITE_WIDTH];
            }
        }
        int frame = 0;
        sprites = new Sprite[width * height];
        for (int ya = 0; ya < height; ya++)
        {
            for (int xa = 0; xa < width; xa++)
            {
                int[] spritePixels = new int[spriteSize * spriteSize];
                for (int y0 = 0; y0 < spriteSize; y0++)
                {
                    for (int x0 = 0; x0 < spriteSize; x0++)
                    {
                        spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * SPRITE_WIDTH];
                    }
                }
                Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
                sprites[frame++] = sprite;
            }
        }
    }
    public Spritesheet ( String path, int width, int height)
    {
        this.path = path;
        SIZE = -1;
        SPRITE_WIDTH = width;
        SPRITE_HEIGHT = height;
        pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];
        load ( );
    }
    public Sprite[] getSprites ( )
    {
        return sprites;
    }
    public int getWidth( )
    {
        return width;
    }
    public int getHeight ( )
    {
        return height;
    }
    private void load ( )
    {
        try {
            BufferedImage image = ImageIO.read(Spritesheet.class.getResource(path)); /// all this loads the spritesheet
             width = image.getWidth( );
             height = image.getHeight( );
             pixels = new int[width * height];
            image.getRGB(0,0,width,height,pixels,0,width);
        } catch (IOException e)
        {
            e.printStackTrace( );
        }
    }

    public int[] getPixels( )
    {
        return pixels;
    }
}
