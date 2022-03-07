package ir.ac.kntu.model.gameRuntime;

import ir.ac.kntu.ImageAddresses;
import ir.ac.kntu.controller.GamePageController;
import ir.ac.kntu.model.GameMap;
import ir.ac.kntu.model.TileEnum;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Bullet extends GameAI implements Runnable{

    DirectionEnum direction;
    Integer length=0;

    public Bullet(Integer x, Integer y, DirectionEnum directionEnum, Integer length) {
        super(x, y);
        this.direction = directionEnum;
        this.length = length;
    }

    @Override
    public void run(){
        switch (direction){
            case Up:
                moveUp();
                break;
            case Down:
                moveDown();
                break;
            case Right:
                moveRight();
                break;
            case Left:
                moveLeft();
                break;
        }
    }

    private void moveLeft() {
        try {
            for (int i = 0; i < length; i++) {
                if (i != 0) {
                    GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
                }
                if (GameMap.getCurrentMap().getMap()[Y][X - 1].getTileEnum() == TileEnum.Empty) {
                    X--;
                } else if (GameMap.getCurrentMap().getMap()[Y][X - 1].getTileEnum() == TileEnum.Balloon) {
                    System.out.println("killed");
                    ((BalloonAI) GameMap.getCurrentMap().getMap()[Y][X - 1].thread).isAlive = false;
                    GameMap.getCurrentMap().getMap()[Y][X - 1].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
                    GameMap.getCurrentMap().getMap()[Y][X - 1].setTileEnum(TileEnum.Empty);
                    GameMap.getCurrentMap().getMap()[Y][X - 1].setRotate(0);
                    GamePageController.playerController.getGamePageController().setPlayerScore(GamePageController.playerController.getGamePageController().getPlayerScore()+200);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GamePageController.playerController.getGamePageController().getGamePage().getScoreLabel().setText(
                                    GamePageController.playerController.getGamePageController().getPlayerScore().toString());
                        }
                    });
                } else {
                    break;
                }
                GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.bulletImage)));
                Thread.sleep(200);
            }
            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moveRight() {
        try {
            for (int i = 0; i < length; i++) {
                if (i != 0) {
                    GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
                }
                if (GameMap.getCurrentMap().getMap()[Y][X + 1].getTileEnum() == TileEnum.Empty) {
                    X++;
                } else if (GameMap.getCurrentMap().getMap()[Y][X + 1].getTileEnum() == TileEnum.Balloon) {
                    System.out.println("killed");
                    ((BalloonAI) GameMap.getCurrentMap().getMap()[Y][X + 1].thread).isAlive = false;
                    GameMap.getCurrentMap().getMap()[Y][X + 1].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
                    GameMap.getCurrentMap().getMap()[Y][X + 1].setTileEnum(TileEnum.Empty);
                    GameMap.getCurrentMap().getMap()[Y][X + 1].setRotate(0);
                    GamePageController.playerController.getGamePageController().setPlayerScore(GamePageController.playerController.getGamePageController().getPlayerScore()+200);                } else {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GamePageController.playerController.getGamePageController().getGamePage().getScoreLabel().setText(
                                    GamePageController.playerController.getGamePageController().getPlayerScore().toString());
                        }
                    });
                    break;
                }
                GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.bulletImage)));
                Thread.sleep(200);
            }
            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moveDown() {
        try {
            for (int i = 0; i < length; i++) {
                if (i != 0) {
                    GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
                }
                if (GameMap.getCurrentMap().getMap()[Y+1][X].getTileEnum() == TileEnum.Empty) {
                    Y++;
                } else if (GameMap.getCurrentMap().getMap()[Y + 1][X].getTileEnum() == TileEnum.Balloon) {
                    System.out.println("killed");
                    ((BalloonAI) GameMap.getCurrentMap().getMap()[Y+1][X].thread).isAlive = false;
                    GameMap.getCurrentMap().getMap()[Y+1][X].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
                    GameMap.getCurrentMap().getMap()[Y+1][X].setTileEnum(TileEnum.Empty);
                    GameMap.getCurrentMap().getMap()[Y+1][X].setRotate(0);
                    GamePageController.playerController.getGamePageController().setPlayerScore(GamePageController.playerController.getGamePageController().getPlayerScore()+200);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GamePageController.playerController.getGamePageController().getGamePage().getScoreLabel().setText(
                                    GamePageController.playerController.getGamePageController().getPlayerScore().toString());
                        }
                    });
                } else {
                    break;
                }
                GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.bulletImage)));
                Thread.sleep(200);
            }
            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moveUp() {
        try {
            for (int i = 0; i < length; i++) {
                if (i != 0) {
                    GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
                }
                if (GameMap.getCurrentMap().getMap()[Y-1][X].getTileEnum() == TileEnum.Empty) {
                    Y--;
                } else if (GameMap.getCurrentMap().getMap()[Y - 1][X].getTileEnum() == TileEnum.Balloon) {
                    System.out.println("killed");
                    ((BalloonAI) GameMap.getCurrentMap().getMap()[Y-1][X].thread).isAlive = false;
                    GameMap.getCurrentMap().getMap()[Y-1][X].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
                    GameMap.getCurrentMap().getMap()[Y-1][X].setTileEnum(TileEnum.Empty);
                    GameMap.getCurrentMap().getMap()[Y-1][X].setRotate(0);
                    GamePageController.playerController.getGamePageController().setPlayerScore(GamePageController.playerController.getGamePageController().getPlayerScore()+200);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GamePageController.playerController.getGamePageController().getGamePage().getScoreLabel().setText(
                                    GamePageController.playerController.getGamePageController().getPlayerScore().toString());
                        }
                    });

                } else {
                    break;
                }
                GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.bulletImage)));
                Thread.sleep(200);
            }
            GameMap.getCurrentMap().getMap()[Y][X].setImage(new Image(new FileInputStream(ImageAddresses.emptyImage)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
