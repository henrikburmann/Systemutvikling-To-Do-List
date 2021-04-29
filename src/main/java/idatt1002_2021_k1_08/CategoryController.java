package idatt1002_2021_k1_08;

import idatt1002_2021_k1_08.datamodel.FileHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.StageStyle;
import java.util.Optional;

public class CategoryController {


    /**
     * Method that opens an input box that allows user to create their own specified category instead of generic ones
     */
    public static void displayNewCategoryTextInput() {
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Press Ok to add new category");
        td.setContentText("Type in name of the new category:");
        td.initStyle(StageStyle.UTILITY);
        td.setHeaderText(null);
        td.setGraphic(null);
        Optional<String> result = td.showAndWait();
        if(result.isPresent()){
            String categoryName = result.get();
            addCategory(categoryName);
        }
    }

    /**
     * Adds category into categoryList
     * Will not add if already exists
     *
     * @param name the name of the category
     */
    @FXML
    public static void addCategory(String name){
        if(FileHandler.getCategories().stream().anyMatch(e -> e.equals(name))){
            alertbox();
            throw new IllegalArgumentException("This category already exsists");
        }
        else{
            FileHandler.getCategories().add(name);
        }
    }

    /**
     * Alertbox if category already exists
     */
    public static void alertbox(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error: category already exists");
        alert.setHeaderText("Type in a new category name. \n Press Ok to try again");
        alert.showAndWait();
    }
}

