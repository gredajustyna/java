package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;


public class MainController {
    @FXML
    private Button feedButton;
    @FXML
    private Button snackButton;
    @FXML
    private Button washButton;
    @FXML
    private Button playButton;
    @FXML
    private Button sleepButton;
    @FXML
    private Button petButton;
    @FXML
    private Button trainButton;
    @FXML
    private Button competitionButton;
    @FXML
    private Pane feedActionPane;
    @FXML
    private Pane trainActionPane;
    @FXML
    private ProgressBar hungerBar;
    @FXML
    private ProgressBar hygieneBar;
    @FXML
    private ProgressBar energyBar;
    @FXML
    private ProgressBar moodBar;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label pointsLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private Label catNameLabel;
    @FXML
    private Slider wetFoodSlider;
    @FXML
    private Slider dryFoodSlider;
    @FXML
    private Button feedActionButton;
    @FXML
    private ChoiceBox trainingBox;
    @FXML
    private Button trainActionButton;
    @FXML
    private ProgressIndicator disIndicator;
    @FXML
    private ProgressIndicator obIndicator;
    @FXML
    private ProgressIndicator intIndicator;
    @FXML
    private Hyperlink customLink;
    @FXML
    private TextArea actionPane;
    @FXML
    private Pane buttonPane;
    @FXML
    private Pane trainingPane;
    @FXML
    private Label goldMedalLabel;
    @FXML
    private Label silverMedalLabel;
    @FXML
    private Label bronzeMedalLabel;
    @FXML
    private ImageView catPic;
    Cat cat = new Cat();
    private Double hunger = Double.valueOf(cat.getHunger())/100;
    private Double hygiene = Double.valueOf(cat.getHygiene())/100;
    private Double energy = Double.valueOf(cat.getEnergy())/100;
    private Double mood = Double.valueOf(cat.getMood())/100;
    private Double discipline = Double.valueOf(cat.getDiscipline())/100;
    private Double obedience = Double.valueOf(cat.getObedience())/100;
    private Double intelligence = Double.valueOf(cat.getIntelligence())/100;
    private int money = cat.getMoney();
    private int level = setLevel();
    private int points = cat.getPoints();
    private int gold = cat.getGold();
    private int silver = cat.getSilver();
    private int bronze = cat.getBronze();
    private int place;

    public void setEverything(){
        moneyLabel.setText(Integer.toString(money));
        pointsLabel.setText(Integer.toString(points));
        levelLabel.setText(Integer.toString(level));
        catNameLabel.setText(cat.getName());
        hungerBar.setProgress(hunger);
        hygieneBar.setProgress(hygiene);
        energyBar.setProgress(energy);
        moodBar.setProgress(mood);
        disIndicator.setProgress(discipline);
        obIndicator.setProgress(obedience);
        intIndicator.setProgress(intelligence);
        goldMedalLabel.setText(Integer.toString(gold));
        silverMedalLabel.setText(Integer.toString(silver));
        bronzeMedalLabel.setText(Integer.toString(bronze));
    }
    public void alterDB(){
        Connection con = DatabaseHelper.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "Update cat set money=?, level=?, points=?, hunger=?, hygiene=?, energy=?, mood=?, gold=?, silver=?, bronze=?, obedience=?, discipline=?, intelligence=? where name = 'kitek'";
            ps = con.prepareStatement(sql);
            ps.setInt(1, money);
            ps.setInt(2, level);
            ps.setInt(3, points);
            ps.setInt(4, (int)Math.round(hunger*100));
            ps.setInt(5, Integer.parseInt(String.valueOf((int)Math.round(hygiene*100))));
            ps.setInt(6, Integer.parseInt(String.valueOf((int)Math.round(energy*100))));
            ps.setInt(7, Integer.parseInt(String.valueOf((int)Math.round(mood*100))));
            ps.setInt(8, gold);
            ps.setInt(9, silver);
            ps.setInt(10, bronze);
            ps.setInt(11, Integer.parseInt(String.valueOf((int)Math.round(obedience*100))));
            ps.setInt(12, Integer.parseInt(String.valueOf((int)Math.round(discipline*100))));
            ps.setInt(13, Integer.parseInt(String.valueOf((int)Math.round(intelligence*100))));
            ps.execute();
        } catch (
                SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                //ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public void petButtonOnAction(){
        pet();
        refresh();
    }
    public void setSnackButton(){
        snack();
        refresh();
    }
    public void setWashButton(){
        wash();
        refresh();
    }
    public void setPlayButton(){
        play();
        refresh();
    }
    public void setSleepButton(){
        sleep();
        refresh();
    }
    public void setFeedButton(){
        feedActionPane.setVisible(true);
        buttonPane.setVisible(false);
        dryFoodSlider.setMin(0);
        dryFoodSlider.setMax(5);
        dryFoodSlider.setShowTickLabels(true);
        dryFoodSlider.setShowTickMarks(true);
        dryFoodSlider.setMinorTickCount(1);
        dryFoodSlider.setSnapToTicks(true);
        dryFoodSlider.setMajorTickUnit(2);
        wetFoodSlider.setMin(0);
        wetFoodSlider.setMax(5);
        wetFoodSlider.setShowTickLabels(true);
        wetFoodSlider.setShowTickMarks(true);
        wetFoodSlider.setMinorTickCount(1);
        wetFoodSlider.setSnapToTicks(true);
        wetFoodSlider.setMajorTickUnit(2);
    }
    public void setFeedActionButton(){
        feed();
        feedActionPane.setVisible(false);
        buttonPane.setVisible(true);
        refresh();
    }
    public void setCompetitionButton(){
        competition();
        moneyToWin();
        refresh();
    }
    public void setTrainButton(){
        trainActionPane.setVisible(true);
        trainingPane.setVisible(false);
        trainingBox.setItems(FXCollections.observableArrayList("Discipline", "Obedience", "Intelligence"));
    }
    public void setTrainActionButton(){
        train();
        trainActionPane.setVisible(false);
        trainingPane.setVisible(true);
        refresh();
    }

    public void pet(){
        if(mood<1 && energy>0.1){
            mood+=0.1;
            money +=2;
            points+=2;
            actionPane.appendText("Your cat got some love! Mood+10\n");
        }
    }
    public void snack(){
        if(money>10 && mood <1 && energy>0.1 && hygiene>0.05){
            money-=10;
            mood+=0.1;
            points+=2;
            hygiene -=0.05;
            hunger+=0.1;
            actionPane.appendText("Your cat ate fish biscuits! Mood+10, Hygiene -5, Hunger +10\n");
        }
    }
    public void wash(){
        if(mood>0.1 && hygiene <1){
            mood-=0.1;
            money+=5;
            hygiene+=0.1;
            points+=2;
            actionPane.appendText("You washed your cat! Mood -10, Hygiene +10\n");
        }
    }
    public void sleep(){
        if(energy<1 && hunger>0.2){
            energy+=0.2;
            money+=5;
            hunger-=0.2;
            points+=2;
            actionPane.appendText("Your cat slept for a short time! Energy +10, Hunger -20\n");
        }
    }
    public void play(){
        if(mood<1 && energy >0.1 && hunger> 0.1 && hygiene >0.05){
            mood +=0.05;
            energy-=0.1;
            money+=5;
            hygiene-=0.05;
            actionPane.appendText("you played with your cat! Mood +5, Hygiene -5, Energy -10\n");
        }
    }
    public void feed(){
        int dryAmount = (int) dryFoodSlider.getValue();
        int wetAmount = (int) wetFoodSlider.getValue();
        if (hunger<1 && energy > dryAmount*0.02 && money>dryAmount*2 && money>wetAmount*2 && energy > wetAmount*0.02 && hygiene>0.1){
            hunger+= dryAmount*0.05;
            hunger+=wetAmount*0.1;
            money-=(dryAmount+wetAmount)*2;
            energy-=(wetAmount+dryAmount)*0.02;
            hygiene-=0.1;
            points+=2;
        }
        actionPane.appendText("you fed your cat!\n");
    }

    public void competition(){
        if(hygiene==1 && mood==1){
            Random rand = new Random();
            if((discipline+obedience+intelligence)*500<300){
                place = generate(4,5);
            }
            else if((discipline+obedience+intelligence)*500>=300 &&(discipline+obedience+intelligence)*500<600){
                place = generate(3,5);
            }
            else if((discipline+obedience+intelligence)*500>=600 &&(discipline+obedience+intelligence)*500<900){
                place = generate(3,4);
            }
            else if((discipline+obedience+intelligence)*500>=900 &&(discipline+obedience+intelligence)*500<1200){
                place = generate(2,3);
            }
            else if((discipline+obedience+intelligence)*500>=1200 &&(discipline+obedience+intelligence)*500<=1500){
                place = generate(1,3);
            }
        }
        actionPane.appendText("Your cat took place in a competition! He ended up in "+ place +" place!\n");
        energy-=0.1;
    }
    public static int generate(int min,int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public void moneyToWin(){
        switch (place){
            case 5:
                money+=50;
                points+=10;
                break;
            case 4:
                money+=100;
                points+=20;
                break;
            case 3:
                money+=150;
                points+=30;
                bronze+=1;
                break;
            case 2:
                money+=200;
                points+=50;
                silver+=1;
                break;
            case 1:
                money+=300;
                points+=100;
                gold+=1;
                break;
        }
    }
    public void train(){
        if(trainingBox.getSelectionModel().getSelectedItem()=="Discipline"){
            discipline+=0.002;
        }
        else if(trainingBox.getSelectionModel().getSelectedItem()=="Obedience"){
            obedience+=0.002;
        }
        else if(trainingBox.getSelectionModel().getSelectedItem()=="Intelligence"){
            intelligence+=0.002;
        }
        mood-=0.1;
        energy -=0.1;
        money+=1;
        points+=1;
        hygiene-=0.05;
        actionPane.appendText("Your cat trained "+ trainingBox.getSelectionModel().getSelectedItem()+". Mood -10, Energy -10, Hygiene -5\n");
    }

    public int setLevel(){
        level = Math.round(points/500);
        return level;
    }
    public void refresh(){
        setEverything();
        alterDB();
        setLevel();
    }






}
