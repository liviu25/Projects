import Controllers.ConcursController;
import Controllers.LoginController;
import Networking.objectProtocol.ServerObjectProxy;
import Repository.InscriereDbRepository;
import Repository.LoginRepository;
import Repository.ParticipantDbRepository;
import Repository.ProbaDbRepository;
import Servers.ConcursServer;
import Service.IServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileReader;
import java.util.Properties;

public class StartLogin extends Application {
    private static int defaultPort=55555;
    private static String defaultHost="localhost";
    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loginLoader=new FXMLLoader();
        loginLoader.setLocation(getClass().getResource("\\View\\LoginView.fxml"));
        Pane loginPane = loginLoader.load();

        LoginController loginController=loginLoader.getController();



        Scene loginScene = new Scene(loginPane);
        primaryStage.setScene(loginScene);
        primaryStage.show();


        FXMLLoader mainWindowLoader=new FXMLLoader();
        mainWindowLoader.setLocation(getClass().getResource("\\View\\ConcursView.fxml"));
        Pane mainWindowPane = mainWindowLoader.load();

//        Properties props=new Properties();
//        props.load(new FileReader("bd.config"));
//
//        ParticipantDbRepository participantDbRepository = new ParticipantDbRepository(props);
//        ProbaDbRepository probaDbRepository=new ProbaDbRepository(props);
//        InscriereDbRepository inscriereDbRepository=new InscriereDbRepository(props);
//        LoginRepository loginRepository=new LoginRepository(props);
//        ConcursServer concursServer =new ConcursServer(loginRepository,participantDbRepository,probaDbRepository,inscriereDbRepository);

        IServer server=new ServerObjectProxy(defaultHost,defaultPort);

        ConcursController concursController=mainWindowLoader.getController();
//        concursController.setConcursServer(concursServer);
//        concursController.init();



        loginController.setChild(mainWindowPane);
        loginController.setConcursController(concursController);



        loginController.setServer(server);


    }
}
