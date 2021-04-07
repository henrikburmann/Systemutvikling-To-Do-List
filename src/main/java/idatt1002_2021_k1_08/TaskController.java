package idatt1002_2021_k1_08;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TaskController {

    @FXML
    Button add_task_button;
    @FXML
    Button edit_task_button;
    @FXML
    Button delete_task_button;

    @FXML
    Label task_information_label;

    @FXML
    Button add_task_complete_button;

    @FXML
    ListView task_list;

    @FXML
    TextField task_name_textfield;
    @FXML
    DatePicker date_time_box;
    @FXML
    ChoiceBox category_list;
    @FXML
    TextArea notes_textarea;
    @FXML
    Image image = new Image(new FileInputStream("images/CiterLogo.png"));

    @FXML
    ImageView menuImageView = new ImageView(image);
    @FXML
    MenuButton menuButton;
    @FXML
    MenuItem helpItem;
    @FXML
    TextField usernameTextField;

    @FXML
    DatePicker datePicker;

    public void initialize() {
        menuImageView.setImage(image);
        menuButton = new MenuButton("Options", null, helpItem);

    }

    public TaskController() throws FileNotFoundException {

    }
    @FXML
    public void changeSceneToAddTask() throws IOException{
        CiterClient.setRoot("addTask");
    }


}
