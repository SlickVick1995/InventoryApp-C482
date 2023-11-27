package com.example.inventoryapp.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.stream.Collectors;


public class Inventory {
    // controller
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    public static void addProduct(Product newProduct){
       allProducts.add(newProduct);
    }

    /**
     * RUNTIME error
     * orElse method can't find Object parameter
     * solution: put null value
     */
    public static Part lookupPart(int partId){
        return getAllParts().stream()
                .filter(part -> part.getId() == partId)
                .findFirst()
                .orElse(null);
    }
    /**
     * RUNTIME Error
     * .orElse method cant find Object parameter
     * Error message says required type is the Product class but provided is
     * Object class
     */
    /**
     * Searches and returns a product based on the provided product ID.
     * This method uses a stream to search through all products in the collection (retrieved from
     * 'getAllProducts'). It filters the products to find one that matches the given product ID.
     * If a matching product is found, it is returned; otherwise, the method returns null.
     *
     * @param productId The unique identifier for the product to be searched.
     * @return The Product object with the specified ID, or null if no such product is found.
     */

    public static Product lookupProduct(int productId){
        return getAllProducts().stream()
                .filter(part -> part.getId() == productId)
                .findFirst()
                .orElse(null);
    }
    /**
     * Searches for and returns a list of parts that match the specified part name.
     * This method uses a stream to filter through 'allParts', looking for parts whose names contain
     * the given partName string (case-insensitive). The filtered parts are then collected into an
     * ObservableList, which is returned. This is useful for implementing search functionality in
     * user interfaces where parts need to be dynamically filtered based on user input.
     *
     * @param partName The name (or part of the name) of the part to search for. The search is case-insensitive.
     * @return An ObservableList of Part objects that match the specified name. If no parts are found,
     *         an empty list is returned.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        return allParts.stream()
                .filter(part -> part.getName().toLowerCase().contains(partName.toLowerCase()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    /**
     * Searches for and returns a list of products that match the specified product name.
     * This method uses a stream to filter through 'allProducts', searching for products whose names
     * contain the given productName string (case-insensitive). The filtered products are then
     * collected into an ObservableList, which is returned. This functionality is particularly useful
     * for search operations in user interfaces, allowing dynamic filtering of products based on user input.
     *
     * @param productName The name (or part of the name) of the product to search for. The search is case-insensitive.
     * @return An ObservableList of Product objects that match the specified name. If no products are found,
     *         an empty list is returned.
     */
    public static ObservableList<Product> lookupProduct(String productName){
        return allProducts.stream()
                .filter(part -> part.getName().toLowerCase().contains(productName.toLowerCase()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    /**
     * Updates an existing part in the collection with the provided part details.
     * This method searches for a part in the 'allParts' collection by the specified ID. Once found,
     * it replaces the existing part at that position with the 'selectedPart' provided. If the part
     * with the given ID is not found, the collection remains unchanged. The method iterates through
     * the collection of parts, comparing each part's ID with the provided ID, and performs the update
     * at the matched index.
     *
     * @param id The unique identifier of the part to be updated.
     * @param selectedPart The new Part object that will replace the existing part with the same ID.
     */
    public static void updatePart(int id, Part selectedPart) {
        int index = -1;
        for (Part part : getAllParts()) {
            index++;
            if (part.getId() == id) {
                getAllParts().set(index, selectedPart);
            }
        }
    }

    /**
     * Updates an existing product in the inventory with new details.
     * This method iterates through the list of all products, searching for a product that matches
     * the provided ID. Once the product is found, it is updated with the details of 'newProduct'.
     * The update is performed in-place within the list. If no product with the specified ID is found,
     * the list remains unchanged.
     *
     * Note: This method assumes that the ID of the 'newProduct' is the same as the product being replaced.
     *
     * @param id The ID of the product to be updated.
     * @param newProduct The new product data to be used for the update. This object should contain
     *                   the updated information for the product.
     * RUNTIME Error
     * Required type is the Part class but the provided type is Product
     * for the error message
     * Solution: was missing Product argument in function
     */
    public static void updateProduct(int id, Product newProduct){
        int index = -1;
        for (Product Product : Inventory.getAllProducts()) {
            index++;
            if (Product.getId() == id) {
                Inventory.getAllProducts().set(index, newProduct);
            }
        }
    }

    /**
     * delete part method
     * @param selectedPart
     */
    public static void deletePart(Part selectedPart){
        allParts.remove(selectedPart);
    }

    /**
     * delete product method
     * @param selectedProduct
     */
    public static void deleteProduct(Product selectedProduct){
        allProducts.remove(selectedProduct);

    }

    /**
     * getter method for parts table
     * @return allParts table
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * getter method for products table
     * @return allProducts table
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
