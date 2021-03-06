package model;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.File;

public class SpaceCatSubscene extends SubScene {
    private final static String FONT_PATH = "src/model/resources/kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = "model/resources/blue_panel.png";

    private boolean isHidden=true;

    public SpaceCatSubscene() {
        super(new AnchorPane(), 600,400);
        prefHeight(400);
        prefWidth(600);

        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE,600,400,false,true),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        AnchorPane root2 = (AnchorPane)this.getRoot();
        root2.setBackground(new Background(image));

        setLayoutX(1024);
        setLayoutY(200);

    }
    public void moveSubScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if(isHidden){
            transition.setToX(-676);
            isHidden=false;
        }else {
            transition.setToX(0);
            isHidden=true;
        }

        transition.play();
    }

    public AnchorPane getPane(){
        return  (AnchorPane)this.getRoot();
    }
}
