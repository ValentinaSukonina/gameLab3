module com.example.gamelab3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gamelab3 to javafx.fxml;
    exports com.example.gamelab3;
}