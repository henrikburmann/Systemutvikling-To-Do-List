package idatt1002_2021_k1_08.datamodel;

import idatt1002_2021_k1_08.CategoryRegister;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public abstract class FileHandler {

    /**
     * TODO: 1.Hvordan bestemme hvilke kategori en task skal lagres i
     * TODO: 2.Hvordan legge til/slette en kategori.(Må gjøres )
     * TODO: 3.Hvordan legge til og slette en task, (Må gjøres i filen også)
     * TODO: 4.Hvor skal selve filen med dataene lagres.
     * TODO: 5.Burde implementer ObsevableList i klassene (Kan dette brukes med objekter)
     */

    //protected ArrayList<ArrayList<String>> fileData;

    private Task task;
    private Category category;

    private ObservableList<Task> tasks;
    private ObservableList<Category> categories;


    /**
     * Constructor
     */
    public FileHandler() {
    }

    //private static String filePath = "filepath.txt";


    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     *
     * Method that retrieves data from file
     */
    public void loadData() throws IOException, ClassNotFoundException {

    }


    /**
     * Method that stores data to a file
     */
    public void storeData(){

    }

}
