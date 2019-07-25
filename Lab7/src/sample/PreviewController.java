package sample;

import Domain.Nota;
import Service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import utils.NotaEvent;
import utils.Observable;
import utils.Observer;


public class PreviewController implements Observer<NotaEvent> {


    @FXML
    Button okButton;

    @FXML
    Button cancelButton;

    @FXML
    TextArea textArea;


    Service service;
    String feedback;
    Nota nota;

    public void setService(Service service) {
        this.service=service;
    }

    public void initPreview(String prev, Nota nota, String feedback)
    {
        textArea.setEditable(false);
        textArea.setText(prev);
        this.feedback=feedback;
        this.nota=nota;

    }

    public void handleOk(){
        Nota nota1=new Nota(nota.getIdStudent(),nota.getIdTema(),nota.getValoare());
        service.addNota(nota.getIdStudent(),nota.getIdTema(),nota.getValoare(),feedback);
        //listNote.add(nota1);
        Stage stage=(Stage)okButton.getScene().getWindow();
        stage.close();
    }

    public void handleCancel(){
        Stage stage=(Stage)okButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void update(NotaEvent notaEvent) {

    }
}
