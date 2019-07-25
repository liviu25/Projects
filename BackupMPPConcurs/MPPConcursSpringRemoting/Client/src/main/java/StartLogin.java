import concurs.client.Controllers.ConcursController;
import concurs.client.Controllers.LoginController;
import concurs.model.Proba;
import concurs.service.IServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartLogin extends Application {
    private static int defaultPort=55555;
    private static String defaultHost="localhost";
    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loginLoader=new FXMLLoader();
        loginLoader.setLocation(getClass().getResource("View\\LoginView.fxml"));
        Pane loginPane = loginLoader.load();

        LoginController loginController=loginLoader.getController();






        FXMLLoader mainWindowLoader=new FXMLLoader();
        mainWindowLoader.setLocation(getClass().getResource("View\\ConcursView.fxml"));




        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        IServer server=(IServer) factory.getBean("concursServer");



        ConcursController concursController=(ConcursController) factory.getBean("concursController");
                mainWindowLoader.setController(concursController);

        Pane mainWindowPane = mainWindowLoader.load();
        Scene loginScene = new Scene(loginPane);
        primaryStage.setScene(loginScene);
        primaryStage.show();




        loginController.setChild(mainWindowPane);
        loginController.setConcursController(concursController);



        loginController.setServer(server);


    }
}
