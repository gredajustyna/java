package dev.justa.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;

    public static BufferedImage grass,rock,sand, tree;
    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage[] btn_start;
    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        player_down = new BufferedImage[4];
        player_up = new BufferedImage[4];
        player_left = new BufferedImage[4];
        player_right = new BufferedImage[4];
        btn_start = new BufferedImage[2];

        btn_start[0] = sheet.crop(WIDTH,HEIGHT*5,WIDTH,HEIGHT);
        btn_start[1] = sheet.crop(WIDTH*2,HEIGHT*5,WIDTH,HEIGHT);

        player_down[0] = sheet.crop(0,HEIGHT,WIDTH,HEIGHT);
        player_down[1] = sheet.crop(0,HEIGHT*2,WIDTH,HEIGHT);
        player_down[2] = sheet.crop(0,HEIGHT*3,WIDTH,HEIGHT);
        player_down[3] = sheet.crop(0,HEIGHT*4,WIDTH,HEIGHT);

        player_up[0] = sheet.crop(WIDTH,HEIGHT,WIDTH,HEIGHT);
        player_up[1] = sheet.crop(WIDTH,HEIGHT*2,WIDTH,HEIGHT);
        player_up[2] = sheet.crop(WIDTH,HEIGHT*3,WIDTH,HEIGHT);
        player_up[3] = sheet.crop(WIDTH,HEIGHT*4,WIDTH,HEIGHT);

        player_right[0] = sheet.crop(WIDTH*2,HEIGHT*1,WIDTH,HEIGHT);
        player_right[1] = sheet.crop(WIDTH*2,HEIGHT*2,WIDTH,HEIGHT);
        player_right[2] = sheet.crop(WIDTH*2,HEIGHT*3,WIDTH,HEIGHT);
        player_right[3] = sheet.crop(WIDTH*2,HEIGHT*4,WIDTH,HEIGHT);

        player_left[0] = sheet.crop(WIDTH*3,HEIGHT*1,WIDTH,HEIGHT);
        player_left[1] = sheet.crop(WIDTH*3,HEIGHT*2,WIDTH,HEIGHT);
        player_left[2] = sheet.crop(WIDTH*3,HEIGHT*3,WIDTH,HEIGHT);
        player_left[3] = sheet.crop(WIDTH*3,HEIGHT*4,WIDTH,HEIGHT);

        grass = sheet.crop(0,0,WIDTH,HEIGHT);
        rock = sheet.crop(WIDTH,0,WIDTH,HEIGHT);
        sand = sheet.crop(WIDTH*2,0,WIDTH,HEIGHT);
        tree = sheet.crop(0,HEIGHT*5,WIDTH,HEIGHT);

    }
}
