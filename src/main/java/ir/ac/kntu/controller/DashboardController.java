package ir.ac.kntu.controller;

import ir.ac.kntu.JavaFxApplication;
import ir.ac.kntu.model.User;
import ir.ac.kntu.view.DashboardPage;
import ir.ac.kntu.view.GamePage;
import ir.ac.kntu.view.LoginPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class DashboardController {
    private DashboardPage dashboardPage;


    public DashboardController(){
        dashboardPage = new DashboardPage();
        initiateButtons();
    }

    private void initiateButtons() {
        initcreateMapButton();
        initPlayButton();
    }

    private void initPlayButton() {
        dashboardPage.getPlayButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GamePageController gamePageController = new GamePageController(1, 0);
//                Scene scene = new Scene(gamePageController.getGamePage(), 900, 900);
//                JavaFxApplication.getPrimaryStage().setScene(scene);
            }
        });
    }

    private void initcreateMapButton() {
        dashboardPage.getCreateMapButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MapCreationController mapCreationController = new MapCreationController();
                JavaFxApplication.getPrimaryStage().setScene(new Scene(mapCreationController.getMapCreationPage(), 900, 800));
            }
        });
    }


    public DashboardPage getDashboardPage() {
        return dashboardPage;
    }
}
