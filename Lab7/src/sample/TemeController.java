package sample;

import Domain.Tema;
import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class TemeController {
    @FXML
    TableView<Tema> tableTeme;

    Service service;

    public void setService(Service service)
    {
        this.service=service;
    }

    ObservableList<Tema> listTeme;
    public void initTeme()
    {

        listTeme= FXCollections.observableArrayList();
        Iterable<Tema> iterable=service.getTeme();
        for(Tema tema:iterable)
        {
            listTeme.add(tema);
        }

        tableTeme.setItems(listTeme);


    }
}
