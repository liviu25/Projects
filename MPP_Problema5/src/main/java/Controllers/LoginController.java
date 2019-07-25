package Controllers;

import Model.User;
import Service.ConcursException;
import Service.IServer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class LoginController {

    public TextField userText;
    public PasswordField passwordText;
    public Button loginButton;
    public Pane childPane;

    ConcursController concursController;

    public void setConcursController(ConcursController concursController) {
        this.concursController = concursController;
    }

    IServer server;

    public void setServer(IServer server) {
        this.server = server;
    }

    public void loginHandler(ActionEvent actionEvent) {
        String id=userText.getText();
        String password=passwordText.getText();
        User user=new User(id,password);
        System.out.println(user);

        try {
            server.login(user, this.concursController);
            concursController.setUser(user);
            concursController.setConcursServer(server);
            concursController.init();
            start();
        }
        catch (ConcursException ex) {
            showErrorMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showErrorMessage() {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Incorect username or password!");

        alert.showAndWait();
    }

    private void start() {

        Scene myScene = new Scene(childPane);
        Stage concursStage=new Stage();
        concursStage.setScene(myScene);



        concursStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                concursController.logout();
                System.exit(0);
            }
        });
        concursStage.show();
        this.loginButton.getScene().getWindow().hide();
    }

    public void setChild(Pane pane) {
        this.childPane=pane;
    }
}
