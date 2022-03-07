package ir.ac.kntu.model.gameRuntime;

import ir.ac.kntu.ImageAddresses;
import ir.ac.kntu.controller.GamePageController;
import ir.ac.kntu.model.GameMap;
import ir.ac.kntu.model.TileEnum;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class DragonAI extends GameAI{

    private DirectionEnum direction = DirectionEnum.Left;


    public DragonAI(Integer x, Integer y) {
        super(x, y);
    }

    @Override
    public void run(){
        while (super.isAlive){
            move();
        }
    }

    private void move() {
        switch (direction){
            case Left:
                moveLeft();
                break;
            case Right:
                moveRight();
                break;
            case Down:
                moveDown();
                break;
            case Up:
                moveUp();
                break;
        }
//        System.out.println("x:"+X+"y:"+Y);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moveUp() {
        if (Y==0){
//            changeDirection();
            disappearAndDamage();
            return;
        }
        if (GameMap.getCurrentMap().getMap()[Y-1][X].getTileEnum()== TileEnum.Rock
                || GameMap.getCurrentMap().getMap()[Y-1][X].getTileEnum()== TileEnum.Sand) {
            changeDirection();
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
        if (GameMap.getCurrentMap().getMap()[Y][X].getTileEnum()==TileEnum.Player){

            PlayerController playerController = (PlayerController) GameMap.getCurrentMap().getMap()[Y][X].thread;
            playerController.setHeartCount(playerController.getHeartCount()-1);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("heart lost");
                    playerController.getGamePageController().getGamePage().getHeartCountLabel().setText(playerController.getHeartCount().toString());
                }
            });
        }
        GameMap.getCurrentMap().getMap()[Y][X].thread =this;
        try {

            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.balloonImage)));
            GameMap.getCurrentMap().getMap()[Y][X].setTileEnum(TileEnum.Balloon);
            GameMap.getCurrentMap().getMap()[Y][X].setRotate(-90);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moveDown() {
        if (Y==12){
//            changeDirection();
            disappearAndDamage();
            return;
        }
        if (GameMap.getCurrentMap().getMap()[Y+1][X].getTileEnum()== TileEnum.Rock
                || GameMap.getCurrentMap().getMap()[Y+1][X].getTileEnum()== TileEnum.Sand) {
            changeDirection();
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
        if (GameMap.getCurrentMap().getMap()[Y][X].getTileEnum()==TileEnum.Player){

            PlayerController playerController = (PlayerController) GameMap.getCurrentMap().getMap()[Y][X].thread;
            playerController.setHeartCount(playerController.getHeartCount()-1);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("heart lost");
                    playerController.getGamePageController().getGamePage().getHeartCountLabel().setText(playerController.getHeartCount().toString());
                }
            });
        }
        GameMap.getCurrentMap().getMap()[Y][X].thread =this;
        try {

            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.balloonImage)));
            GameMap.getCurrentMap().getMap()[Y][X].setTileEnum(TileEnum.Balloon);
            GameMap.getCurrentMap().getMap()[Y][X].setRotate(90);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moveRight() {
        if (X==10){
//            changeDirection();
            disappearAndDamage();
            return;
        }
        if (GameMap.getCurrentMap().getMap()[Y][X+1].getTileEnum()== TileEnum.Rock
                || GameMap.getCurrentMap().getMap()[Y-1][X+1].getTileEnum()== TileEnum.Sand) {
            changeDirection();
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
        if (GameMap.getCurrentMap().getMap()[Y][X].getTileEnum()==TileEnum.Player){

            PlayerController playerController = (PlayerController) GameMap.getCurrentMap().getMap()[Y][X].thread;
            playerController.setHeartCount(playerController.getHeartCount()-1);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("heart lost");
                    playerController.getGamePageController().getGamePage().getHeartCountLabel().setText(playerController.getHeartCount().toString());
                }
            });
        }
        GameMap.getCurrentMap().getMap()[Y][X].thread =this;
        try {

            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.balloonImage)));
            GameMap.getCurrentMap().getMap()[Y][X].setTileEnum(TileEnum.Balloon);
            GameMap.getCurrentMap().getMap()[Y][X].setRotate(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moveLeft() {
        if (X==0){
//            changeDirection();
            disappearAndDamage();
            return;
        }
        if (GameMap.getCurrentMap().getMap()[Y][X-1].getTileEnum()== TileEnum.Rock
                || GameMap.getCurrentMap().getMap()[Y][X-1].getTileEnum()== TileEnum.Sand) {
            changeDirection();
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
        if (GameMap.getCurrentMap().getMap()[Y][X].getTileEnum()==TileEnum.Player){

            PlayerController playerController = (PlayerController) GameMap.getCurrentMap().getMap()[Y][X].thread;
            playerController.setHeartCount(playerController.getHeartCount()-1);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("heart lost");
                    playerController.getGamePageController().getGamePage().getHeartCountLabel().setText(playerController.getHeartCount().toString());
                }
            });
        }
        GameMap.getCurrentMap().getMap()[Y][X].thread =this;
        try {

            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.balloonImage)));
            GameMap.getCurrentMap().getMap()[Y][X].setTileEnum(TileEnum.Balloon);
            GameMap.getCurrentMap().getMap()[Y][X].setRotate(180);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void changeDirection() {
        switch ((new Random()).nextInt(4)){
            case 0:
                this.direction = DirectionEnum.Left;
                break;
            case 1:
                this.direction = DirectionEnum.Right;
                break;
            case 2:
                this.direction = DirectionEnum.Up;
                break;
            case 3:
                this.direction = DirectionEnum.Down;
                break;
        }
    }

    private void disappearAndDamage(){
        try {
            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        GameMap.getCurrentMap().getMap()[Y][X].setTileEnum(TileEnum.Empty);
        GameMap.getCurrentMap().getMap()[Y][X].setRotate(0);
        super.isAlive =false;
        GamePageController.playerController.setHeartCount(GamePageController.playerController.getHeartCount()-1);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                GamePageController.playerController.getGamePageController().getGamePage().
                        getHeartCountLabel().setText(GamePageController.playerController.getHeartCount().toString());
            }
        });
    }
}
