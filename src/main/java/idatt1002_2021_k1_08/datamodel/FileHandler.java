package idatt1002_2021_k1_08.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author marcusjohannessen
 */

public class FileHandler {

    //TODO: Må forandre på dette fordi funker faen ikke! why?

    private static final String FILE_PATH = "filepathName.txt";
    private ObservableList<Task> obTasks;
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

    public void addTask(Task task) {
        obTasks.add(task);
    }

    /**
     * Writes a Category to file
     */
    private void serializeTask(ArrayList<Task> tasks) throws IOException {
        Path path = Paths.get(FILE_PATH);
        try (FileOutputStream fs = new FileOutputStream(String.valueOf(path)); //åpner opp en stream
             ObjectOutputStream os = new ObjectOutputStream(fs)) {
            for (Task task : obTasks) {
                os.writeObject(task);
            }
        }
    }

    private ArrayList<Task> deserializeTask() throws IOException {
        ArrayList<Task> tasks1 = new ArrayList<>();

        //TODO: Burde filen ligge i resources???
        Path path = Paths.get(FILE_PATH);

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
    public void storeData() throws IOException {
        ArrayList<Task> store = new ArrayList<>();
        for (Task t : obTasks) {
            Task task = new Task(t.getTaskName(), t.getDescription(), t.getStartDate(), t.getEndDate(), t.getPriority());
            store.add(task);
        }
        serializeTask(store);
    }


    public void loadData() throws IOException {
        obTasks = FXCollections.observableArrayList();
        try {
            ArrayList<Task> list = deserializeTask();  //Denne burde returnere task
            System.out.println("Størrelse på liste i loadData()" + list.size());
            System.out.println("Inni metode");

            for (int i = 0; i < list.size(); i++) {
                Task task = new Task(list.get(i).getTaskName(), list.get(i).getDescription());
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

    }
}
