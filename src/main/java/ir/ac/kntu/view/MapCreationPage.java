package ir.ac.kntu.view;

import ir.ac.kntu.ImageAddresses;
import ir.ac.kntu.model.GameMap;
import ir.ac.kntu.model.Tile;
import ir.ac.kntu.model.TileEnum;
import ir.ac.kntu.model.TileUsage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class MapCreationPage extends VBox {

    private HBox header;

    private GridPane body;
    private GameMap currentMap;

    private HBox buttonsBox;
    private Button saveMapButton;
    private TextField nameText;
    private Button backButton;


    public MapCreationPage(){
        header = new HBox();
        createHeader();
        header.setAlignment(Pos.CENTER);
        //above is header
        GameMap.setCurrentMap(new GameMap());
        currentMap = GameMap.getCurrentMap();

        body = new GridPane();
        createBody();
        body.setAlignment(Pos.CENTER);
        //below is footer
        saveMapButton = new Button("Save");
        nameText = new TextField();
        nameText.setPromptText("map name");
        backButton = new Button("Back");
        buttonsBox = new HBox(saveMapButton, nameText, backButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(5);

        this.getChildren().addAll(header, body, buttonsBox);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
    }

    private void createBody() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 11; j++) {
                this.body.add(currentMap.getMap()[i][j], j, i);
            }
        }
    }

    private void createHeader() {
        //Empty, Sand, Rock, Heart, Mushroom, Balloon, Dragon, Player;
        try {
            Tile emptyHeaderTile = new Tile(TileEnum.Empty, TileUsage.CreationHeader, new Image(new FileInputStream(ImageAddresses.emptyImage)),0,0);
            Tile sandHeaderTile = new Tile(TileEnum.Sand, TileUsage.CreationHeader, new Image(new FileInputStream(ImageAddresses.sandImage)),0,0);
            Tile rockHeaderTile = new Tile(TileEnum.Rock, TileUsage.CreationHeader, new Image(new FileInputStream(ImageAddresses.rockImage)),0,0);
            Tile heartHeaderTile = new Tile(TileEnum.Heart, TileUsage.CreationHeader, new Image(new FileInputStream(ImageAddresses.heartImage)),0,0);
            Tile mushroomHeaderTile = new Tile(TileEnum.Mushroom, TileUsage.CreationHeader, new Image(new FileInputStream(ImageAddresses.mushroomImage)),0,0);
            Tile balloonHeaderTile = new Tile(TileEnum.Balloon, TileUsage.CreationHeader, new Image(new FileInputStream(ImageAddresses.balloonImage)),0,0);
            Tile dragonHeaderTile = new Tile(TileEnum.Dragon, TileUsage.CreationHeader, new Image(new FileInputStream(ImageAddresses.dragonImage)),0,0);
            Tile playerHeaderTile = new Tile(TileEnum.Player, TileUsage.CreationHeader, new Image(new FileInputStream(ImageAddresses.playerImage)),0,0);

            this.header.getChildren().addAll(emptyHeaderTile, sandHeaderTile, rockHeaderTile, heartHeaderTile
                    , mushroomHeaderTile, balloonHeaderTile, dragonHeaderTile, playerHeaderTile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.header.setSpacing(5);
    }

    public HBox getHeader() {
        return header;
    }

    public GridPane getBody() {
        return body;
    }

    public GameMap getCurrentMap() {
        return currentMap;
    }

    public HBox getButtonsBox() {
        return buttonsBox;
    }

    public Button getSaveMapButton() {
        return saveMapButton;
    }

    public TextField getNameText() {
        return nameText;
    }

    public Button getBackButton() {
        return backButton;
    }
}
