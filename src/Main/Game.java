package Main;

import Main.Entity.Mob.Player;
import Main.GameStateManager.GameState;
import Main.GameStateManager.GameStateManager;
import Main.Graphics.Font;
import Main.Graphics.Screen;
import Main.Input.Keyboard;
import Main.Input.Mouse;
import Main.level.Level;
import Main.level.TileCoordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements  Runnable
{
    private static int width = 640;
    private static int height = 360;
    private static int scale = 2;
    private Thread thread; /// creez un subproces
    private JFrame frame;
    private Keyboard key;
    private Level level;
    private Graphics2D g;
    public static int health = 250;
    private Player player;
    private boolean running = false;
    private Screen screen;
    private GameStateManager gsm;
    private Font font;
    private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    /// Raster -> lista de pixeli care fac imaginea
    public Game ( )
    {
        Dimension size = new Dimension( width*scale,height*scale);
        setPreferredSize(size);
        requestFocus();
        screen = new Screen(width, height);
        frame = new JFrame( );
        key = new Keyboard( );
        level = Level.spawn;
        font = new Font( );
        TileCoordinate playerSpawn = new TileCoordinate(19,70);
        player = new Player(playerSpawn.x( ),playerSpawn.y( ),key);
        level.add(player);
        addKeyListener(key);
        Mouse mouse = new Mouse( );
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }
    public static int getWindowWidth ( )
    {
        return width * scale;
    }
    public static int getWindowHeight ( )
    {
        return height * scale;
    }
    public synchronized void start ( )
    {
        running = true;
        thread = new Thread( this, "Display" );
        thread.start( );
    }
    public synchronized void stop ( )
    {
        running = false;
        try {
            thread.join( );
        } catch (InterruptedException e) {
            e.printStackTrace( );
        }
    }

    public void run( )
    {
        long lastTime = System.nanoTime( );
        long timer = System.currentTimeMillis( );
        final double ns = 1000000000.0 / 60.0; /// nano secunde
        double delta = 0;
        int frames = 0;
        int updates = 0;
        while (running)
        {
            long now = System.nanoTime( );
            delta += (now - lastTime) / ns;
            lastTime = now;
            while ( delta >= 1)
            {
                update( );
                updates ++;
                delta--;
            }
            render( );
            frames ++;
            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                frame.setTitle("The Medieval Sorcerer " + " | " + frames + " FPS");
                updates = 0;
                frames = 0;
            }
        }
        stop ( );
    }
    public void update ( )
    {
            key.update( );
            level.update( );
            gsm.update( );
    }
    private void draw ( )
    {
        gsm.draw(g);
    }
    public void render ( )
    {
        BufferStrategy bs = getBufferStrategy( );
        if (bs == null ) {
            createBufferStrategy(3); /// prin triple buffering se creste eficienta la viteza, daca era
            return; /// doar double buffering se putea pune doar cate una pe rand;
        }
        if (GameStateManager.currentState == 1)
        {
            screen.clear();
            double xScroll = player.getX() - screen.width / 2;
            double yScroll = player.getY() - screen.height / 2;
            level.render((int) xScroll, (int) yScroll, screen);
            /// font.render("Hello",screen);
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = screen.pixels[i];
            }
            Graphics g = bs.getDrawGraphics(); /// face legatura dintre buffer si graphics
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            //   g.fillRect(Mouse.getX( ) - 32,Mouse.getY( ) - 32,64,64);
            g.setColor(Color.GRAY);
            g.fillRect(5, 5, 250, 50);
            g.setColor(Color.GREEN);
            g.fillRect(5, 5, health, 50);
            g.setColor(Color.WHITE);
            g.drawRect(5, 5, 250, 50);
            g.dispose();
            bs.show();
        }
    }


    public static void main(String[] args) {
        Game game = new Game( );
        game.frame.setResizable(false);
        game.frame.setTitle("The Medieval Sorcerer");
        game.frame.add(game); /// adaug o componenta game
        game.frame.pack( ); /// face frame-ul sa aiba acelasi size cu componenta
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null); /// o sa puna window-ul in mijlocul ecranului automat;
        game.frame.setVisible(true);
        game.start( );
    }
}
