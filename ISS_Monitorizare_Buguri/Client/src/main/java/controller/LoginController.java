package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;
import service.IService;
import service.ServiceException;

import java.io.IOException;


public class LoginController {
    public PasswordField passwordText;
    public TextField usernameText;

    private IService service;

    public void loginHandler(ActionEvent actionEvent) {
        String username=usernameText.getText();
        String password=passwordText.getText();
        try {
            User user = service.login(username, password);
            if(user.getType().equals("Admin"))
            {
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("..\\adminView.fxml"));
                Pane pane = loader.load();
                AdminController adminController=loader.getController();


                Scene scene = new Scene(pane);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();
                adminController.setUser(user);
                adminController.setService(service);

                this.usernameText.getScene().getWindow().hide();
            }
            if(user.getType().equals("Programmer"))
            {
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("..\\programmerView.fxml"));
                Pane pane = loader.load();
                ProgrammerController programmerController=loader.getController();


                Scene scene = new Scene(pane);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();
                programmerController.setCurrentUser(user);
                programmerController.setService(service);

                this.usernameText.getScene().getWindow().hide();
            }
            if(user.getType().equals("Tester"))
            {
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("..\\testerView.fxml"));
                Pane pane = loader.load();
                TesterController testerController=loader.getController();


                Scene scene = new Scene(pane);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();
                testerController.setCurrentUser(user);
                testerController.setService(service);

                this.usernameText.getScene().getWindow().hide();
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setservice(IService service) {
        this.service = service;
    }
}
