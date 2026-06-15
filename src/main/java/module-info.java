module com.example.mitienda {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mitienda to javafx.fxml;
    exports com.example.mitienda;
}