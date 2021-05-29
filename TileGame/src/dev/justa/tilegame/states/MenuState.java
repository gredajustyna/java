package dev.justa.tilegame.states;

import dev.justa.tilegame.Game;
import dev.justa.tilegame.Handler;
import dev.justa.tilegame.gfx.Assets;
import dev.justa.tilegame.ui.ClickListener;
import dev.justa.tilegame.ui.UIImageButton;
import dev.justa.tilegame.ui.UIManager;

import java.awt.*;

public class MenuState extends State{
    private UIManager uiManager;
    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.btn_start, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void update() {
        uiManager.update();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
