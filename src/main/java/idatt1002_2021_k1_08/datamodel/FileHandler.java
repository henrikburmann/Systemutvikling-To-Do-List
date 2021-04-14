package idatt1002_2021_k1_08.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author marcusjohannessen
 */

public abstract class FileHandler {


    private final Path path = Paths.get("..\\src\\main\\resources");
    private final Path file = Files.createFile(path);
    /**
     * Constructor
     */
    public FileHandler() throws IOException{
    }

    /**
     * Writes a Category to file
     */
    public void serialize(ArrayList<Task> tasks) throws IOException{
        try(FileOutputStream fs = new FileOutputStream(String.valueOf((file))); //åpner opp en stream
            ObjectOutputStream os = new ObjectOutputStream(fs)){
            for (Task t : tasks){ //Reads every task Object in Arraylist and serializes them individually
                os.writeObject(t);
            }
        }
    }
    /*
    metode fra Listerners "fra" FXCollections. For å adde tasks med category
    Da skal det ikke være behøvelig med Category.java eller CategoryRegister.java

    //TODO: Delete category and categoryregister ?
    //TODO: Make loader method and saver method for initialize APP and Stop APP
    //TODO: Make tests for serializer, also JAVADOC
    //TODO: Incorporate Category from tasks, into list for sorting, not for creating.
    //TODO: ^Create categories inside every task to make them sortable.
     */

    /**
     *
     * @return an Observable Arraylist
     * @throws IOException ifAny
     */
    public ObservableList<Task> deserialize() throws IOException, ClassNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        while (true) {
            try (FileInputStream fs = new FileInputStream(String.valueOf(file));
                 ObjectInputStream is = new ObjectInputStream(fs)) {
                tasks = (ArrayList<Task>) is.readObject(); // read one object that is serialized

            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ioe){
                ioe.printStackTrace();
            }
            // Returns an ObservableArrayList from FXCollections to able listeners
            return FXCollections.observableArrayList(tasks);
        }
    }
}
