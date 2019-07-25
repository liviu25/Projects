package sample;

import Domain.Nota;
import Domain.Student;
import Domain.Tema;
import Service.Service;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.NotaEvent;
import utils.Observer;

import java.util.Observable;

public class NoteController implements Observer<NotaEvent> {

    private Service service;

    @FXML
    TableView<Nota> table;

    @FXML
    TextField textStudent;

    @FXML
    TextField textValoare;
    @FXML
    TextArea textFeedback;

    @FXML
    ComboBox<String> temeComboBox;

    @FXML
    TextArea textArea;

    @FXML
    Button okButton;

    @FXML
    Button cancelButton;

    Nota nota;

    ObservableList<Nota> listNote;
    ObservableList<String> listTeme;
    private String feedback;

    public void setService(Service service) {
        this.service=service;
        service.addObserver(this);
    }

    public void initNote() {
        listNote= FXCollections.observableArrayList();
        Iterable<Nota> iterable=service.getNote();
        for(Nota nota:iterable)
        {
            listNote.add(nota);
        }

        table.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldvalue,newValue)->showDetails(newValue) );

        table.setItems(listNote);


        listTeme= FXCollections.observableArrayList();
        Iterable<Tema> iterableTeme=service.getTeme();
        for(Tema tema:iterableTeme)
        {
            listTeme.add(tema.getID());
        }
        listTeme.forEach(x-> System.out.println(x));
        temeComboBox.setItems(listTeme);
    }

    private void showDetails(Nota value) {
        if(value!=null) {
            String nume = service.findStudent(value.getIdStudent()).getNume();
            textStudent.setText(nume);
            temeComboBox.getSelectionModel().select(value.getIdTema());
            textValoare.setText(String.valueOf( value.getValoare()));
        }
    }

    public void handleAdd()
    {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("previewNota.fxml"));
            Pane myPane = loader.load();
            Scene scene = new Scene(myPane);

            String preview=new String();
            preview+="Student"+ textStudent.getText() + "\n" +
                    "Tema" + temeComboBox.getSelectionModel().getSelectedItem()+"\n"+
                    "Valoare" + textValoare.getText()+"\n"+
                    "Feedback"+textFeedback.getId()+"\n";



            Nota n=new Nota(findStudentbyNume(textStudent.getText()),temeComboBox.getSelectionModel().getSelectedItem(),Integer.parseInt(textValoare.getText()));

            PreviewController noteController2=loader.getController();
            noteController2.setService(service);
            noteController2.initPreview(preview,n,textFeedback.getText());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    private String findStudentbyNume(String text) {
        for (Student student : service.getAllStudents()) {
            if(student.getNume().contains(text))
                return student.getID();
        }
        return null;
    }

    public void initPreview(String prev,Nota nota,String feedback)
    {
        textArea.setEditable(false);
        textArea.setText(prev);
        this.feedback=feedback;
        this.nota=nota;

    }

    @Override
    public void update(NotaEvent notaEvent) {
        listNote.add(notaEvent.getData());
    }
}
