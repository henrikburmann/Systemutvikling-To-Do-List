/**
 * The module defining what is required
 * Also describes what is open for JavaFX to read.
 * Contains terminal digits, but doesn't hinder application. aka not a serious matter on our level
 */
module idatt1002_2021_k1_08 {
    requires javafx.controls;
    requires javafx.fxml;

    opens idatt1002_2021_k1_08 to javafx.fxml;
    exports idatt1002_2021_k1_08;
}