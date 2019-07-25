package sample;

import Domain.Student;
import Repository.RepositoryException;
import Validator.ValidationException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class StudentView {
    BorderPane pane;
    TextField textID=new TextField();
    TextField textGupa=new TextField();
    TextField textNume=new TextField();
    TextField textEmail=new TextField();
    TextField textProfesor=new TextField();
    Controller controller;

    private TableView<Student> table = new TableView<>();


    public BorderPane getView() {
        return pane;
    }

    public StudentView(Controller controller) {
        this.controller = controller;
        initView();
    }

    private void initView() {
        pane=new BorderPane();
        pane.setRight(createStudent());
        pane.setCenter(createTable());
    }

    private GridPane createStudent() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15, 15, 15, 15));

        Text scenetitle = new Text("Student");
        grid.add(scenetitle, 0, 0, 2, 1);


        grid.add(new Label("ID"),0,1);
        grid.add(textID,1,1);

        grid.add(new Label("Grupa"),0,2);
        grid.add(textGupa,1,2);

        grid.add(new Label("Nume"),0,3);
        grid.add(textNume,1,3);

        grid.add(new Label("Email"),0,4);
        grid.add(textEmail,1,4);

        grid.add(new Label("Profesor"),0,5);
        grid.add(textProfesor,1,5);



        Button addButton=new Button("Add");
        Button deleteButton=new Button("Delete");
        Button updateButton=new Button("Update");
        Button cancelButton=new Button("Cancel");
        HBox hbBtn = new HBox(10);


        addButton.setOnAction(x->handleAdd());
        deleteButton.setOnAction(x->handleDelete());
        updateButton.setOnAction(x->handleUpdate());
        cancelButton.setOnAction(x->clearFields());
        hbBtn.getChildren().addAll(addButton, deleteButton,updateButton, cancelButton);

        grid.add(hbBtn,0,6,2,1);

        Button backButton=new Button("Back");
        grid.add(backButton,0,7);
        backButton.setOnAction(x->handleBack());

        return grid;
    }

    private void handleBack() {
        controller.back(pane);

    }

    private void handleUpdate() {
        String idStudent, grupa, nume, email, profesor;
        idStudent=textID.getText();
        grupa=textGupa.getText();
        nume=textNume.getText();
        email=textEmail.getText();
        profesor=textProfesor.getText();
        Student oldS=table.getSelectionModel().getSelectedItem();
        try{
            controller.updateStudent(oldS,idStudent,grupa,nume,email,profesor);

        }
        catch (ValidationException e)
        {
            Alert message=new Alert(Alert.AlertType.ERROR);
            message.show();
            System.out.println("Eroare");
        }
        clearFields();
    }

    private void handleAdd() {
        String idStudent, grupa, nume, email, profesor;
        idStudent=textID.getText();
        grupa=textGupa.getText();
        nume=textNume.getText();
        email=textEmail.getText();
        profesor=textProfesor.getText();
        try{
        controller.addStudent(idStudent,grupa,nume,email,profesor);


        }
        catch (ValidationException e)
        {
            Alert message=new Alert(Alert.AlertType.ERROR);
            message.setContentText("Student invalid");
            message.show();
        }
        catch (RepositoryException e)
        {
            Alert message=new Alert(Alert.AlertType.ERROR);
            message.setContentText("Studentul "+nume+" exista deja");
            message.show();
        }
        clearFields();
    }

    private void handleDelete() {
        System.out.println("delete");
        String idStudent, grupa, nume, email, profesor;
        idStudent=textID.getText();
        grupa=textGupa.getText();
        nume=textNume.getText();
        email=textEmail.getText();
        profesor=textProfesor.getText();
        try{
            controller.deleteStudent(idStudent,grupa,nume,email,profesor);
        }
        catch (ValidationException e)
        {
            Alert message=new Alert(Alert.AlertType.ERROR);
            message.show();
            System.out.println("Eroare");
        }
        clearFields();
    }

    private void showDetails(Student value)
    {
        if(value==null)
            clearFields();
        else
        {
            textID.setText(value.getID());
            textGupa.setText(value.getGrupa());
            textNume.setText(value.getNume());
            textEmail.setText(value.getEmail());
            textProfesor.setText(value.getProfesor());
        }
    }

    private void clearFields() {
        textID.setText("");
        textGupa.setText("");
        textNume.setText("");
        textEmail.setText("");;
        textProfesor.setText("");
    }

    private StackPane createTable() {
        StackPane pane=new StackPane();

        TableColumn<Student, String> idCol = new TableColumn<>("Id");
        TableColumn<Student, String> grupaCol = new TableColumn<>("Grupa");
        TableColumn<Student, String> numeCol = new TableColumn<>("Nume");
        TableColumn<Student, String> emailCol = new TableColumn<>("Email");
        TableColumn<Student, String> profesorCol = new TableColumn<>("Profesor");

        table.getColumns().addAll(idCol,grupaCol,numeCol,emailCol,profesorCol);

        profesorCol.setEditable(true);

        idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("idStudent"));
        grupaCol.setCellValueFactory(new PropertyValueFactory<Student, String>("grupa"));
        numeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("nume"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        profesorCol.setCellValueFactory(new PropertyValueFactory<Student, String>("profesor"));

        table.setItems(controller.getStudents());

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldvalue,newValue)->showDetails(newValue) );
        pane.getChildren().add(table);
        return pane;
    }




}
