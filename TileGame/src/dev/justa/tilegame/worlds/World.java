package dev.justa.tilegame.worlds;

import dev.justa.tilegame.Game;
import dev.justa.tilegame.Handler;
import dev.justa.tilegame.entities.Entity;
import dev.justa.tilegame.entities.EntityManager;
import dev.justa.tilegame.entities.creatures.Player;
import dev.justa.tilegame.entities.statics.Tree;
import dev.justa.tilegame.tiles.Tile;
import dev.justa.tilegame.utils.Utils;

import java.awt.*;
import java.net.URL;

public class World {
    private int width;
    private int height;
    private int spawnX, spawnY;
    private int[][] tiles;
    private Handler handler;

    private EntityManager entityManager;

    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler,new Player(handler,100,100));
        entityManager.addEntity(new Tree(handler,100,250));
        loadWorld(path);
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }
    public void update(){
        entityManager.update();
    }
    public void render(Graphics g){
        int xStart = (int)Math.max(0,handler.getGameCamera().getxOffset()/Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset()+handler.getWidth())/Tile.TILE_WIDTH +1);
        int yStart = (int)Math.max(0,handler.getGameCamera().getyOffset()/Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset()+handler.getHeight())/Tile.TILE_HEIGHT +1);

        for(int y=yStart;y<yEnd;y++){
            for(int x=xStart;x<xEnd;x++){
                getTile(x,y).render(g,(int)(x*Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
                        (int)(y*Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));

            }
        }
        entityManager.render(g);
    }
    public Tile getTile(int x, int y){
        if (x<0 || y<0 || x>= width || y>= height){
            return Tile.grassTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if(t==null){
            return Tile.sandTile;
        }
        return t;
    }

    public void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0;y<height; y++){
            for (int x = 0; x<width; x++){
                tiles[x][y] = Utils.parseInt(tokens[(x+y*width)+4]);
            }
        }
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
