package ir.ac.kntu.view;

import ir.ac.kntu.ImageAddresses;
import ir.ac.kntu.model.GameMap;
import ir.ac.kntu.model.TileUsage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GamePage extends HBox {

    private Label heartCountLabel;
    private Label levelNumberLabel;
    private Label hasSniperLabel;
    private Label timeLabel;
    private Label speedLabel;
    private Label scoreLabel;
    private GridPane gamePane;
    private GameMap currentMap;//has no use. it exists just in case we need to access the current map in the gamePage.

    public GamePage() {
        VBox infoBox = makeInfoBox();
        infoBox.setMinWidth(200);
        infoBox.setPadding(new Insets(25,0,0,0));
        infoBox.setSpacing(30);
        infoBox.setStyle("-fx-background-color: #ccc");
        ///// above is infoBox
        ///// below is gamePane

        gamePane = new GridPane();
//        gamePane.setMaxWidth(600);
        gamePane.setStyle("-fx-background-color: #888");

        this.getChildren().addAll(gamePane, infoBox);
    }

    private VBox makeInfoBox() {
        HBox heartsBox = makeHeartBox();
        heartsBox.setAlignment(Pos.CENTER);
        heartsBox.setSpacing(15);

        HBox levelBox = makeLevelBox();
        levelBox.setAlignment(Pos.CENTER);
        levelBox.setSpacing(25);

        HBox hasSniperBox = makeHasSniperBox();
        hasSniperBox.setAlignment(Pos.CENTER);
        hasSniperBox.setSpacing(8);

        HBox timeBox = makeTimeBox();
        timeBox.setAlignment(Pos.CENTER);

        HBox movementSpeedBox = makeMovementSpeedBox();
        movementSpeedBox.setAlignment(Pos.CENTER);
        movementSpeedBox.setSpacing(25);

        HBox scoreBox = makeScoreBox();
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.setSpacing(25);

        return new VBox(heartsBox, levelBox, hasSniperBox, movementSpeedBox, timeBox, scoreBox);
    }

    private HBox makeScoreBox() {
        Label label = new Label("Score: ");
        this.scoreLabel = new Label("1");
        label.setScaleX(2);
        label.setScaleY(2);
        scoreLabel.setScaleX(2);
        scoreLabel.setScaleY(2);

        return new HBox(label, this.scoreLabel);
    }

    private HBox makeMovementSpeedBox() {
        Label label = new Label("speed: ");
        label.setScaleX(2);
        label.setScaleY(2);
        speedLabel = new Label("1");
        speedLabel.setScaleX(2);
        speedLabel.setScaleY(2);
        return new HBox(label, speedLabel);
    }

    private HBox makeTimeBox() {
        timeLabel = new Label("0:0");
        timeLabel.setScaleX(2);
        timeLabel.setScaleY(2);
        return new HBox(timeLabel);
    }

    private HBox makeHasSniperBox() {
        ImageView sniperImage= new ImageView();
//        sniperImage.setFitHeight(50);
//        sniperImage.setFitWidth(100);
        try {
            sniperImage = new ImageView(new Image(new FileInputStream(ImageAddresses.sniperImage)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        hasSniperLabel = new Label("No");
        hasSniperLabel.setScaleX(2);
        hasSniperLabel.setScaleY(2);
        return new HBox(sniperImage, hasSniperLabel);
    }

    private HBox makeLevelBox(){
        Label levelLabel = new Label("Level: ");
        this.levelNumberLabel = new Label("1");
        levelLabel.setScaleX(2);
        levelLabel.setScaleY(2);
        this.levelNumberLabel.setScaleX(2);
        this.levelNumberLabel.setScaleY(2);
        return new HBox(levelLabel, levelNumberLabel);
    }

    private HBox makeHeartBox(){
        ImageView heartImage = new ImageView();
        heartImage.setFitWidth(50);
        heartImage.setFitHeight(50);
        try {
            heartImage.setImage(new Image(new FileInputStream(ImageAddresses.heartImage)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.heartCountLabel = new Label("3");
        this.heartCountLabel.setScaleX(5);
        this.heartCountLabel.setScaleY(5);
        return new HBox(heartImage, heartCountLabel);
    }

    public void loadGameMap(GameMap gameMap) {
        currentMap = gameMap;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 11; j++) {
                currentMap.getMap()[i][j].setTileUsage(TileUsage.InGame);
                gamePane.add(currentMap.getMap()[i][j], j, i);
            }
        }
    }

    public Label getHeartCountLabel() {
        return heartCountLabel;
    }

    public Label getLevelNumberLabel() {
        return levelNumberLabel;
    }

    public Label getHasSniperLabel() {
        return hasSniperLabel;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public Label getSpeedLabel() {
        return speedLabel;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public GridPane getGamePane() {
        return gamePane;
    }
}
