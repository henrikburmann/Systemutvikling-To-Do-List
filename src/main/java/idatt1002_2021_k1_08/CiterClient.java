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

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
        System.out.println(stage.getWidth());
        System.out.println(stage.getHeight());
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CiterClient.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*
    *When initializing Application
    * get Data from former
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
            System.out.println("Denne burde printes " + FileHandler.getInstance().getTasks().toString());
            System.out.println("Denne burde også printes " + FileHandler.getCategories().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}