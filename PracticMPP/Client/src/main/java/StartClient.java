import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import practic.client.LoginController;
import practic.service.IServer;

public class StartClient extends Application {
    public static void main(String[] args) {
            launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("loginView.fxml"));
        Pane pane = loader.load();
        LoginController loginController=loader.getController();

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();

        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        IServer server=(IServer) factory.getBean("gameServer");

        loginController.setServer(server);
    }
}
