package idatt1002_2021_k1_08.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class CategoryAndTaskData {

    /**
     * TODO: 1.Hvordan bestemme hvilke kategori en task skal lagres i
     * TODO: 2.Hvordan legge til/slette en kategori.(Må gjøres )
     * TODO: 3.Hvordan legge til og slette en task, (Må gjøres i filen også)
     * TODO: 4.Hvor skal selve filen med dataene lagres.
     * TODO: 5.Burde implementer ObsevableList i klassene (Kan dette brukes med objekter)
     * TODO: 6. Hvor de
     */


    private Task task;
    private Category category;
    private ArrayList<Task> tasks;
    private ArrayList<Category> categories;

    private final DateTimeFormatter formatter;
    private final String FILE_PATH = "filepathName.txt";


    /**
     * Constructor
     */
    private CategoryAndTaskData() {
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    /*

     */
    public void serialize(Object object) throws IOException {
        try (FileOutputStream fs = new FileOutputStream(FILE_PATH);
             ObjectOutputStream os = new ObjectOutputStream(fs)) {
            os.writeObject(object);
        }
    }

    public Object deserialize() throws IOException {
        Object object = null;
        try (FileInputStream fs = new FileInputStream(FILE_PATH);
             ObjectInputStream is = new ObjectInputStream(fs)) {
            object = is.readObject();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return object;
    }

}
