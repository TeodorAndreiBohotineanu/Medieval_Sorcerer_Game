package Main.level.tile;

import Main.Graphics.Screen;
import Main.Graphics.Sprite;

public class WoodTile extends Tile
{

    public WoodTile(Sprite sprite) {
        super(sprite);
    }
    public void render (int x, int y, Screen screen)
    {
        screen.renderTile(x * 16,y * 16,this);
    }
}
