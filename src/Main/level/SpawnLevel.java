package Main.level;

import Main.Entity.Mob.Chaser;
import Main.Entity.Mob.Enemy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnLevel extends Level
{
    public SpawnLevel(String path)
    {
        super(path);
    }
    protected void loadLevel(String path)
    {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
           int w = width = image.getWidth( );
           int h = height = image.getHeight( );
           tiles = new int[w*h];
           image.getRGB(0,0,w,h, tiles,0,w);
        } catch (IOException e) {
            e.printStackTrace( );
        }
        add ( new Chaser(19,70));
      ///  add ( new Dummy(20,55));
         add ( new Enemy ( 22,60));
    }
    protected void generateLevel ( )
    {

            /// Black -> floor
            /// Blue -> wall
//            if (tiles[i] == 0xff000000)
//                tiles[i] = Tile.floor;
//            if (tiles[i] == 0xff0000FF)
//                tiles[i] = Tile.wall_1;
    }
}
