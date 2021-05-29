package dev.justa.tilegame.entities.creatures;

import dev.justa.tilegame.Game;
import dev.justa.tilegame.Handler;
import dev.justa.tilegame.gfx.Animation;
import dev.justa.tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature{
    //Animation
    private Animation animDown;
    private Animation animUp;
    private Animation animLeft;
    private Animation animRight;


    public Player(Handler handler, float x, float y) {
        super(handler,x, y,Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        //Animations
        animDown = new Animation(200,Assets.player_down);
        animUp = new Animation(200,Assets.player_up);
        animLeft = new Animation(200,Assets.player_left);
        animRight = new Animation(200,Assets.player_right);
    }

    @Override
    public void update() {
        animDown.tick();
        animRight.tick();
        animLeft.tick();
        animUp.tick();
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;
        if(handler.getKeyManager().up){
            yMove = -speed;
        }
        if(handler.getKeyManager().down){
            yMove = speed;
        }
        if(handler.getKeyManager().left){
            xMove = -speed;
        }
        if(handler.getKeyManager().right){
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(),(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width,height,null);
    }

    private BufferedImage getCurrentAnimationFrame(){
        if(xMove<0){
            return animLeft.getCurrentFrame();
        }else if (xMove>0){
            return animRight.getCurrentFrame();
        }else if (yMove<0){
            return animUp.getCurrentFrame();
        }else {
            return animDown.getCurrentFrame();
        }
    }
}
