package com.example.inventoryapp.Controller;

import com.example.inventoryapp.Model.Inventory;
import com.example.inventoryapp.Model.Part;
import com.example.inventoryapp.Model.Product;
import com.example.inventoryapp.viewloader.Controller;
import com.example.inventoryapp.viewloader.ViewLoader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController extends Controller<Part> implements Initializable {
    /**
     * Handles event on the home page
     * Provides the GUI for navigating the App
     */
    public Label errorLabel;


    public  TableView<Part> parts_table_view;
    public TableColumn<Part, Integer> part_Id_column;
    public TableColumn<Part, String> part_name_column;
    public TableColumn<Part, Integer> part_inventory_level_column;
    public TableColumn<Part, Double> part_price_per_unit_column;


    public TableView<Product> product_table_view;
    public TableColumn<Product, Integer> product_id_column;
    public TableColumn<Product, String> product_name_column;
    public TableColumn<Product, Integer> product_inventory_level_column;
    public TableColumn<Product, Double> product_price_column;
    public TextField part_search;
    public TextField product_search;


    public MainController() {

    }
    /** LOGIC ERROR
     * Had trouble initializing the addPart method because I had called the wrong fxml class
     * and the path was incorrect as well, so the addPart method was able to load
     * @throws IOException
     */
    /**
     * Add new part button
     */
    @FXML
    public void addNewPart() throws IOException {
        Stage stage = new Stage();
        ViewLoader.showStage(getPart(), "/com/example/inventoryapp/newPart.fxml", "Add new Products", stage);
    }
    /** LOGIC ERROR
     * Had the same mistake as previous method where the path was incorrect
     * and was calling the wrong fxml class as well,
     * so as soon that was fixed, clicking the add button to call this method worked
     * @throws IOException
     */

    /**
     * Add new Products button
     */
    @FXML
    public void addNewProduct() throws IOException {
        Stage stage = new Stage();
        ViewLoader.showStage(getProduct(),"/com/example/inventoryapp/newProduct.fxml", "Add new Products", stage);
    }

    /**
     * Modify part button
     * @throws IOException
     */
    @FXML
    public void modifyPart() throws IOException {
        if (getPart() != null) {
            Stage stage = new Stage();
            ViewLoader.showStage(getPart(), "/com/example/inventoryapp/modifyPart.fxml", "Inventory", stage);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("WARNING");
            alert.setContentText("No part selected");
            alert.showAndWait();
        }
    }

    /**
     * modify product button
     */
    @FXML
    public void modifyProduct() throws IOException {
        if (getProduct() != null) {
            Stage stage = new Stage();
            ViewLoader.showStage(getProduct(), "/com/example/inventoryapp/modifyProduct.fxml", "Inventory", stage);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("WARNING");
            alert.setContentText("No product selected");
            alert.showAndWait();
        }
    }

    /**
     * getter for Part
     * @return parts table view
     */

    public  Part getPart() {
        return parts_table_view.getSelectionModel().getSelectedItem();
    }

    /**
     * getter for Product
     * @return products table view
     */
    public  Product getProduct(){
        return product_table_view.getSelectionModel().getSelectedItem();
    }

    /**
     * function search parts table field
     */
    @FXML
    void partSearchOnKeyTyped() {
        if (part_search.getText().isEmpty()) {
            parts_table_view.setItems(Inventory.getAllParts());
        }
    }

    /**
     * function to check for double
     * @param str
     * @return
     */
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     * function to search Part by name
     * @param searchField
     */

    private void searchPartByName(String searchField) {
        ObservableList<Part> searchedParts = Inventory.lookupPart(searchField);
        if (searchedParts.size() == 0) {
            parts_table_view.setItems(Inventory.getAllParts());
            return;
        }
        parts_table_view.setItems(searchedParts);
    }

    /**
     * search part table by id
     * @param id
     */

    private void searchPartById(int id) {
        ObservableList<Part> searchedParts = Inventory.lookupPart(part_search.getText());
        Part searchPart = Inventory.lookupPart(id);
        if (searchPart == null) {
            parts_table_view.setItems(Inventory.getAllParts());
            return;
        }
        searchedParts.add(searchPart);
        parts_table_view.setItems(searchedParts);

    }

    /**
     * search product by name
     * @param searchField
     */
    private void searchProductByName(String searchField) {
        ObservableList<Product> searchedParts = Inventory.lookupProduct(searchField);
        if (searchedParts.size() == 0) {
            product_table_view.setItems(Inventory.getAllProducts());
            return;
        }
        product_table_view.setItems(searchedParts);
    }

    /**
     * search product by ID
     * @param id
     */

    private void searchProductById(int id) {
        ObservableList<Product> searchedParts = Inventory.lookupProduct(product_search.getText());
        Product searchPart = Inventory.lookupProduct(id);
        if (searchPart == null) {
            product_table_view.setItems(Inventory.getAllProducts());
            return;
        }
        searchedParts.add(searchPart);
        product_table_view.setItems(searchedParts);
    }

    /**
     * delete part button
     */

    public void deletePart()   {
        Part selectedPart = parts_table_view.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("WARNING");
            alert.setContentText("No part selected");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }

    }

    /**
     * delete product button
     */

    public void deleteProducts() {
        Product selectedProduct = product_table_view.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("WARNING");
            alert.setContentText("No product selected");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                ObservableList<Part> associatedParts = selectedProduct.getAllAssociatedParts();

                if (associatedParts.size()>0) {
                    errorLabel.setText("This product has parts");
                    System.out.println(associatedParts);
                } else {
                    Inventory.deleteProduct(selectedProduct);
                }
            }
        }
    }
    /**
     * Initializes components and sets listeners for searching parts and products by name or ID.
     * @param url            The location used to resolve relative paths for the root object or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * Listener for searching parts by name or ID
         */
        part_search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isNumeric(newValue)) {
                searchPartByName(newValue);
            } else {
                searchPartById(Integer.parseInt(newValue));
            }
        });
        /**
         *   Listener for searching products by name or ID
         */
        product_search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isNumeric(newValue)) {
                searchProductByName(newValue);
            } else {
                searchProductById(Integer.parseInt(newValue));
            }
        });

        /**
         * Populate parts table with data
         */
        parts_table_view.setItems(Inventory.getAllParts());
        part_Id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        part_name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        part_inventory_level_column.setCellValueFactory(new PropertyValueFactory<>("stock"));
        part_price_per_unit_column.setCellValueFactory(new PropertyValueFactory<>("price"));

        /**
         * Populate products table with data
         */
        product_table_view.setItems(Inventory.getAllProducts());
        product_id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        product_name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        product_inventory_level_column.setCellValueFactory(new PropertyValueFactory<>("stock"));
        product_price_column.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
