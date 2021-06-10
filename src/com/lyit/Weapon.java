/*
 * Weapon
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

public class Weapon extends  Item {

    // Class attributes
    private int weaponHitStrength;//The damage the weapon does. Must be in range [1..100]
    private int weaponHealth; //The health of the weapon. Must be in range [1..100]
    private boolean doubleHanded; // Does the weapon require both hands? True or false.
    private boolean weaponEquipped; // Is the weapon being held, true or false.

    // Getters and Setters
    // Throw an IllegalArgumentException if a setter is provided with an invalid argument
    public int getWeaponHitStrength() {
        return weaponHitStrength;
    }

    public void setWeaponHitStrength(int weaponHitStrength) {
        this.weaponHitStrength = weaponHitStrength;
    }

    public int getWeaponHealth() {
        return weaponHealth;
    }
    public void setWeaponHealth(int weaponHealth) {
        this.weaponHealth = weaponHealth;
    }

    public boolean isDoubleHanded() {
        return doubleHanded;
    }

    public void setDoubleHanded(boolean doubleHanded) {
        this.doubleHanded = doubleHanded;
    }

    public boolean isWeaponEquipped() {
        return weaponEquipped;
    }

    public void setWeaponEquipped(boolean weaponEquipped) {
        this.weaponEquipped = weaponEquipped;
    }

    // Constructor
    // If an invalid argument is provided throw an IllegalArgumentException exception
    public Weapon(String itemName, double itemWeight, double itemValue, int itemMagic, int weaponHitStrength, int weaponHealth, boolean doubleHanded) {
        super(itemName, itemWeight, itemValue, itemMagic);
        this.weaponHitStrength = weaponHitStrength;
        this.weaponHealth = weaponHealth;
        this.doubleHanded = doubleHanded;
    }

    // Note - You can implement additional supporting private methods if you want. Add them below this section
    // Additional methods ------------------------------------------------------------------------------------
}
