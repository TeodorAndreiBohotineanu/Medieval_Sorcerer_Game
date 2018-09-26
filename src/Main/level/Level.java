package Main.level;

import Main.Entity.Entity;
import Main.Entity.Mob.Player;
import Main.Entity.Particle.Particle;
import Main.Entity.Projectile.Projectile;
import Main.Graphics.Screen;
import Main.level.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Level
{
    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    private List<Entity>entities = new ArrayList<Entity>( );
    public List<Projectile> projectiles = new ArrayList<Projectile>( );
    public List<Particle> particles = new ArrayList<Particle>( );
    private List<Player> players = new ArrayList<Player>( );
    public static Level spawn = new SpawnLevel("/levels/spawn.png");
    public Level (int width, int height )
    {
        this.width = width;
        this.height = height;
        tilesInt = new int [width * height];
        generateLevel ( );
    }
    public Level (String path)
    {
        loadLevel (path);
        generateLevel( );
    }

    protected void generateLevel( )
    {

    }

    protected void loadLevel(String path)
    {

    }
    public void update ( )
    {
        for (int i = 0 ; i < entities.size( ) ; i ++)
        entities.get(i).update( );
        for (int i = 0 ; i < projectiles.size( ) ; i ++)
            projectiles.get(i).update( );
        for (int i = 0 ; i < particles.size( ) ; i ++)
            particles.get(i).update( );
        for ( int i = 0 ; i < players.size( ) ; i ++)
            players.get(i).update( );
        remove( );
    }
    private void remove ( )
    {
        for (int i = 0 ; i < entities.size( ) ; i ++)
            if(entities.get(i).isRemoved( )) entities.remove(i);
        for (int i = 0 ; i < projectiles.size( ) ; i ++)
            if(projectiles.get(i).isRemoved( )) projectiles.remove(i);
        for (int i = 0 ; i < particles.size( ) ; i ++)
            if(particles.get(i).isRemoved( )) particles.remove(i);
        for (int i = 0 ; i < players.size( ) ; i ++)
            if(players.get(i).isRemoved( )) players.remove(i);

    }
    public List<Projectile> getProjectiles( )
    {
        return projectiles;
    }
    private void time ( )
    {

    }
    public List<Entity> getEntities(Entity e, int radius)
    {
        List<Entity> result = new ArrayList<Entity>( );
        int ex = (int) e.getX( );
        int ey = (int)  e.getY( );
        for (int i = 0 ; i < entities.size( ) ; i ++)
        {
            Entity entity = entities.get(i);
            int x = (int) entity.getX( );
            int y = (int) entity.getY( );
            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);
            double distance = Math.sqrt((dx * dx) + (dy * dy));
            if (distance <= radius)
            {
                result.add(entity);
            }
        }
        return result;
    }
    public List<Player> getPlayers ( Entity e, int radius)
    {
        int ex = (int) e.getX( );
        int ey = (int) e.getY( );
        List<Player> result = new ArrayList<Player>( );
        for (int i = 0 ; i < players.size( ) ; i ++)
        {
            Player player = players.get(i);
            int x = (int) player.getX( );
            int y = (int) player.getY( );
            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);
            double distance = Math.sqrt((dx * dx) + (dy * dy));
            if (distance <= radius)
            {
                result.add(player);
            }
           }
        return result;
    }
    public boolean tileCollision (int x, int y, int size, int xOffset, int yOffset)
    {
        boolean solid = false;
        for (int c = 0 ; c < 4 ; c ++)
        {
            int xt = (x - c % 2 * size + xOffset) / 16;
            int yt = (y - c / 2 * size + yOffset) / 16;
            if (getTile(xt, yt).solid( ))
                solid = true;
        }
        return  solid;
    }
    public void render (int xScroll, int yScroll, Screen screen)
    {
        screen.setOffset( xScroll, yScroll );
        int x0 = (xScroll - 16 )/ 16;
        int x1 = (xScroll + screen.width + 16) / 16;
        int y0 = (yScroll - 16 ) / 16;
        int y1 = (yScroll + screen.height + 16) / 16; /// regiunea ferestrei, sa afiseze doar alea care se vad
        for (int y = y0; y < y1 ; y ++)
        {
            for (int x = x0 ; x < x1 ; x ++)
            {
                getTile(x,y).render(x,y,screen);
            }
        }
        for (int i = 0 ; i < entities.size( ) ; i ++)
            entities.get(i).render(screen);
        for (int i = 0 ; i < projectiles.size( ) ; i ++)
            projectiles.get(i).render(screen);
        for (int i = 0 ; i < particles.size( ) ; i ++)
            particles.get(i).render(screen);
        for (int i = 0; i < players.size( ); i ++)
        {
            players.get(i).render(screen);
        }
    }
    public void add (Entity e)
    {
        e.init(this);
        if (e instanceof Particle)
        {
            particles.add((Particle)e);
        }
        else if (e instanceof Projectile )
        {
            projectiles.add((Projectile)e);
        }
        else if (e instanceof Player)
        {
            players.add((Player) e);
        }
        else
            {
                entities.add(e);
            }
    }
    public List<Player> getPlayers ( )
    {
        return players;
    }
    public Player getPlayerAt ( int index)
    {
        return players.get(index);
    }
    public Player getClientPlayer ( )
    {
        return players.get(0);
    }
    public Tile getTile (int x, int y)
    {
        if (x < 0 || y < 0 ||  x>= width || y>=height) return Tile.voidTile;
        if (tiles[x+y*width] == Tile.col_spawn_board) return Tile.spawn_board_tile;
        if (tiles[x+y*width] == Tile.col_spawn_bones) return Tile.spawn_bones_tile;
        if (tiles[x+y*width] == Tile.col_spawn_crevace) return Tile.spawn_crevace_tile;
        if (tiles[x+y*width] == Tile.col_spawn_dead_tree) return Tile.spawn_dead_tree_tile;
        if (tiles[x+y*width] == Tile.col_spawn_door) return Tile.spawn_door_tile;
        if (tiles[x+y*width] == Tile.col_spawn_grass_1) return Tile.spawn_grass_tile;
        if (tiles[x+y*width] == Tile.col_spawn_grass_2) return Tile.spawn_grass_2_tile;
        if (tiles[x+y*width] == Tile.col_spawn_grave) return Tile.spawn_graveyard_tile;
        if (tiles[x+y*width] == Tile.col_spawn_rock) return Tile.spawn_rock_tile;
        if (tiles[x+y*width] == Tile.col_spawn_stone) return Tile.spawn_stone_tile;
        if (tiles[x+y*width] == Tile.col_spawn_ship) return Tile.spawn_ship_tile;
        if (tiles[x+y*width] == Tile.col_spawn_water) return Tile.spawn_water_tile;
        if (tiles[x+y*width] == Tile.col_spawn_whirl) return Tile.spawn_whirl_tile;
        if (tiles[x+y*width] == Tile.col_spawn_wall) return Tile.spawn_wall_tile;
        if (tiles[x+y*width] == Tile.col_spawn_sign) return Tile.spawn_sign_tile;
        if (tiles[x+y*width] == Tile.col_spawn_tree) return Tile.spawn_tree_tile;
        return  Tile.voidTile;
    }
}
