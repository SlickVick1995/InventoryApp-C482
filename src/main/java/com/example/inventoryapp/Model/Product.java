package com.example.inventoryapp.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product { // Model class
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    /**
     * Constructs a new Product object with the specified attributes.
     * This constructor initializes a Product instance with essential details like its identifier,
     * name, price, stock level, and minimum and maximum stock levels. It provides a base definition
     * for a product, setting up its fundamental properties for further manipulation and use within
     * the inventory system.
     *
     * @param id The unique identifier for the product. Typically a numeric value.
     * @param name The name of the product. This should be a descriptive string.
     * @param price The price of the product. Represented as a double value.
     * @param stock The current stock level of the product. An integer representing the available quantity.
     * @param min The minimum stock level allowed for the product. An integer setting the lower limit for stock.
     * @param max The maximum stock level allowed for the product. An integer setting the upper limit for stock.
     */

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * getter method
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * setter method
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter method
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter method
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter method
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * setter method
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * getter method
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * setter method
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * getter method
     * @return min
     */
    public int getMin() {
        return min;
    }

    /**
     * setter method
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * getter method
     * @return max
     */
    public int getMax() {
        return max;
    }

    /**
     * setter method
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * getter method for observablelist
     * @return
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /**
     * setter method for add part
     * @param part
     */
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /**
     * delete associated part
     * @param selectedAssociatedPart
     * @return
     */
    public boolean deleteAssociatedParts(Part selectedAssociatedPart) {
      return  associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * getter method for associated parts
     * @return associatedParts
     */

    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    /**
     * toString method
     * @return Product List
     */
    @Override
    public String toString() {
        return "Product{" +
                "associatedParts=" + associatedParts +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
