package com.lyit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FoodTest {
    Food test = new Food("apple",100,10,0, FoodState.Mouldy,2,52);

    //testing all non inherited details as items are tested already
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void FoodInvalid(){
        Food test = new Food("apple",100,10,0, null,0,0);
    }

    @Test
    public void FoodValid(){
        Food test = new Food("apple",100,10,0, FoodState.Mouldy,2,52);

        assertEquals(test.getFoodState(),FoodState.Mouldy);
        assertEquals(test.getCalories(), 52);
    }

    @Test
    public void testGetFoodState() {
        FoodState expected = FoodState.Mouldy;
        FoodState actual = test.getFoodState();

        assertEquals(actual,expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetFoodStateInvalid() {
        test.setFoodState(null);
    }
    @Test
    public void testSetFoodStateValid() {
        FoodState expected = FoodState.Fresh;
        test.setFoodState(FoodState.Fresh);
        FoodState actual = test.getFoodState();
        assertEquals(actual,expected);
    }

    @Test
    public void testGetCalories() {
        int expected = 52;
        int actual = test.getCalories();

        assertEquals(actual,expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetCaloriesInvalid() {
        test.setCalories(-100);
    }
    @Test
    public void testSetCaloriesValid() {
        int expected = 100;
        test.setCalories(100);

        int actual = test.getCalories();

        assertEquals(actual,expected);
    }
}