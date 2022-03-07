package ir.ac.kntu.controller;

import com.sun.glass.ui.Application;
import ir.ac.kntu.ImageAddresses;
import ir.ac.kntu.JavaFxApplication;
import ir.ac.kntu.model.GameMap;
import ir.ac.kntu.model.Tile;
import ir.ac.kntu.model.gameRuntime.*;
import ir.ac.kntu.view.GamePage;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GamePageController {
    private GamePage gamePage;

    private Integer levelNumber = 1;
    private Integer playerScore = 0;
    public static PlayerController playerController;
    private boolean hasSniper = false;
    //speed and heartCount will be inside the playerController object

    private ArrayList<Thread> enemyThreads = new ArrayList<Thread>();
    private ArrayList<Thread> otherThreads = new ArrayList<Thread>(); //except timer
    private Thread timerThread;

    private Scene scene;



    public GamePageController(Integer levelNumber, Integer playerScore){
//        initiateButtons();
        this.levelNumber =levelNumber;
        this.playerScore = playerScore;

        setUpNewLevel();

        scene = new Scene(this.getGamePage(), 750, 660);
        JavaFxApplication.getPrimaryStage().setScene(scene);

        playerController = new PlayerController(this, scene);
        Thread playerThread = new Thread(playerController);
        playerThread.start();

        setUpLevelDetails();

        timerThread = new Thread(new InGameTimer(this));
        timerThread.start();

        setUpLevelThreads();
    }

    public void setUpLevelThreads() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 11; j++) {
                switch (GameMap.getCurrentMap().getMap()[i][j].getTileEnum()){
                    case Player:
                        this.playerController.setX(j);
                        this.playerController.setY(i);
                        GameMap.getCurrentMap().getMap()[i][j].thread = playerController;
                        break;
                    case Rock:
                        Thread rockThread = new Thread(new RockAI(j, i));
                        this.otherThreads.add(rockThread);
                        rockThread.start();
                        break;
                    case Balloon:
                        BalloonAI balloonAI = new BalloonAI(j, i);
                        Thread balloonThread = new Thread(balloonAI);
                        this.enemyThreads.add(balloonThread);
                        balloonThread.start();
                        GameMap.getCurrentMap().getMap()[i][j].thread = balloonAI;
                        break;
                    case Dragon:
                        DragonAI dragonAI = new DragonAI(j, i);
                        Thread dragonThread = new Thread(dragonAI);
                        this.enemyThreads.add(dragonThread);
                        dragonThread.start();
                        GameMap.getCurrentMap().getMap()[i][j].thread = dragonAI;
                        break;
                }
            }
        }

    System.out.println(enemyThreads.size());
    }

    public void setUpNewLevel() {
        gamePage = new GamePage();
        GameMap.setCurrentMap(GameMap.getAllGameMaps().get(levelNumber - 1));
        gamePage.loadGameMap(GameMap.getCurrentMap());
    }

    public void setUpLevelDetails(){
        gamePage.getHasSniperLabel().setText(this.hasSniper?"Yes":"No");
        gamePage.getLevelNumberLabel().setText(levelNumber.toString());
        gamePage.getSpeedLabel().setText(this.playerController.getSpeed().toString());
        gamePage.getHeartCountLabel().setText(this.playerController.getHeartCount().toString());
        gamePage.getScoreLabel().setText(this.playerScore.toString());
    }

    public GamePage getGamePage() {
        return gamePage;
    }

    public boolean isHasSniper() {
        return hasSniper;
    }

    public void setHasSniper(boolean hasSniper) {
        this.hasSniper = hasSniper;
    }

    public Integer getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(Integer playerScore) {
        this.playerScore = playerScore;
    }

    public Integer getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(Integer levelNumber) {
        this.levelNumber = levelNumber;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene){
        this.scene=scene;
    }

    public ArrayList<Thread> getEnemyThreads() {
        return enemyThreads;
    }

    public ArrayList<Thread> getOtherThreads() {
        return otherThreads;
    }
}
