package idatt1002_2021_k1_08;


import idatt1002_2021_k1_08.datamodel.FileHandler;
import idatt1002_2021_k1_08.datamodel.Task;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class TaskController {

    /**
     * Circles indicates color coding for completed tasks
     */
    @FXML Circle circle_orange;
    @FXML Circle circle_green;

    /**
     * Button to add task
     */
    @FXML Button add_task_button;
    /**
     * Button to edit already existing task
     */
    @FXML Button edit_task_button;
    /**
     * Button to delete existing task
     */
    @FXML Button delete_task_button;
    /**
     * Button to mark existing task as completed for sorting purposes
     */
    @FXML Button complete_task_button;
    /**
     * List of all tasks that exists inside application
     */

    @FXML private ListView<Task> tasksView;
    /**
     * Our logo for the project
     */
    @FXML Image logoImage = new Image(new FileInputStream("images/CiterLogo.png"));
    /**
     * ImageView for our logo
     */
    @FXML ImageView logoImageView;
    /**
     * Button for the menu
     */
    @FXML MenuButton menuButton;
    /**
     *  Help item inside menu
     */
    @FXML MenuItem helpItem;
    /**
     * DatePicker item for sorting purposes
     */
    @FXML DatePicker datePicker;
    /**
     * AnchorPane for fxml
     */
    @FXML AnchorPane taskDisplayAnchor;
    /**
     * Display field for Task name
     */
    @FXML TextField taskNameTextField;
    /**
     * Display field for End Date
     */
    @FXML  TextField endDateTextField;
    /**
     * Display field for Start Date
     */
    @FXML  TextField startDateTextField;
    /**
     * Display field for priority
     */
    @FXML TextField priorityTextField;
    /**
     * Display field for Category
     */
    @FXML TextField categoryTextField;
    /**
     * Display field for Notes / Details on each task
     */
    @FXML TextArea notesTextArea;
    /**
     * List of all textfields
     */
    @FXML ArrayList<TextField> textfieldList = new ArrayList<>();
    /**
     * Label taht shows finished date
     */
    @FXML Label finished;
    /**
     * EndDate Label
     */
    @FXML Label endDate;
    /**
     * Button for sorting completed and uncompleted tasks
     */
    @FXML ToggleButton chooseCompletedToggleButton;
    /**
     * choiceBox for sorting
     */
    @FXML ChoiceBox choiceBox;

    @FXML ComboBox<String> categoryList = new ComboBox<>();
    @FXML ChoiceBox <String> priorityChoiceBox = new ChoiceBox<>();
    @FXML Button saveEditedTask;
    @FXML DatePicker startDateEdit;
    @FXML DatePicker endDateEdit;

    /**
     * Default constructor for controller
     *
     * @throws FileNotFoundException
     */
    public TaskController() throws FileNotFoundException { }

    /**
     * Initialize method for all items inside primary fxml
     * provides an initial load for all items and sets all items to designated values
     */
    public void initialize() {
        viewUnCompletedTasks();
        choiceBox.setValue("Show all uncompleted");
        priorityChoiceBox.getItems().addAll("Low", "Medium", "High");
        priorityChoiceBox.setVisible(false);
        categoryList.setItems(FileHandler.getCategories());
        categoryList.setVisible(false);
        saveEditedTask.setVisible(false);
        finished.setVisible(false);
        startDateEdit.setVisible(false);
        endDateEdit.setVisible(false);
        choiceBox.getItems().add(0,"Sort by category");
        choiceBox.getItems().add(1,"Sort by priority");
        choiceBox.getItems().add(2,"Show all completed tasks");
        choiceBox.getItems().add(3,"Show all uncompleted tasks");
        choiceBox.getItems().add(4,"Show all tasks");

        choiceBox.setOnAction((event) -> {
            int selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
            switch(selectedIndex) {
                case 0:
                    viewByCategory();
                    break;
                case 1:
                    viewByPriority();
                    break;
                case 2:
                    viewCompletedTasks();
                    break;
                case 3:
                    viewUnCompletedTasks();
                    break;
                case 4:
                    viewAllTasks();
                    break;
                default:
            }
        });

        textfieldList.add(taskNameTextField);
        textfieldList.add(endDateTextField);
        textfieldList.add(startDateTextField);
        textfieldList.add(priorityTextField);
        textfieldList.add(categoryTextField);
        logoImageView.setImage(logoImage);
        menuButton = new MenuButton("Options", null, helpItem);

        for(TextField textField : textfieldList){
            textField.setEditable(false);
        }
        notesTextArea.setEditable(false);
        helpItem.setOnAction(e-> {
            try {
                changeSceneToHelp();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        tasksView.getSelectionModel().selectFirst();
        tasksView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {
            @Override
            public void changed(ObservableValue<? extends Task> observableValue, Task task, Task t1) {

                if(t1 != null){

                    Task task1 = tasksView.getSelectionModel().getSelectedItem();

                    taskNameTextField.setText(task1.getTaskName());
                    startDateTextField.setText(task1.getStartDate().toString());
                    endDateTextField.setText(task1.getDeadline().toString());
                    priorityTextField.setText(task1.getPriority());
                    categoryTextField.setText(task1.getCategory());
                    notesTextArea.setText(task1.getDescription());


                if (t1 != null) {
                    Task task2 = tasksView.getSelectionModel().getSelectedItem();
                    fillInformationArea(task2);

                }
            }
        };});

        tasksView.setItems(sortListofTaskUnFinished());
        tasksView.getSelectionModel().selectFirst();

        //Changes color of task if it is due today or begyond
        tasksView.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> taskListView) {
                ListCell<Task> taskCell = new ListCell<Task>(){

                    @Override
                    protected void updateItem(Task task, boolean empty) {
                        super.updateItem(task, empty);
                        if(empty){
                            setText(null);
                        }else{
                            setText(task.toString());
                            //If task is due for tomorrow of beyond
                            if(task.getDeadline().isBefore(LocalDate.now())){
                                setTextFill(Color.ORANGE);
                            }else if(task.isCompleted()){
                                setTextFill(Color.GREEN);
                            }
                        }
                    }
                };
                return taskCell;
            }
        });
    }

    /**
     * This method updates list after a change has happened.
     * A change could be sorting, editing, deleting etc.
     */
    public void filterOptionHandler() {
        int selectedIndex1 = choiceBox.getSelectionModel().getSelectedIndex();
        System.out.println(selectedIndex1);
                int selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
                switch (selectedIndex) {
                    case 0:
                        viewByCategory();
                        break;
                    case 1:
                        viewByPriority();
                        break;
                    case 2:
                        viewCompletedTasks();
                        break;
                    case 3:
                        viewUnCompletedTasks();
                        break;
                    case 4:
                        viewAllTasks();
                        break;
                    case -1:
                        viewUnCompletedTasks();
                        break;
                    default:
            };
        }

    /**
     * Changes scene to addTask if "NEW" button is pressed
     *
     * @throws IOException
     */
    @FXML
    public void changeSceneToAddTask() throws IOException {
        CiterClient.setRoot("addTask");
    }

    /**
     * Shows finish date for completed task
     */
    public void showFinishDate(boolean a){
        finished.setVisible(a);
        endDate.setVisible(!a);
    }

    /**
     * Changes scene to "help" if help is pressed inside menu
     *
     * @throws IOException
     */
    public void changeSceneToHelp() throws IOException {
        CiterClient.setRoot("help");
    }


    /**
     * method sorts list based on status of completed or not completed
     *
     * @return ObservableList boo
     */
    public ObservableList<Task> sortListofTaskUnFinished(){
        ObservableList<Task> listOfTasks = FileHandler.getInstance().getTasks();
        SortedList<Task> sortedList = new SortedList<Task>(listOfTasks, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return(task1.getDeadline().compareTo(task2.getDeadline()));
            }
        });

        ObservableList<Task> boo = FXCollections.observableArrayList();
        for(Task t:sortedList){
            if(!t.isCompleted()){
                boo.add(t);
            }
        }
        return boo;
    }


    /**
     * Sorts tasks based on their status of completion
     * Then display method is called on the list to update
     */
    @FXML
    public void viewCompletedTasks(){
        ObservableList<Task> boo = FXCollections.observableArrayList();
        for(Task t:getTasksFilehandler()){
            if(t.isCompleted()){
                boo.add(t);
            }
        }
        displayTasks(boo);
    }

    /**
     * Helper method
     *
     * Sort tasks based on their status of completion
     * Then display method is called on the list to update, with filter method to do the sorting
     */
    private void viewUnCompletedTasks(){
        displayTasks(filterOutUnCompleted());
    }

    /**
     * Gets tasks stored at a earlier stage if tasks exists.
     * Will load if any, else will create file and launch application with a new clean .ser file
     * @return ObservableList obTasks
     */
    public ObservableList<Task> getTasksFilehandler(){
        return FileHandler.getInstance().getTasks();
    }

    /**
     * Sorter for uncompleted tasks
     *
     * @return ObservableList boo
     */
    public ObservableList<Task> filterOutUnCompleted(){
        ObservableList<Task> boo = FXCollections.observableArrayList();
        for(Task t:getTasksFilehandler()){
            if(!t.isCompleted()){
                boo.add(t);
            }
        }
        return boo;
    }

    /**
     * Sorter for tasks based on their priority value
     * then displays with displayTasks method
     */
    public void viewByPriority(){
        ObservableList<Task> tasksOfPriority;
        if(chooseCompletedToggleButton.isSelected()){
            tasksOfPriority = getTasksFilehandler();
        }
        else{
            tasksOfPriority = filterOutUnCompleted();
        }

        SortedList<Task> sortedList = new SortedList<Task>(tasksOfPriority, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return (task2.setPriorityNumber()-(task1.setPriorityNumber()));
            }
        });
        displayTasks(sortedList);
    }

    /**
     * Sorter to enable user the viewing of tasks connected to a specific category
     * will only display tasks that are uncompleted
     */
    public void viewByCategory(){
        ObservableList<Task> tasksOfCategory = checkOfCompleted();
        SortedList<Task> sortedList = new SortedList<>(tasksOfCategory, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return (task1.getCategory().compareTo(task2.getCategory()));
            }
        });
        displayTasks(sortedList);
    }

    /**
     * helper method to check if list contains completed tasks after initial sorting
     *
     * @return completedList
     */
    public ObservableList<Task> checkOfCompleted(){
        ObservableList<Task> completedList;
        if(chooseCompletedToggleButton.isSelected()){
            completedList = getTasksFilehandler();
        }
        else{
            completedList = filterOutUnCompleted();
        }
        //displayTasks(tasksOfCategory);
        tasksView.setItems(completedList);
        return completedList;
    }

    /**
     * Sorter that allows the user to view tasks due on a specific date
     */
    public void tasksOnChosenDate(){
        LocalDate date = datePicker.getValue();
        ObservableList<Task> tasksOnDate = FXCollections.observableArrayList();
        for (int i = 0; i < getTasksFilehandler().size(); i++) {
            if (getTasksFilehandler().get(i).getDeadline().equals(date)){
                tasksOnDate.add(getTasksFilehandler().get(i));
            }
        }
        tasksView.setItems(tasksOnDate);
    }

    /**
     * Method to remove all sorting done. To view all tasks inside application in bulk
     */
    public void viewAllTasks(){
        displayTasks(getTasksFilehandler());
    }

    /**
     * Helper method to display tasks
     * @param tasks tasks inside list
     */
    public void displayTasks(ObservableList<Task> tasks){
        datePicker.setValue(null);
        tasksView.setItems(tasks);
    }
    /**
     * @param task1 Fills information area of Task with its information
     */

    private void fillInformationArea(Task task1) {
        showFinishDate(false);
        taskNameTextField.setText(task1.getTaskName());
        startDateTextField.setText(task1.getStartDate().toString());
        if(task1.isCompleted()){
            showFinishDate(true);
            endDateTextField.setText(task1.getFinishDate().toString());
        }
        else {
            endDateTextField.setText(task1.getDeadline().toString());
        }
        priorityTextField.setText(task1.getPriority());
        notesTextArea.setText(task1.getDescription());
    }

    /**
     * Helper method to clear text from textfield after deletion
     */
    private void clearText(){
        for (int i = 0; i < textfieldList.size(); i++) {
            textfieldList.get(i).setText(null);
        }
        notesTextArea.setText(null);
    }

    /**
     * Delete action handler for key pressed
     * @param e the action registered to the buttonpress
     */
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
     * Handler for editbutton
     */
    public void handleEditButton(){
        setToEdit(true);
    }

    /**
     * help method to set primary in edit mode
     * @param a
     */
    public void setToEdit(boolean a){
        taskNameTextField.setEditable(a);
        notesTextArea.setEditable(a);
        categoryList.setVisible(a);
        priorityChoiceBox.setVisible(a);
        saveEditedTask.setVisible(a);
        startDateEdit.setVisible(a);
        endDateEdit.setVisible(a);
        boolean b  = !a;
        edit_task_button.setVisible(b);
        categoryTextField.setVisible(b);
        startDateTextField.setVisible(b);
        endDateTextField.setVisible(b);
        priorityTextField.setVisible(b);
    }

    /**
     * Method to save the edited Task
     */
    public void saveEditedTask(){
        LocalDate startDate;
        LocalDate deadline = null;

        if(startDateEdit.getValue()!=null) {
            startDate = LocalDate.of(startDateEdit.getValue().getYear(),
                    startDateEdit.getValue().getMonthValue(), startDateEdit.getValue().getDayOfMonth());
        } else{
                startDate = tasksView.getSelectionModel().getSelectedItem().getStartDate();
        }if(endDateEdit.getValue()!=null){
            if(endDateEdit.getValue().isBefore(LocalDate.now())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("INPUT WARNING");
                alert.setHeaderText("WRONG DATE INPUT");
                alert.setContentText("Deadline can be at the earliest today");
                alert.show();}
            else{
                deadline = LocalDate.of(endDateEdit.getValue().getYear(),
                endDateEdit.getValue().getMonthValue(), endDateEdit.getValue().getDayOfMonth());}
        } else{
        deadline = tasksView.getSelectionModel().getSelectedItem().getDeadline();
        }
        tasksView.getSelectionModel().getSelectedItem().setTaskName(taskNameTextField.getText());
        if(priorityChoiceBox.getValue()!=null){
        tasksView.getSelectionModel().getSelectedItem().setPriority(priorityChoiceBox.getValue());
        } else {
            tasksView.getSelectionModel().getSelectedItem().setPriority(tasksView.getSelectionModel().getSelectedItem().getPriority());
        }
        if(categoryList.getSelectionModel().getSelectedItem()!=null){
            tasksView.getSelectionModel().getSelectedItem().setCategory(categoryList.getSelectionModel().getSelectedItem());
        }else{
            tasksView.getSelectionModel().getSelectedItem().setCategory(tasksView.getSelectionModel().getSelectedItem().getCategory());
        }
        tasksView.getSelectionModel().getSelectedItem().setDescription(notesTextArea.getText());
        tasksView.getSelectionModel().getSelectedItem().setStartDate(startDate);
        tasksView.getSelectionModel().getSelectedItem().setDeadline(deadline);
        setToEdit(false);
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
        alert.setTitle("Delete Task");
        alert.setHeaderText("Delete Task: " + task.getTaskName());
        alert.setContentText("Are you sure? Press OK to confirm, or cancel to Back out.");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            FileHandler.getInstance().deleteTask(task); //Deletes from list in FileHandler class
        }
        clearText();
        filterOptionHandler();

    }

    /**
     * Handler for complete button
     *
     * @param complete the complete button
     */
    @FXML
    public void handleCompleteButton(ActionEvent complete){
        Task selectedTask = tasksView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            if (complete.getSource().equals(complete_task_button)) {
                completeTask(selectedTask);
            }
        }
    }

    /**
     * Helper method for complete task handler
     * @param task the selected task that provides the task inndata
     */
    private void completeTask(Task task){
        task.setCompleted(true);
        task.setFinishDate(LocalDate.now());
        if(choiceBox.getSelectionModel().getSelectedIndex() == -1){
            displayTasks(sortListofTaskUnFinished());
        }
        else{
            filterOptionHandler();
        }
    }
}

