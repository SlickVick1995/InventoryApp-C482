package com.example.inventoryapp.Controller;

import com.example.inventoryapp.Model.*;
import com.example.inventoryapp.viewloader.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreatePart extends Controller<Part> implements Initializable {
    /**
     * global class variables
     */
    private static int partID = 201;

    public RadioButton radioOutsourced;
    public TextField inventory_field;
    public TextField name_field;
    public TextField min_field;
    public TextField price_field;
    public TextField machineId_field;
    public TextField max_field;
    public ToggleGroup toggleGroup;
    public RadioButton radioInHouse;
    private static final String  RADIO_BUTTON_TEXT = "In-house";
    public Label labelMachineID;
    public Label error_label;
    /**
     * RUNTIME ERROR
     * mistakenly put getCharacters instead of getText
     * corrected
     */
    /**
     * machine id getter
     * @return Integer machine ID
     */
    private int getMachine(){
       return Integer.parseInt(machineId_field.getText());
    }

    /**
     * part ID getter
     * @return partID
     */
    public static int getPartID() {
        partID++;
        return partID;
    }
    /** RUNTIME ERROR
     * Missing validator class
     * had to import validator class
     */
    /**
     * cancel button to close out window
     */
    @FXML
    public void cancel() {
        this.stage.close();
    }

    /**
     * Saves a part with details entered in the form fields.
     * This method performs validation on the input fields (name, price, inventory, min, max)
     * and saves the part information if the inputs are valid. It uses a series of checks to
     * validate the data:
     * - Checks if the price, inventory, min, and max values are in valid formats (double or integer).
     * - Validates if the name contains only letters.
     * - Ensures that the minimum value is not greater than the maximum.
     * - Verifies that the inventory amount is within the specified min and max range.
     *
     * If any validations fail, the method accumulates error messages. If all validations pass,
     * the method determines whether to create an 'InHouse' or 'Outsourced' part based on the
     * selected toggle button and adds the new part to the inventory. It closes the form upon
     * successful saving of the part or displays accumulated error messages otherwise.
     *
     * Assumptions:
     * - The method assumes that UI components like text fields and toggle buttons are already set up.
     * - It assumes that 'Inventory', 'InHouse', and 'Outsourced' are valid classes with appropriate methods and constructors.
     */
    public void savePart() {
        List<String> errors = new ArrayList<>();

        String name = name_field.getText();
        String priceText = price_field.getText();
        String stockText = inventory_field.getText();
        String minText = min_field.getText();
        String maxText = max_field.getText();

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
            if(((toggleGroup.getSelectedToggle().toString()).equals(RADIO_BUTTON_TEXT))){
                Part inHousePart =  new InHouse(getPartID(),name, price, stock ,min, max, getMachine());
                Inventory.addPart(inHousePart);
            }else{
                Part outSourcedPart =
                        new Outsourced(10,name, price, stock ,min, max,  machineId_field.getText());
                Inventory.addPart(outSourcedPart);
            }
           stage.close();
        } else {
            error_label.setText(builder.toString());
        }

    }

    /**
     * InHouse Radio button function
     */
    @FXML
    void inHouseRadioButtonAction() {
        labelMachineID.setText("Machine ID");
    }

    /**
     * Outsourced Radio button function
     */
    @FXML
    void outsourcedRadioButtonAction() {
        labelMachineID.setText("Company \n Name");
    }

    /**
     * Implementation of Initialize interface
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toggleGroup = new ToggleGroup();
        radioInHouse.setToggleGroup(toggleGroup);
        radioOutsourced.setToggleGroup(toggleGroup);
    }
}
