package idatt1002_2021_k1_08.datamodel;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class FileHandler {

    /**
     * TODO: 1.Hvordan bestemme hvilke kategori en task skal lagres i
     * TODO: 2.Hvordan legge til/slette en kategori.(Må gjøres )
     * TODO: 3.Hvordan legge til og slette en task, (Må gjøres i filen også)
     * TODO: 4.Hvor skal selve filen med dataene lagres.
     * TODO: 5.Burde implementer ObsevableList i klassene (Kan dette brukes med objekter)
     * TODO: 6.Hvordan henter man ut et task fra en dserialisert ArrayList????
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
    private FileHandler() {
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    /**
     * Writes a Category to file
     */
    public void serializeCategory(ArrayList<Task> categories) throws IOException{
        try(FileOutputStream fs = new FileOutputStream(CATEGORY_PATH); //åpner opp en stream
            ObjectOutputStream os = new ObjectOutputStream(fs)){
            os.writeObject(categories);
            os.flush();
        }
    }

    /**
     * Reads a category from a file
     */
    public ArrayList<Category> deserializeCategory() throws IOException {
        ArrayList<Category> category = new ArrayList<>();

        try (FileInputStream fs = new FileInputStream(CATEGORY_PATH);
             ObjectInputStream is = new ObjectInputStream(fs)) {

            category = (ArrayList<Category>) is.readObject();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return category;
    }

}
