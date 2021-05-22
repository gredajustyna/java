package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;
    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;

    private SpaceCatSubscene creditsSubScene;
    private SpaceCatSubscene helpSubScene;
    private SpaceCatSubscene scoreSubScene;
    private SpaceCatSubscene shipChooserScene;

    private SpaceCatSubscene sceneToHide;

    List<SpaceCatButton>menuButtons;
    List<ShipPicker> shipsList;
    private SHIP chosenShip;

    public ViewManager(){
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,WIDTH,HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createButtons();
        createBackground();
        createLogo();

    }
    private void showSubScene(SpaceCatSubscene subScene){
        if(sceneToHide !=null){
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void createSubScenes(){
        creditsSubScene = new SpaceCatSubscene();
        mainPane.getChildren().add(creditsSubScene);

        helpSubScene = new SpaceCatSubscene();
        mainPane.getChildren().add(helpSubScene);

        scoreSubScene = new SpaceCatSubscene();
        mainPane.getChildren().add(scoreSubScene);

        createShipChooserSubScene();
    }

    private void createShipChooserSubScene() {
        shipChooserScene = new SpaceCatSubscene();
        mainPane.getChildren().add(shipChooserScene);

        InfoLabel chooseShipLabel = new InfoLabel("choose your ship");
        chooseShipLabel.setLayoutX(110);
        chooseShipLabel.setLayoutY(25);
        shipChooserScene.getPane().getChildren().add(chooseShipLabel);
        shipChooserScene.getPane().getChildren().add(createShipsToChoose());
        shipChooserScene.getPane().getChildren().add(createButtonToStart());
    }

    private HBox createShipsToChoose(){
        HBox box = new HBox();
        box.setSpacing(20);
        shipsList = new ArrayList<>();
        for (SHIP ship : SHIP.values()){
            ShipPicker shipToPick = new ShipPicker(ship);
            shipsList.add(shipToPick);
            box.getChildren().add(shipToPick);
            shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    for(ShipPicker ship : shipsList){
                        ship.setIsCircleChosen(false);
                    }
                    shipToPick.setIsCircleChosen(true);
                    chosenShip = shipToPick.getShip();
                }
            });
        }
        box.setLayoutX(300-(118*2));
        box.setLayoutY(100);
        return box;
    }

    private SpaceCatButton createButtonToStart(){
        SpaceCatButton startButton = new SpaceCatButton("start");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(chosenShip!=null){
                    GameViewManager gameViewManager = new GameViewManager();
                    gameViewManager.createNewGame(mainStage,chosenShip);
                }
            }
        });
        return startButton;
    }

    public Stage getMainStage(){
        return mainStage;
    }

    private void addMenuButton(SpaceCatButton button){
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size()*100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createButtons(){
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }
    private void createStartButton(){
        SpaceCatButton startButton = new SpaceCatButton("start");
        addMenuButton(startButton);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(shipChooserScene);
            }
        });
    }
    private void createScoresButton(){
        SpaceCatButton scoresButton = new SpaceCatButton("score");
        addMenuButton(scoresButton);
        scoresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(scoreSubScene);
            }
        });
    }
    private void createHelpButton(){
        SpaceCatButton helpButton = new SpaceCatButton("help");
        addMenuButton(helpButton);
        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(helpSubScene);
            }
        });
    }
    private void createCreditsButton(){
        SpaceCatButton creditsButton = new SpaceCatButton("credits");
        addMenuButton(creditsButton);
        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(creditsSubScene);
            }
        });
    }
    private void createExitButton(){
        SpaceCatButton exitButton = new SpaceCatButton("exit");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });
    }


    private void createBackground(){
        Image backgroundImage = new Image("blue.png",256,256,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo(){
        ImageView logo = new ImageView("space_cats.png");
        logo.setLayoutX(320);
        logo.setLayoutY(50);
        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logo.setEffect(new DropShadow());
            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logo.setEffect(null);
            }
        });
        mainPane.getChildren().add(logo);
    }
}
