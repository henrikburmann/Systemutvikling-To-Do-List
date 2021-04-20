package idatt1002_2021_k1_08;

import idatt1002_2021_k1_08.CiterClient;
import idatt1002_2021_k1_08.datamodel.FileHandler;
import idatt1002_2021_k1_08.datamodel.Task;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class TaskController {

    @FXML Button add_task_button;
    @FXML Button edit_task_button;
    @FXML Button delete_task_button;
    @FXML Button complete_task_button;
    @FXML MenuButton filterButton;
    @FXML MenuItem showCompletedTasksItem;
    @FXML MenuItem sortbyPriorityItem;
    @FXML MenuItem sortByCategoryItem;
    @FXML MenuItem showUnCompletedTasksItem;
    @FXML MenuItem showAllTasksItem;
    @FXML Button deleteCategory;
    @FXML private ListView<Task> tasksView;
    @FXML Image logoImage = new Image(new FileInputStream("images/CiterLogo.png"));
    @FXML ImageView logoImageView;
    @FXML MenuButton menuButton;
    @FXML MenuItem helpItem;
    @FXML DatePicker datePicker;
    @FXML AnchorPane taskDisplayAnchor;

    @FXML TextField taskNameTextField;
    @FXML  TextField endDateTextField;
    @FXML  TextField startDateTextField;
    @FXML TextField priorityTextField;
    @FXML TextField categoryTextField;
    @FXML TextArea notesTextArea;
    @FXML ArrayList<TextField> textfieldList = new ArrayList<>();
    @FXML ToggleButton chooseCompletedToggleButton;

    @FXML ChoiceBox choiceBox;
    


    public TaskController() throws FileNotFoundException { }

    public void initialize() {

        choiceBox.setValue("filter");
        choiceBox.getItems().add(0,"Sort by category");
        choiceBox.getItems().add(1,"Sort by priority");
        choiceBox.getItems().add(2,"Show completed tasks");
        choiceBox.getItems().add(3,"Show uncompleted tasks");
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
            System.out.println("Selection made: [" + selectedIndex + "]");
            System.out.println("   ChoiceBox.getValue(): " + choiceBox.getValue());


        });

        textfieldList.add(taskNameTextField);
        textfieldList.add(endDateTextField);
        textfieldList.add(startDateTextField);
        textfieldList.add(priorityTextField);
        textfieldList.add(categoryTextField);
        logoImageView.setImage(logoImage);
        menuButton = new MenuButton("Options", null, helpItem);
        filterButton = new MenuButton("Filter", null, sortByCategoryItem, sortbyPriorityItem, showCompletedTasksItem, showUnCompletedTasksItem, showAllTasksItem);
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
                    endDateTextField.setText(task1.getEndDate().toString());
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
    }

    public void filterOptionHandler() {
        int selectedIndex1 = choiceBox.getSelectionModel().getSelectedIndex();
        System.out.println(selectedIndex1);
        if (!(selectedIndex1 == -1)) {
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
                    default:
                System.out.println("Selection made: [" + selectedIndex + "]");
                System.out.println("   ChoiceBox.getValue(): " + choiceBox.getValue());
            };
        }
    }


    @FXML
    public void changeSceneToAddTask() throws IOException {
        CiterClient.setRoot("addTask");
    }
    public void changeSceneToHelp() throws IOException {
        CiterClient.setRoot("help");
    }




    public ObservableList<Task> sortListofTaskUnFinished(){
        ObservableList<Task> listOfTasks = FileHandler.getInstance().getTasks();
        SortedList<Task> sortedList = new SortedList<Task>(listOfTasks, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return(task1.getEndDate().compareTo(task2.getEndDate()));
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

    private void viewUnCompletedTasks(){
        displayTasks(filterOutUnCompleted());
    }

    public ObservableList<Task> getTasksFilehandler(){
        return FileHandler.getInstance().getTasks();
    }

    public ObservableList<Task> filterOutUnCompleted(){
        ObservableList<Task> boo = FXCollections.observableArrayList();
        for(Task t:getTasksFilehandler()){
            if(!t.isCompleted()){
                boo.add(t);
            }
        }
        return boo;
    }

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

    public void tasksOnChosenDate(){
        LocalDate date = datePicker.getValue();
        ObservableList<Task> tasksOnDate = FXCollections.observableArrayList();
        for (int i = 0; i < getTasksFilehandler().size(); i++) {
            if (getTasksFilehandler().get(i).getEndDate().equals(date)){
                tasksOnDate.add(getTasksFilehandler().get(i));
            }
        }
        tasksView.setItems(tasksOnDate);
        System.out.println(tasksOnDate);
    }
    public void viewAllTasks(){
        displayTasks(getTasksFilehandler());
    }


    public void displayTasks(ObservableList<Task> tasks){
        datePicker.setValue(null);
        tasksView.setItems(tasks);
    }
    /**
     * @param task1 Fills information area of Task with its information
     */

    private void fillInformationArea(Task task1) {
        taskNameTextField.setText(task1.getTaskName());
        startDateTextField.setText(task1.getStartDate().toString());
        endDateTextField.setText(task1.getEndDate().toString());
        priorityTextField.setText(task1.getPriority());
        notesTextArea.setText(task1.getDescription());

    }

    private void clearText(){
        for (int i = 0; i < textfieldList.size(); i++) {
            textfieldList.get(i).setText(null);
        }
        notesTextArea.setText(null);
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

    public void handleEditButton(){
        for(TextField textField : textfieldList){
            textField.setEditable(true);
        }
        notesTextArea.setEditable(true);
        delete_task_button.setText("Save");
        saveEditedTask();
    }
    public void saveEditedTask(){
        LocalDate startLocalDate = LocalDate.parse(startDateTextField.getText());
        LocalDate endLocalDate = LocalDate.parse(endDateTextField.getText());
        Task task1 = tasksView.getSelectionModel().getSelectedItem();

        task1.setTaskName(taskNameTextField.getText());
        task1.setStartDate(startLocalDate);
        task1.setEndDate(endLocalDate);
        task1.setPriority(priorityTextField.getText());
        task1.setCategory(categoryTextField.getText());
        task1.setDescription(notesTextArea.getText());
        for(TextField textField : textfieldList){
            textField.setEditable(false);
        }
        notesTextArea.setEditable(false);
        delete_task_button.setText("Delete Task");
    }

    /**
     * Directs user to add new category
     * @throws IOException
     */
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
    }

    @FXML
    public void handleCompleteButton(ActionEvent complete){
        Task selectedTask = tasksView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            if (complete.getSource().equals(complete_task_button)) {
                completeTask(selectedTask);
            }
        }
    }

    private void completeTask(Task task){
        task.setCompleted(true);
        if(choiceBox.getSelectionModel().getSelectedIndex() == -1){
            displayTasks(sortListofTaskUnFinished());
        }
        else{
            filterOptionHandler();
        }
    }



    public void deleteCategory (String category) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Category");
        alert.setHeaderText("Delete Category: " + category);
        alert.setContentText("Are you sure? Press OK to confirm, or cancel to Back out.");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            FileHandler.getInstance().deleteCategory(category); //Deletes Category string in filehandler Class
        }
        clearText();
    }
    //Metode laget på forhånd, ingen deletebutton eller delete category laget enda
    @FXML
    public void handleDeleteCategory(ActionEvent delete){
    }



}

