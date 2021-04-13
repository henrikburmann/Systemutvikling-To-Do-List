package idatt1002_2021_k1_08.datamodel;

import idatt1002_2021_k1_08.TaskController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author marcusjohannessen
 */

public class FileHandler {

    //TODO: Må forandre på dette fordi funker faen ikke! why?
    private static final String FILE_PATH = "filepathName.txt";
    private ObservableList<Task> tasks;
    private static FileHandler fileHandlerInstance = new FileHandler();


    /**
     * Constructor
     */
    public FileHandler() {
    }

    /**
     *
      * @return instance of the class
     */
    public static FileHandler getInstance(){
        return fileHandlerInstance;
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    /**
     * Writes a Category to file
     */
    private void serializeTask(ArrayList<Task> categories) throws IOException{
        try(FileOutputStream fs = new FileOutputStream(FILE_PATH); //åpner opp en stream
            ObjectOutputStream os = new ObjectOutputStream(fs)){
            os.writeObject(categories);
        }
    }

     private ArrayList<Task> deserializeTask() throws IOException {
         ArrayList<Task> tasks1 = new ArrayList<>();

         try (FileInputStream fs = new FileInputStream(FILE_PATH);
              ObjectInputStream is = new ObjectInputStream(fs)) {

              tasks1 = (ArrayList<Task>) is.readObject();

         }catch (ClassNotFoundException ex){
             ex.printStackTrace();
         }
         return tasks1;
     }

     //TODO: Denne funker ikke som det skal så må fikse på den
     public void storeData() throws IOException{
        ArrayList<Task> store = new ArrayList<>();
        for (Task t: tasks){
            Task task = new Task(t.getTaskName(),t.getDescription(),t.getStartDate(),t.getEndDate(),t.getPriority());
            store.add(task);
        }
        serializeTask(store);
     }

     public void loadData() throws IOException{
        tasks = FXCollections.observableArrayList();
        try{
            ArrayList<Task> list = deserializeTask();
            for (Task t: list){
                Task task = new Task(t.getTaskName(), t.getDescription(), t.getStartDate(), t.getEndDate(), t.getPriority());
                tasks.add(task);
                System.out.println(task.toString());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
     }

    /**
     *
     * @param task
     * deletes item from observableList
     */
    public void deleteTask(Task task){
        tasks.remove(task);
    }
}
