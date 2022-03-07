package ir.ac.kntu.model;

import ir.ac.kntu.ImageAddresses;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.logging.Handler;

public class Tile extends ImageView implements Serializable {
    private TileEnum tileEnum;
    private TileUsage tileUsage;
//    private ImageView tileImage;
    private int gridX;
    private int gridY;

    public Runnable thread;

    public Tile(){

    }

    public Tile(TileEnum tileEnum, TileUsage tileUsage, Image tileImage, int x, int y) {
        this.tileEnum = tileEnum;
        this.setImage(tileImage);
        this.tileUsage = tileUsage;
        this.gridX = x;
        this.gridY = y;
        this.setFitWidth(50);
        this.setFitHeight(50);
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println();
                switch (((Tile)event.getSource()).tileUsage){
                    case CreationHeader:
                        GameMap.chosenTileType = ((Tile)event.getSource()).tileEnum;
                        System.out.println("creation");
                        break;
                    case CreationBody:
                        ((Tile)event.getSource()).setTileEnum(GameMap.chosenTileType);
                        try {
                            ((Tile)event.getSource()).setImage(new Image(new FileInputStream(
                                    ImageAddresses.startupPath+GameMap.chosenTileType.toString()+".png")));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        System.out.println("body");
                        break;
                    case InGame://nothing
                        System.out.println(((Tile)event.getSource()).tileEnum.toString());
                        break;
                }
            }
        });
    }



    public TileEnum getTileEnum() {
        return tileEnum;
    }

    public void setTileEnum(TileEnum tileEnum) {
        this.tileEnum = tileEnum;
    }

    public void setTileUsage(TileUsage tileUsage) {
        this.tileUsage = tileUsage;
    }

    public TileUsage getTileUsage() {
        return tileUsage;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }
}
