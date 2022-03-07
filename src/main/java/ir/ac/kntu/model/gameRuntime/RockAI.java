package ir.ac.kntu.model.gameRuntime;

import ir.ac.kntu.ImageAddresses;
import ir.ac.kntu.controller.GamePageController;
import ir.ac.kntu.model.GameMap;
import ir.ac.kntu.model.TileEnum;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RockAI extends GameAI{

//    private boolean isAlive =true;

    public RockAI(Integer x, Integer y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (super.isAlive){
            if (super.getY()==12){
                disappear();
                return;
            }
            if (GameMap.getCurrentMap().getMap()[super.getY()+1][super.getX()].getTileEnum() == TileEnum.Empty){
                fall();
            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void disappear() {
        GameMap.getCurrentMap().getMap()[super.getY()][super.getX()].setTileEnum(TileEnum.Empty);
        try {
            GameMap.getCurrentMap().getMap()[super.getY()][super.getX()].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        super.isAlive=false;
    }

    synchronized private void fall(){
        while (isAlive) {
            if (super.getY()==12){
                disappear();
                return;
            }
            if (GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].getTileEnum() == TileEnum.Player) {
                //heart--
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        GamePageController.playerController.setHeartCount(GamePageController.playerController.getHeartCount() - 1);

                        GamePageController.playerController.getGamePageController().getGamePage().getHeartCountLabel().
                                setText(GamePageController.playerController.getHeartCount().toString());
                    }
                });

            }
            if (GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].getTileEnum() == TileEnum.Balloon) {
                //kill enemy
                ((BalloonAI) GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].thread).isAlive = false;

                try {
                    GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].setTileEnum(TileEnum.Empty);
                GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].setRotate(0);
            }else if (GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].getTileEnum() == TileEnum.Dragon){
                ((DragonAI) GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].thread).isAlive = false;

                try {
                    GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].setTileEnum(TileEnum.Empty);
                GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].setRotate(0);
            }
            if (GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].getTileEnum() == TileEnum.Rock
                /*|| GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].getTileEnum() == TileEnum.Sand*/) {
                disappear();
            } else { //if (GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].getTileEnum() == TileEnum.Empty){
                GameMap.getCurrentMap().getMap()[super.getY()][super.getX()].setTileEnum(TileEnum.Empty);
                GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].setTileEnum(TileEnum.Rock);
                try {
                    GameMap.getCurrentMap().getMap()[super.getY()][super.getX()].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
                    GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].setImage(new Image(new FileInputStream(ImageAddresses.rockImage)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
//            System.out.println("x:"+super.getX()+"y:" +super.getY());
//            System.out.println(GameMap.getCurrentMap().getMap()[super.getY() + 1][super.getX()].getTileEnum().toString());



            super.setY(super.getY() + 1);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
