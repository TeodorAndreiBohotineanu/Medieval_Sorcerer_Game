package Main.level.tile;

import Main.Graphics.Screen;
import Main.Graphics.Sprite;

public class Wall_1_Tile extends Tile {
    public Wall_1_Tile(Sprite sprite) {
        super(sprite);
    }
    public void render (int x, int y, Screen screen)
    {
        screen.renderTile(x * 16,y * 16,this);
    }
    public boolean solid ( )
    {
        return true;
    }
}
