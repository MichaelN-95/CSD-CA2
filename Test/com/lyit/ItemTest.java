package com.lyit;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ItemTest {
    Item test = new Item("Ring",20,10,0);

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void ItemNameInvalid (){
        Item testNullName = new Item(null,0,-10,-10);
    }
    @Test
    public void ItemValid (){
        Item test = new Item("Ring",20,10,0);
        assertEquals(test.getItemName(),"Ring");
        assertEquals(test.getItemWeight(),20);
        assertEquals(test.getItemValue(),10);
        assertEquals(test.getItemMagicValue(),0);
    }


    @Test
    public void testGetItemName() {
        String expectedResult = "Ring";
        String actualResult = test.getItemName();

        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetItemNameInvalid() {
        test.setItemName("");
    }

    @Test()
    public void testSetItemNameValid() {
        String expected="Stamp";
        test.setItemName("Stamp");

        String actual = test.getItemName();

        Assert.assertEquals(actual,expected);
    }
    @Test
    public void testGetItemWeight() {
        double expected = 20;
        double actual = test.getItemWeight();

        assertEquals(actual,expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetItemWeightInvalid() {
        test.setItemWeight(0);
    }

    @Test
    public void testSetItemWeightValid() {
        double expected = 20;
        test.setItemWeight(20);

        double actual = test.getItemWeight();

        assertEquals(actual,expected);
    }

    @Test
    public void testGetItemValue() {
        double expected = 10;
        double actual = test.getItemValue();

        assertEquals(actual,expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetItemValueInvalid() {
        test.setItemValue(-10);
    }

    @Test
    public void testSetItemValueValid() {
        double expected = 100;
        test.setItemValue(100);
        double actual = test.getItemValue();

        assertEquals(actual,expected);
    }

    @Test
    public void testGetItemMagicValue() {
        int expected = 0;
        int actual = test.getItemMagicValue();

        assertEquals(actual,expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetItemMagicValueInvalid() {
        test.setItemMagicValue(-10);
    }

    @Test
    public void testSetItemMagicValueValid() {
        int expected = 50;
        test.setItemMagicValue(50);
        int actual = test.getItemMagicValue();

        assertEquals(actual,expected);
    }
}