package dev.justa.tilegame;


import dev.justa.tilegame.display.Display;
import dev.justa.tilegame.gfx.Assets;
import dev.justa.tilegame.gfx.GameCamera;
import dev.justa.tilegame.gfx.ImageLoader;
import dev.justa.tilegame.gfx.SpriteSheet;
import dev.justa.tilegame.input.KeyManager;
import dev.justa.tilegame.input.MouseManager;
import dev.justa.tilegame.states.GameState;
import dev.justa.tilegame.states.MenuState;
import dev.justa.tilegame.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{
    private Display display;
    private int width, height;
    public String title;
    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;

    //STATES
    public State gameState;
    public State menuState;

    //INPUT
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //CAMERA
    private GameCamera gameCamera;

    //HANDLER
    private Handler handler;

    public Game(String title, int width, int height){
        this.width=width;
        this.height=height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init() {
        display = new Display(title,width,height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler,0,0);

        gameState = new GameState(handler);
        menuState = new GameState(handler);
        State.setState(new MenuState(handler));
    }

    private void update(){
        keyManager.update();
        if(State.getState()!=null){
            State.getState().update();
        }
    }
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear the screen!
        g.clearRect(0,0,width,height);
        //Draw Here!
        if(State.getState()!=null){
            State.getState().render(g);
        }


        //End Drawing!
        bs.show();
        g.dispose();
    }

    public void run(){
        init();
        int fps = 60;//how many times a second we want update and render to run
        double timePerTick = 1000000000/fps;//maximum amount of time we have to run our update and render methods to achieve 60 fps
        double delta = 0;//when and when not to call tick and render
        long now;//current time of our computer
        long lastTime = System.nanoTime();//returns the current time of computer in nanoseconds
        long timer = 0;
        int ticks = 0;



        while (running){
            now = System.nanoTime();
            //now - lastTime returns amount of time since we last run the line of code below
            //adds to delta how much time do we have until we call update and render again
            delta += (now - lastTime)/timePerTick;
            //we add to timer the amount of time since these 4 lines have run
            timer += now - lastTime;
            lastTime = now;

            if(delta >=1){
                update();
                render();
                ticks++;
                delta--;
            }
            if (timer>=1000000000){
                System.out.println("TiX: "+ticks);
                ticks = 0;
                timer = 0;
            }

        }
        stop();
    }



    public synchronized void start(){
        if(running){
            return;
        }
        running=true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop(){
        if(!running){
            return;
        }
        running=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public KeyManager getKeyManager(){
        return keyManager;
    }

    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public MouseManager getMouseManager(){
        return mouseManager;
    }
}
