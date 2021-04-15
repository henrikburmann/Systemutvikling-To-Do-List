package idatt1002_2021_k1_08;

import idatt1002_2021_k1_08.datamodel.FileHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CategoryController {

    @FXML
    TextField categoryName;
    @FXML
    Button addButton;
    @FXML
    Button okButton;

    @FXML
    public void changeScene() throws IOException {
        CiterClient.setRoot("primary");
    }

    @FXML
    public void addCategory() throws IOException {
        if(FileHandler.getCategories().stream().anyMatch(e -> e.equals(categoryName.getText()))){
            alertbox();
            throw new IllegalArgumentException("This category already exsists");
        }
        else {
            FileHandler.getCategories().add(categoryName.getText());
            changeScene();
        }
    }

    public static void alertbox(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error: category already exists");
        alert.setHeaderText("Type in a new category name. \n Press Ok to try again");
        alert.showAndWait();
    }
}
