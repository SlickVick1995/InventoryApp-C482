package com.example.inventoryapp.Controller;

import com.example.inventoryapp.Model.Inventory;
import com.example.inventoryapp.Model.Part;
import com.example.inventoryapp.Model.Product;
import com.example.inventoryapp.Model.Validator;
import com.example.inventoryapp.viewloader.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ModifyProduct extends Controller<Product> implements Initializable {
    /**
     * GUI for modifying product
     * Handles events from Modify prodcut GUI
     */
    public TableView<Part> product_parts_table;
    public TableColumn<Part, Integer> product_part_id_column;
    public TableColumn<Product, String> product_part_name_column;
    public TableColumn<Product, Integer> product_part_inv_column;
    public TableColumn<Product, Double> product_part_price_column;


    public TableView<Part> parts_table;
    public TableColumn<Part, Integer> part_id_column;
    public TableColumn<Product, String> part_name_column;
    public TableColumn<Product, Integer> part_inv_column;
    public TableColumn<Product, Double> part_price_column;
    public TextField inventory_text;
    public TextField name_text;
    public TextField price_text;
    public TextField max_text;
    public TextField min_text;
    public TextField search_field;
    public Label error_label;

    public ModifyProduct() {
    }
    /**
     * Updates the product details based on user input.
     * This method collects text input from various fields (name, price, inventory, min, max),
     * validates this input, and updates the product details if the input is valid. The validations include:
     * - Checking if the price, inventory, min, and max are valid numbers (double or integer).
     * - Verifying that the name contains only letters.
     * - Ensuring the minimum value is not greater than the maximum.
     * - Making sure the inventory level is between the minimum and maximum values.
     * If any validations fail, the method accumulates error messages in a list. If all validations
     * pass, it updates the product's details in the 'model' and then in the inventory. The method
     * also handles closing the form or displaying accumulated error messages.
     * Assumptions:
     * - The method assumes that UI components like text fields and labels are available and correctly linked.
     * - 'Inventory' and 'Product' are assumed to be valid classes with appropriate methods and attributes.
     * FXML Annotation indicates that this method is linked to a FXML file component, typically used in JavaFX.
     */
    @FXML
    public void updateProduct() {
        List<String> errors = new ArrayList<>();

        String name = name_text.getText();
        String priceText = price_text.getText();
        String stockText = inventory_text.getText();
        String minText = min_text.getText();
        String maxText = max_text.getText();

        if (!Validator.isDouble(priceText)) {
            Validator.addError(errors, "Price is not a valid double");
        }

        if (!Validator.isInteger(stockText)) {
            Validator.addError(errors, "Inventory is not a valid integer");
        }

        if (!Validator.isInteger(minText)) {
            Validator.addError(errors, "Min is not a valid integer");
        }

        if (!Validator.isInteger(maxText)) {
            Validator.addError(errors, "Max is not a valid integer");
        }

        if (Validator.isValidName(name)) {
            Validator.addError(errors, "Name must contain only letters");
        }

        double price = Validator.isDouble(priceText) ? Double.parseDouble(priceText) : 0;
        int stock = Validator.isInteger(stockText) ? Integer.parseInt(stockText) : 0;
        int min = Validator.isInteger(minText) ? Integer.parseInt(minText) : 0;
        int max = Validator.isInteger(maxText) ? Integer.parseInt(maxText) : 0;

        if (min > max) {
            Validator.addError(errors, "Min can't be greater than max");
        }

        if (stock < min || stock > max) {
            Validator.addError(errors, "Inventory must be between min and max");
        }

        StringBuilder builder = new StringBuilder();

        for (String err : errors) {
            builder.append(err).append("\n");
        }

        if (errors.isEmpty()) {
            model.setName(name);
            model.setPrice(price);
            model.setStock(stock);
            model.setMin(min);
            model.setMax(max);
            model.setAssociatedParts(model.getAllAssociatedParts());
            Inventory.updateProduct(model.getId(), model);
            stage.close();
        } else {
            error_label.setText(builder.toString());
        }

    }

    /**
     * cancel button method
     */

    @FXML
    public void cancel() {
        stage.close();
    }

    /**
     * remove part from product table
     */
    @FXML
    public void removePartForProduct() {
        Part selectedPart = product_parts_table.getSelectionModel().getSelectedItem();
        if (selectedPart != null) model.getAllAssociatedParts().remove(selectedPart);
    }

    /**
     * Add Product to Part table
     */
    @FXML
    public void addProductToPart() {
        Part selectedPart = parts_table.getSelectionModel().getSelectedItem();
        if (selectedPart != null && !model.getAllAssociatedParts().contains(selectedPart)) {
            model.getAllAssociatedParts().add(selectedPart);
        }
    }
    /**
     * Initializes the controller class for product management.
     * This method is automatically called after the FXML file has been loaded. It sets up the initial state
     * of various UI components including text fields and tables. The text fields are populated with data from
     * the 'model' object, which represents the current product. Additionally, it configures the table views
     * for displaying associated parts of the product and all available parts in the inventory.
     *
     * The method performs the following key tasks:
     * - Setting text fields (inventory, name, price, max, min) with product data from 'model'.
     * - Configuring the 'product_parts_table' to display parts associated with the current product.
     * - Setting up 'parts_table' to list all parts available in the inventory.
     * - Adding a listener to the 'search_field' to handle real-time search of parts by name or ID.
     *
     * @param url The location used to resolve relative paths for the root object, or null if
     *            the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if
     *                       the root object was not localized.
     * Override Indicates that this method overrides a method declared in a superclass.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        inventory_text.setText(String.valueOf(model.getStock()));
        name_text.setText(model.getName());
        price_text.setText(String.valueOf(model.getPrice()));
        max_text.setText(String.valueOf(model.getMax()));
        min_text.setText(String.valueOf(model.getMin()));


        product_parts_table.setItems(model.getAllAssociatedParts());
        product_part_id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        product_part_name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        product_part_inv_column.setCellValueFactory(new PropertyValueFactory<>("stock"));
        product_part_price_column.setCellValueFactory(new PropertyValueFactory<>("price"));

        parts_table.setItems(Inventory.getAllParts());
        part_id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        part_name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        part_inv_column.setCellValueFactory(new PropertyValueFactory<>("stock"));
        part_price_column.setCellValueFactory(new PropertyValueFactory<>("price"));


        search_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isNumeric(newValue)) {
                searchPartByName(newValue);
            } else {
                searchPartById(Integer.parseInt(newValue));
            }
        });
    }

    /**
     * function to check is argument is a double
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
     * function to search part by name
     * @param searchField
     */
    private void searchPartByName(String searchField) {
        ObservableList<Part> searchedParts = Inventory.lookupPart(searchField);
        if (searchedParts.size() == 0) {
            parts_table.setItems(Inventory.getAllParts());
            return;
        }
        parts_table.setItems(searchedParts);
    }

    /**
     * function search part by ID
     * @param id
     */

    private void searchPartById(int id) {
        ObservableList<Part> searchedParts = Inventory.lookupPart(search_field.getText());
        Part searchPart = Inventory.lookupPart(id);
        if (searchPart == null) {
            parts_table.setItems(Inventory.getAllParts());
            return;
        }
        searchedParts.add(searchPart);
        parts_table.setItems(searchedParts);

    }
}
