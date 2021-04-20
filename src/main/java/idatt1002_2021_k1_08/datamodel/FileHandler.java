package idatt1002_2021_k1_08.datamodel;

import idatt1002_2021_k1_08.CiterClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class FileHandler {

    private static final File FILE_PATH_CATEGORY = new File("src/main/resources/idatt1002_2021_k1_08/DataStorage/CategoryStrings.ser");
    private static final File FILE_PATH = new File("src/main/resources/idatt1002_2021_k1_08/DataStorage/TaskData.ser");
    private ObservableList<Task> obTasks;
    private static ObservableList<String> categories;
    private static FileHandler fileHandlerInstance = new FileHandler();

    private final DateTimeFormatter formatter;

    /**
     * Constructor
     */
    public FileHandler() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    /**
     * @return instance of the class
     */
    public static FileHandler getInstance() {
        return fileHandlerInstance;
    }

    /**
     *
     * @return Observablelist of tasks
     */
    public ObservableList<Task> getTasks() {
        return obTasks;
    }

    /**
     *
     * @return Observablelist of categories
     */
    public static ObservableList<String> getCategories() {return categories;}

    /**
     * Adds task object to the observable list
     * @param task the task object
     */
    public void addTask(Task task) {
        obTasks.add(task);
    }

    /**
     * Writes a Category to file
     */
    private void serializeTask(ArrayList<Task> tasks) throws IOException {
        Path path = Paths.get(String.valueOf(FILE_PATH));
        try (FileOutputStream fs = new FileOutputStream(String.valueOf(path)); //åpner opp en stream
             ObjectOutputStream os = new ObjectOutputStream(fs)) {
            for (Task task : obTasks) {
                os.writeObject(task);
            }
        }
    }

    /**
     * Method to deserialize all tasks
     * @return ArrayList of tasks that gets deserialized from TaskData.ser file
     * @throws IOException
     */
    private ArrayList<Task> deserializeTask() throws IOException {
        ArrayList<Task> tasks1 = new ArrayList<>();

        Path path = Paths.get(String.valueOf(FILE_PATH));

        //try with resources, so that fs closes
        try (FileInputStream fs = new FileInputStream(String.valueOf(path));
             ObjectInputStream is = new ObjectInputStream(fs)) {

            //Will eventually throw exception
            while (true) {                                                                   
                tasks1.add((Task) is.readObject());                                                                   
            }


        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (EOFException ignore) {
            //Expected
        } catch (OptionalDataException e) {
            if (!e.eof) {
                throw e;
            }
        }                                                                    
        return tasks1;
    }

    /**
     * StoreData runs when application is closed.
     * Stores all data regarding tasks and categories into separate files with a .ser extension
     * Files stored in src/resources/idatt1002_2021_k1_08/DataStorage
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void storeData() throws IOException, ClassNotFoundException {
        ArrayList<String> categoryStore = new ArrayList<>(categories);
        ArrayList<Task> taskStore = new ArrayList<>();

        for (Task t : obTasks) {
            Task task = new Task(t.getTaskName(),t.getCategory(), t.getEndDate(), t.getPriority());
            task.setDescription(t.getDescription());
            task.setCompleted(t.isCompleted());
            task.setStartDate(t.getStartDate());
            taskStore.add(task);
        }
        serializeCategory(categoryStore);
        serializeTask(taskStore);
    }

    /**
     * LoadData is run when init method from application is run.
     * Method gets data from files inside DataStorage folder.
     * Reads deserialize and adds all objects and/or Strings to each observableList
     * @throws IOException
     */
    public void loadData() throws IOException {
        categories = FXCollections.observableArrayList();
        obTasks = FXCollections.observableArrayList();
        if (FILE_PATH.createNewFile());
        if (FILE_PATH_CATEGORY.createNewFile());
        try {
            ArrayList<String> catlist = deserializeCategory(); // Denne burde returnere category string
            ArrayList<Task> list = deserializeTask();  //Denne burde returnere task

            categories.addAll(catlist);

            for (int i = 0; i < list.size(); i++) {
                Task task = new Task(list.get(i).getTaskName(), list.get(i).getCategory(), list.get(i).getEndDate(), list.get(i).getPriority());
                task.setCompleted(list.get(i).isCompleted());
                task.setStartDate(list.get(i).getStartDate());
                task.setDescription(list.get(i).getDescription());
                obTasks.add(task);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param task deletes item from observableList
     */
    public void deleteTask(Task task) {
        obTasks.remove(task);
    }

    /**
     *
     * @param category deletes item from observableList
     */
    public void deleteCategory(String category){
        categories.remove(category);
    }

    /**
     * Serializes CategoryList
     * @param category the ArrayList containing each item of category created inside application
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void serializeCategory(ArrayList<String> category) throws IOException, ClassNotFoundException{
        Path path = Paths.get(String.valueOf(FILE_PATH_CATEGORY));
        try(FileOutputStream fos = new FileOutputStream(String.valueOf(path));
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(category);

        }catch (EOFException eofe){
            eofe.printStackTrace();
        }
    }

    /**
     *
     * @return ArrayList containing each object of category previously stored inside CategoryStrings.ser
     * category1 = (ArrayList<String>) ois.readObject(); This line warns us of an unchecked cast,
     * But since we know that each item of category inside category1 is a object with only string content,
     * and the need for it to be sorted is not there we can ignore this warning.
     * @throws IOException
     */
    private ArrayList<String> deserializeCategory()throws IOException{
        ArrayList<String> category1 = new ArrayList<>();
        Path path = Paths.get(String.valueOf(FILE_PATH_CATEGORY));

        //Try with resources, so that fs closes
        try(FileInputStream fis = new FileInputStream(String.valueOf(path));
            ObjectInputStream ois = new ObjectInputStream(fis)){
            //Will eventually throw exception

                category1 = (ArrayList<String>) ois.readObject();

        //Catches every needed exception
        } catch (ClassNotFoundException cnf){
            cnf.printStackTrace();
        } catch (EOFException ignore){
            //Expected
        } catch (OptionalDataException ode){
            if(!ode.eof) {
                throw ode;
            }
        }
        return category1;
    }

}
