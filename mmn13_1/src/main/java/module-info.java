module com.example.mmn13_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mmn13_1 to javafx.fxml;
    exports com.example.mmn13_1;
}