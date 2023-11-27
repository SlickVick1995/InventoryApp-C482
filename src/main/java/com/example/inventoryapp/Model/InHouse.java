package com.example.inventoryapp.Model;

public class  InHouse extends Part {
    /** LOGIC ERROR
     * building the constructor for this class and
     * had to use the super keyword
     */
    private int machineId;
    /**
     * Constructs a new InHouse part.
     * This constructor initializes an InHouse part with the specified details. It calls the superclass
     * constructor to initialize common attributes like id, name, price, stock, min, and max. Additionally,
     * it sets the machineId specific to InHouse parts.
     *
     * @param id The unique identifier for the part.
     * @param name The name of the part.
     * @param price The price of the part.
     * @param stock The current stock level of the part.
     * @param min The minimum stock level allowed for the part.
     * @param max The maximum stock level allowed for the part.
     * @param machineId The machine ID associated with this InHouse part.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Empty constructor
     */
    public InHouse() {

    }

    /**
     * setter for Machine ID
     * @param machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * getter for machine ID
     * @return
     */
    public int getMachineId() {return machineId;}
}
