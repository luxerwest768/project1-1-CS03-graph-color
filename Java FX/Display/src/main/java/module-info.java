module com.example.display {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.display to javafx.fxml;
    exports com.example.display;
}