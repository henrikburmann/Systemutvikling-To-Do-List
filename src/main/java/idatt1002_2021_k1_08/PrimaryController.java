package idatt1002_2021_k1_08;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        CiterClient.setRoot("secondary");
    }
}
