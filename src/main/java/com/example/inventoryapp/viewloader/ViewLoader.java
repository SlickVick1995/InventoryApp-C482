package com.example.inventoryapp.viewloader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewLoader {

    public final static int X = 5;
    public final static int Y = 5;

    /*
     * Handles different Stage objects
     */
    /**
     * LOGIC Error
     * was missing comma after stage
     * fixed after insterting comma
     */
    /**
     * Displays a JavaFX stage with the specified view and model.
     * This method is a generic utility for setting up and showing a JavaFX stage. It loads the specified FXML file,
     * sets the stage title, and applies other configurations. The method also assigns the provided model to the
     * controller of the FXML view and defines behavior for when the stage is closed.
     *
     * @param <T> The type of the model object to be used in the controller.
     * @param model The model object to be passed to the controller of the FXML view.
     * @param fxml The path to the FXML file that describes the GUI layout.
     * @param title The title to be set for the stage window.
     * @param stage The JavaFX Stage object to be shown.
     * @param onStageClosed A Runnable that defines behavior for when the stage is closed.
     * @throws IOException If the FXML file cannot be loaded.
     */
    public static <T> void showStage(T model, String fxml, String title, Stage stage, Runnable onStageClosed) throws IOException {
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource(fxml), null, null,
                type -> {
                    try {
                        @SuppressWarnings("unchecked")
                        Controller<T> controller = (Controller<T>) type.newInstance();
                        controller.model = model;
                        controller.stage = stage;
                        return controller;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        Parent root = loader.load();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> onStageClosed.run());
        stage.show();
    }
    /**
     * Displays a JavaFX stage with the specified view and model, without specifying a close action.
     * This method is an overloaded version of 'showStage' that sets up and shows a JavaFX stage based
     * on the given FXML file, model, and title. It uses a default empty close action. This method is useful
     * when no specific actions are required upon closing the stage.
     *
     * @param <T> The type of the model object to be used in the controller.
     * @param model The model object to be passed to the controller of the FXML view.
     * @param fxml The path to the FXML file that describes the GUI layout.
     * @param title The title to be set for the stage window.
     * @param stage The JavaFX Stage object to be shown.
     * @throws IOException If the FXML file cannot be loaded.
     */
    public static <T> void showStage(T model, String fxml, String title, Stage stage) throws IOException {
        showStage(model, fxml, title, stage, () -> {
        });
    }


}