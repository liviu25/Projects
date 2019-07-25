package controller;


import domain.MessageTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.MessageTaskService;
import utils.MessageTaskChangeEvent;
import utils.Observer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MessageTaskController implements Observer<MessageTaskChangeEvent> {

    private MessageTaskService service;
    private ObservableList<MessageTask> model=FXCollections.observableArrayList();

    @FXML
    TableView<MessageTask> tableView;
    @FXML
    TableColumn<MessageTask,String> tableColumnDesc;
    @FXML
    TableColumn<MessageTask,String> tableColumnFrom;
    @FXML
    TableColumn<MessageTask,String> tableColumnTo;
    @FXML
    TableColumn<MessageTask,String> tableColumnData;

    @FXML
    public void initialize(){
        tableColumnDesc.setCellValueFactory(new PropertyValueFactory<MessageTask, String>("description"));
        tableColumnFrom.setCellValueFactory(new PropertyValueFactory<MessageTask, String>("from"));
        tableColumnTo.setCellValueFactory(new PropertyValueFactory<MessageTask, String>("to"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<MessageTask, String>("date"));

        tableView.setItems(model);
    }

    @Override
    public void update(MessageTaskChangeEvent messageTaskChangeEvent) {
        model.setAll(StreamSupport.stream(service.getAll().spliterator(),false)
                .collect(Collectors.toList()));
    }

    public void setMessageTaskService(MessageTaskService messageTaskService) {
        this.service=messageTaskService;
        messageTaskService.addObserver(this);
        initModel();
    }

    private void initModel() {
        List<MessageTask> list= StreamSupport.stream(service.getAll().spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(list);
    }

    @FXML
    public void handleAddMessage(ActionEvent ev)
    {
        showMessageTaskEditDialog(null);
    }

    @FXML
    public void handleDeleteMessage(ActionEvent ev)
    {
        MessageTask selected=tableView.getSelectionModel().getSelectedItem();
        if (selected!=null) {
            MessageTask deleted=service.deleteMessageTask(selected);
            if (null!=deleted)
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Delete","Studentul a fost sters cu succes!");
        }
        else MessageAlert.showErrorMessage(null, "Nu ati selectat nici un student!");
    }

    @FXML
    public void handleUpdateMessage(ActionEvent ev)
    {
        MessageTask selected=tableView.getSelectionModel().getSelectedItem();
        if(selected!=null){
            showMessageTaskEditDialog(selected);
        }
        else
            MessageAlert.showErrorMessage(null,"NU ati selectat nici un student");
    }


    public void showMessageTaskEditDialog(MessageTask messageTask) {
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/editMessageTaskView.fxml"));

            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Message");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            EditMessageTaskController  editMessageViewController = loader.getController();
            editMessageViewController.setService(service, dialogStage,messageTask);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
