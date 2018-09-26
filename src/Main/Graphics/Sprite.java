package Main.Graphics;

public class Sprite
{
    public final int SIZE;
    private int x,y;
    public int[] pixels;
    protected Spritesheet sheet;
    private int width, height;

    /// Spawn  Level

    public static Sprite spawn_grass = new Sprite (16,1,0,Spritesheet.spawn_level);
    public static Sprite spawn_grass_2 = new Sprite (16,1,2,Spritesheet.spawn_level);
    public static Sprite spawn_door = new Sprite (16,0,1,Spritesheet.spawn_level);
    public static Sprite spawn_rock = new Sprite (16,2,0,Spritesheet.spawn_level);
    public static Sprite spawn_crevace = new Sprite (16,3,0,Spritesheet.spawn_level);
    public static Sprite spawn_water = new Sprite (16,0,0,Spritesheet.spawn_level);
    public static Sprite spawn_stone = new Sprite (16,1,1,Spritesheet.spawn_level);
    public static Sprite spawn_tree = new Sprite (16,2,1,Spritesheet.spawn_level);
    public static Sprite spawn_bones = new Sprite (16,3,1,Spritesheet.spawn_level);
    public static Sprite spawn_dead_tree = new Sprite (16,0,2,Spritesheet.spawn_level);
    public static Sprite spawn_graveyard = new Sprite (16,3,2,Spritesheet.spawn_level);
    public static Sprite spawn_sign = new Sprite (16,2,2,Spritesheet.spawn_level);
    public static Sprite spawn_wall = new Sprite (16,0,3,Spritesheet.spawn_level);
    public static Sprite spawn_board = new Sprite (16,3,3,Spritesheet.spawn_level);
    public static Sprite spawn_ship = new Sprite (16,1,3,Spritesheet.spawn_level);
    public static Sprite spawn_whirl = new Sprite (16,2,3,Spritesheet.spawn_level);

    /// Projectiles
    public static Sprite projectile_wizard = new Sprite (16,0,0,Spritesheet.projectile_wizard);


    /// Altele
    public static Sprite wood = new Sprite(16,0,0,Spritesheet.tiles);
    public static Sprite wall_1 = new Sprite(16,4,0,Spritesheet.tiles);
    public static Sprite floor = new Sprite(16,3,0,Spritesheet.tiles);
    public static Sprite voidSprite = new Sprite (16, 0);

    /// Player
    public static Sprite player_down= new Sprite(32,3,4,Spritesheet.tiles);
    public static Sprite player_up= new Sprite(32,3,5,Spritesheet.tiles);
    public static Sprite player_left= new Sprite(32,3,7,Spritesheet.tiles);
    public static Sprite player_right= new Sprite(32,3,6,Spritesheet.tiles);
    public static Sprite player_down_1= new Sprite(32,4,4,Spritesheet.tiles);
    public static Sprite player_down_2= new Sprite(32,5,4,Spritesheet.tiles);
    public static Sprite player_up_1= new Sprite(32,4,5,Spritesheet.tiles);
    public static Sprite player_up_2= new Sprite(32,5,5,Spritesheet.tiles);
    public static Sprite player_left_1= new Sprite(32,4,7,Spritesheet.tiles);
    public static Sprite player_left_2= new Sprite(32,5,7,Spritesheet.tiles);
    public static Sprite player_right_1= new Sprite(32,4,6,Spritesheet.tiles);
    public static Sprite player_right_2= new Sprite(32,5,6,Spritesheet.tiles);

    /// NPCs / Mobs
    public static Sprite dummy = new Sprite(32,0,0,Spritesheet.dummy_down);
    public static Sprite chaser = new Sprite(32,0,0,Spritesheet.chaser_down);

    /// Particles
    public static Sprite particle_normal = new Sprite(3,0xffAAAAAA);
    protected Sprite ( Spritesheet sheet, int width, int height)
    {
        if (width == height)
            SIZE = width;
        else
        SIZE = -1;
        this.width = width;
        this.height = height;
        this.sheet = sheet;
    }
    public Sprite ( int size, int x, int y, Spritesheet sheet)
    {
        SIZE=size;
        pixels = new int[SIZE * SIZE];
        this.x = x *size;
        this.y = y * size;
        this.width = size;
        this.height = size;
        this.sheet = sheet;
        load( );
    }
    public Sprite (int width, int height, int color)
    {
        SIZE = -1;
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        setColor(color);
    }
    public Sprite ( int size, int color )
    {
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[SIZE * SIZE];
        setColor( color );
    }

    public Sprite(int[] pixels, int width, int height)
    {
        if ( width == height)
            SIZE = width;
        else
            SIZE = -1;
        this.width = width;
        this.height = height;
        this.pixels = new int[pixels.length];
        for ( int i = 0 ; i < pixels.length ; i ++)
        {
            this.pixels[i] = pixels[i];
        }
    }

    public static Sprite[] split(Spritesheet sheet)
    {
        int amount = (sheet.getHeight( ) * sheet.getWidth( )) / (sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT);
        Sprite[] sprites = new Sprite[amount];
        int current = 0;
        int[ ] pixels = new int[sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT];
        for (int yp = 0 ; yp < sheet.getHeight( ) / sheet.SPRITE_HEIGHT ; yp ++)
        {
            for (int xp = 0 ; xp < sheet.getWidth( ) / sheet.SPRITE_WIDTH ; xp ++)
            {
                for ( int y = 0 ; y < sheet.SPRITE_HEIGHT ; y ++)
                {
                    for (int x = 0 ; x < sheet.SPRITE_WIDTH ; x ++)
                    {
                        int xo = x + xp * sheet.SPRITE_WIDTH;
                        int yo = y + yp * sheet.SPRITE_HEIGHT;
                        pixels[x + y * sheet.SPRITE_WIDTH] = sheet.getPixels( )[xo + yo *sheet.getWidth( )];
                    }
                }
                sprites[current ++] = new Sprite (pixels, sheet.SPRITE_WIDTH, sheet.SPRITE_HEIGHT);
            }
        }
        return sprites; /// o sa extraga dintr-un sprite mare in altele mai mici, individual letters la font
    }

    private void setColor(int color )
    {
        for (int i = 0; i < width * height ; i ++)
        {
            pixels[i] = color;
        }
    }
    public int getWidth ( )
    {
        return width;
    }
    public int getHeight ( )
    {
        return  height;
    }
    private void load ( )
    {
        for ( int  y = 0 ; y < height ; y ++)
        {
            for (int x = 0 ; x < width ; x++)
                pixels[x+y*width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SPRITE_WIDTH];
        } /// extrage un spritesheet din toata pagina

    }
}
