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

/**
 * Runtime ERROR message
 * had to implement the intialize method and
 * add implements Initializable interface
 */

public class CreateProduct extends Controller<Product> implements Initializable {
    /**
     * Controller to create product
     * Implements initializable for launching Product Stage
     */
    /**
     * Global class variables
     */
    private static int productID =101;

    public TextField name_text;
    public TextField inventory_text;
    public TextField price_text;
    public TextField max_text;
    public TextField min_text;
    public TableView<Part> product_parts_table;
    public TableColumn<Part, Integer> product_part_id_column;
    public TableColumn<Product, String>  product_part_name_column;
    public TableColumn<Product, Integer> product_part_inv_column;
    public TableColumn<Product, Double> product_part_price_column;


    public TableView<Part> parts_table;
    public TableColumn<Part, Integer> part_id_column;
    public TableColumn<Product, String> part_name_column;
    public TableColumn<Product, Integer> part_inv_column;
    public TableColumn<Product, Double> part_price_column;
    public TextField search_field;
    public Label error_label;

    /**
     * cancel button to close out window
     */
    @FXML
    public  void cancel() {
        stage.close();
    }

    /**
     * ProductID getter method
     * @return
     */

    public static int getProductID() {
       productID++;
        return productID;
    }

    /**
     * Create new object of product
     * Add new object from GUI to product list
     */
    /**
     * function to save Product array into Array List
     */
    @FXML
    public void saveProduct() {
        List<String> errors = new ArrayList<>();

        String name = name_text.getText();
        String priceText = price_text.getText();
        String stockText = inventory_text.getText();
        String minText = min_text.getText();
        String maxText = max_text.getText();

        /**
         * Handle necessary errors
         * Add errors to errorMessage list
         */
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
        /**
         * RUNTIME ERROR
         * ran into problem with shorthand boolean
         * wasnt converting into integer or double value
         * had to parse
         */
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
        /** RUNTIME ERROR
         * setAssociatedParts
         * Non-static method 'setAssociatedParts(javafx.collections.
         * ObservableList<com.example.qkmk2project.Model.Part>)'
         * cannot be referenced from a static context
         * FIX:
         * "Product" should be "product" on line 122
         */

        if (errors.isEmpty()) {
            //Add product if no error is found
            Product product = new Product(getProductID(), name, price, stock, min, max);
            product.setAssociatedParts(product_parts_table.getItems());
            Inventory.addProduct(product);
            stage.close();
        } else {
            error_label.setText(builder.toString());
        }

    }

    /**
     * Function to add Parts to product table
     */
    @FXML
    public void addPartsToProduct(){
        Part selectedPart = parts_table.getSelectionModel().getSelectedItem();
        if(selectedPart!=null && !product_parts_table.getItems().contains(selectedPart)){
            product_parts_table.getItems().add(selectedPart);
        }
    }

    /**
     *   Delete part from product
     */

    @FXML
    public  void removePartFromProduct(){
        Part selectedPart = product_parts_table.getSelectionModel().getSelectedItem();
        if(selectedPart!=null)
            product_parts_table.getItems().remove(selectedPart);
    }

    /**
     * Adding product properties to table
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isNumeric(newValue)) {
                searchPartByName(newValue);
            } else {
                searchPartById(Integer.parseInt(newValue));
            }
        });


        parts_table.setItems(Inventory.getAllParts());
        part_id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        part_name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        part_inv_column.setCellValueFactory(new PropertyValueFactory<>("stock"));
        part_price_column.setCellValueFactory(new PropertyValueFactory<>("price"));


        product_part_id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        product_part_name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        product_part_inv_column.setCellValueFactory(new PropertyValueFactory<>("stock"));
        product_part_price_column.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * Function check if argument is a double
     * @param str
     * @return boolean value
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
     *   Search part using name
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

    /** Search part using id
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
