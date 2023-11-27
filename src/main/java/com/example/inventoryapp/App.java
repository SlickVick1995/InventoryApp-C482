package com.example.inventoryapp;

import com.example.inventoryapp.Model.InHouse;
import com.example.inventoryapp.Model.Inventory;
import com.example.inventoryapp.Model.Outsourced;
import com.example.inventoryapp.Model.Product;
import com.example.inventoryapp.viewloader.ViewLoader;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *  By @author: Vi Le
 * JAVADOCS FOLDER LOCATION: in this project src/JavaDOCS
 * FUTURE ENHANCEMENTS:
 * Add properties to the part and product class such as description, serial number,
 * manufacturer information and etc. to be used in an Ecommerce business
 */
public class App extends Application {
    /**
     * Initializes and displays the primary stage of the JavaFX application.
     * This method is the main entry point for JavaFX applications. It sets up the primary stage
     * with specified properties and loads the initial view. In this implementation, the stage is set
     * to be non-resizable, and the 'main.fxml' view is loaded with an instance of 'InHouse' as the model.
     * The title of the stage is set to "Inventory".
     *
     * @param stage The primary stage for this application, onto which the application scene is set.
     * @throws IOException If the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        ViewLoader.showStage(new InHouse(), "/com/example/inventoryapp/main.fxml", "Inventory ", stage);
    }
    /**
     * The entry point of the Java application.
     * This method initializes sample parts and products and adds them to the inventory. It creates instances
     * of 'InHouse' and 'Outsourced' parts, such as cords, adapters, headphones, and pouches. It also creates
     * sample products like 'MacBook Air' and 'Dell xps', associating them with parts. Finally, it launches
     * the JavaFX application.
     * Key steps include:
     * - Creating sample parts and adding them to the inventory.
     * - Creating sample products, associating them with parts, and adding them to the inventory.
     * - Launching the JavaFX application.
     *
     * @param args Command-line arguments passed to the application. Not used in this implementation.
     */
    public static void main(String[] args) {
        // Instantiating different instance of product and part
        InHouse cord = new InHouse(1,"Cord", 300.00, 5, 1, 20,
                101);
        InHouse adapter = new InHouse(2,"Adapter", 100.00, 5, 1, 20,
                101);
        InHouse headPhones = new InHouse(3,"Head Phones", 2.99, 5, 1, 20,
                101);
        Outsourced pouch = new Outsourced(5, "Pouch",29.99, 50, 30,
                100, "Panasonic");

        Inventory.addPart(cord);
        Inventory.addPart(adapter);
        Inventory.addPart(headPhones);
        Inventory.addPart(pouch);

//        Add sample product
        Product macBookAir = new Product(5, "Mac Book Air", 499.99, 20, 10,
                100);
        Product dell = new Product(6, "Dell xps", 499.99, 20, 12,
                98);


        macBookAir.addAssociatedPart(cord);
        macBookAir.addAssociatedPart(adapter);
        Inventory.addProduct(macBookAir);

        dell.addAssociatedPart(cord);
        dell.addAssociatedPart(adapter);
        dell.addAssociatedPart(headPhones);
        Inventory.addProduct(dell);

        launch(args);
    }
}