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
/** RUNTIME ERROR
 * Class 'ModifyProduct' must either
 * be declared abstract or implement abstract method
 * 'initialize(URL, ResourceBundle)' in 'Initializable'
 */
public class ModifyPart extends Controller<Part> implements Initializable {
    /**
     * GUI for modifying part
     * Handles events from Modify part GUI
     */
    public TextField name_field;
    public RadioButton inHouseRadioButton;
    public RadioButton outSourcedRadioButton;
    public Label machineIdLabel;
    public TextField inventory_field;
    public TextField price_field;
    public TextField min_field;
    public TextField machineId_field;
    public TextField max_field;
    public Label error_label;
    public ModifyPart() {
    }

    /**
     * cancel button
     */
    @FXML
    public void cancel() {
        this.stage.close();
    }

    /**
     * inHouseRadio Button
     */
    @FXML
    void inHouseRadioButtonAction() {
        machineIdLabel.setText("Machine ID");
    }

    /**
     * outsourcedRadio Button
     */
    @FXML
    void outsourcedRadioButtonAction() {
        machineIdLabel.setText("Company \n Name");
    }

    /**
     * Updates the part details based on user input.
     * This method retrieves text input from various fields (name, price, inventory, min, max),
     * validates them, and updates the part if the input is valid.
     * It performs several checks including:
     * - Validating the format of the price, inventory, minimum, and maximum values.
     * - Checking if the name contains only letters.
     * - Ensuring that the minimum value is not greater than the maximum.
     * - Ensuring that the inventory is within the minimum and maximum limits.
     * If any validations fail, the method accumulates error messages and displays them.
     * If validations pass, it updates the part information in the inventory.
     * This includes handling different scenarios for 'InHouse' and 'Outsourced' parts.
     * The method assumes that UI components like text fields and radio buttons are available.
     * FXML Annotation indicates that this method is linked to a FXML file component, typically used in JavaFX.
     */
    @FXML
    public void updatePart() {
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
            if (inHouseRadioButton.isSelected()) {
                int machineID = Integer.parseInt(machineId_field.getText());
                Inventory.updatePart(model.getId(), new InHouse(model.getId(), name, price, stock, min, max, machineID));
            }
            else if (outSourcedRadioButton.isSelected()) {
                String companyName = machineId_field.getText();
                Inventory.updatePart(model.getId(), new Outsourced(model.getId(), name, price, stock, min, max, companyName));
            }
            stage.close();
        } else {
            error_label.setText(builder.toString());
        }

    }




    /**
     * Initializes the controller class.
     * This method is automatically called after the FXML file has been loaded. It initializes
     * the form fields with data from the 'model'. The method sets up the text fields for part
     * details like name, max, min, price, and inventory. It also configures the toggle group for
     * the radio buttons to switch between 'InHouse' and 'Outsourced' part types and sets their
     * initial states based on the type of the model.
     *
     * If 'model' is an instance of 'InHouse', it selects the 'InHouse' radio button and sets
     * the 'machineId' field. If 'model' is an instance of 'Outsourced', it selects the
     * 'Outsourced' radio button and sets the 'companyName' field. If 'model' does not match
     * either type, it defaults to selecting the 'InHouse' radio button.
     *
     * @param url The location used to resolve relative paths for the root object, or null if
     *            the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if
     *                       the root object was not localized.
     * Override Indicates that this method overrides a method declared in a superclass.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        name_field.setText(model.getName());
        max_field.setText(String.valueOf(model.getMax()));
        min_field.setText(String.valueOf(model.getMin()));
        price_field.setText(String.valueOf(model.getPrice()));
        inventory_field.setText(String.valueOf(model.getStock()));

        ToggleGroup toggleGroup = new ToggleGroup();
        inHouseRadioButton.setToggleGroup(toggleGroup);
        outSourcedRadioButton.setToggleGroup(toggleGroup);

        if (model instanceof InHouse) {
            inHouseRadioButton.setSelected(true);
            machineIdLabel.setText("Machine ID");
            machineId_field.setText(String.valueOf(((InHouse) model).getMachineId()));
        }

        if (model instanceof Outsourced) {
            outSourcedRadioButton.setSelected(true);
            machineIdLabel.setText("Company \n Name");
            machineId_field.setText(String.valueOf(((Outsourced) model).getCompanyName()));

        } else {
            inHouseRadioButton.setSelected(true);
        }
    }


}
