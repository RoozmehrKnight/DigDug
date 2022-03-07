package ir.ac.kntu.controller;

import ir.ac.kntu.JavaFxApplication;
import ir.ac.kntu.model.GameMap;
import ir.ac.kntu.view.MapCreationPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class MapCreationController {
    private MapCreationPage mapCreationPage;

    public MapCreationPage getMapCreationPage() {
        return mapCreationPage;
    }

    public MapCreationController(){
        mapCreationPage = new MapCreationPage();
        InitiateButtons();
    }

    private void InitiateButtons() {
        initBackButton();
        initSaveButton();
    }

    private void initBackButton() {
        mapCreationPage.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DashboardController dashboardController = new DashboardController();
                JavaFxApplication.getPrimaryStage().setScene(new Scene(dashboardController.getDashboardPage(), 300, 500));
            }
        });
    }

    private void initSaveButton() {
        mapCreationPage.getSaveMapButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameMap.writeMapToFile(GameMap.getCurrentMap(),System.getProperty("user.dir")+"/files/maps/"+
                        mapCreationPage.getNameText().getText()+".txt");
            }
        });
    }
}
