package idatt1002_2021_k1_08;

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
    /**
     * <code>@FXML ImageView</code> for the group logo
     * <code>@FXML Image</code> for the fileread for the actual .png image
     */
    @FXML
    ImageView logoImageView;
    Image logo = new Image(new FileInputStream("images/CiterLogo.png"));
    /**
     * <code>@FXML Textfield</code> Task Name input field for the user
     */
    @FXML TextField task_name_textfield;
    /**
     * <code>@FXML DatePicker</code> Date time box for user input for a finish date to a task.
     */
    @FXML DatePicker date_time_box;
    /**
     * <code>@FXML ComboBox</code> category drop down box for a category.
     */
    @FXML ComboBox<String> categoryList;
    /**
     * <code>@FXML TextArea</code> Text area for detailed description of task, also a user input
     */
    @FXML TextArea notes_textarea;
    /**
     * <code>@FXML Button</code> Adds a task determined to be completed to a list of completed tasks
     */
    @FXML Button add_task_complete_button;
    /**
     * <code>@FXML ChoiceBox</code> Drop down box containing different priorities for sorting purposes.
     */
    @FXML ChoiceBox <String> priorityChoiceBox;
    /**
     * <code>@FXML Button</code> Button to create a new category
     */
    @FXML Button newCategoryButton;

    /**
     * Initializes the window for creating a new task
     */
    public void initialize(){
        logoImageView.setImage(logo);
        priorityChoiceBox.getItems().addAll("Low", "Medium", "High");
        priorityChoiceBox.setValue("Medium");
        categoryList.setItems(FileHandler.getCategories());
    }

    /**
     * Default constructor for controller class
     *
     * @throws FileNotFoundException
     */
    public AddTaskController() throws FileNotFoundException {
    }

    /**
     * Changes scene to primary, "primary" being the name of the fxml
     *
     * @throws IOException
     */
    @FXML
    public void changeSceneToPrimary() throws IOException {
        CiterClient.setRoot("primary");
    }

    /**
     * Handler for creating a new category
     */
    @FXML
    public void handleNewCategoryButton(){
        CategoryController.displayNewCategoryTextInput();
    }

    /**
     * Method for adding a task
     * This method adds a task according to input inside application
     * Here we also validate according to clients wishes whatever input is permitted
     * <code>changeSceneToPrimary();</code> only happens if these requirements are met
     *
     * @throws IOException
     */
    @FXML
    public void addTaskMethod() throws IOException {
        if(date_time_box.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("INPUT WARNING");
            alert.setHeaderText("WRONG DATE INPUT");
            alert.setContentText("Please choose a deadline");
            alert.show();
        }else if(date_time_box.getValue().isBefore(LocalDate.now())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("INPUT WARNING");
            alert.setHeaderText("WRONG DATE INPUT");
            alert.setContentText("Deadline can be at the earliest today");
            alert.show();
        }else if(task_name_textfield.getText() == null || task_name_textfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("INPUT WARNING");
            alert.setHeaderText("Null INPUT");
            alert.setContentText("Please set a name for the new task");
            alert.show();
        }else {
            String taskName = task_name_textfield.getText().trim();

            LocalDate date = LocalDate.of(date_time_box.getValue().getYear(),
                    date_time_box.getValue().getMonthValue(), date_time_box.getValue().getDayOfMonth());

            String priority = priorityChoiceBox.getValue();

            String category = categoryList.getSelectionModel().getSelectedItem();

                Task task = new Task(taskName, category,date,priority);
                task.setDescription(notes_textarea.getText());
                task.setDeadline(date);
                task.setPriority(priority);

                FileHandler.getInstance().addTask(task);
                changeSceneToPrimary();

        }
    }
}
