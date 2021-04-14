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
import java.time.LocalDate;
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




    public TaskController() throws FileNotFoundException {
    }

    public void initialize() {
        logoImageView.setImage(logoImage);
        menuButton = new MenuButton("Options", null, helpItem);
<<<<<<< HEAD
        taskTextFields.add(taskTextfield1);
        taskTextFields.add(taskTextfield2);
        taskTextFields.add(taskTextfield3);
        taskTextFields.add(taskTextfield4);
        taskTextFields.add(taskTextfield5);
        taskTextFields.add(taskTextfield6);
        taskTextFields.add(taskTextfield7);
        taskTextFields.add(taskTextfield8);
        taskTextFields.add(taskTextfield9);
        for (int i = 0; i < TaskRegister.getTasks().size(); i++) {
            taskTextFields.get(i).setText(TaskRegister.getTasks().get(i).getTaskName());
        }
        task_information_TextArea.setText(TaskRegister.getTasks()
                .get(TaskRegister.getTasks().size()-1).toString());
        System.out.println(TaskRegister.getTasks().size());
    }
=======
>>>>>>> 09f8b40f459e976f943b2c2a14547f179913ee52

        //Show information of task in description area
        //Also implements listener for every Task
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

        //Should fix a sorting method here that displays a sortedList (by date f.eksample)
        //TODO: look at filtered list and sorted list, for displaying tasks by category...
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

    /**
     *
     * @param delete
     * Handles deletebutton
     */
    @FXML
    public void handleDeleteButton(ActionEvent delete){
        Task selectedTask = tasksView.getSelectionModel().getSelectedItem();
        if(selectedTask != null){
            if(delete.getSource().equals(delete_task_button)){
                deleteTask(selectedTask);
            }
        }
    }

    /**
     *
     * @param task
     * Deletes a task with method from FileHandler class
     */
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
