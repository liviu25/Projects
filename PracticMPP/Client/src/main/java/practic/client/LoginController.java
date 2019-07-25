package practic.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import practic.model.User;
import practic.service.IServer;
import practic.service.ServiceException;

import java.io.IOException;

public class LoginController {

    public PasswordField passwordText;
    public TextField usernameText;

    private IServer server;

    public void loginHandler(ActionEvent actionEvent) {
        String username=usernameText.getText();
        String password=passwordText.getText();
        try {
            User user = server.login(username, password);

            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("..\\..\\mainView.fxml"));
            Pane pane = loader.load();
            MainController mainController=loader.getController();


            Scene scene = new Scene(pane);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.setTitle(username+" window");
            stage.show();
            mainController.setUser(user);
            mainController.setService(server);

            this.usernameText.getScene().getWindow().hide();


        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setServer(IServer server) {
        this.server=server;
    }
}
