module com.example.mmn14_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mmn14_2 to javafx.fxml;
    exports com.example.mmn14_2;
}