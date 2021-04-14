package idatt1002_2021_k1_08;

import idatt1002_2021_k1_08.datamodel.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

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
    ListView task_list;

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

    @FXML
    TextField taskTextfield1;
    @FXML
    TextField taskTextfield2;
    @FXML
    TextField taskTextfield3;
    @FXML
    TextField taskTextfield4;
    @FXML
    TextField taskTextfield5;
    @FXML
    TextField taskTextfield6;
    @FXML
    TextField taskTextfield7;
    @FXML
    TextField taskTextfield8;
    @FXML
    TextField taskTextfield9;

    @FXML ArrayList<TextField> taskTextFields = new ArrayList<>();
    @FXML AddTaskController addTaskController;



    public void initialize() {
        logoImageView.setImage(logoImage);
        menuButton = new MenuButton("Options", null, helpItem);
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

    public static void addExampleTasks(){
        TaskRegister.addTask(new Task("Task 1", "Description"));
        TaskRegister.addTask(new Task("Task 2", "Description"));
        TaskRegister.addTask(new Task("Task 3", "Description"));
        TaskRegister.addTask(new Task("Task 4", "Description"));
    }

    public TaskController() throws FileNotFoundException {
    }
    @FXML
    public void changeSceneToAddTask() throws IOException{
        CiterClient.setRoot("addTask");
    }


    @FXML
    public void addTaskMethod(){
        for (int i = 0; i < 5; i++) {
            taskTextFields.get(i).setText(TaskRegister.getTasks().get(i).getTaskName());
        }
    }






}
