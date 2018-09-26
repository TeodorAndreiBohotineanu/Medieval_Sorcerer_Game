package Main.GameStateManager;

import Main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background
{
    private BufferedImage image;
    private double x;
    private double dx;
    private double y;
    private double dy;
    public Background (String path)
    {
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        }catch ( Exception e)
        {
            e.printStackTrace( );
        }
    }
    public void setVector ( double dx, double dy )
    {
        this.dx = dx;
        this.dy = dy;
    }
    public void setPosition (double x, double y)
    {
        this.x = x % Game.getWindowWidth( );
        this.y = y % Game.getWindowHeight( );
    }
    public void draw (Graphics2D g)
    {
        g.drawImage(image, (int) x, (int) y, null);
        if ( x < 0 )
            g.drawImage(image,(int) x + Game.getWindowWidth( ), (int) y, null);
        if (x > 0 )
            g.drawImage(image,(int) x - Game.getWindowWidth( ), (int) y, null);
    }

    public void update( )
    {
        x += dx;
        y += dy;
    }
}
