package com.lyit;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class WeaponTest {

    Weapon weapon = new Weapon("sword",20,100,0,20,10,true);

    //testing all non inherited details as items are tested already
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void WeaponInvalid(){
        Weapon weapon = new Weapon("Sword",20,100,0,-10,0,false);
    }

    //Not testing doublehanded as it can only be either true or false both which are valid
    @Test
    public void WeaponValid(){
        Weapon weapon = new Weapon("sword",20,100,0,20,10,false);

        assertEquals(weapon.getWeaponHitStrength(),20);
        assertEquals(weapon.getWeaponHealth(),10);
    }

    @Test
    public void testGetWeaponHitStrength() {
        int expected = 20;
        int actual = weapon.getWeaponHitStrength();

        assertEquals(actual,expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetWeaponHitStrengthInvalid() {
        weapon.setWeaponHitStrength(-100);
    }

    @Test
    public void testSetWeaponHitStrengthValid() {
        int expected = 50;
        weapon.setWeaponHitStrength(50);

        int actual = weapon.getWeaponHitStrength();

        assertEquals(actual,expected);
    }

    @Test
    public void testGetWeaponHealth() {
        int expected = 10;
        int actual = weapon.getWeaponHealth();

        assertEquals(actual,expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetWeaponHealthInvalid() {
        weapon.setWeaponHealth(0);
    }

    @Test
    public void testSetWeaponHealthValid() {
        int expected =55;
        weapon.setWeaponHealth(55);
        int actual = weapon.getWeaponHealth();

        assertEquals(actual,expected);
    }

    @Test
    public void testIsDoubleHanded() {
        boolean expected = true;
        boolean actual = weapon.isDoubleHanded();

        assertEquals(actual,expected);
    }

    @Test
    public void testSetDoubleHanded() {
        boolean expected = false;
        weapon.setDoubleHanded(false);
        boolean actual = weapon.isDoubleHanded();
        assertEquals(actual,expected);

    }

    //setting up character to equip weapon to enable it to be tested
    @Test
    public void testIsWeaponEquipped() {
        GameCharacter apollo = new GameCharacter("Apollo", 100,150,0,CharacterState.Idle);
        apollo.inventory.add(weapon); apollo.equipWeapon(0);

        boolean expected = true;
        boolean actual = weapon.isWeaponEquipped();

        assertEquals(actual,expected);
    }

    @Test
    public void testSetWeaponEquipped() {
        GameCharacter apollo = new GameCharacter("Apollo", 100,150,0,CharacterState.Idle);
        apollo.inventory.add(weapon); apollo.equipWeapon(0);

        boolean expected = false;
        weapon.setWeaponEquipped(false);
        boolean actual = weapon.isWeaponEquipped();
        assertEquals(actual,expected);
    }
}