package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import model.Bug;
import model.User;
import service.IService;
import service.ITesterClient;
import service.ServiceException;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TesterController extends UnicastRemoteObject implements ITesterClient, Serializable {

    public TextField denumireText;
    public TextArea descriereText;
    public Button addButton;

    private IService service;
    private User currentUser;

    public TesterController() throws RemoteException {
    }

    @FXML
    public void initialize(){

    }

    public void addHandler(ActionEvent actionEvent) {
        String denumire= denumireText.getText();
        String descriere = descriereText.getText();
        Bug bug=new Bug(denumire,descriere);
        service.addBug(bug);
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
                    service.logout(currentUser,TesterController.this);
                    Platform.exit();
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
