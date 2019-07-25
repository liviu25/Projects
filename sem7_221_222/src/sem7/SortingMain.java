package sem7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sem7.controller.TasksController;
import sem7.repository.SortingTaskRepository;
import sem7.repository.SortingTaskValidator;
import sem7.service.TaskService;
import sem7.view.SortingTasksView;

/**
 * Created by grigo on 11/14/16.
 */
public class SortingMain extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SortingTasks Application");
        BorderPane pane=getView();
        Scene scene = new Scene(pane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static BorderPane getView(){
        SortingTaskRepository repo=new SortingTaskRepository(new SortingTaskValidator());
        TaskService service=new TaskService(repo);
        TasksController contr=new TasksController(service);
        SortingTasksView view=new SortingTasksView(contr);
        return view.getView();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
