package controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import model.User;
import service.IAdministratorClient;
import service.IService;
import service.ServiceException;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AdminController extends UnicastRemoteObject implements IAdministratorClient,Serializable {
    public TextField usernameText;
    public TextField nameText;
    public TextField passwordText;
    public TableView<User> usersTable;
    public TableColumn<User,String> userColumn;
    public TableColumn<User,String> nameColumn;
    public TableColumn<User,String> passwordColumn;
    public TableColumn<User,String> typeColumn;
    public ComboBox<String> typeComboBox;

    private IService service;
    private User currentUser;
    private ObservableList<User> userObservableList;

    public AdminController() throws RemoteException {
    }

    @FXML
    public void initialize()
    {

        userColumn.setCellValueFactory(x->new SimpleObjectProperty<>( x.getValue().getUsername()));
        nameColumn.setCellValueFactory(x->new SimpleObjectProperty<>( x.getValue().getName()));
        passwordColumn.setCellValueFactory(x->new SimpleObjectProperty<>( x.getValue().getPassword()));
        typeColumn.setCellValueFactory(x->new SimpleObjectProperty<>( x.getValue().getType()));

        userObservableList = FXCollections.observableArrayList();

        typeComboBox.setItems(FXCollections.observableArrayList("Admin","Tester","Programmer"));

        usersTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable,oldvalue,newValue)-> {
                    userSelected();
                }
        );

//        loadList();
    }

    private void userSelected() {
        if(!usersTable.getSelectionModel().isEmpty()) {
            usernameText.setText(usersTable.getSelectionModel().getSelectedItem().getUsername());
            nameText.setText(usersTable.getSelectionModel().getSelectedItem().getName());
            passwordText.setText(usersTable.getSelectionModel().getSelectedItem().getPassword());
            typeComboBox.getSelectionModel().select(usersTable.getSelectionModel().getSelectedItem().getType().toString());
        }
    }

    private void loadList() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                userObservableList.clear();
                for (User user1 : service.getUsers()) {
                    userObservableList.add(user1);
//            System.out.println(user1);
                }
                usersTable.setItems(userObservableList);
            }
        });


    }

    @Override
    public void usersListUpdated() throws RemoteException {
        loadList();
    }

    public void addHandler(ActionEvent actionEvent) {
        String username = usernameText.getText();
        String name = nameText.getText();
        String password = passwordText.getText();
        String type = typeComboBox.getSelectionModel().getSelectedItem();
        User user1=new User(username,name,password,type);
        service.addUser(user1);
    }

    public void deleteHandler(ActionEvent actionEvent) {
        String username = usernameText.getText();
        String name = nameText.getText();
        String password = passwordText.getText();
        String type = typeComboBox.getSelectionModel().getSelectedItem();
        User user1=new User(username,name,password,type);
        service.deleteUser(user1);
    }

    public void setService(IService service) {
        this.service = service;
        try {
            service.addObeserver(currentUser,this);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        usernameText.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    service.logout(currentUser,AdminController.this);
                    Platform.exit();
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setUser(User user) {
        this.currentUser = user;
        loadList();
    }

    public void updateHandler(ActionEvent actionEvent) {
        String username = usernameText.getText();
        String name = nameText.getText();
        String password = passwordText.getText();
        String type = typeComboBox.getSelectionModel().getSelectedItem();
        User user1=new User(username,name,password,type);
        service.updateUser(user1);

    }
}
