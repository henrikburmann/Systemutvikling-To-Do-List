package idatt1002_2021_k1_08;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HelpController {
    @FXML Button getBackButton;

    public void changeSceneToPrimary() throws IOException {
        CiterClient.setRoot("primary");
    }
}
