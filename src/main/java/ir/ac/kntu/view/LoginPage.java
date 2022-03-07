package ir.ac.kntu.view;

import ir.ac.kntu.ImageAddresses;
import ir.ac.kntu.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

public class LoginPage extends VBox {

    private TextField usernameField;
    private TextField passwordField;
    private Label errorLabel;
    private Button loginButton;
    private Button createButton;
    private TableView tableView;


    public LoginPage(){
        ImageView imageView=new ImageView();
        try {
            Image image = new Image(new FileInputStream(ImageAddresses.title));
            imageView= new ImageView(image);
            imageView.setFitWidth(300);
            imageView.setFitHeight(100);
        }catch (IOException e){
            e.printStackTrace();
        }

        tableView = new TableView();
        fillTableView(tableView);

        Label usernameLabel = new Label("username: ");
        usernameField = new TextField();
        HBox usernameRow = new HBox(usernameLabel, usernameField);
        usernameRow.setAlignment(Pos.CENTER);

        Label passwordLabel = new Label("password: ");
        passwordField = new TextField();
        HBox passwordRow = new HBox(passwordLabel, passwordField);
        passwordRow.setAlignment(Pos.CENTER);

        errorLabel = new Label();

        loginButton = new Button("Login");
        createButton = new Button("Create");
        HBox buttonRow = new HBox(loginButton, createButton);
        buttonRow.setSpacing(5);
        buttonRow.setAlignment(Pos.CENTER);

        this.getChildren().addAll(imageView,tableView, usernameRow, passwordRow, errorLabel, buttonRow);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
//        this.setStyle("-fx-background-image: url(\""+System.getProperty("user.dir")+"/images/wallpaper.png"+"\");");
    }

    private void fillTableView(TableView tableView) {
        TableColumn<User, String> column1 = new TableColumn<>("username");
        column1.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, Integer> column2 = new TableColumn<>("played times");
        column2.setCellValueFactory(new PropertyValueFactory<>("playedTimes"));

        TableColumn<User, Integer> column3 = new TableColumn<>("High score");
        column3.setCellValueFactory(new PropertyValueFactory<>("highScore"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);


        Collections.sort(User.getAllUsers());
        for (User item:User.getAllUsers()) {
            tableView.getItems().add(item);
        }
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public TableView getTableView() {
        return tableView;
    }

}
