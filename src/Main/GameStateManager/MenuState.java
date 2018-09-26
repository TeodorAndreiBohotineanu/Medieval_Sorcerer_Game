package Main.GameStateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState
{
    private Background bg;
    private int currentChoice = 0;
    private String[] options = { " Start", "Help", "Quit"};
    private Color titleColor;
    private Font titleFont;
    private Font font;
    public MenuState(GameStateManager gsm)
    {
        super( );
        this.gsm = gsm;
        bg.setVector(0,0);
        try {
            bg = new Background("/Background/braun_hogenber.jpg");
            titleColor = new Color ( 128,0,0);
            titleFont = new Font ("Cambria",Font.PLAIN,46);
            font = new Font ("Cambria",Font.PLAIN,50);
        }catch (Exception e)
        {
            e.printStackTrace( );
        }
    }
    public void render ( )
    {

    }
    public void update ( )
    {
        bg.update( );
    }
    public void draw (Graphics2D g)
    {
        bg.draw(g);
        /// Draw Title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("The Medieval Sorcerer", 430,132);
        /// Draw Menu Options
        g.setFont(font);
        for (int i = 0; i < options.length ; i ++)
        {
            if ( i == currentChoice)
                g.setColor(Color.BLACK);
            else
                g.setColor(Color.RED);
            g.drawString(options[i],105 + i * 500, 664);
        }
    }
    public void select ( )
    {
        if (currentChoice == 0)
            gsm.setState(GameStateManager.GAMESTATE);
        if (currentChoice == 1)
        {
            /// Help menu
        }
        if (currentChoice == 2)
            System.exit(0);
    }
    public void keyPressed ( int k )
    {
        if ( k == KeyEvent.VK_ENTER)
            select ( );
        if ( k == KeyEvent.VK_RIGHT)
        {
            currentChoice ++;
            if ( currentChoice == options.length)
                currentChoice = 0;
        }
        if ( k == KeyEvent.VK_LEFT)
        {
            currentChoice--;
            if (currentChoice == -1)
                currentChoice = options.length - 1 ;
        }
    }
    public void keyReleased ( int k )
    {

    }
}
