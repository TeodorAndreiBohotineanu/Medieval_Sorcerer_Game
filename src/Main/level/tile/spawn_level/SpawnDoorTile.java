package Main.level.tile.spawn_level;

import Main.Graphics.Screen;
import Main.Graphics.Sprite;
import Main.level.tile.Tile;

public class SpawnDoorTile extends Tile
{
    public SpawnDoorTile(Sprite sprite)
    {
        super(sprite);
    }
    public void render (int x, int y, Screen screen)
    {
        screen.renderTile(x * 16,y * 16,this);
    }

    public boolean solid( )
    {
        return true;
    }
}
