import Repository.NoteFileRepository;
import Repository.NoteXMLRepository;
import Repository.StudentFileRepository;
import Repository.TemeFileRepository;
import Service.Service;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemaValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.MainMenuController;

public class MainGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Main Menu");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sample/mainMenu.fxml"));
        Pane myPane = loader.load();
        //MainMenuController controller=loader.getController();

        StudentValidator studentValidator=new StudentValidator();
        NotaValidator notaValidator=new NotaValidator();
        TemaValidator temaValidator=new TemaValidator();

        StudentFileRepository studentRepository=new StudentFileRepository(studentValidator,"Studenti.txt");
        TemeFileRepository temeRepository=new TemeFileRepository(temaValidator,"Teme.txt");
        NoteFileRepository noteRepository=new NoteFileRepository(notaValidator,"Note.txt");
        //NoteXMLRepository noteRepository=new NoteXMLRepository(notaValidator,"Note.xml");

        Service service=new Service(studentRepository,temeRepository,noteRepository);

        MainMenuController controller =loader.getController();
        controller.setService(service);
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
