/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import gamecore.Dice;
import gamecore.GameController;
import gamecore.adventure.CombatCommand;
import gamecore.adventure.CombatTag;
import gamecore.adventure.Scene;
import gamecore.entity.Attribute;
import gamecore.item.BasicItemType;
import gamecore.item.BasicWeaponType;
import gamecore.item.Item;
import gamecore.item.ItemBuilder;
import gamecore.item.UseTag;
import gamecore.item.Weapon;
import gamecore.location.Encounter;
import gamecore.shop.Storefront;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author achyt_000
 */
public class TextInterface {

    Scanner scanner;
    Random rand;
    GameController controller;

    String breakSpace;

    public TextInterface(Scanner scanner, Random rand, GameController controller) {
	this.scanner = scanner;
	this.rand = rand;
	this.controller = controller;
	this.breakSpace = ".-*-.-*-.-*-.-*-.-*-.-*-.-*-.";
    }
    
    private String getInput() {
	System.out.print("👉>");
	return this.scanner.nextLine();
    }

    public void startInterface() {
	String command = "";
	System.out.println("You are an experienced adventurer, a brave soul who has won many battles and gained much gold. Somewhere along the way, you got it into your head that it would be a great idea to "
		+ "open your own item shop.");
	while (true) {
	    System.out.println("Please select from the following choices (use the number):");
	    System.out.println("1:Character, 2:Inventory, 3:Adventure, 4:Shop, e:Exit");
	    

	    command = getInput();

	    switch (command) {
	    case "1":
		System.out.println("calling the hero function.");
		this.manageCharacter();
		break;
	    case "2":
		System.out.println("calling the inventory function.");
		this.manageInventory();
		break;
	    case "3":
		System.out.println("calling the adventure function.");
		this.adventure();
		break;
	    case "4":
		System.out.println("calling the shop function.");
		this.manageShop();
		break;
	    case "e":
		System.out.println("Exiting. Bye now!");
		return;
	    default:
		System.out.println("Sorry, that's not a valid command.");
		break;
	    }

	    System.out.println(this.breakSpace);
	}
    }

    public void manageCharacter() {
	System.out.println(this.breakSpace);

	while (true) {
	    System.out.println("Hero menu. Please select from the following options:");
	    System.out.println("1:View Hero, 2:Change Hero Name, 3:Re-Roll Hero, e:Return to main menu");
	    
	    String command = getInput();

	    switch (command) {
	    case "1":
		System.out.println("Your Hero:");
		System.out.println(controller.getPlayerCharacter().toString());
		break;
	    case "2":
		System.out.println("What should the hero's name be?");
		this.controller.nameThePlayer(getInput());
		break;
	    case "3":
		System.out.println("Making a new hero!");
		System.out.println("What should the hero's name be?");
		
		this.controller.reRoll(getInput());
		break;
	    case "e":
		System.out.println("Return to main menu");
		System.out.println(this.breakSpace);
		return;
	    default:
		System.out.println("Sorry, that's not a valid command.");
		break;
	    }
	}

    }

    public void manageInventory() {
	System.out.println(this.breakSpace);
	String command = "";
	while (true) {
	    Item[] playerInventory = this.controller.getPlayerCharacter().getInventory().getItems();
	    System.out.println("Inventory Menu. Please select from the following:");
	    System.out.println("1:View inventory, 2:Add test data, 3:Remove Item, 4:Equip Weapon, e:Return to main menu");
	   

	    command = getInput();

	    switch (command) {
	    case "1":
		System.out.println("The hero's inventory:");

		if (playerInventory.length == 0) {
		    System.out.println("The inventory is empty");
		} else {
		    int counter = 0;
		    System.out.println("player inventory has " + playerInventory.length + " items in it., and weighs " + this.controller.getPlayerCharacter().getInventory().getCurrentWeight());
		    for (Item item : playerInventory) {

			System.out.println(counter + 1 + ": " + item.toString());
			counter++;
		    }

		    /*
		     * for (int i = 0; i < playerInventory.length; i++) { try {
		     * System.out.println(i+1 + ": " +
		     * playerInventory[i].toString()); } catch (Exception e) {
		     * // TODO Auto-generated catch block e.printStackTrace(); }
		     * }
		     */
		}

		break;
	    case "2":
		System.out.println("Adding a whole mess of items!");
		this.controller.getPlayerCharacter().getInventory().addItem(new ItemBuilder("Short Sword", BasicItemType.WEAPON).setPotency(Dice.D6.roll()).setWeaponType(BasicWeaponType.SWORD).setWeight(1).build());
		this.controller.getPlayerCharacter().getInventory().addItem(new ItemBuilder("Battle Axe", BasicItemType.WEAPON).setPotency(Dice.D6.roll()).setWeaponType(BasicWeaponType.BATTLE_AXE).setWeight(1).build());
		this.controller.getPlayerCharacter().getInventory().addItem(new ItemBuilder("Health Potion", BasicItemType.POTION).setCombatTags(CombatTag.USEABLE_FROM_INVENTORY, CombatTag.USEABLE_FROM_INVENTORY).setUseTag(UseTag.USABLE_ANYWHERE).setPotency(Dice.D6.roll()).setWeight(1).build(), Dice.D10.roll());

		break;
	    case "3":

		while (true) {
		    try {
			System.out.println("At which position?");
			int itemIndex = Integer.parseInt(getInput());
			itemIndex--;
			this.controller.getPlayerCharacter().getInventory().removeItem(playerInventory[itemIndex]);
			break;
		    } catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Sorry, that didn't work. Please enter a value between 1 and " + this.controller.getPlayerCharacter().getInventory().getItems().length);
		    }
		}
		System.out.println("Item successfully removed.");

		break;
	    case "4":
		System.out.println("Weapons:");
		if (this.controller.getPlayerCharacter().getInventory().getItems(BasicItemType.WEAPON).length == 0) {
		    System.out.println("The inventory is empty");
		} else {
		    int count = 1;
		    for (Item item : this.controller.getPlayerCharacter().getInventory().getItems(BasicItemType.WEAPON)) {
			System.out.println(count + ": " + item.toString());
			count++;
		    }

		    System.out.println("Which weapon do you want to equip?");
		    try {
			int itemIndex = Integer.parseInt(getInput());
			itemIndex--;
			this.controller.getPlayerCharacter().equipWeapon((Weapon) this.controller.getPlayerCharacter().getInventory().getItems(BasicItemType.WEAPON)[itemIndex]);
			break;
		    } catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Sorry, that didn't work. Please enter a value between 1 and " + this.controller.getPlayerCharacter().getInventory().getItems().length);
		    }
		}
		break;
	    case "e":
		System.out.println("Returning you to the main menu.");
		return;
	    default:
		System.out.println("Sorry, that's not a valid command.");
		break;
	    }
	}
    }

    public void adventure() {
	System.out.println(this.breakSpace);
	Encounter encounter = this.controller.randomEncounter();
	Scene scene = new Scene(this.controller.getPlayerCharacter(), encounter);
	while (scene.stillOngoing()) {
	    System.out.println("You went adventuring");
	    System.out.println(scene.getEncounter().getDisplayLine());
	    System.out.println("Enemy Health: " + scene.getEncounter().getEntityToFight().getAttribute(Attribute.CURRENT_HEALTH));
	    System.out.println("Possible commands are:");
	    List<CombatCommand> commands = scene.getCommands();
	    for (int i = 0; i < commands.size(); i++) {
		System.out.println(i + 1 + ": " + commands.get(i).getCommand());
	    }

	    
	    String command = getInput();

	    try {
		int commandIndex = -1;
		commandIndex = Integer.parseInt(command) - 1;
		System.out.println(scene.advanceRound(commandIndex));
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		// System.out.println("Sorry, please try entering a number within range.");
		System.out.println(e);
		e.printStackTrace();
	    }

	}
    }

    public void manageShop() {

	System.out.println(this.breakSpace);
	while (true) {
	    System.out.println("Employees have " + this.controller.getTestEmployee().howManyChances() + " items available for pickup.");
	    System.out.println("Time until next item: " + this.controller.getTestEmployee().secondsUntilNextChance());
	    System.out.println("Shop Menu (alpha). Please select from the following items:");
	    System.out.println("1:Wait a while, 2:Collect items, 3:Employee basket, 4:basket xFer to backroom, 5:Backroom, e:Return to main menu");
	    
	    String input = getInput();
	    
	    boolean toExit = false;
	    switch (input) {
	    case "1":
		System.out.println("waiting a while...");
		break;
	    case "2":
		System.out.println("Employees have " + this.controller.getTestEmployee().howManyChances() + " items available for pickup.");
		    System.out.println("Time until next item: " + this.controller.getTestEmployee().secondsUntilNextChance());
		System.out.println("Collecting items.");
		int collected = this.controller.getTestEmployee().collectItems();
		System.out.println("Collected " + collected + " items.");
		
		break;
	    case "3": 

		for (Item item : this.controller.getTestEmployee().getItemsCollectedSoFar().keySet()) {
		    System.out.println(this.controller.getTestEmployee().getItemsCollectedSoFar().get(item) + "x " + item.toString());
		}
		break;
	    case "4": if (this.controller.getTestEmployee().dropOff()) {
		System.out.println("successfully drops off the items");
	    } else {
		System.out.println("We couldn't drop everything off, but we did what we could!");
	    }
	    break;
	    case "5":
	    Map<Item,Integer> backroom = Storefront.getInstance().getBackroom().getItemMap();
	     for (Item item : backroom.keySet()) {
		System.out.println(backroom.get(item) + "x " + item.toString());
	    }
		break;
	    case "e":
		System.out.println("returning to main menu.");
		toExit = true;
		break;
	    }
	    if (toExit) {
		break;
	    }

	}

    }

}
