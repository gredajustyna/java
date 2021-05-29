package dev.justa.tilegame.states;

import dev.justa.tilegame.Game;
import dev.justa.tilegame.Handler;
import dev.justa.tilegame.entities.creatures.Player;
import dev.justa.tilegame.entities.statics.Tree;
import dev.justa.tilegame.gfx.Assets;
import dev.justa.tilegame.tiles.Tile;
import dev.justa.tilegame.worlds.World;

import java.awt.*;

public class GameState extends State{

    private World world;


    public GameState(Handler handler){
        super(handler);
        world = new World(handler,"res/worlds/world1.txt");
        handler.setWorld(world);

    }

    @Override
    public void update() {
        world.update();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}
