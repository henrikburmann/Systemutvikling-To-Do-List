package idatt1002_2021_k1_08;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        CiterClient.setRoot("primary");
    }
}