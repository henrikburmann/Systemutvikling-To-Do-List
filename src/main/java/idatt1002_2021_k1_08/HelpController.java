package idatt1002_2021_k1_08;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HelpController {
    /**
     * Button to return from fxml scene into main scene
     */
    @FXML Button getBackButton;

    /**
     * Changes scene from help to primary through fxml roots
     *
     * @throws IOException
     */
    public void changeSceneToPrimary() throws IOException {
        CiterClient.setRoot("primary");
    }
}
