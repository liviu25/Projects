package Controllers;

import Model.Participant;
import Model.Proba;
import Model.User;
import Service.ConcursException;
import Service.IObserver;
import Service.IServer;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Pair;

import java.io.IOException;

public class ConcursController implements IObserver {
    @FXML
    public TableColumn<Proba, Integer> colID;

    @FXML
    public TableColumn<Proba, String> colTip;

    @FXML
    public TableColumn<Proba, Integer> colVarstaMin;

    @FXML
    public TableColumn<Proba, Integer> colVarstaMax;

    @FXML
    public TableColumn<Proba, Integer> colNrParticipanti;

    @FXML
    public TableColumn<Participant, Integer> colIdParticipant;

    @FXML
    public TableColumn<Participant, String> colNume;

    @FXML
    public TableColumn<Participant, String> colPrenume;

    @FXML
    public TableColumn<Participant, Integer> colVarsta;

    @FXML
    public Button addButton;

    private IServer concursServer;



    User user;

    @FXML
    TableView<Proba> probeTable;

    @FXML
    TableView<Participant> participantiTable;

    Stage inscriereStage=new Stage();

    ObservableList<Proba> probeObservableList = FXCollections.observableArrayList();

    ObservableList<Participant> participantObservableList=FXCollections.observableArrayList();



    public void setConcursServer(IServer concursService) {
        this.concursServer = concursService;
    }

    public void init() throws IOException, ConcursException {

        LoadList();


        colID.setCellValueFactory(x->new SimpleObjectProperty<>( x.getValue().getID()));
        colTip.setCellValueFactory(x->new SimpleObjectProperty<>(x.getValue().getTipProba().toString()));
        colVarstaMin.setCellValueFactory(x->new SimpleObjectProperty<>(x.getValue().getVarstaMin()));
        colVarstaMax.setCellValueFactory(x->new SimpleObjectProperty<>(x.getValue().getVarstaMax()));
        colNrParticipanti.setCellValueFactory(x->new SimpleObjectProperty<>(x.getValue().getNrParticipanti()));


        colIdParticipant.setCellValueFactory(x->new SimpleObjectProperty<>(x.getValue().getID()));
        colNume.setCellValueFactory(x->new SimpleObjectProperty<>(x.getValue().getNume()));
        colPrenume.setCellValueFactory(x->new SimpleObjectProperty<>(x.getValue().getPrenume()));
        colVarsta.setCellValueFactory(x->new SimpleObjectProperty<>(x.getValue().getVarsta()));



        probeTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable,oldvalue,newValue)-> {
                    try {
                        probaSelected();
                    } catch (ConcursException e) {
                        e.printStackTrace();
                    }
                }
        );

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("..\\View\\InscriereView.fxml"));
        Pane myPane = (Pane)loader.load();

        InscriereController inscriereController=loader.getController();
        inscriereController.setConcursServer(concursServer);
        inscriereController.init();


        Scene myScene = new Scene(myPane);

        inscriereStage.setScene(myScene);

    }

    private void LoadList() throws ConcursException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    probeObservableList.clear();
                    for (Proba proba : concursServer.getProbeAndNrParticipanti()) {
                        probeObservableList.add(proba);
                    }
                    probeTable.setItems(probeObservableList);
                } catch (ConcursException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void probaSelected() throws ConcursException {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(!probeTable.getSelectionModel().isEmpty())
                {
                    participantObservableList.clear();
                    Integer idProba = probeTable.getSelectionModel().getSelectedItem().getID();
                    try {
                        for (Participant participant : concursServer.getParticipantiByProba(idProba)) {
                            participantObservableList.add(participant);
                        }
                    } catch (ConcursException e) {
                        e.printStackTrace();
                    }
                }


                participantiTable.setItems(participantObservableList);
            }
        });

    }



    @FXML
    private void exit()
    {
        logout();
        Stage stage = (Stage) probeTable.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage,WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.close();
    }

    public void openAdd() throws IOException {
        inscriereStage.show();


    }

    public void setUser(User user) {
        this.user = user;
    }

    void logout() {
        try {
            concursServer.logout(user, this);
        } catch (ConcursException e) {
            System.out.println("Logout error " + e);
        }

    }

    @Override
    public void probeUpdated() throws ConcursException {

        LoadList();
    }
}
