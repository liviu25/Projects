package Controllers;

import Model.Participant;
import Model.TipProba;
import Service.ConcursException;
import Service.IServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class InscriereController {

    public TextField numeTextField;
    public TextField prenumeTextField;
    public TextField varstaTextField;
    public ComboBox<TipProba> proba1ComboBox;
    public ComboBox<TipProba> proba2ComboBox;
    public Button inscriereButton;
    public Button cancelButton;

    ObservableList<TipProba> tipProbaObservableList= FXCollections.observableArrayList();

    public void setConcursServer(IServer concursServer) {
        this.concursServer = concursServer;
    }

    private IServer concursServer;



    public void init()
    {
        tipProbaObservableList.setAll(TipProba.values());


        proba1ComboBox.setItems(tipProbaObservableList);
        proba2ComboBox.setItems(tipProbaObservableList);


    }

    public void inscriereParticipant() throws ConcursException {

        Participant participant=
                new Participant(1,numeTextField.getText(),prenumeTextField.getText(),Integer.valueOf( varstaTextField.getText()));

        String proba1=null;
        String proba2=null;
        if(!proba1ComboBox.getSelectionModel().isEmpty())
            proba1=proba1ComboBox.getSelectionModel().getSelectedItem().toString();
        if(!proba2ComboBox.getSelectionModel().isEmpty())
            proba2=proba2ComboBox.getSelectionModel().getSelectedItem().toString();

        concursServer.inscriereParticipant(participant,proba1,proba2);

        Stage stage = (Stage) cancelButton.getScene().getWindow();
//        stage.fireEvent(new WindowEvent(stage,WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.hide();
    }

    public void exit() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.hide();
    }


}
