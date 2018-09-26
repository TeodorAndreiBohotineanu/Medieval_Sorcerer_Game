package Main.Graphics;


public class Font
{
    private static Spritesheet font = new Spritesheet("/fonts/macondo.png", 16);
    private static Sprite[] characters = Sprite.split(font);
    private static String charIndex = "ABCDEFGHIJKLM" + //
            "NOPQRSTUVWXYZ" + //
            "abcdefghijklm" + //
            "nopqrstuvwxyz" + //
            ",^_`~!#$%&'*" + //
            "0123456789:;?";
    public Font ( )
    {

    }
    public void render (String text, Screen screen)
    {
        int xOffset = 0;
        int x = 50;
        int y = 50;
        for (int i = 0 ; i < text.length( ) ; i ++ )
        {
            int yOffset = 0;
            char currentChar = text.charAt(i);
            int index = charIndex.indexOf(currentChar);
            if ( index  == - 1) continue;
            screen.renderSprite(x + i * 16,y + yOffset,characters[index],true);
        }
    }
}
