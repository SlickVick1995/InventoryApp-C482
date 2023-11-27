package com.example.inventoryapp.viewloader;

import javafx.stage.Stage;

public  abstract class Controller<M> {
    /**
     * Main Controller
     * Handles different Stage objects
     */

    protected M model;
    protected Stage stage;
}
