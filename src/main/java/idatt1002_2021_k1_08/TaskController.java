package idatt1002_2021_k1_08;

import idatt1002_2021_k1_08.datamodel.FileHandler;
import idatt1002_2021_k1_08.datamodel.Task;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class TaskController {

    @FXML
    Button add_task_button;
    @FXML
    Button edit_task_button;
    @FXML
    Button delete_task_button;

    @FXML
    TextArea task_information_TextArea;

    @FXML
    private ListView<Task> tasksView;

    @FXML
    Image logoImage = new Image(new FileInputStream("images/CiterLogo.png"));

    @FXML
    ImageView logoImageView;
    @FXML
    MenuButton menuButton;
    @FXML
    MenuItem helpItem;
    @FXML
    TextField usernameTextField;

    @FXML
    DatePicker datePicker;

    @FXML ArrayList<TextField> taskTextFields = new ArrayList<>();

    @FXML AddTaskController addTaskController;

    private Collection<Task> tasks;



    public TaskController() throws FileNotFoundException {
    }

    public void initialize() {
        logoImageView.setImage(logoImage);
        menuButton = new MenuButton("Options", null, helpItem);

        //Show information of task in description area
        tasksView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {
            @Override
            public void changed(ObservableValue<? extends Task> observableValue, Task task, Task t1) {
                if(t1 != null){

                    Task task1 = tasksView.getSelectionModel().getSelectedItem();

                    task_information_TextArea.setText(task1.getDescription());
                }
            }
        });

        ObservableList<Task> listOfTasks = FileHandler.getInstance().getTasks();

        //Can fix sorting methods here and display that in tasksView;

        tasksView.setItems(listOfTasks);
        tasksView.getSelectionModel().selectFirst();


    }


    @FXML
    public void changeSceneToAddTask() throws IOException{
        CiterClient.setRoot("addTask");
    }

    @FXML
    public void handleKeyPressed(KeyEvent e){
        Task taskSelected = tasksView.getSelectionModel().getSelectedItem();
        if(taskSelected != null){
            if(e.getCode().equals(e)){
                deleteTask(taskSelected);
            }
        }
    }

    @FXML
    public void handleDeleteButton(ActionEvent delete){
        Task selectedTask = tasksView.getSelectionModel().getSelectedItem();
        if(selectedTask != null){
            if(delete.getSource().equals(delete_task_button)){
                deleteTask(selectedTask);
            }
        }
    }

    //forandrer på denne senere
    public void deleteTask(Task task) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Todo Item");
        alert.setHeaderText("Delete Item: " + task.getTaskName());
        alert.setContentText("Are you sure? Press OK to confirm, or cancel to Back out.");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            FileHandler.getInstance().deleteTask(task); //Deletes from list in FileHandler class
        }
    }
}
