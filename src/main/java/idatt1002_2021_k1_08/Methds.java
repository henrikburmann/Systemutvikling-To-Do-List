package idatt1002_2021_k1_08;
/**
import idatt1002_2021_k1_08.datamodel.FileHandler;
import idatt1002_2021_k1_08.datamodel.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Methds {
    private void addItemsInPriorityComboBox(){
        priorityComboBox.getItems().add("All");
        priorityComboBox.getItems().add("Low");
        priorityComboBox.getItems().add("Medium");
        priorityComboBox.getItems().add("High");
        priorityComboBox.setValue("All");
    }
     //@FXML
     public void viewCompletedTasks(){
     ObservableList<Task> tasks = FileHandler.getInstance().getTasks();
     ObservableList<Task> boo = FXCollections.observableArrayList();
     for(Task t:tasks){
     if(t.isCompleted()){
     boo.add(t);
     }
     }
     displayTasks(boo);
     }
    public void viewByPriority(){
        ObservableList<Task> tasksOfPriority = FXCollections.observableArrayList();
        String priority = (String) priorityComboBox.getValue();
        if (priority.equals("All")){
            tasksView.setItems(FileHandler.getInstance().getTasks());
        }
        else{
            for (int i = 0; i < FileHandler.getInstance().getTasks().size(); i++) {
                if (FileHandler.getInstance().getTasks().get(i).getPriority().equals(priority)){
                    tasksOfPriority.add(FileHandler.getInstance().getTasks().get(i));
                }
            }
            displayTasks(tasksOfPriority);
            System.out.println(priority);
        }
    }
}
*/