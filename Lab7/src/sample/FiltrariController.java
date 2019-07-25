package sample;

import Domain.Nota;
import Domain.Student;
import Domain.Tema;
import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FiltrariController {

    Service service;
    @FXML
    ComboBox<String> temaComboBox;

    @FXML
    ComboBox<String> temaComboBox2;

    @FXML
    ComboBox<String> grupaComboBox;

    @FXML
    ComboBox<String> saptamanaComboBox;

    @FXML
    Button button1;

    @FXML
    TextField textStudent;

    public void setService(Service service)
    {
        this.service=service;
    }

    public void init()
    {
        ObservableList<String> listTeme= FXCollections.observableArrayList();
        service.getTeme().forEach(tema -> listTeme.add(tema.getID()));
        temaComboBox.setItems(listTeme);
        temaComboBox2.setItems(listTeme);

        ObservableList<String> listGrupe= FXCollections.observableArrayList("221","222","223","224","225","226","227");
        grupaComboBox.setItems(listGrupe);

        ObservableList<String> listSaptamani= FXCollections.observableArrayList("1","2","3","3","5","6","7","8","9","10","11","12","13","14");
        saptamanaComboBox.setItems(listSaptamani);


    }


    public void showNotaTema()
    {
        ListView<String> notaListView=new ListView<>();
        ObservableList<String> note=FXCollections.observableArrayList();
        String idTema=temaComboBox.getSelectionModel().getSelectedItem();
        service.filterNotaTema(idTema).forEach(x->note.add(
                "idSstudent: "+x.getIdStudent()+";idTema: "+x.getIdTema()+";nota: "+x.getValoare()));
        notaListView.setItems(note);

        Pane pane=new Pane();
        pane.getChildren().add(notaListView);
        Scene scene=new Scene(pane);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public void showNoteStudenti()
    {

        String nume=textStudent.getText();
        Iterable<Student> iterable=service.getAllStudents();
        Student s=new Student("","","","","");
        for(Student student:iterable)
        {
            if(student.getNume().contains(nume))
            {
                s=student;
                break;
            }
        }


        ListView<String> notaListView=new ListView<>();
        ObservableList<String> note=FXCollections.observableArrayList();

        service.filterStudentTema(s.getID()).forEach(x->note.add(
                "idSstudent: "+x.getIdStudent()+";idTema: "+x.getIdTema()+";nota: "+x.getValoare()));
        notaListView.setItems(note);

        Pane pane=new Pane();
        pane.getChildren().add(notaListView);
        Scene scene=new Scene(pane);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void showGrupaTema()
    {
        String grupa=grupaComboBox.getSelectionModel().getSelectedItem();
        String tema=temaComboBox2.getSelectionModel().getSelectedItem();

        ListView<String> notaListView=new ListView<>();
        ObservableList<String> note=FXCollections.observableArrayList();

        service.filterGrupaTema(grupa,tema).forEach(x->note.add(
                "idSstudent: "+x.getIdStudent()+";idTema: "+x.getIdTema()+";nota: "+x.getValoare()));
        notaListView.setItems(note);

        Pane pane=new Pane();
        pane.getChildren().add(notaListView);
        Scene scene=new Scene(pane);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void showNoteSaptamana()
    {
        ListView<String> notaListView=new ListView<>();
        ObservableList<String> note=FXCollections.observableArrayList();

        service.filterNoteSaptamana(Integer.parseInt( saptamanaComboBox.getSelectionModel().getSelectedItem())).forEach(x->note.add(
                "idSstudent: "+x.getIdStudent()+";idTema: "+x.getIdTema()+";nota: "+x.getValoare()));
        notaListView.setItems(note);

        Pane pane=new Pane();
        pane.getChildren().add(notaListView);
        Scene scene=new Scene(pane);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
