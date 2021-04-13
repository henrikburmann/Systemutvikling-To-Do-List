package idatt1002_2021_k1_08.datamodel;

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
    private void serializeTask(ArrayList<Task> tasks) throws IOException{
        Path path = Paths.get(FILE_PATH);
        try(FileOutputStream fs = new FileOutputStream(String.valueOf(path)); //åpner opp en stream
            ObjectOutputStream os = new ObjectOutputStream(fs)){
            for(Task task: tasks){
                os.writeObject(task);       //Denne funker ikke
            }
        }
    }

     private ArrayList<Task> deserializeTask() throws IOException {
         ArrayList<Task> tasks1 = new ArrayList<>();
         Path path = Paths.get(FILE_PATH);
         try (FileInputStream fs = new FileInputStream(String.valueOf(path));
              ObjectInputStream is = new ObjectInputStream(fs)) {

              for(Task task: tasks){
                  Task t = (Task) is.readObject();
                  tasks1.add(t);
              }

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
                Task task = new Task(t.getTaskName(), t.getDescription());
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
