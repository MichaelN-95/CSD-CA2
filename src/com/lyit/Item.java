/*
 * Item
 *
 * Version information
 *
 * Date 11/06/2021
 *
 * Author: <Michael Norman L00162933
 *
 * Copyright notice
 */

package com.lyit;

public class Item {
    private String itemName;  //Cannot be empty
    private double itemWeight; //Must be > 0
    private double itemValue; //Must >=0
    private int itemMagicValue; //Must >=0

    // Getters and Setters
    // Throw an IllegalArgumentException if a Setter is provided with an invalid argument
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) throws IllegalArgumentException {
        if (itemName==null||itemName.isBlank()) throw new IllegalArgumentException("Invalid item name provided");
        this.itemName = itemName;
    }

    public double getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(double itemWeight) throws IllegalArgumentException{
        if (itemWeight<1) throw new IllegalArgumentException("Item must have a weight of 1 or greater");
        this.itemWeight = itemWeight;
    }

    public double getItemValue() {
        return itemValue;
    }

    public void setItemValue(double itemValue) throws IllegalArgumentException{
        if (itemValue<0) throw new IllegalArgumentException("Item must have a value of 0 or greater");
        this.itemValue = itemValue;
    }

    public int getItemMagicValue() {
        return itemMagicValue;
    }

    public void setItemMagicValue(int itemMagicValue)throws IllegalArgumentException {
        if (itemMagicValue <0) throw new IllegalArgumentException("Item must have a magic value of 0 or greater");
        this.itemMagicValue = itemMagicValue;
    }

    // Constructor
    // If an invalid argument is provided throw an IllegalArgumentException exception


    // Constructor
    // If an invalid argument is provided throw an IllegalArgumentException exception
    public Item(String itemName, double itemWeight, double itemValue, int itemMagic) throws IllegalArgumentException{
        if (itemName ==null || itemWeight <1 || itemValue <0 || itemMagic <0)throw new IllegalArgumentException("Invalid Item arguments provided");
        this.itemName = itemName;
        this.itemWeight = itemWeight;
        this.itemValue = itemValue;
        this.itemMagicValue = itemMagic;

    }

    // Note - You can implement additional supporting private methods if you want. Add them below this section
    // Additional methods ------------------------------------------------------------------------------------

}
