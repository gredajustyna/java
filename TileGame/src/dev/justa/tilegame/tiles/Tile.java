package dev.justa.tilegame.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    //STATIC STUFF
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile sandTile = new SandTile(1);
    public static Tile rockTile = new RockTile(2);



    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;


    //CLASS
    protected BufferedImage texture;
    protected final int ID;

    public Tile(BufferedImage texture, int ID){
        this.texture = texture;
        this.ID = ID;
        tiles[ID] = this;
    }

    public void update(){

    }
    public void render(Graphics g, int x, int y){
        g.drawImage(texture,x,y,TILE_WIDTH,TILE_HEIGHT,null);
    }

    public int getID(){
        return ID;
    }

    public boolean isSolid(){
        return false;
    }
}
