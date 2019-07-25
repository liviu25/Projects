package controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import model.Bug;
import model.User;
import service.IProgrammerClient;
import service.IService;
import service.ServiceException;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProgrammerController extends UnicastRemoteObject implements IProgrammerClient, Serializable {
    public Button removeButton;
    public Button logoutButton;
    @FXML
    private TextField denumireText;
    @FXML
    private TextArea descriereText;
    @FXML
    private TableView<Bug> bugsTable;
    @FXML
    private TableColumn<Bug,String> idColumn;
    @FXML
    private TableColumn<Bug,String> deumireColumn;
    @FXML
    private TableColumn<Bug,String> statusColumn;

    private IService service;
    private User currentUser;
    private ObservableList<Bug> bugsObservableList;

    public ProgrammerController() throws RemoteException {
    }

    @FXML
    public void initialize(){
        idColumn.setCellValueFactory(x->new SimpleObjectProperty( x.getValue().getId()));
        deumireColumn.setCellValueFactory(x->new SimpleObjectProperty<>( x.getValue().getDenumire()));
        statusColumn.setCellValueFactory(x->new SimpleObjectProperty<>( x.getValue().getStatus()));

        bugsObservableList = FXCollections.observableArrayList();

        bugsTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable,oldvalue,newValue)-> {
                    bugSelected();
                }
        );
    }

    private void bugSelected() {
        if(!bugsTable.getSelectionModel().isEmpty()) {
            denumireText.setText(bugsTable.getSelectionModel().getSelectedItem().getDenumire());
            descriereText.setText(bugsTable.getSelectionModel().getSelectedItem().getDescriere());
            if(bugsTable.getSelectionModel().getSelectedItem().equals(currentBug))
            {
                removeButton.setDisable(false);
            }
            else {
                removeButton.setDisable(true);
            }
        }
    }
    private void loadList() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                bugsObservableList.clear();
                for (Bug bug : service.getBugs()) {
                    bugsObservableList.add(bug);
//            System.out.println(user1);
                }
                bugsTable.setItems(bugsObservableList);
            }
        });


    }


    @Override
    public void bugsListUpdated() throws RemoteException {
        loadList();
    }

    Bug currentBug;

    public void selectHandler(ActionEvent actionEvent) {
        if(currentBug==null)
        {
            currentBug=bugsTable.getSelectionModel().getSelectedItem();
            if(currentBug.getStatus().equals("nerezolvat"))
            {
                currentBug.setStatus("in progres");
                service.updateBug(currentBug);
                removeButton.setDisable(false);
            }
        }



    }

    public void removeHandler(ActionEvent actionEvent) {
        if(currentBug!=null)
        {
            service.deleteBug(currentBug);
            removeButton.setDisable(true);
        }
    }

    public void setService(IService service) {
        this.service = service;
        try {
            service.addObeserver(currentUser,this);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        denumireText.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    currentBug.setStatus("nerezolvat");
                    service.updateBug(currentBug);
                    service.logout(currentUser,ProgrammerController.this);
                    Platform.exit();
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        loadList();
    }

    public void logout(ActionEvent actionEvent) throws ServiceException {


        currentBug.setStatus("nerezolvat");
        service.updateBug(currentBug);
        service.logout(currentUser,this);
        Platform.exit();
    }


}
