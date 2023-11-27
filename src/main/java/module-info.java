module com.example.inventoryapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.inventoryapp to javafx.fxml;
    exports com.example.inventoryapp;

    opens com.example.inventoryapp.Model to javafx.fxml;
    exports com.example.inventoryapp.Model;

    opens com.example.inventoryapp.viewloader to javafx.fxml;
    exports com.example.inventoryapp.viewloader;

    opens com.example.inventoryapp.Controller to javafx.fxml;
    exports com.example.inventoryapp.Controller;
}