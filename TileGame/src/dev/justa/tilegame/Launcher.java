package dev.justa.tilegame;

import dev.justa.tilegame.display.Display;

public class Launcher {
    public static void main(String[] args){
        Game game = new Game("Title!",640,360);
        game.start();
    }
}
