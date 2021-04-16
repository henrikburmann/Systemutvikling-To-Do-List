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
    //TODO: Should program check if a file exists at launch? And if so, should one be created with a createNewFile method?
    //TODO: Må forandre på dette fordi funker faen ikke! why?
    private static final File FILE_PATH_CATEGORY = new File("src/main/resources/idatt1002_2021_k1_08/DataStorage/CategoryStrings.ser");
    private static final File FILE_PATH = new File("src/main/resources/idatt1002_2021_k1_08/DataStorage/TaskData.ser");
    //private static final File FILE_COMPLETED_TASKS = new File ("src/main/resources/idatt1002_2021_k1_08/DataStorage/CompletedTasks.ser");
    private ObservableList<Task> obTasks;
    private static ObservableList<String> categories;
    //private ObservableList<Task> obCompletedTasks;
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

    public ObservableList<Task> getTasks() {
        return obTasks;
    }

    public static ObservableList<String> getCategories() {return categories;}

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

    //TODO: Denne funker ikke som det skal så må fikse på den
    public void storeData() throws IOException, ClassNotFoundException {
        ArrayList<String> categoryStore = new ArrayList<>(categories);
        ArrayList<Task> taskStore = new ArrayList<>();

        for (Task t : obTasks) {
            Task task = new Task(t.getTaskName(),t.getCategory(), t.getStartDate(), t.getEndDate(), t.getPriority());
            task.setDescription(t.getDescription());
            taskStore.add(task);
        }
        serializeCategory(categoryStore);
        serializeTask(taskStore);
    }


    public void loadData() throws IOException {
        categories = FXCollections.observableArrayList();
        obTasks = FXCollections.observableArrayList();
        if (FILE_PATH.createNewFile()) System.out.println("TaskData.ser doesn't exist, CREATING FILE");
        if (FILE_PATH_CATEGORY.createNewFile()) System.out.println("CategoryStrings.ser doesn't exist, CREATING FILE");
        //if (FILE_COMPLETED_TASKS.createNewFile()) System.out.println("CompletedTask.ser exists");
            else {
            System.out.println("Files already exists");
            System.out.println("Loading from files");
        }
        try {
            ArrayList<String> catlist = deserializeCategory(); // Denne burde returnere category string
            ArrayList<Task> list = deserializeTask();  //Denne burde returnere task
            System.out.println("Størrelse på catlist i loadData() " + catlist.size());
            System.out.println("Inni metode");
            System.out.println("Størrelse på liste i loadData()" + list.size());
            System.out.println("Inni metode");

            categories.addAll(catlist);

            for (int i = 0; i < list.size(); i++) {
                Task task = new Task(list.get(i).getTaskName(), list.get(i).getCategory(),list.get(i).getStartDate(), list.get(i).getEndDate(), list.get(i).getPriority());
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


    public void newStoreData() {

    }

    public void newLoadData() {
        obTasks = FXCollections.observableArrayList();
        categories = FXCollections.observableArrayList();

    }

    public void deleteCategory(String category){
        categories.remove(category);
    }


    private void serializeCategory(ArrayList<String> category) throws IOException, ClassNotFoundException{
        Path path = Paths.get(String.valueOf(FILE_PATH_CATEGORY));
        try(FileOutputStream fos = new FileOutputStream(String.valueOf(path));
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(category);

        }catch (EOFException eofe){
            eofe.printStackTrace();
        }
    }

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

    public void addCategory(String category){
        categories.add(category);
    }
}
