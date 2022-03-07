package ir.ac.kntu.model.gameRuntime;

import ir.ac.kntu.JavaFxApplication;
import ir.ac.kntu.controller.GamePageController;
import ir.ac.kntu.controller.LoginPageController;
import ir.ac.kntu.model.GameMap;
import ir.ac.kntu.model.TileEnum;
import ir.ac.kntu.model.User;
import ir.ac.kntu.view.LoginPage;
import javafx.application.Platform;
import javafx.scene.Scene;

public class InGameTimer implements Runnable{

    public static Integer currentTime = 0;
    GamePageController gamePageController;

    public InGameTimer(GamePageController gamePageController){
        this.gamePageController = gamePageController;
    }

    Boolean isAlive=true;

    @Override
    public void run() {
        while (isAlive){
            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        currentTime++;
                        gamePageController.getGamePage().getTimeLabel().setText(giveTimeString());
                    }
                });
                CheckEvents();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void CheckEvents() {
        if (checkHasWon()){
            loadNextLevel();
        }
        if (checkHasLost()){
            endGame();
            isAlive = false;
        }

    }

    private void endGame() {
        if (User.getCurrentUser().getHighScore()< GamePageController.playerController.getGamePageController().getPlayerScore()) {
            User.getCurrentUser().setHighScore(GamePageController.playerController.getGamePageController().getPlayerScore());
        }
        User.getCurrentUser().setPlayedTimes(User.getCurrentUser().getPlayedTimes() + 1);
        User.addAllUsersToFile();

        for (Thread item:GamePageController.playerController.getGamePageController().getEnemyThreads()) {
            item.interrupt();
        }
        for (Thread item:GamePageController.playerController.getGamePageController().getOtherThreads()) {
            item.interrupt();
        }

        for (int i = 0; i < GameMap.getAllGameMaps().size(); i++) {
            GameMap.getAllGameMaps().remove(i);
        }

        GameMap.readAllMainMapsFromFile();
//        GameMap.setCurrentMap(GameMap.getAllGameMaps().get(0));

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    LoginPageController loginPageController = new LoginPageController();
                    JavaFxApplication.getPrimaryStage().setScene(new Scene(loginPageController.getLoginPage(), 400, 600));
                }
            });
    }

    private void loadNextLevel() {
        System.out.println("load next level");

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (GamePageController.playerController.getGamePageController().getLevelNumber() == GameMap.getAllGameMaps().size()){
                    isAlive = false;
                    endGame();
                }else {
                    GamePageController gamePageController = new GamePageController(
                            GamePageController.playerController.getGamePageController().getLevelNumber() + 1,
                            GamePageController.playerController.getGamePageController().getPlayerScore());
                    isAlive = false;
                }
            }
        });
    }

    private Boolean checkHasLost() {
        if (GamePageController.playerController.getHeartCount()<=0){
            return true;
        }
        return false;
    }

    private Boolean checkHasWon() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 11; j++) {
                if (GameMap.getCurrentMap().getMap()[i][j].getTileEnum() == TileEnum.Balloon){
                    return false;
                }
            }
        }
        return true;
    }

    public static String giveTimeString(){
        Integer minute = currentTime / 60;
        Integer second = currentTime % 60;
        return minute + ":" + second;
    }
}
