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
import java.util.Optional;

/**
 * Class for connecting to fxml scene
 * this is where Tasks are made
 */
public class AddTaskController {
    /**
     *  ImageView for the group logo
     *  Image for the fileread for the actual .png image
     */
    @FXML
    ImageView logoImageView;
    /**
     * The logo for our project
     */
    Image logo = new Image(new FileInputStream("images/CiterLogo.png"));
    /**
     *  Textfield Task Name input field for the user
     */
    @FXML TextField task_name_textfield;
    /**
     *  DatePicker for user input for a finish date to a task.
     */
    @FXML DatePicker date_time_box;
    /**
     *  ComboBox for a category.
     */
    @FXML ComboBox<String> categoryList;
    /**
     *  TextArea for detailed description of task, also a user input
     */
    @FXML TextArea notes_textarea;
    /**
     *  Button Adds a task determined to be completed to a list of completed tasks
     */
    @FXML Button add_task_complete_button;
    /**
     *  ChoiceBox  containing different priorities for sorting purposes.
     */
    @FXML ChoiceBox <String> priorityChoiceBox;
    /**
     *  Button to add new category
     */
    @FXML Button newCategoryButton;
    /**
     *
     * Button for deleting category
     */
    @FXML Button deleteCategory;

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
     * @throws FileNotFoundException if file is not found from file
     */
    public AddTaskController() throws FileNotFoundException {
    }

    /**
     * Changes scene to primary, "primary" being the name of the fxml
     *
     * @throws IOException through java.io package utilities
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
     * @throws IOException an ioexception
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

    /**
     * method to delete a category
     * this alerts the user about whet they are about to do
     * and only lets the operation proceed if they confirm
     */
    public void deleteCategory() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Category? ");
        alert.setHeaderText("Sure you want to delete category " + categoryList.getSelectionModel().getSelectedItem() + "?");
        alert.setContentText("Press OK to delete category");
        Optional<ButtonType> result = alert.showAndWait();
        for(Task task: FileHandler.getInstance().getTasks()){
            if(categoryList.getSelectionModel().getSelectedItem().equals(task.getCategory())){
                task.setCategory("");
            }
        }
        if (result.isPresent() && result.get() == ButtonType.OK) {
            FileHandler.getCategories().remove(categoryList.getSelectionModel().getSelectedItem());
        }
    }
}
