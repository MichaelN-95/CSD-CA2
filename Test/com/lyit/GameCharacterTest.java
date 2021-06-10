package com.lyit;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class GameCharacterTest {
    GameCharacter apollo = new GameCharacter("Apollo", 100,150,0,CharacterState.Dead);

    @Test
    public void testPickUpItem() {
        Item test = new Item("Ring",20,10,0);

        apollo.pickUpItem(test);

        boolean expectedRes = true;
        boolean acualRes = apollo.inventory.contains(test);

        Assert.assertEquals(acualRes, expectedRes);
    }

    @Test
    public void testPickUpItemWeightCheckUnderLimit() {
        Item test = new Item("Ring",20,10,0);
        Weapon weapon = new Weapon("sword",20,100,0,20,10,false);
        apollo.pickUpItem(weapon);

        boolean expectedRes = true;
        boolean acualRes = apollo.inventory.contains(weapon);

        Assert.assertEquals(acualRes, expectedRes);
    }
    @Test
    public void testPickUpItemWeightCheckOverLimit() {
        Item test = new Item("Ring",160,10,0);

        apollo.pickUpItem(test);

        boolean expectedRes = false;
        boolean acualRes = apollo.inventory.contains(test);

        Assert.assertEquals(acualRes, expectedRes);
    }

    @Test
    public void testDropItem() {
        Item test = new Item("Ring",10,10,0);

        apollo.pickUpItem(test);
        System.out.println(apollo.inventory.contains(test));
        apollo.dropItem(test);
        boolean expectedRes = false;
        boolean actualRes = apollo.inventory.contains(test);

        Assert.assertEquals(actualRes,expectedRes);
    }

    @Test
    public void testEquipWeapon() {
        Weapon weapon = new Weapon("sword",20,100,0,20,10,false);
        apollo.inventory.add(0,weapon);
        System.out.println(weapon.isWeaponEquipped());
        apollo.equipWeapon(0);

        boolean expectedRes = true;
        boolean actualRes = weapon.isWeaponEquipped();

        Assert.assertEquals(actualRes,expectedRes);
    }
    @Test
    public void testEquipWeapon_DoubleHandedWeapon() {
        Weapon weaponSingleHanded = new Weapon("sword",20,100,0,20,10,false);
        Armour shieldtest = new Armour("Shield",20,20,20,20,20,true,ArmourTypes.Hold,ArmourMaterial.Iron);
        Weapon weaponDoubleHanded = new Weapon("Hammer",20,100,0,20,10,true);
        apollo.inventory.add(0,weaponSingleHanded);
        apollo.equipWeapon(0);
        apollo.inventory.add(1,weaponDoubleHanded);
        System.out.println(weaponSingleHanded.isWeaponEquipped());
        apollo.equipWeapon(1);
        System.out.println(weaponSingleHanded.isWeaponEquipped());
        boolean expectedRes = false;
        boolean actualRes = weaponSingleHanded.isWeaponEquipped();

        Assert.assertEquals(actualRes,expectedRes);
    }

    @Test
    public void testEquipWeapon_DoubleHandedWeaponWithShield() {
        //setting up equipped shield and double handed weapon and that will uneqip the shield
        Armour shieldTest = new Armour("Shield",20,20,20,20,20,true,ArmourTypes.Hold,ArmourMaterial.Iron);
        Weapon weaponDoubleHanded = new Weapon("Hammer",20,100,0,20,10,true);
        apollo.inventory.add(0,shieldTest);
        apollo.inventory.add(1,weaponDoubleHanded);
        boolean expectedRes = false;

        //equiping double handed weapon that will remove the shield
        apollo.equipWeapon(1);
        boolean actualRes = shieldTest.isEquipped();

        Assert.assertEquals(actualRes,expectedRes);
    }

    @Test
    public void testUnEquipWeapon() {
        Weapon weaponSingleHanded = new Weapon("sword",20,100,0,20,10,false);
        apollo.inventory.add(1,weaponSingleHanded);
        apollo.equipWeapon(1);
        boolean expectedResult = true;

        boolean actualResult = apollo.unEquipWeapon(1);

        Assert.assertEquals(actualResult,expectedResult);

    }

    @Test
    public void testEquipArmour_Wearables() {
        Armour helmetTest = new Armour("Helmet",20,20,20,20,20,false,ArmourTypes.Wearable,ArmourMaterial.Iron);

        apollo.inventory.add(0,helmetTest);
        boolean expectedRes = true;
        boolean actualRes = apollo.equipArmour(0);

        Assert.assertEquals(actualRes,expectedRes);

    }
    @Test
    public void testEquipArmour_ShieldReplace() {
        apollo.inventory.clear();
        Armour normalShield = new Armour("Shield",20,20,20,20,20,true,ArmourTypes.Hold,ArmourMaterial.Iron);
        Armour betterShield = new Armour("Shield",20,20,20,20,20,false,ArmourTypes.Hold,ArmourMaterial.Iron);

        apollo.inventory.add(0,normalShield);
        apollo.inventory.add(1,betterShield);

        apollo.equipArmour(1);

        boolean expectedRes = false;
        boolean actualRes = normalShield.isEquipped();

        Assert.assertEquals(actualRes,expectedRes);
    }

    @Test
    public void testRemoveArmour() {
        Armour normalShield = new Armour("Shield",20,20,20,20,20,true,ArmourTypes.Hold,ArmourMaterial.Iron);
        apollo.inventory.add(0,normalShield);

        boolean expectedRes = false;
        apollo.removeArmour(0);

        boolean actualRes = normalShield.isEquipped();

        Assert.assertEquals(actualRes,expectedRes);

    }

    @Test
    public void testGetCharacterDefence() {
        //clearing inventory to avoid previous additions to inventory and make testing simpler
        apollo.inventory.clear();
        Armour normalShield = new Armour("Shield",20,20,20,60,20,true,ArmourTypes.Hold,ArmourMaterial.Iron);
        Armour helmetTest = new Armour("Helmet",20,20,20,20,20,true,ArmourTypes.Wearable,ArmourMaterial.Iron);
        Armour chestplate = new Armour("Chestplate",20,40,20,40,20,true,ArmourTypes.Wearable,ArmourMaterial.Iron);

        apollo.inventory.addAll(Arrays.asList(normalShield,helmetTest,chestplate));

        double expectedRes = 120;
        double actualRes = apollo.getCharacterDefence();

        Assert.assertEquals(actualRes,expectedRes);
    }

    @Test
    public void testGetCharacterAttackValue() {

        //clearing inventory to avoid previous additions to inventory and make testing simpler
        apollo.inventory.clear();
        Weapon dagger = new Weapon("Dagger",20,100,0,60,10,false);
        Weapon spear = new Weapon("Spear",20,100,0,20,10,false);

        apollo.inventory.addAll(Arrays.asList(dagger,spear));

        apollo.equipWeapon(0);
        apollo.equipWeapon(1);

        System.out.println(dagger.isWeaponEquipped() + " " + spear.isWeaponEquipped());

        double expectedRes = 80;
        double actualRes = apollo.getCharacterAttackValue();

        Assert.assertEquals(actualRes,expectedRes);
    }

    @Test
    public void testSleep_HealthAt80() {
        apollo.setHealth(60);
        apollo.sleep();

        double expectedResult = 80;
        double actualresult = apollo.getHealth();

        Assert.assertEquals(actualresult,expectedResult);
    }

    @Test
    public void testSleep_HealthAt90() {
        apollo.setHealth(90);
        apollo.sleep();

        double expectedResult = 100;
        double actualresult = apollo.getHealth();

        Assert.assertEquals(actualresult,expectedResult);
    }

    @Test
    public void testAttack_TwoWeapons() {
        GameCharacter mars = new GameCharacter("Mars",100,100,0,CharacterState.Defending);
        Weapon dagger = new Weapon("Dagger",20,100,0,60,10,false);
        Weapon spear = new Weapon("Spear",20,100,0,20,10,false);
        Weapon hammer = new Weapon("hammer",20,100,0,60,10,false);
        Weapon sickle = new Weapon("sickle",20,100,0,20,10,false);

        apollo.inventory.addAll(Arrays.asList(dagger,spear,hammer,sickle));

        apollo.equipWeapon(0);
        apollo.equipWeapon(1);

        apollo.attack(mars);

        String expectedRes = "Apollo is attacking Mars with a Dagger and a Spear";

        String actualRes = apollo.attack(mars);

        Assert.assertEquals(actualRes,expectedRes);
    }

    @Test
    public void testAttack_OneWeapon() {
        apollo.inventory.clear();
        GameCharacter mars = new GameCharacter("Mars",100,100,0,CharacterState.Defending);
        Weapon dagger = new Weapon("Dagger",20,100,0,60,10,false);
        Weapon spear = new Weapon("Spear",20,100,0,20,10,false);
        Weapon hammer = new Weapon("hammer",20,100,0,60,10,false);
        Weapon sickle = new Weapon("sickle",20,100,0,20,10,false);

        apollo.inventory.addAll(Arrays.asList(dagger,spear,hammer,sickle));

        apollo.equipWeapon(0);

        apollo.attack(mars);

        String expectedRes = "Apollo is attacking Mars with a Dagger";

        String actualRes = apollo.attack(mars);

        Assert.assertEquals(actualRes,expectedRes);
    }

    @Test
    public void testEatHealthIncreaseWithMouldy() {
        //arrange
        apollo.inventory.clear();
        apollo.inventory.add(new Food("apple",100,10,0,FoodState.Mouldy,2,52));
        apollo.setHealth(40);
        double expectedResult =40.83;

        //act
        apollo.eat(0);
        double actualResult =apollo.getHealth();

        //assert
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testEatHealthIncreaseWithFresh() {
        //arrange
        apollo.inventory.clear();
        apollo.inventory.add(new Food("Bread",100,10,0,FoodState.Fresh,2,264));
        apollo.setHealth(40);
        double expectedResult =50.56;

        //act
        apollo.eat(0);
        double actualResult =apollo.getHealth();

        //assert
        Assert.assertEquals(actualResult,expectedResult);
    }
}