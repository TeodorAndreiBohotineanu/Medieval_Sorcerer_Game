package Main.level.tile;

import Main.Graphics.Screen;
import Main.Graphics.Sprite;
import Main.level.tile.spawn_level.*;


public class Tile
{
    public int x, y;
    public Sprite sprite;
    public static Tile wood = new WoodTile(Sprite.wood); /// static, exista numai un singur wood tile, nu mai multe
    public static Tile floor = new FloorTile(Sprite.floor);
    public static Tile wall_1 = new Wall_1_Tile(Sprite.wall_1);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);
    /// Spawn
    public static Tile spawn_grass_tile = new SpawnGrassTile(Sprite.spawn_grass);
    public static Tile spawn_door_tile = new SpawnDoorTile(Sprite.spawn_door);
    public static Tile spawn_water_tile = new SpawnWaterTile(Sprite.spawn_water);
    public static Tile spawn_stone_tile = new SpawnStoneTile(Sprite.spawn_stone);
    public static Tile spawn_grass_2_tile = new SpawnGrass2Tile(Sprite.spawn_grass_2);
    public static Tile spawn_graveyard_tile = new SpawnGraveTile(Sprite.spawn_graveyard);
    public static Tile spawn_sign_tile = new SpawnSignTile(Sprite.spawn_sign);
    public static Tile spawn_dead_tree_tile = new SpawnDeadTreeTile(Sprite.spawn_dead_tree);
    public static Tile spawn_tree_tile = new SpawnTreeTile(Sprite.spawn_tree);
    public static Tile spawn_rock_tile = new SpawnRockTile(Sprite.spawn_rock);
    public static Tile spawn_ship_tile = new SpawnShipTile(Sprite.spawn_ship);
    public static Tile spawn_whirl_tile = new SpawnWhirlTile(Sprite.spawn_whirl);
    public static Tile spawn_wall_tile = new SpawnWallTile(Sprite.spawn_wall);
    public static Tile spawn_board_tile = new SpawnBoardTile(Sprite.spawn_board);
    public static Tile spawn_bones_tile = new SpawnBonesTile(Sprite.spawn_bones);
    public static Tile spawn_crevace_tile = new SpawnCrevaceTile(Sprite.spawn_crevace);
    public static final int col_spawn_grass_1 = 0xff0CFF20;
    public static final int col_spawn_grass_2 = 0xffAAFF9B;
    public static final int col_spawn_door = 0xff000000;
    public static final int col_spawn_water = 0xff14FFF7;
    public static final int col_spawn_stone = 0xff0C0020;
    public static final int col_spawn_grave = 0xffF600FF;
    public static final int col_spawn_sign = 0xffFFB0AD;
    public static final int col_spawn_dead_tree = 0xffFF5154;
    public static final int col_spawn_tree = 0xffBFFF3F;
    public static final int col_spawn_rock = 0xff404040;
    public static final int col_spawn_ship = 0xff3A99FF;
    public static final int col_spawn_whirl = 0xff47CAFF;
    public static final int col_spawn_wall = 0xff7A7CFF;
    public static final int col_spawn_board = 0xffFF8CD6;
    public static final int col_spawn_bones = 0xffFFFCBA;
    public static final int col_spawn_crevace = 0xffFF0000;
    public Tile (Sprite sprite)
    {
        this.sprite = sprite;
    }
    public void render (int x, int y, Screen screen)
    {

    }
    public boolean solid ( )
    {
        return false;
    }
}
