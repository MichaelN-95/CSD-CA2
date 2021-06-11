/*
 * GameCharacter
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


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

enum CharacterState { Idle, Running, Sleeping, Walking, Defending, Dead, Eating, Attacking };

// Note - If the character state is Dead, the character should not be able to do anything.
// Invoking a method when the character is dead should do nothing

//public abstract class GameCharacter {
public class GameCharacter {
    //Attributes
    private String characterName; //Cannot be empty
    private double health; //Must be in range [0..100]. If 0 then character state should be dead. If > 0 the character cannot be dead
    private double weightLimit; //Must be > 0
    private double totalWeightOfItems; //Cannot exceed weightLimit
    ArrayList<Object> inventory = new ArrayList<>();
    // GameCharacter inventory
    // NOTE: The GameCharacter's inventory (items, food, weapons, armour) must be stored within the class.
    // You need to select appropriate collection(s) to allow the character to pickup and drop game items.

    private CharacterState characterState;

    // Getters and setters
    // Setters should be modified to deal with invalid arguments. Throw an IllegalArgumentException if
    // the setter is provided with an invalid argument

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        if (characterName.isBlank())throw new IllegalArgumentException("Character name must not be null or empty");
        this.characterName = characterName;
    }

    public double getHealth() {
        return Math.round(health*100.00)/100.00;
    }

    public void setHealth(double health) {
        if (health<0.1 || health > 100)throw new IllegalArgumentException("Health must be greater than 0.1 and no higher than 100");
        this.health = health;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(double weightLimit) {
        if (weightLimit<1)throw new IllegalArgumentException("Weight limit must be 1 or higher");
        this.weightLimit = weightLimit;
    }

    public double getTotalWeightOfItems() {
        return totalWeightOfItems;
    }

    public void setTotalWeightOfItems(double totalWeightOfItems) {
        if (totalWeightOfItems<0 || totalWeightOfItems>weightLimit)throw new IllegalArgumentException("Weight must be 0 or higher and no higher than characters weight limit");
        this.totalWeightOfItems = totalWeightOfItems;
    }

    public CharacterState getCharacterState() {
        return characterState;
    }

    public void setCharacterState(CharacterState characterState) {
        if (characterState==null)throw new IllegalArgumentException("Character state cannot be null");
        this.characterState = characterState;
    }


    // GameCharacter Constructor
    // If an invalid argument is provided throw an IllegalArgumentException exception
    public GameCharacter(String characterName, int health, double weightLimit, int food, CharacterState characterState) {
        if (characterName.isBlank()||health<0.1 || health > 100 || weightLimit<1||food<0||characterState==null)throw new IllegalArgumentException("Game character given invalid arguments");
        this.characterName = characterName;
        this.health = health;
        this.weightLimit = weightLimit;
        this.characterState = characterState;
        //TODO - Complete this method
    }


    // Logic for the eat method
    // If the item at inventoryItemIndex is of the class Food, the item is consumed and removed from the inventory.
    // Eating the item should increase the characters health. 1 unit of health = 25 calories.
    // The state of the food modifies the calorie content for only food with positive calorie values, as follows:
    // Fresh 100% of calories, stale 60%, Mouldy 40%, rotten 10%
    // Eg. an apple is 52 calories per 100g. 100g of mouldy apple will give only 20.8 (52*.4) calories.
    // Health should not exceed 100 which is full health for the character.
    // Consuming poisonous food (food with a negative calorie value) should reduce the health accordingly.
    // When the eat method is invoked, the character's status should also change to "Eating"
    // Return true if the item was food and consumed, otherwise false
    public boolean eat(int inventoryFoodIndex) {
        if (isCharDead()) {
            Item selectedItem = (Item) inventory.get(inventoryFoodIndex);
            double calories = 0.00;
            if (selectedItem instanceof Food) {
                calories = (((selectedItem.getItemWeight() / 100) * ((Food) selectedItem).getCalories()));
                switch (((Food) selectedItem).getFoodState()) {
                    case Fresh -> {
                        health += (calories / 25.00);
                        return true;
                    }
                    case Stale -> {
                        health += (calories * 0.6) / 25.00;
                        return true;
                    }
                    case Mouldy -> {
                        health += (calories * .4) / 25.00;
                        return true;
                    }
                    case Rotten -> {
                        health += (calories * .1);
                        return true;
                    }
                }
            }
            System.out.println("Selected Item is not food!");
            return false;
        }
        System.out.println("You are dead");
        return false;
    }

    // The attack method should  return a string with the following information:
    // "<this.getName()> is attacking targetCharacter.getName() with a <equippedWeapon.getItemName()>
    // Example output “Lord Percival is attacking Lord Blackadder with a sword"
    // If the attacker is using two weapons:
    // Example output “Lord Percival is attacking Lord Blackadder with a sword and a knife"

    public String attack(GameCharacter targetCharacter) {
        if (isCharDead()) {
            String targetName = targetCharacter.getCharacterName();
            List<Weapon> weapons;
            String returnString;
            weapons= inventory.stream().filter(weapon -> weapon instanceof Weapon).map(weapon -> (Weapon)weapon).filter(Weapon::isWeaponEquipped).collect(Collectors.toList());
            if (weapons.size() >1){
                returnString = this.characterName + " is attacking " + targetName+ " with a " + weapons.get(0).getItemName() + " and a " + weapons.get(1).getItemName();
            }else {
                returnString = this.characterName + " is attacking " + targetName+ " with a " + weapons.get(0).getItemName();
            }
            return returnString;
        }
        return "You are dead!";
    }

    // The equipWeapon will equip the weapon at inventoryWeaponIndex DONE
    // If the item in the inventory index is a weapon the method should return true, otherwise false
    // If the weapon requires both hands (doubleHanded = true) the character must   any weapons or shields
    // they are currently holding.
    public boolean equipWeapon(int inventoryWeaponIndex){
        if (isCharDead()) {
            //if item at selected index is a weapon assign and cast to a selected weapon
            if (inventory.get(inventoryWeaponIndex) instanceof Weapon){
                Weapon selected = (Weapon) inventory.get(inventoryWeaponIndex);
                if (selected.isDoubleHanded()) {
                    //loop through all items to check if any are equipped
                    for (Object check: inventory) {
                        //if item is armour assign to a temp armour item and set equiped to false
                        if (check instanceof Armour && ((Armour) check).getArmourType()==ArmourTypes.Hold){
                            ((Armour) check).setEquipped(false);
                        }
                        // if item is a weapon assign and cast to a temp and unequip it
                        else if(check instanceof Weapon){
                            ((Weapon) check).setWeaponEquipped(false);
                        }
                    }
                }
                selected.setWeaponEquipped(true);
                return true;
            }
            else {
                System.out.println("Item at " + inventoryWeaponIndex + " is not a weapon!");
                return false;
            }
        }
        return false;
    }

    // If the item in the inventory at inventoryWeaponIndex is a weapon, unequip it and return true, otherwise false
    // Weapons that are unequipped remain in the inventory.
    public boolean unEquipWeapon(int inventoryWeaponIndex){
        if (isCharDead()) {
            if(inventory.get(inventoryWeaponIndex) instanceof Weapon){
                ((Weapon) inventory.get(inventoryWeaponIndex)).setWeaponEquipped(false);
                return true;
            }
            System.out.println("Not a weapon");
        }
        return false;
    }

    // The equipArmour method should equip the item of armour at armourIndex in the inventory
    // The character can only equip one shield at a time. If a shield is already equipped and the armourIndex
    // argument points to another shield, then the shield at armourIndex is equipped instead. If the armourIndex points
    // to a wearable piece of armour the GameCharacter should put it on. The character may wear multiple pieces of armour
    public boolean equipArmour(int armourIndex) {
        if (isCharDead()) {
            //if item at selected index is a weapon assign and cast to a selected weapon
            if (inventory.get(armourIndex) instanceof Armour){
                Armour selected = (Armour) inventory.get(armourIndex);
                if (selected.getArmourType()== ArmourTypes.Hold) {
                    //loop through all items to check if any are equipped
                    for (Object check: inventory) {
                        //if item is armour assign to a temp armour item and set equiped to false
                        if (((Armour) check).getArmourType()==ArmourTypes.Hold){
                            ((Armour) check).setEquipped(false);
                        }
                    }
                }
                selected.setEquipped(true);
                return true;
            }
            else {
                System.out.println("Item at " + armourIndex + " is not Armour!");
                return false;
            }
        }
        System.out.println("You are dead!");
        return false;
    }

    // The method should remove the item of armour at armourIndex from the gameCharacter. The character essentially
    // takes off the piece of armour but it remains in their inventory
    public void removeArmour(int armourIndex){
        if (isCharDead()) {
            if(inventory.get(armourIndex) instanceof Armour){
                ((Armour) inventory.get(armourIndex)).setEquipped(false);
            }
            else {
                System.out.println("Not Armour");
            }
        }
    }

    // The defend method should set the characters state to defending
    public void defend(){
        if (isCharDead()) {
            characterState = CharacterState.Defending;
        }
    }

    // The method should change the characters state to walking
    public void walk(){
        if (isCharDead()) {
            characterState = CharacterState.Walking;
        }
    }

    // The method should change the characters state to running
    public void run(){
        if (isCharDead()) {
            characterState = CharacterState.Running;
        }
    }

    // The method should change the characters state to sleeping
    // The character's health should increase by 20 but should not exceed the maximum (100)
    public void sleep(){
        if (isCharDead()) {
            this.characterState = CharacterState.Sleeping;
            if (health<=80){
                health+=20;
            }
            else{
                health=100;
            }
        }
    }

    // If the character is sleeping the wakeUp method should change the character's state to Idle
    // If the character is not sleeping, the method should not change the character's state.
    public void wakeUp(){
        if (isCharDead()) {
            if (characterState== CharacterState.Sleeping){
                characterState = CharacterState.Idle;
            }
        }
    }

    // The pickUpItem method should add the item to the game characters inventory if the item does not exceed the
    // current weightLimit for the character. If the character does pick up the item the weight of the item
    // should be added to the total weight of items carried by the character
    // The item object may be an instance of the Item or any subclass (armour, weapon or food)
    public void pickUpItem(Item item){
        if (isCharDead()){
            if (getTotalWeightOfItems() + item.getItemWeight() <= getWeightLimit()){
                totalWeightOfItems += item.getItemWeight();
                inventory.add(item);
            }
            else System.out.println("Item is too heavy to add to inventory!");
        }
    }

    // The dropItem method should remove the item from the game character's inventory. The total weight of items
    // should be updated accordingly.
    // The item object may be an instance of the Item or any subclass (armour, weapon or food)
    public void dropItem(Item item){
        if (isCharDead()){
            if (inventory.contains(item)){
                totalWeightOfItems -= item.getItemWeight();
                inventory.remove(item);
            }
            else System.out.println("Item is not in your inventory!");
        }
    }

    // The defence value is determined by totalling the armourDefence values of all armour items currently equipped
    // by the game character
    public double getCharacterDefence() {
        if (isCharDead()) {
            double totalDefence =0;
            for (Object check: inventory) {
                //if item is armour assign to a temp armour item and set equiped to false
                if (check instanceof Armour && ((Armour) check).isEquipped()){
                    totalDefence += ((Armour) check).getArmourDefence();
                }
            }
            return totalDefence;
        }
        System.out.println("You are dead!");
        return 0;
    }

    // The attack value is determined by totalling the weaponHitStrength values of all weapons currently equipped
    // by the game character
    public double getCharacterAttackValue() {
        if (isCharDead()) {
            double totalAttack =0;
            for (Object check: inventory) {
                //if item is armour assign to a temp armour item and set equiped to false
                if (check instanceof Weapon && ((Weapon) check).isWeaponEquipped()){
                    totalAttack += ((Weapon) check).getWeaponHitStrength();
                }
            }
            return totalAttack;
        }
        return 0;
    }

    // Note - You can implement additional supporting private methods if you want. Add them below this section
    // Additional methods ------------------------------------------------------------------------------------

    //additional method returns if char is dead, cleans up if statements above
    private boolean isCharDead(){
        return this.characterState != CharacterState.Dead;
    }

}
