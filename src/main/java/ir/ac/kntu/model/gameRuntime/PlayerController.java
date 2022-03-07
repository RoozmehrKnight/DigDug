package ir.ac.kntu.model.gameRuntime;

import ir.ac.kntu.ImageAddresses;
import ir.ac.kntu.JavaFxApplication;
import ir.ac.kntu.controller.GamePageController;
import ir.ac.kntu.model.GameMap;
import ir.ac.kntu.model.TileEnum;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.TimeZone;
import java.util.Timer;

public class PlayerController implements Runnable{
    private GamePageController gamePageController;

    private Integer heartCount = 3;
    private Integer speed = 5;
    private Integer X = 0;
    private Integer Y = 0;

    private Scene scene;


    public PlayerController(GamePageController gamePageController, Scene scene){
        this.gamePageController = gamePageController;
        this.scene = scene;
    }

    @Override
    public void run() {
        System.out.println("asd");
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.A){
                moveLeft();
            }else if (keyEvent.getCode() == KeyCode.D){
                moveRight();
            }else if (keyEvent.getCode() == KeyCode.W){
                moveUp();
            }else if (keyEvent.getCode() == KeyCode.S){
                moveDown();
            }else if (keyEvent.getCode() == KeyCode.SPACE){
                //shoot
                shoot();
            }
        });
    }

    private void shoot() {
        DirectionEnum directionEnum;
        if (GameMap.getCurrentMap().getMap()[Y][X].getRotate() == 0){
            Bullet bullet = new Bullet(X, Y, DirectionEnum.Right, gamePageController.isHasSniper()?5:3);
            Thread thread = new Thread(bullet);
            thread.start();
        }else if (GameMap.getCurrentMap().getMap()[Y][X].getRotate() == 90){
            Bullet bullet = new Bullet(X, Y, DirectionEnum.Down, gamePageController.isHasSniper()?5:3);
            Thread thread = new Thread(bullet);
            thread.start();
        }else if (GameMap.getCurrentMap().getMap()[Y][X].getRotate() == 180){
            Bullet bullet = new Bullet(X, Y, DirectionEnum.Left, gamePageController.isHasSniper()?5:3);
            Thread thread = new Thread(bullet);
            thread.start();
        }else if (GameMap.getCurrentMap().getMap()[Y][X].getRotate() == -90){
            Bullet bullet = new Bullet(X, Y, DirectionEnum.Up, gamePageController.isHasSniper()?5:3);
            Thread thread = new Thread(bullet);
            thread.start();
        }

    }

    private void moveDown() {
        if (GameMap.getCurrentMap().getMap()[Y+1][X].getTileEnum()==TileEnum.Rock) {
            return;
        }
        try {
            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
            GameMap.getCurrentMap().getMap()[Y][X].setTileEnum(TileEnum.Empty);
            GameMap.getCurrentMap().getMap()[Y][X].setRotate(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        GameMap.getCurrentMap().getMap()[Y][X].thread =null;
        if (Y!=12){
            Y++;
        }
        GameMap.getCurrentMap().getMap()[Y][X].thread =this;
        try {
            if (GameMap.getCurrentMap().getMap()[Y][X].getTileEnum()==TileEnum.Balloon
                    || GameMap.getCurrentMap().getMap()[Y][X].getTileEnum() == TileEnum.Dragon){
                this.heartCount--;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("heart lost");
                        gamePageController.getGamePage().getHeartCountLabel().setText(heartCount.toString());
                    }
                });
            }
            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.playerImage)));
            GameMap.getCurrentMap().getMap()[Y][X].setTileEnum(TileEnum.Player);
            GameMap.getCurrentMap().getMap()[Y][X].setRotate(90);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moveUp() {
        if (GameMap.getCurrentMap().getMap()[Y-1][X].getTileEnum()==TileEnum.Rock) {
            return;
        }
        try {
            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
            GameMap.getCurrentMap().getMap()[Y][X].setTileEnum(TileEnum.Empty);
            GameMap.getCurrentMap().getMap()[Y][X].setRotate(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        GameMap.getCurrentMap().getMap()[Y][X].thread =null;
        if (Y!=0){
            Y--;
        }
        GameMap.getCurrentMap().getMap()[Y][X].thread =this;
        try {
            if (GameMap.getCurrentMap().getMap()[Y][X].getTileEnum()==TileEnum.Balloon
                    || GameMap.getCurrentMap().getMap()[Y][X].getTileEnum() == TileEnum.Dragon){
                this.heartCount--;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("heart lost");
                        gamePageController.getGamePage().getHeartCountLabel().setText(heartCount.toString());
                    }
                });
            }
            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.playerImage)));
            GameMap.getCurrentMap().getMap()[Y][X].setTileEnum(TileEnum.Player);
            GameMap.getCurrentMap().getMap()[Y][X].setRotate(-90);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moveRight() {
        if (GameMap.getCurrentMap().getMap()[Y][X+1].getTileEnum()==TileEnum.Rock) {
            return;
        }
        try {
            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
            GameMap.getCurrentMap().getMap()[Y][X].setTileEnum(TileEnum.Empty);
            GameMap.getCurrentMap().getMap()[Y][X].setRotate(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        GameMap.getCurrentMap().getMap()[Y][X].thread =null;
        if (X!=10){
            X++;
        }
        GameMap.getCurrentMap().getMap()[Y][X].thread =this;

        try {
            if (GameMap.getCurrentMap().getMap()[Y][X].getTileEnum()==TileEnum.Balloon
                    || GameMap.getCurrentMap().getMap()[Y][X].getTileEnum() == TileEnum.Dragon){
                this.heartCount--;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("heart lost");
                        gamePageController.getGamePage().getHeartCountLabel().setText(heartCount.toString());
                    }
                });
            }
            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.playerImage)));
            GameMap.getCurrentMap().getMap()[Y][X].setTileEnum(TileEnum.Player);
            GameMap.getCurrentMap().getMap()[Y][X].setRotate(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moveLeft() {
        if (GameMap.getCurrentMap().getMap()[Y][X-1].getTileEnum()==TileEnum.Rock) {
            return;
        }
        try {
            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
            GameMap.getCurrentMap().getMap()[Y][X].setTileEnum(TileEnum.Empty);
            GameMap.getCurrentMap().getMap()[Y][X].setRotate(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        GameMap.getCurrentMap().getMap()[Y][X].thread =null;
        if (X!=0){
            X--;
        }
        GameMap.getCurrentMap().getMap()[Y][X].thread =this;
        try {
            if (GameMap.getCurrentMap().getMap()[Y][X].getTileEnum()==TileEnum.Balloon
                    || GameMap.getCurrentMap().getMap()[Y][X].getTileEnum() == TileEnum.Dragon){
                this.heartCount--;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("heart lost");
                        gamePageController.getGamePage().getHeartCountLabel().setText(heartCount.toString());
                    }
                });
            }
            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.playerImage)));
            GameMap.getCurrentMap().getMap()[Y][X].setTileEnum(TileEnum.Player);
            GameMap.getCurrentMap().getMap()[Y][X].setRotate(180);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer getHeartCount() {
        return heartCount;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer getX() {
        return X;
    }

    public void setX(Integer x) {
        X = x;
    }

    public Integer getY() {
        return Y;
    }

    public void setY(Integer y) {
        Y = y;
    }

    public GamePageController getGamePageController() {
        return gamePageController;
    }

    public void setHeartCount(Integer heartCount) {
        this.heartCount = heartCount;
    }
}
