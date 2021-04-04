package idatt1002_2021_k1_08;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;

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
    public void changeSceneToAddTask() throws IOException{
        CiterClient.setRoot("addTask");

    }


}
