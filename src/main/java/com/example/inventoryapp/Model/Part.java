package com.example.inventoryapp.Model;

public abstract class Part {
    private int id;
    private String name;

    private double price;
    private int stock;
    private int min;
    private int max;
    /**
     * Constructs a new Part object with the specified details.
     * This constructor initializes a Part instance with various attributes defining its properties.
     * These attributes include an identifier, name, price, stock level, and minimum and maximum stock levels.
     * This generic constructor is suitable for creating any type of Part object, providing a base
     * from which specialized parts can be derived.
     *
     * @param id The unique identifier for the part. Typically a numeric value.
     * @param name The name of the part. This should be a descriptive string.
     * @param price The price of the part. Represented as a double value.
     * @param stock The current stock level of the part. An integer representing how many units are available.
     * @param min The minimum stock level allowed for the part. An integer setting the lower bound for stock.
     * @param max The maximum stock level allowed for the part. An integer setting the upper bound for stock.
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * empty constructor
     */
    public Part (){

    }

    /**
     * getter method
     * @return ID
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
     * @return
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
     * @return
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
     * @return
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
}
