package idatt1002_2021_k1_08.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class FileHandler {
    /**
     * <code>FILE_PATH</code> & <code>FILE_PATH_CATEGORY</code>
     * are .ser extension files directly created inside directory resources/../DataStorage upon launch
     * To avoid a read from wrong files we do not allow user to search and choose files themself.
     */
    private static final File FILE_PATH_CATEGORY = new File("src/main/resources/idatt1002_2021_k1_08/DataStorage/CategoryStrings.ser");
    private static final File FILE_PATH = new File("src/main/resources/idatt1002_2021_k1_08/DataStorage/TaskData.ser");
    public ObservableList<Task> obTasks = FXCollections.observableArrayList(); // Must be public due to FileHandlerTest class
    public static ObservableList<String> categories = FXCollections.observableArrayList();
    private static FileHandler fileHandlerInstance = new FileHandler();


    /**
     * Constructor for FileHandler.
     */
    public FileHandler() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    /**
     * @return instance of the class
     */
    public static FileHandler getInstance() {
        return fileHandlerInstance;
    }

    /**
     *
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
    public void serializeTask(ObservableList<Task> obTasks1, File filePath) throws IOException {
        Path path = Paths.get(String.valueOf(filePath));
        try (FileOutputStream fs = new FileOutputStream(String.valueOf(path)); //åpner opp en stream
             ObjectOutputStream os = new ObjectOutputStream(fs)) {
            for (Task task : obTasks1) {
                os.writeObject(task);
            }
        }
    }

    /**
     * Method to deserialize all tasks
     * @return ArrayList of tasks that gets deserialized from TaskData.ser file
     * @throws IOException
     */
    public ArrayList<Task> deserializeTask(File filePath) throws IOException {
        ArrayList<Task> tasks1 = new ArrayList<>();

        Path path = Paths.get(String.valueOf(filePath));

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

        taskStore.addAll(obTasks);
        serializeCategory(categoryStore, FILE_PATH_CATEGORY);
        serializeTask(obTasks, FILE_PATH);
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
        //createFiles(FILE_PATH, FILE_PATH_CATEGORY);
        if (FILE_PATH.createNewFile());
        if (FILE_PATH_CATEGORY.createNewFile());
        try {
            ArrayList<String> catlist = deserializeCategory(); // Denne burde returnere category string
            ArrayList<Task> list = deserializeTask(FILE_PATH);  //Denne burde returnere task

            categories.addAll(catlist);

            for (int i = 0; i < list.size(); i++) {
                Task task = new Task(list.get(i).getTaskName(), list.get(i).getCategory(), list.get(i).getDeadline(), list.get(i).getPriority());
                task.setCompleted(list.get(i).isCompleted());
                task.setStartDate(list.get(i).getStartDate());
                task.setDescription(list.get(i).getDescription());
                task.setFinishDate(list.get(i).getFinishDate());
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
    private void serializeCategory(ArrayList<String> category, File filePathCategory) throws IOException, ClassNotFoundException{
        Path path = Paths.get(String.valueOf(filePathCategory));
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
     * <code>category1 = (ArrayList<String>) ois.readObject();</code> This line warns us of an unchecked cast,
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
