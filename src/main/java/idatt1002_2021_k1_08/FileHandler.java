package idatt1002_2021_k1_08;

import java.io.*;
import java.util.ArrayList;

public abstract class FileHandler {

    private final File FILE;
    protected ArrayList<ArrayList<String>> fileData;

    public FileHandler(String path) throws IOException, ClassNotFoundException {
        this.FILE = new File(path);
        this.fileData = new ArrayList<>();
        if(!FILE.exists()) {
            FILE.createNewFile();
        }else{
            loadData();
        }
    }

        //TODO: Should this FILE be a .ser file caused by serialization
    
    public void loadData() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE));
        oos.writeObject(FILE);
        oos.close();



        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE));
        CategoryRegister catRegRead = (CategoryRegister) ois.readObject();
        ois.close();
        //TODO: Create class categoryRegister and category, then test filehandling.

        System.out.println(/** catRegRead.contains(??FILE??)*/ );
        System.out.println(/** catRegRead.contains(?? Something to check that it works*/ );
    }


}
