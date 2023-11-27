package com.example.inventoryapp.Model;

import java.util.List;

public class Validator{
    /**
     * Input validation class
     * Handles NumberFormat exceptions
     * Handles custom error messages from GUI
     */
    public static void addError(List<String> errors, String errorMessage) {
        errors.add(errorMessage);
    }

    /**
     * Checks if value is a double
     * @param value
     * @return
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * check if value is an integer
     * @param value
     * @return
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * check if value is valid String representation
     * @param value
     * @return
     */
    public static boolean isValidName(String value) {
        return value.isEmpty();
    }
}
