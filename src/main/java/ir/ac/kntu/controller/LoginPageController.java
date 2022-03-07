package ir.ac.kntu.controller;

import ir.ac.kntu.JavaFxApplication;
import ir.ac.kntu.model.User;
import ir.ac.kntu.view.LoginPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class LoginPageController {

    private LoginPage loginPage;

    public LoginPageController(){
        loginPage = new LoginPage();
        initiateButtons();
    }

    public void initiateButtons(){
        initLoginButton();
        initCreateButton();
    }

    private void initLoginButton() {
        loginPage.getLoginButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (User item:User.getAllUsers()) {
                    if (item.getUsername().equals(loginPage.getUsernameField().getText())
                            && item.getPassword().equals(loginPage.getPasswordField().getText())){
                        login(item);
                        return;
                    }
                }
                loginPage.getErrorLabel().setText("not found");
                loginPage.getErrorLabel().setTextFill(Color.RED);
            }
        });
    }

    private void initCreateButton() {
        loginPage.getCreateButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (User item: User.getAllUsers()) {
                    if (item.getUsername().equals(loginPage.getUsernameField().getText())){
                        loginPage.getErrorLabel().setText("username already exists");
                        loginPage.getErrorLabel().setTextFill(Color.RED);
                        return;
                    }
                }
                User newUser = new User(loginPage.getUsernameField().getText(), loginPage.getPasswordField().getText());
                User.getAllUsers().add(newUser);
                User.addAllUsersToFile();
                loginPage.getTableView().getItems().add(newUser);
                loginPage.getErrorLabel().setText("new user created");
                loginPage.getErrorLabel().setTextFill(Color.GREEN);
            }
        });
    }

    private void login(User user) {
        User.setCurrentUser(user);
        //show dashboard
        DashboardController dashboardController = new DashboardController();
        JavaFxApplication.getPrimaryStage().setScene(new Scene(dashboardController.getDashboardPage(), 300, 500));
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }
}
