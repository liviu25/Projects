package sample;

import Domain.Student;
import Domain.Tema;
import Repository.NoteXMLRepository;
import Repository.StudentFileRepository;
import Repository.TemeFileRepository;
import Service.Service;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemaValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenuController {
    @FXML
    Button studentsMenuButton;

    @FXML
    TableView<Tema> tableTeme;


    Service service;

    ObservableList<Tema> listTeme;
    public void setService(Service service)
    {
        this.service=service;
    }


    @FXML
    public void showStudentsMenu(){

        Controller controller=new Controller(service);
        StudentView view=new StudentView(controller);

        try {

            BorderPane pane=view.getView();
            Scene scene = new Scene(pane, 800, 500);
            Stage stage = (Stage) studentsMenuButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        /*
        BorderPane pane=view.getView();
        Scene scene = new Scene(pane, 800, 500);

        Stage stage=new Stage();
        stage.setScene(scene);
        */
    }



    public void showTemeMenu(ActionEvent actionEvent) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("temeView.fxml"));
            Pane myPane = loader.load();
            Scene scene = new Scene(myPane, 800, 500);

            TemeController temeController=loader.getController();
            temeController.setService(service);
            temeController.initTeme();
            //Stage stage = (Stage) studentsMenuButton.getScene().getWindow();
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void showNoteMenu(ActionEvent actionEvent) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("noteView.fxml"));
            Pane myPane = loader.load();
            Scene scene = new Scene(myPane, 800, 500);

            NoteController noteController=loader.getController();
            noteController.setService(service);
            noteController.initNote();
            Stage stage = (Stage) studentsMenuButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void showRapoarte(ActionEvent actionEvent) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("filtrariView.fxml"));
            Pane myPane = loader.load();
            Scene scene = new Scene(myPane, 800, 500);

            FiltrariController filtrariController=loader.getController();
            filtrariController.setService(service);
            filtrariController.init();

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
