package ir.ac.kntu;

import ir.ac.kntu.controller.LoginPageController;
import ir.ac.kntu.model.GameMap;
import ir.ac.kntu.model.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;


public class JavaFxApplication extends Application implements Serializable {

    private static Stage primaryStage;

    public static void main(String[] args) {
        User.readAllUsersFromFile();
        GameMap.readAllMainMapsFromFile();

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        JavaFxApplication.primaryStage = stage;

        LoginPageController loginPageController = new LoginPageController();
        stage.setScene(new Scene(loginPageController.getLoginPage(), 400, 600));
        stage.setTitle("Dig Dug");
        stage.show();

    }



    public static Stage getPrimaryStage() {
        return primaryStage;
    }

}
