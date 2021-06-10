/*
 * Item
 *
 * Version information
 *
 * Date 30/01/2021
 *
 * Author: <Insert your Name and StudentID
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

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(double itemWeight) {
        this.itemWeight = itemWeight;
    }

    public double getItemValue() {
        return itemValue;
    }

    public void setItemValue(double itemValue) {
        this.itemValue = itemValue;
    }

    public int getItemMagicValue() {
        return itemMagicValue;
    }

    public void setItemMagicValue(int itemMagicValue) {
        this.itemMagicValue = itemMagicValue;
    }

    // Constructor
    // If an invalid argument is provided throw an IllegalArgumentException exception
    public Item(String itemName, double itemWeight, double itemValue, int itemMagic) {
        this.itemName = itemName;
        this.itemWeight = itemWeight;
        this.itemValue = itemValue;
        this.itemMagicValue = itemMagic;

    }

    // Note - You can implement additional supporting private methods if you want. Add them below this section
    // Additional methods ------------------------------------------------------------------------------------
}
