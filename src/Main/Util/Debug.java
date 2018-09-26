package Main.Util;

import Main.Graphics.Screen;

public class Debug {
    private Debug ( )
    {

    }
    public static void drawRect (Screen screen, int x, int y, int width, int height, int col, boolean fixed)
    {
        screen.drawRect(x ,y ,width ,height ,col, fixed);
    }
}
