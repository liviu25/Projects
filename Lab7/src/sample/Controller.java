package sample;


import Domain.Student;
import Service.Service;
import Validator.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Observer;

public class Controller  {
     Service service;
    ObservableList<Student> list;

    public Controller(Service service) {
        this.service = service;

        list= FXCollections.observableArrayList();
        Iterable<Student> iterable=service.getAllStudents();
        for(Student student:iterable)
        {
            list.add(student);
        }
    }

    public ObservableList<Student> getStudents(){

        return list;
    }

    public void back(BorderPane pane)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
            Pane myPane = (Pane) loader.load();
            MainMenuController controller = loader.getController();
            controller.setService(service);
            Scene scene = new Scene(myPane);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addStudent(String idStudent,String grupa,String nume,String email,String profesor) throws ValidationException
    {
        Student s=new Student(idStudent,grupa,nume,email,profesor);
        service.addStudent(idStudent,grupa,nume,email,profesor);
        list.add(s);
    }

    public void updateStudent(Student oldS,String idStudent,String grupa,String nume,String email,String profesor)
    {
        Student s=new Student(idStudent,grupa,nume,email,profesor);
        service.updateStudent(idStudent,grupa,nume,email,profesor);
        list.remove(oldS);
        list.add(s);
    }

    public void deleteStudent(String idStudent,String grupa,String nume,String email,String profesor)
    {
        Student s=new Student(idStudent,grupa,nume,email,profesor);
        service.deleteStudent(idStudent,grupa,nume,email,profesor);

        list.remove(s);
    }
}
