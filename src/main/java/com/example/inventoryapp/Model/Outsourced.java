package com.example.inventoryapp.Model;

public class Outsourced extends Part{
    private String companyName;

    /**
     * Constructs a new Outsourced part object with the specified details.
     * This constructor initializes an Outsourced part with the provided attributes. It calls the superclass
     * constructor to initialize common fields such as id, name, price, stock, min, and max. Additionally,
     * it sets the 'companyName', which is a unique attribute for Outsourced parts.
     *
     * @param id The unique identifier for the part.
     * @param name The name of the part.
     * @param price The price of the part.
     * @param stock The current stock level of the part.
     * @param min The minimum stock level allowed for the part.
     * @param max The maximum stock level allowed for the part.
     * @param companyName The name of the company that provides this outsourced part.
     * LOGIC ERROR
     * when building the constructor for this class
     * solution:  had to use the super keyword
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Emmpty constructor
     */
    public Outsourced(){

    }

    /**
     * setter method for the company name
     * @param companyName
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    /**
     * getter for the company name
     * @return
     */
    public String getCompanyName() { return this.companyName; }

}
