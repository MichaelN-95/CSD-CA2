package com.lyit;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArmourTest {
    Armour shieldTest = new Armour("Shield",20,20,20,20,20,true,ArmourTypes.Hold,ArmourMaterial.Iron);
    Armour helmetTest = new Armour("Helmet",20,20,20,20,20,true,ArmourTypes.Wearable,ArmourMaterial.Iron);

    @Test
    public void Armour(){
        Armour helmetTest = new Armour("Helmet",20,20,20,20,20,true,ArmourTypes.Wearable,ArmourMaterial.Iron);

    }
    @Test
    public void testGetArmourDefence() {
    }

    @Test
    public void testSetArmourDefence() {
    }

    @Test
    public void testGetArmourHealth() {
    }

    @Test
    public void testSetArmourHealth() {
    }

    @Test
    public void testGetArmourType() {
    }

    @Test
    public void testGetArmourMaterial() {
    }

    @Test
    public void testIsEquipped() {
    }

    @Test
    public void testSetEquipped() {
    }
}