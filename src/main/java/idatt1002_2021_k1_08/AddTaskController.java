package idatt1002_2021_k1_08;

import idatt1002_2021_k1_08.datamodel.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AddTaskController {
    @FXML
    ImageView logoImageView;
    Image logo = new Image(new FileInputStream("images/CiterLogo.png"));
    @FXML
    TextField task_name_textfield;
    @FXML
    DatePicker date_time_box;
    @FXML
    ChoiceBox category_list;
    @FXML
    TextArea notes_textarea;
    @FXML
    Button add_task_complete_button;


    @FXML
    TextArea task_information_TextArea;


    public void initialize(){
        logoImageView.setImage(logo);
    }

    public AddTaskController() throws FileNotFoundException {
    }

    @FXML
    public void changeSceneToPrimary() throws IOException {
        CiterClient.setRoot("primary");
    }

    @FXML
    public void addTaskMethod() throws IOException {
        String taskName = task_name_textfield.getText();
        String description = notes_textarea.getText();
        String status = "Underway";
        Task task = new Task(taskName, description, status);
        task_information_TextArea.setText(task.toString());
        TaskRegister.addTask(task);
        changeSceneToPrimary();
    }



}
