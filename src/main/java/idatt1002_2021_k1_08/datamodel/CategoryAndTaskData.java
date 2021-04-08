package idatt1002_2021_k1_08.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public abstract class CategoryAndTaskData {

    /**
     * TODO: 1.Hvordan bestemme hvilke kategori en task skal lagres i
     * TODO: 2.Hvordan legge til/slette en kategori.(Må gjøres )
     * TODO: 3.Hvordan legge til og slette en task, (Må gjøres i filen også)
     * TODO: 4.Hvor skal selve filen med dataene lagres.
     * TODO: 5.Burde implementer ObsevableList i klassene (Kan dette brukes med objekter)
     * TODO: 6.
     */

    //protected ArrayList<ArrayList<String>> fileData;

    private Task task;
    private Category category;
    private ObservableList<Task> tasks;
    private ObservableList<Category> categories;
    private final DateTimeFormatter formatter;
    private static String filename = "filepathName.txt";

    /**
     * Constructor
     */

    private CategoryAndTaskData(){
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public void serializeTask(Object object, File file) throws IOException{
        Object Task = null;
        try(FileOutputStream fs = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(fs)) {

            os.writeObject(object);
        }
    }

    public void deserializeTask(){

    }

    public void serializeCategory(){

    }

    public void deserializeCategory(){

    }




    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     *
     * Method that retrieves data from file
     */
    public void loadData() throws IOException, ClassNotFoundException {
        categories = FXCollections.observableArrayList();
        tasks = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
    }

    /**
     * Method that stores data to a file
     */
    public void storeData(){

    }

}
