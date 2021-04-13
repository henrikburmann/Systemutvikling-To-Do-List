package idatt1002_2021_k1_08.datamodel;

import idatt1002_2021_k1_08.TaskController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

/**
 * @author marcusjohannessen
 */

public class FileHandler {

    private final String FILE_PATH = "filepathName.txt";
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

    /**
     * Writes a Category to file
     */
    public void serializeCategory(ArrayList<Task> categories) throws IOException{
        try(FileOutputStream fs = new FileOutputStream(FILE_PATH); //åpner opp en stream
            ObjectOutputStream os = new ObjectOutputStream(fs)){
            os.writeObject(categories);
        }
    }

    /**
     * Reads a category from a file
     */
    public ArrayList<Category> deserializeCategory() throws IOException {
        ArrayList<Category> category = new ArrayList<>();

        try (FileInputStream fs = new FileInputStream(FILE_PATH);
             ObjectInputStream is = new ObjectInputStream(fs)) {

            category = (ArrayList<Category>) is.readObject();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return category;
    }

    /**
     *
     * @param ob
     * @throws IOException
     */
    public void serializeTask(Object ob) throws IOException{
        try (FileOutputStream fs = new FileOutputStream(FILE_PATH);
            ObjectOutputStream os = new ObjectOutputStream(fs)){
            os.writeObject(ob);
        }
    }

    /**
     *
     * @return Object representing a task
     * @throws IOException
     */
    public Object deserializeObject() throws IOException{
        Object task = null;
        try(FileInputStream fs = new FileInputStream(FILE_PATH);
            ObjectInputStream is = new ObjectInputStream(fs)){
            task = is.readObject();

        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return task;
    }

     public ArrayList<Task> deserializeTask() throws IOException {
         ArrayList<Task> tasks1 = new ArrayList<>();

         try (FileInputStream fs = new FileInputStream(FILE_PATH);
              ObjectInputStream is = new ObjectInputStream(fs)) {

             tasks1 = (ArrayList<Task>) is.readObject();
         }catch (ClassNotFoundException ex){
             ex.printStackTrace();
         }
         return tasks1;
     }


     public void storeData(){

     }

     public void loadData() throws IOException{
        tasks = FXCollections.observableArrayList();
        try{
            ArrayList<Task> list = deserializeTask();
            for (Task t: list){
                Task task = new Task(t.getTaskName(), t.getDescription(), t.getStartDate(), t.getEndDate(), t.getPriority());
                tasks.add(task);
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
