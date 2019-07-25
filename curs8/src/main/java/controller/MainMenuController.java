package controller;

import domain.MessageTask;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utils.MessageTaskChangeEvent;
import utils.Observer;

public class MainMenuController {

    Stage primaryStage;
    AnchorPane messages;
    AnchorPane grades;



    @FXML
    public void handleMessageTaskCRUD()
    {
        //this.primaryStage.setScene(messageTaskScene);
        BorderPane rootLayout= (BorderPane) this.primaryStage.getScene().getRoot();
        rootLayout.setCenter(messages);

    }

    @FXML
    public void handleGradeCRUD()
    {
        BorderPane rootLayout= (BorderPane) this.primaryStage.getScene().getRoot();
        rootLayout.setCenter(grades);

    }

    public void setCenterMessageLayout(AnchorPane messageTaskLayout) {
        this.messages=messageTaskLayout;
    }

    public void setCenterGradeLayout(AnchorPane gradeLayout) {
        this.grades=gradeLayout;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage=primaryStage;
    }
}
