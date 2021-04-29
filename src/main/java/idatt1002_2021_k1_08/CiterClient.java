package idatt1002_2021_k1_08;

import idatt1002_2021_k1_08.datamodel.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class CiterClient extends Application{

    private static Scene scene;

    /**
     * Method extended from Application
     * runs on every launch of program
     * <code>start(Stage stage)</code> method sets limits on stage (window) size.
     * @param stage the main page where the scene is connected.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setMaxHeight(535);
        stage.setMaxWidth(770);
        stage.setScene(scene);
        stage.setTitle("Citer app");
        stage.show();
    }

    /**
     * Sets the start window for the application
     * @param fxml the fxml file that is to be loaded
     * @throws IOException
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loader for the fxml file
     *
     * @param fxml all fxml files inside resources file
     * @return the loaded fxml for the fxml indicated by method
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CiterClient.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Main method, initializes methods extended from Application through <code>launch(args)</code>
     * @param args arguments passed through launch method. (Start, init, load...)
     */
    public static void main(String[] args) {
        launch(args);
    }

    /*
     * When initializing Application
     * get Data from formerly saved state
     */
    @Override
    public void init() throws Exception {
        try{
            FileHandler.getInstance().loadData();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @throws Exception
     * When closing application
     * Store all data
     */
    @Override
    public void stop() throws Exception {
        try {
            FileHandler.getInstance().storeData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}