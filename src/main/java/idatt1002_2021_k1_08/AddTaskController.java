package idatt1002_2021_k1_08;

import idatt1002_2021_k1_08.CiterClient;
import idatt1002_2021_k1_08.datamodel.FileHandler;
import idatt1002_2021_k1_08.datamodel.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;



public class AddTaskController {
    @FXML
    ImageView logoImageView;
    Image logo = new Image(new FileInputStream("images/CiterLogo.png"));
    @FXML
    TextField task_name_textfield;
    @FXML
    DatePicker date_time_box;
    @FXML
    ComboBox<String> categoryList;
    @FXML
    TextArea notes_textarea;
    @FXML
    Button add_task_complete_button;

    @FXML
    ChoiceBox <String> priorityChoiceBox;

    @FXML
    TextArea task_information_TextArea;


    public void initialize(){
        logoImageView.setImage(logo);
        priorityChoiceBox.getItems().addAll("Low", "Medium", "High");
        priorityChoiceBox.setValue("Medium");
        categoryList.setItems(FileHandler.getCategories());
    }

    public AddTaskController() throws FileNotFoundException {
    }

    @FXML
    public void changeSceneToPrimary() throws IOException {
        CiterClient.setRoot("primary");
    }

    @FXML
    public void handleNewCategoryButton(){
        CategoryController.displayNewCategoryTextInput();
    }
    @FXML
    public void addTaskMethod() throws IOException {
        String taskName = task_name_textfield.getText().trim();

        LocalDate date = LocalDate.of(date_time_box.getValue().getYear(),
                date_time_box.getValue().getMonthValue(), date_time_box.getValue().getDayOfMonth());

        String priority = priorityChoiceBox.getValue();

        String category = categoryList.getSelectionModel().getSelectedItem();

        Task task = new Task(taskName, category,LocalDate.now(),date,priority);

        task.setEndDate(date);
        task.setPriority(priority);

        FileHandler.getInstance().addTask(task);

        changeSceneToPrimary();
    }
}
