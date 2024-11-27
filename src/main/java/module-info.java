module org.example.demo6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;

    // Make your package visible if necessary
    opens org.example.demo6 to javafx.fxml;
    exports org.example.demo6;

}
