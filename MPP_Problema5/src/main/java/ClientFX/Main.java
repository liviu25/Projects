package ClientFX;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
/*
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("..\\View\\ConcursView.fxml"));
        Pane myPane = (Pane)loader.load();

        Properties props=new Properties();
        props.load(new FileReader("bd.config"));

        ParticipantDbRepository participantDbRepository = new ParticipantDbRepository(props);
        ProbaDbRepository probaDbRepository=new ProbaDbRepository(props);
        InscriereDbRepository inscriereDbRepository=new InscriereDbRepository(props);



        ConcursService concursService=new ConcursService(participantDbRepository,probaDbRepository,inscriereDbRepository);

        ConcursController concursController=loader.getController();
        concursController.setConcursService(concursService);
        concursController.init();

        Scene myScene = new Scene(myPane);
        Stage stage=primaryStage;
        stage.setScene(myScene);
        stage.show();
*/
    }
}
