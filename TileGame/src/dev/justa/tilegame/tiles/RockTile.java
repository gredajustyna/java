package dev.justa.tilegame.tiles;

import dev.justa.tilegame.gfx.Assets;

import java.awt.image.BufferedImage;

public class RockTile extends Tile{
    public RockTile(int ID) {
        super(Assets.rock, ID);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
