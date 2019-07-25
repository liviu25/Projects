package tasks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tasks.repository.SortingTaskRepository;
import tasks.repository.SortingTaskValidator;
import tasks.service.TaskService;
import tasks.utils.ObservableTaskRunner;
import tasks.utils.TaskStack;
import tasks.view.SortingTasksFXML;

/**
 * Created by grigo on 11/22/16.
 */
public class SortingFXMain extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FXML TableView Example");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("view/tasks.fxml"));
        Pane myPane = (Pane) loader.load();
       SortingTasksFXML ctrl=loader.getController();

        ctrl.setTasksService(getTasksService());
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    static TaskService  getTasksService(){
        SortingTaskRepository repo=new SortingTaskRepository(new SortingTaskValidator());
        ObservableTaskRunner runner=new ObservableTaskRunner(new TaskStack());
        TaskService service=new TaskService(repo,runner);
        return service;
    }
}
