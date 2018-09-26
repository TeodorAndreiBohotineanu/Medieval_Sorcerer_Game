package Main.Entity.Spawner;

import Main.Entity.Entity;
import Main.Entity.Particle.Particle;
import Main.level.Level;

import java.util.ArrayList;
import java.util.List;

public class Spawner extends Entity
{
    public enum Type
    {
        PARTICLE, MOB ;
    }
    private Type type;
    public Spawner(int x, int y, Type type, int amount, Level level)
    {
        init (level);
        this.x = x;
        this.y = y;
        this.type = type;

    }
}
