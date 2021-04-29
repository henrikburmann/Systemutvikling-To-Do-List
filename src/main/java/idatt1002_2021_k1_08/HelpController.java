package idatt1002_2021_k1_08;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelpController {
    /**
     * Button to return from fxml scene into main scene
     */
    @FXML Button getBackButton;
    @FXML Label helpLabel;
    @FXML ImageView imageView;
    Image logo = new Image(new FileInputStream("images/CiterLogo.png"));

    /**
     * Default constructor for class
     * @throws FileNotFoundException
     */
    public HelpController() throws FileNotFoundException {
    }

    /**
     * Initialize method to set the Citer-team photo on scene set on Event.
     */
    public void initialize(){
        imageView.setImage(logo);

    }

    /**
     * Changes scene from help to primary through fxml roots
     *
     * @throws IOException
     */
    public void changeSceneToPrimary() throws IOException {
        CiterClient.setRoot("primary");
    }
}
