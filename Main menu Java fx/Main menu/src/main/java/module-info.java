module com.example.main_menu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.main_menu to javafx.fxml;
    exports com.example.main_menu;
}