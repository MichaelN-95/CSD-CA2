package com.lyit;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArmourTest {
    Armour shieldTest = new Armour("Shield",20,20,20,20,20,true,ArmourTypes.Hold,ArmourMaterial.Iron);
    Armour helmetTest = new Armour("Helmet",20,20,20,20,20,true,ArmourTypes.Wearable,ArmourMaterial.Iron);

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void ArmourInvalid(){
        Armour helmetTest = new Armour("Helmet",20,20,20,-10,0,true,null,null);
    }
    @Test
    public void ArmourValid(){
        Armour helmetTest = new Armour("Helmet",20,20,20,20,20,true,ArmourTypes.Wearable,ArmourMaterial.Iron);
        assertEquals(helmetTest.getArmourDefence(), 20);
        assertEquals(helmetTest.getArmourHealth(),20);
        assertEquals(helmetTest.getArmourType(),ArmourTypes.Wearable);
        assertEquals(helmetTest.getArmourMaterial(),ArmourMaterial.Iron);
    }
    @Test
    public void testGetArmourDefence() {
        int expected = 20;
        int actual = helmetTest.getArmourDefence();
        assertEquals(actual,expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetArmourDefenceInvalid() {
        helmetTest.setArmourDefence(-10);
    }

    @Test
    public void testSetArmourDefenceValid() {
        int expected = 40;
        helmetTest.setArmourDefence(40);
        int actual = helmetTest.getArmourDefence();
        assertEquals(actual,expected);
    }

    @Test
    public void testGetArmourHealth() {
        int expected = 20;
        int actual = helmetTest.getArmourHealth();
        assertEquals(actual,expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetArmourHealthInvalid() {
        helmetTest.setArmourHealth(-10);
    }

    @Test
    public void testSetArmourHealthValid() {
        int expected = 55;
        helmetTest.setArmourHealth(55);
        int actual = helmetTest.getArmourHealth();
        assertEquals(actual,expected);
    }

    @Test
    public void testGetArmourType() {
        ArmourTypes expected = ArmourTypes.Wearable;
        ArmourTypes actual = helmetTest.getArmourType();
        assertEquals(actual,expected);
    }

    @Test
    public void testGetArmourMaterial() {
        ArmourMaterial expected = ArmourMaterial.Iron;
        ArmourMaterial actual = helmetTest.getArmourMaterial();
        assertEquals(actual,expected);
    }

    @Test
    public void testIsEquipped() {
        boolean expected = true;
        boolean actual = helmetTest.isEquipped();

        assertEquals(actual,expected);
    }

    @Test
    public void testSetEquipped() {
        boolean expected = false;
        helmetTest.setEquipped(false);
        boolean actual = helmetTest.isEquipped();
        assertEquals(actual,expected);
    }
}