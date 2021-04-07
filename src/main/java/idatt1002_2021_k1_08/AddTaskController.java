package idatt1002_2021_k1_08;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddTaskController {
    @FXML
    ImageView logoImageView;
    Image logo = new Image(new FileInputStream("images/CiterLogo.png"));

    public void initialize(){
        logoImageView.setImage(logo);
    }

    public AddTaskController() throws FileNotFoundException {
    }

    @FXML
    public void changeSceneToPrimary() throws IOException {
        CiterClient.setRoot("primary");
    }
}
