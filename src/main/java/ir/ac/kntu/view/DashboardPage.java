package ir.ac.kntu.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class DashboardPage extends VBox {

    Button playButton;
    Button createMapButton;
    Button playCustomMapButton;

    public DashboardPage(){
        playButton = new Button("Play");
        createMapButton = new Button("create map");
        playCustomMapButton = new Button("Play custom map");


        this.getChildren().addAll(playButton, createMapButton, playCustomMapButton);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
    }


    public Button getPlayButton() {
        return playButton;
    }

    public Button getCreateMapButton() {
        return createMapButton;
    }

    public Button getPlayCustomMapButton() {
        return playCustomMapButton;
    }
}
